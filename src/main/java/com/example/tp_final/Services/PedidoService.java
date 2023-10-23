package com.example.tp_final.Services;

import com.example.tp_final.Entidades.*;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService extends BaseServiceImpl<Pedido, Long>{
    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Pedido save(Pedido entity) throws Exception {
        try{
            //Se settea la fecha
            verificarStock(entity);
            entity.setFechaPedido(LocalDateTime.now());
            calcularTotalCosto(entity);
            calcularTotal(entity);
            calcularHoraEstimadaEntrega(entity);
            emitirFactura(entity);

            entity = pedidoRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Pedido update(Long id, Pedido entity) throws Exception {
        try{
            Optional<Pedido> entityOptional = pedidoRepository.findById(id);
            Pedido entityUpdate = entityOptional.get();
            calcularTotalCosto(entity);
            calcularTotal(entity);
            calcularHoraEstimadaEntrega(entity);
            emitirFactura(entity);

            if ("En_Delivery".equals(entity.getEstadoPedido().toString()) || "Entregado".equals(entity.getEstadoPedido().toString())) {
                ConfiguracionGeneral.tiempoEstimadoCocina -= entity.getHoraEstimadaPreparacion();
            }
            entityUpdate = pedidoRepository.save(entity);
            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public void verificarStock(Pedido entity) throws Exception{
        for (DetallePedido detallePedido : entity.getDetallesPedido()) {
            if (detallePedido.getArticuloInsumo() != null) {
                if(detallePedido.getCantidad() > detallePedido.getArticuloInsumo().getStockActual()) {
                    throw new Exception();
                }
            }else {
                if(detallePedido.getCantidad() > detallePedido.getArticuloInsumo().getStockActual()) {
                    throw new Exception();
                }
            }
        }

    }

    public void calcularTotalCosto(Pedido entity){
        // Calcular el totalCosto del pedido
        double totalCosto = 0;
        for (DetallePedido detallePedido : entity.getDetallesPedido()) {
            if (detallePedido.getArticuloInsumo() != null) {
                totalCosto += detallePedido.getArticuloInsumo().getPrecioCompra() * detallePedido.getCantidad();
            }else {
                totalCosto += detallePedido.getArticuloManufacturado().getPrecioCosto() * detallePedido.getCantidad();
            }
        }
        entity.setTotalCosto(totalCosto);
    }
    public void calcularTotal(Pedido entity){
        // Calcular el total del pedido
        double total = 0;
        for (DetallePedido detallePedido : entity.getDetallesPedido()) {
            double subtotal;
            if (detallePedido.getArticuloInsumo() != null) {
                subtotal = detallePedido.getArticuloInsumo().getPrecioVenta() * detallePedido.getCantidad();
            }else {
                subtotal = detallePedido.getArticuloManufacturado().getPrecioVenta() * detallePedido.getCantidad();
            }
            detallePedido.setSubtotal(subtotal);
            total += detallePedido.getSubtotal();
        }
        entity.setTotal(total);
    }
    public void calcularHoraEstimadaEntrega(Pedido entity){
        //Se calcula la hora estimada entrega
        int horaEstimada = 0;
        for (DetallePedido detallePedido : entity.getDetallesPedido()) {
            if (detallePedido.getArticuloManufacturado() != null) {
                horaEstimada += detallePedido.getArticuloManufacturado().getTiempoEstimadoCocina() * detallePedido.getCantidad();
            }
        }
        horaEstimada = horaEstimada / ConfiguracionGeneral.cocineros;
        entity.setHoraEstimadaPreparacion(horaEstimada);
        ConfiguracionGeneral.tiempoEstimadoCocina += horaEstimada ;
        horaEstimada = ConfiguracionGeneral.tiempoEstimadoCocina;
        entity.setHoraEstimadaEntrega(LocalTime.of(0, 0).plusMinutes(horaEstimada));
    }
    public void emitirFactura(Pedido entity){
        if("Si".equals(entity.getPagado().toString())) {
            List<DetalleFactura> detallesFactura = new ArrayList<>();
            for (DetallePedido detallePedido : entity.getDetallesPedido()) {
                DetalleFactura detalleFactura = new DetalleFactura();
                detalleFactura.setCantidad(detallePedido.getCantidad());
                detalleFactura.setArticuloInsumo(detallePedido.getArticuloInsumo());
                detalleFactura.setArticuloManufacturado(detallePedido.getArticuloManufacturado());
                detalleFactura.setSubtotal(detallePedido.getSubtotal());
                detallesFactura.add(detalleFactura);
            }
            Factura factura = new Factura();
            factura.setFechaFacturacion(LocalDateTime.now());
            factura.setDetallesFactura(detallesFactura);

            if ("retira".equals(entity.getTipoEnvio().toString())) {
                factura.setDescuento(entity.getTotal() * 0.1);
                factura.setTotalFinal(entity.getTotal() - factura.getDescuento());
            } else {
                factura.setDescuento(0);
            }
            entity.setFactura(factura);
        }
    }
    public PedidoService(BaseRepository<Pedido, Long> baseRepository, PedidoRepository pedidoRepository) {
        super(baseRepository);
        this.pedidoRepository = pedidoRepository;
    }
}
