package com.example.tp_final.Services;

import com.example.tp_final.Entidades.*;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService extends BaseServiceImpl<Pedido, Long>{
    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Pedido save(Pedido entity) throws Exception {
        try{

            double total = 0;
            // Calcular el total del pedido con la suma de los subtotales de los detalles pedido
            for (DetallePedido detalleP : entity.getDetallesPedido()) {
                double subtotal = 0;
                subtotal = detalleP.getArticulo().getPrecioVenta() * detalleP.getCantidad();
                detalleP.setSubtotal(subtotal);
                total += detalleP.getSubtotal();
            }
            List<DetalleFactura> detallesFactura = new ArrayList<>();
            for (DetallePedido detallePedido: entity.getDetallesPedido()) {
                DetalleFactura detalleFactura = new DetalleFactura();
                detalleFactura.setCantidad(detallePedido.getCantidad());
                detalleFactura.setArticulo(detallePedido.getArticulo());
                detalleFactura.setSubtotal(detallePedido.getSubtotal());
                detallesFactura.add(detalleFactura);
            }
            Factura factura = new Factura(entity.getFechaPedido(),total,detallesFactura);

            //Si el tipo envio es retira entonces se aplica un 10% de descuento
            if (entity.getTipoEnvio().name()=="retira") {
                factura.setDescuento(0.1);
            }else {
                factura.setDescuento(0);
            }
            factura.setTotalFinal(total - total * factura.getDescuento());
            entity.setFactura(factura);

            entity = pedidoRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public PedidoService(BaseRepository<Pedido, Long> baseRepository, PedidoRepository pedidoRepository) {
        super(baseRepository);
        this.pedidoRepository = pedidoRepository;
    }
}
