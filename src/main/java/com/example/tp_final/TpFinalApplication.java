package com.example.tp_final;

import com.example.tp_final.Entidades.*;
import com.example.tp_final.Enumeraciones.*;
import com.example.tp_final.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TpFinalApplication {
    //Inyeccion de dependencias por atributo

    /*@Autowired
      UsuarioRepository usuarioRepository;
      @Autowired
      EmpleadoRepository empleadoRepository;
      @Autowired
      ClienteRepository clienteRepository;
      @Autowired
      DomicilioRepository domicilioRepository;
      @Autowired
      ArticuloRepository articuloRepository;
      @Autowired
      ArticuloManufacturadoRepository articuloManufacturadoRepository;
      @Autowired
      RubroRepository rubroRepository;
      @Autowired
      UnidadMedidaRepository unidadMedidaRepository;
      @Autowired
      PedidoRepository pedidoRepository;
      @Autowired
      DetallePedidoRepository detallePedidoRepository;
      @Autowired
      DatalleArtManufacturadoRepository datalleArtManufacturadoRepository; */
    public static void main(String[] args) {
        SpringApplication.run(TpFinalApplication.class, args);
    }
    @Bean
    public CommandLineRunner run() {
        return (args) -> {
            /*
            //Se crean 3 clientes
            Cliente cliente1 = new Cliente("Dalia", "Adam", "123456789","DaliaAdam@gmail.com","password", Estado.Alta);
            Cliente cliente2 = new Cliente("Trisha", "Gee", "123456789","TrishaGee@gmail.com","password", Estado.Alta);
            Cliente cliente3 = new Cliente("Helen", "Scott", "123456789","HelenScott@gmail.com","password", Estado.Alta);

            //Se persiste las entidades cliente
            usuarioRepository.save(cliente1);
            usuarioRepository.save(cliente2);
            usuarioRepository.save(cliente3);

            //Se crean los empleados
            Empleado empleado1 = new Empleado("Matias","Morales","2612463645","MatiasMorales@gmail.com","password", Estado.Alta, Rol.CAJERO);
            Empleado empleado2 = new Empleado("Juan","Rodriguez","2616585321","JuanRodriguez@gmail.com","password",Estado.Alta, Rol.COCINERO);
            Empleado empleado3 = new Empleado("Martin","Dominguez","2616987523","MartinDominguez@gmail.com","password",Estado.Alta, Rol.REPARTIDOR);

            //Se persiste las entidades empleados
            usuarioRepository.save(empleado1);
            usuarioRepository.save(empleado2);
            usuarioRepository.save(empleado3);

            //Se recuperan clientes de db y se muestran sus datos
            System.out.println("Clientes Registrados");
            Iterable<Cliente> clientesRecuperados = clienteRepository.findAll();
            for (Cliente clienteRecuperado: clientesRecuperados) {
                System.out.println("ClienteId: "+ clienteRecuperado.getId());
                System.out.println("Nombre: "+clienteRecuperado.getNombre());
                System.out.println("Apellido: "+clienteRecuperado.getApellido());
                System.out.println("Telefono: "+clienteRecuperado.getTelefono());
                System.out.println("Email: "+clienteRecuperado.getEmail());
                System.out.println("----------------------------------");
            }

            //Se recuperan empleados de db y se muestran sus datos
            System.out.println("Empleados Registrados");
            Iterable<Empleado> empleadosRecuperados = empleadoRepository.findAll();
            for (Empleado empleadoRecuperado: empleadosRecuperados) {
                System.out.println("EmpleadoId: "+ empleadoRecuperado.getId());
                System.out.println("Nombre: "+empleadoRecuperado.getNombre());
                System.out.println("Apellido: "+empleadoRecuperado.getApellido());
                System.out.println("Telefono: "+empleadoRecuperado.getTelefono());
                System.out.println("Email: "+empleadoRecuperado.getEmail());
                System.out.println("----------------------------------");
            }
            //Se crean 4 domicilios, se asocian a los clientes y se persisten
            Domicilio domicilio1 = new Domicilio("234","Belgrano","5534","2","1", Localidad.Ciudad,cliente1);
            Domicilio domicilio2 = new Domicilio("324","Colon","5515","12","3", Localidad.Godoy_Cruz,cliente2);
            Domicilio domicilio3 = new Domicilio("275","Libertad","5611","-","-", Localidad.Guaymallen,cliente3);
            Domicilio domicilio4 = new Domicilio("542","Roca","5611","-","-", Localidad.Guaymallen,cliente3); //El cliente 3 tiene 2 domicilios asociados

            domicilioRepository.save(domicilio1);
            domicilioRepository.save(domicilio2);
            domicilioRepository.save(domicilio3);
            domicilioRepository.save(domicilio4);

            cliente1.getDomicilios().add(domicilio1);
            cliente2.getDomicilios().add(domicilio2);
            cliente3.getDomicilios().add(domicilio3);
            cliente3.getDomicilios().add(domicilio4);

            usuarioRepository.save(cliente1);
            usuarioRepository.save(cliente2);
            usuarioRepository.save(cliente3);

            //Se crean rubros
            Rubro rubroInsumo1 = new Rubro("Vinos",Estado.Alta, TipoRubro.insumo);
            Rubro rubroInsumo2 = new Rubro("Gaseosas",Estado.Alta, TipoRubro.insumo);
            Rubro rubroInsumo3 = new Rubro("Vegetales",Estado.Alta, TipoRubro.insumo);
            Rubro rubroInsumo4 = new Rubro("Quesos",Estado.Alta, TipoRubro.insumo);

            Rubro rubroArtManufacturado1 = new Rubro("Pizzas",Estado.Alta, TipoRubro.manufacturado);
            Rubro rubroArtManufacturado2 = new Rubro("Hamburguesas",Estado.Alta, TipoRubro.manufacturado);
            Rubro rubroArtManufacturado3 = new Rubro("Sándwiches",Estado.Alta, TipoRubro.manufacturado);

            rubroRepository.save(rubroInsumo1);
            rubroRepository.save(rubroInsumo2);
            rubroRepository.save(rubroInsumo3);
            rubroRepository.save(rubroInsumo4);
            rubroRepository.save(rubroArtManufacturado1);
            rubroRepository.save(rubroArtManufacturado2);
            rubroRepository.save(rubroArtManufacturado3);

            //Se crean Unidade de medida de insumos y se persisten
            UnidadMedida unidadMedida1 = new UnidadMedida("litro","l");
            UnidadMedida unidadMedida2 = new UnidadMedida("Kilogramo","Kg");
            unidadMedidaRepository.save(unidadMedida1);
            unidadMedidaRepository.save(unidadMedida2);

            //Se crean articulos y se asocias a los rubros
            ArticuloInsumo articuloInsumo1 = new ArticuloInsumo("Vino Blanco","descripcion..","urlImagen..",1500,Estado.Alta,rubroInsumo1,1000,1,1,unidadMedida1);
            ArticuloInsumo articuloInsumo2 = new ArticuloInsumo("Pepsi","descripcion..","urlImagen..",1200,Estado.Alta,rubroInsumo2,800.00,1,1,unidadMedida1);
            ArticuloInsumo articuloInsumo3 = new ArticuloInsumo("Queso Mozzarella","descripcion..","urlImagen..",700,Estado.Alta,rubroInsumo2,300,1,1,unidadMedida2);
            ArticuloInsumo articuloInsumo4 = new ArticuloInsumo("Tomate","descripcion..","urlImagen..",1000,Estado.Alta,rubroInsumo2,500,1,1,unidadMedida2);

            articuloRepository.save(articuloInsumo1);
            articuloRepository.save(articuloInsumo2);
            articuloRepository.save(articuloInsumo3);
            articuloRepository.save(articuloInsumo4);

            DetalleArtManufacturado detalleArtManufacturado1 = new DetalleArtManufacturado(1,articuloInsumo3);
            DetalleArtManufacturado detalleArtManufacturado2 = new DetalleArtManufacturado(2,articuloInsumo4);
            List<DetalleArtManufacturado> detallesArtManufacturado = new ArrayList<>();
            detallesArtManufacturado.add(detalleArtManufacturado1);
            detallesArtManufacturado.add(detalleArtManufacturado2);

            ArticuloManufacturado articuloManufacturado1 = new ArticuloManufacturado("Pizza Mozzarella","descripcion..","urlImagen..",1500,Estado.Alta,rubroArtManufacturado1,25,"receta...",detallesArtManufacturado);
            articuloRepository.save(articuloManufacturado1);

            //Se crea un pedido
            DetallePedido detallePedido1 = new DetallePedido(1,articuloManufacturado1);
            DetallePedido detallePedido2 = new DetallePedido(2,articuloInsumo1);
            List<DetallePedido> detallesPedido = new ArrayList<>();
            detallesPedido.add(detallePedido1);
            detallesPedido.add(detallePedido2);

            Pedido pedido1 = new Pedido(Pagado.Si, EstadoPedido.A_Confirmar,TipoEnvio.retira,cliente1,detallesPedido);
            pedidoRepository.save(pedido1);

            //Se elimina el cliente 3, eliminando tambien sus domicilios y pedidos asociados
            clienteRepository.deleteById(3L);

            //Se muestran datos del pedido 1
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(1L);
            Pedido pedidoRecuperado = pedidoOptional.get();
            System.out.println("Pedido Id: "+pedidoRecuperado.getId());
            System.out.println("Precio Total de pedido: $"+ pedidoRecuperado.getTotal());
            System.out.println("Detalles de Pedido");
            for (DetallePedido detallePedido: pedidoRecuperado.getDetallesPedido()) {
                System.out.println("Nombre Articulo: "+detallePedido.getArticulo().getDenominacion());
                 System.out.println("Cantidad : "+detallePedido.getCantidad());
                System.out.println("Precio por Unidad $"+ detallePedido.getArticulo().getPrecioVenta());
                System.out.println("----------------------------------");
            }

            //Se muestran datos del articulo Manufacturado 1
            Optional<ArticuloManufacturado> articuloManufacturadoOptional = articuloManufacturadoRepository.findById(5L);
            ArticuloManufacturado articuloManufacturadoRecuperado = articuloManufacturadoOptional.get();
            System.out.println("Nombre articulo Manufacturado: "+articuloManufacturadoRecuperado.getDenominacion());
            System.out.println("Costo Total: "+ articuloManufacturadoRecuperado.getPrecioCosto());
            System.out.println("Detalles de articulo");
            for (DetalleArtManufacturado detalleArtManufacturado: articuloManufacturadoRecuperado.getDetallesArtManufacturado()) {
                System.out.println("Nombre Articulo Insumo: "+detalleArtManufacturado.getArticuloInsumo().getDenominacion());
                System.out.println("Cantidad : "+detalleArtManufacturado.getCantidad());
                System.out.println("Precio por Unidad: "+ detalleArtManufacturado.getArticuloInsumo().getPrecioCompra());
                System.out.println("Unidad Medida: "+ detalleArtManufacturado.getArticuloInsumo().getUnidadMedida().getDenominacion());
                System.out.println("----------------------------------");
            }
            pedido1.emitirFactura(FormaPago.efectivo);
            System.out.println(pedido1.getFactura().getTotalFinal()); */
        };
    }
}
