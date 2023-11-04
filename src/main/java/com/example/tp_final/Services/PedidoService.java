package com.example.tp_final.Services;

import com.example.tp_final.DTO.PedidoDTO;
import com.example.tp_final.Entidades.*;
import com.example.tp_final.Repositories.ArticuloInsumoRepository;
import com.example.tp_final.Repositories.ArticuloManufacturadoRepository;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService extends BaseServiceImpl<Pedido, Long>{
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Override
    @Transactional
    public Pedido save(Pedido entity) throws Exception {
        try{
            verificarStock(entity);
            actualizarStock(entity);
            entity.setFechaPedido(LocalDateTime.now());
            calcularTotalCosto(entity);
            calcularTotal(entity);
            calcularHoraEstimadaEntrega(entity);
            emitirFactura(entity);

            entity = pedidoRepository.save(entity);
            entity = findById(entity.getId());
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
            entity.setFechaPedido(LocalDateTime.now());
            verificarStock(entity);
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
                ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detallePedido.getArticuloInsumo().getId());
                if(detallePedido.getCantidad() > articuloInsumo.getStockActual()) {
                    throw new Exception("Stock insuficiente");
                }
            }else {
                ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getById(detallePedido.getArticuloManufacturado().getId());
                for (DetalleArtManufacturado detalleArtManufacturado : articuloManufacturado.getDetallesArtManufacturado()) {
                    if (detalleArtManufacturado.getCantidad() * detallePedido.getCantidad() > detalleArtManufacturado.getArticuloInsumo().getStockActual()) {
                        throw new Exception("Stock insuficiente");
                    }
                }
            }
        }

    }
    public void actualizarStock(Pedido entity){
        for (DetallePedido detallePedido : entity.getDetallesPedido()) {
            if (detallePedido.getArticuloInsumo() != null) {
                ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detallePedido.getArticuloInsumo().getId());
                int stock = articuloInsumo.getStockActual();
                stock -= detallePedido.getCantidad();
                articuloInsumo.setStockActual(stock);
                articuloInsumoRepository.save(articuloInsumo);
            }else {
                ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getById(detallePedido.getArticuloManufacturado().getId());
                for (DetalleArtManufacturado detalleArtManufacturado : articuloManufacturado.getDetallesArtManufacturado()) {
                    int stock = detalleArtManufacturado.getArticuloInsumo().getStockActual();
                    stock -= detallePedido.getCantidad() * detalleArtManufacturado.getCantidad();
                    detalleArtManufacturado.getArticuloInsumo().setStockActual(stock);
                    articuloInsumoRepository.save(detalleArtManufacturado.getArticuloInsumo());
                }
            }
        }
    }

    public void calcularTotalCosto(Pedido entity){
        // Calcular el totalCosto del pedido
        double totalCosto = 0;
        for (DetallePedido detallePedido : entity.getDetallesPedido()) {
            if (detallePedido.getArticuloInsumo() != null) {
                ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detallePedido.getArticuloInsumo().getId());
                totalCosto += articuloInsumo.getPrecioCompra() * detallePedido.getCantidad();
            }else {
                ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getById(detallePedido.getArticuloManufacturado().getId());
                totalCosto += articuloManufacturado.getPrecioCosto() * detallePedido.getCantidad();
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
                ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detallePedido.getArticuloInsumo().getId());
                subtotal = articuloInsumo.getPrecioVenta() * detallePedido.getCantidad();
            }else {
                ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getById(detallePedido.getArticuloManufacturado().getId());
                subtotal = articuloManufacturado.getPrecioVenta() * detallePedido.getCantidad();
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
                ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getById(detallePedido.getArticuloManufacturado().getId());
                horaEstimada += articuloManufacturado.getTiempoEstimadoCocina() * detallePedido.getCantidad();
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
            factura.setTotalFinal(entity.getTotal() - factura.getDescuento());
            entity.setFactura(factura);
        }
    }
    public Page<Pedido> findByCliente(Long Id, Pageable pageable) throws Exception {
        try {
            Page<Pedido> pedidosCliente = pedidoRepository.findByCliente(Id,pageable);
            return pedidosCliente;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<PedidoDTO> movimientosMonerios(Pageable pageable) throws Exception {
        try {
            Page<PedidoDTO> pedidoDTOS = pedidoRepository.movimientosMonerios(pageable);
            return pedidoDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<PedidoDTO> movimientosMoneriosByDate(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable) throws Exception {
        try {
            LocalDateTime fechaI = fechaInicio.atStartOfDay();
            LocalDateTime fechaF = LocalDateTime.of(fechaFin, LocalTime.of(23, 59, 59, 999_999_999));
            Page<PedidoDTO> pedidoDTOS = pedidoRepository.movimientosMoneriosByDate(fechaI,fechaF,pageable);
            return pedidoDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public PedidoService(BaseRepository<Pedido, Long> baseRepository, PedidoRepository pedidoRepository) {
        super(baseRepository);
        this.pedidoRepository = pedidoRepository;
    }
}
