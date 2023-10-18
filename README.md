“El Buen Sabor”

El delivery de comidas de la ciudad “El Buen Sabor” ofrece a sus clientes una amplia variedad de bebidas y de comidas de fabricación propia. Posee un horario de atención de lunes a domingos de 20:00 a 12:00, y de sábados y domingos de 11:00 a 15:00. Los clientes tienen a disposición un menú que describe cada una de las comidas, el nombre, los ingredientes y el precio. Los clientes realizan sus pedidos en el mostrador del local mediante una PC o pueden hacerlo en forma remota desde su casa o su celular personal (la aplicación debe ser responsive).

El cliente debe proceder a registrarse en la aplicación como paso inicial para realizar el pedido o ejecutar el login en la misma si ya se encuentra registrado, el identificador único y nombre de usuario para el cliente será su email.

El pedido debe contener el número y la fecha del pedido, el nombre del Cliente, teléfono, su domicilio y el detalle de las comidas y bebidas deseadas. Además, el cliente deberá indicar si retira el pedido en el local (en este caso se le otorga un 10% de descuento en la compra) o desea que se lo envíen a su domicilio. El local admite 2 formas de pago, efectivo únicamente si el pago se realiza en el local de forma presencial, si el pedido se entrega a domicilio solo se acepta pago mediante mercado pago.

El sistema debe validar que el stock de artículos insumos que se posee sea suficiente para llevar a cabo el pedido, por ejemplo, si el cliente pide una pizza de jamón crudo y rúcula, pero el stock de rúcula es 0, el sistema deberá emitir un mensaje indicando la situación e impidiendo la carga de dicho artículo manufacturado al pedido.

Finalizada la carga del pedido el sistema le informará al cliente cuánto es el tiempo estimado para el retiro o entrega de su pedido, este tiempo surgirá de la siguiente formula: ∑ Sumatoria del tiempo estimado de los artículos manufacturados solicitados por el cliente en el pedido actual

∑ Sumatoria del tiempo estimado de los artículos manufacturados que se encuentran en la cocina / cantidad cocineros
10 Minutos de entrega por delivery (solo si corresponde).
El pedido realizado por el cliente ingresa a la bandeja de entrada de pedidos pendientes del cajero, este usuario (cajero) revisa el pedido y si está correcto, lo aprueba, dicha acción envía el pedido a la cocina. El usuario cocinero consulta los pedidos aprobados que debe preparar y cuando el pedido está listo lo marca con estado terminado, esta acción envía el pedido nuevamente al usuario cajero pero a la bandeja de pedidos listos para entregar, ahora el pedido puede seguir 2 caminos, el primero es que sea entregado al cliente directamente en el local lo cual originara la factura correspondiente, con la forma de pago que corresponda y será entregada al cliente, dejando el pedido con el estado final FACTURADO, o segundo el pedido es enviado mediante delivery al domicilio del cliente asignando al pedido el estado “En delivery” hasta que el empleado que realizo el envío retorna al local e indica que la entrega fue exitosa con lo cual el estado del pedido pasa a su estado final FACTURADO. La factura generada es enviada mediante email al cliente, y puede ser accedida por él mediante la aplicación web en la sección Historial de Pedidos.

El cliente además de poder cargar el pedido deberá poder consultar el historial de todos los pedidos realizados y acceder a las correspondientes facturas asociadas, las cuales podrá descargar (Formato PDF).
El cajero cumple un rol central de gestión ya que procesa todos los pedidos e interactúa con el resto de los actores del sistema.
El cocinero recepciona los pedidos y los elabora y además tiene el control de los artículos manufacturados pudiendo variar la composición de los mismos.
Finalmente, el usuario Administrador tiene acceso a todas las funcionalidades del sistema pero se ocupa principalmente de los artículos insumo controlando que los mismos no se agoten.
Funcionalidades principales que deberá soportar el sistema

Registro y administración de los clientes.
Administrar Usuarios y Roles (ABMC)
La clave de usuario debe estar encriptada (MD5, SHA, etc).
Administración de pedidos. (ABMC)
Administración de facturas. (ABMC)
Administración de artículos y artículos manufacturados. (ABMC)
Envío vía email al cliente de la factura.
Implementar Pago: Mediante varias formas de pago.
Control de Stock. Cada pedido finalizado, decrementa el stock actual de los artículos asociados al mismo.
Administración del Rubro
El dueño del Delivery ha manifestado la necesidad de acceder al menos a la siguiente información, la cual desea pueda exportarse a un archivo Excel:

Ranking comidas más pedidas en un periodo de tiempo determinado
Ingresos (recaudaciones) por períodos de tiempo. Diario / Mensual
Cantidad de pedidos agrupados por cliente en un determinado periodo de tiempo.
Monto de Ganancia en un periodo de tiempo (ventas – costos)
Interfaces de usuario principales del sistema

Portal WEB donde el cliente podrá registrarse y realizar los pedidos vía PC o celular.
Interfaces de administración de pedidos y facturas por parte del empleado cajero.
Interface de pedidos pendientes para el cocinero.
Interface de administración de artículos y artículos manufacturados.
Reportes estadísticos para toma de decisiones.
Checkout / Proceso de Pago Al finalizar la selección de los platos (pizzas, empanadas, lomos, bebidas, etc.) el proceso de pago deberá contener al menos los siguientes pasos:

Verificar que el cliente se encuentre logueado en la aplicación, caso contrario solicitarle que realice el login o en su defecto que se registre en el sistema si todavía no lo ha hecho.
Forma de envío, en este paso el cliente debe seleccionar entre las opciones “Retiro en Local” o “Envió a Domicilio”, solo si selecciona esta última opción el sistema deberá solicitarle los datos del domicilio de envío (calle, número, localidad, etc), presentando inicialmente los datos precargados en la registración al sistema.
Método de pago: Si en el paso anterior el cliente selecciono la forma de envío “Retiro en Local” se le presentarán las opciones de Pago, por ejemplo: “Efectivo”, “Mercado Pago”, etc..
Fin del Checkout
Tecnologías para el desarrollo de la Aplicación PORTAL WEB Puede ser desarrollado con cualquier tipo de framework HTML/JavaScript, más el uso de cualquier otro lenguaje de acceso al servidor (Java, ASP, Node, etc), es importante que el portal sea responsive para adaptarse a diferentes dispositivos.

MÓDULO DE ADMINISTRACIÓN Front End Debe ser desarrollado por cualquier framework o librería que desee siempre y cuando la misma sea reactiva (RxJs, React, Vue, Angular, etc.)

Back End

Se debe presentar un modelo de base datos.
Se debe presentar documentación de las API Rest del sistema.
Front End Desarrollado en React.

Las interfaces de usuarios deberán ser modeladas utilizando figma.
Podrán utilizarse herramientas y librerías relacionadas con la cátedra, con la aprobación de los docentes.
