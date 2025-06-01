# **API REST - El Buen Sabor**  

![GitHub](https://img.shields.io/badge/Java-Spring%20Boot-brightgreen)  
![Postman](https://img.shields.io/badge/Postman-Collection-orange)  
![JWT](https://img.shields.io/badge/Auth-JWT-blue)  

API REST para el sistema de delivery **"El Buen Sabor"**, desarrollada con **Spring Boot** y documentada en Postman.  

---

## **📌 Características Principales**  
✅ **Autenticación JWT** (registro y login).  
✅ **Gestión de usuarios** (clientes, empleados, administradores).  
✅ **ABMC de productos** (insumos, artículos manufacturados).  
✅ **Pedidos con validación de stock**.  
✅ **Facturación y envío por email**.  
✅ **Reportes estadísticos** (ventas, ranking de productos).  

---

## **🔌 Endpoints**  

### **🔐 Autenticación**  
| Método | Endpoint               | Descripción                          |  
|--------|------------------------|--------------------------------------|  
| `POST` | `/auth/register`       | Registro de clientes.                |  
| `POST` | `/auth/login`          | Login (retorna JWT).                 |  

### **👥 Personas (Usuarios)**  
| Método   | Endpoint                              | Descripción                          |  
|----------|---------------------------------------|--------------------------------------|  
| `GET`    | `/api/v1/personas`                    | Listar todas las personas.           |  
| `GET`    | `/api/v1/personas/paged`              | Listar paginado.                     |  
| `GET`    | `/api/v1/personas/{id}`               | Obtener por ID.                      |  
| `POST`   | `/api/v1/personas`                    | Crear persona.                       |  
| `PUT`    | `/api/v1/personas/{id}`               | Actualizar persona.                  |  
| `DELETE` | `/api/v1/personas/{id}`               | Eliminar persona.                    |  
| `GET`    | `/api/v1/personas/clientes`           | Listar solo clientes.                |  
| `GET`    | `/api/v1/personas/empleados`          | Listar solo empleados.               |  
| `GET`    | `/api/v1/personas/rankingClientes`    | Ranking de clientes (por pedidos).   |  

### **🛒 Pedidos**  
| Método   | Endpoint                                      | Descripción                          |  
|----------|-----------------------------------------------|--------------------------------------|  
| `GET`    | `/api/v1/pedidos`                             | Listar todos los pedidos.           |  
| `GET`    | `/api/v1/pedidos/paged`                       | Listar paginado.                     |  
| `POST`   | `/api/v1/pedidos`                             | Crear pedido.                        |  
| `GET`    | `/api/v1/pedidos/{id}`                        | Obtener por ID.                      |  
| `DELETE` | `/api/v1/pedidos/{id}`                        | Eliminar pedido.                     |  
| `GET`    | `/api/v1/pedidos/findByCliente/{id}`          | Pedidos por cliente.                 |  
| `GET`    | `/api/v1/pedidos/movimientosMonerios`         | Reporte de ingresos.                 |  

### **📦 Artículos (Insumos/Manufacturados)**  
| Método   | Endpoint                                              | Descripción                          |  
|----------|-------------------------------------------------------|--------------------------------------|  
| `GET`    | `/api/v1/articulos/insumos`                          | Listar insumos.                      |  
| `POST`   | `/api/v1/articulos/insumos`                          | Crear insumo.                        |  
| `GET`    | `/api/v1/articulos/manufacturados`                   | Listar manufacturados.               |  
| `POST`   | `/api/v1/articulos/manufacturados`                   | Crear manufacturado.                 |  
| `GET`    | `/api/v1/articulos/insumos/searchSoldestByDate`      | Insumos más vendidos (por fecha).    |  

### **📊 Facturas**  
| Método   | Endpoint                      | Descripción                          |  
|----------|-------------------------------|--------------------------------------|  
| `GET`    | `/api/v1/facturas`            | Listar facturas.                     |  
| `GET`    | `/api/v1/facturas/paged`      | Listar paginado.                     |  
| `DELETE` | `/api/v1/facturas/{id}`       | Eliminar factura.                    |  

### **📍 Localidades**  
| Método   | Endpoint                      | Descripción                          |  
|----------|-------------------------------|--------------------------------------|  
| `GET`    | `/api/v1/localidades`         | Listar localidades.                  |  
| `POST`   | `/api/v1/localidades`         | Crear localidad.                     |  

---

## **⚙️ Configuración**  

### **Variables de Entorno**  
```env  
# Base de Datos  
DB_URL=jdbc:mysql://localhost:3306/elbuensabor  
DB_USER=root  
DB_PASSWORD=1234  

# JWT  
JWT_SECRET=mi_clave_secreta  

# Email (para facturas)  
EMAIL_USER=correo@gmail.com  
EMAIL_PASS=password  
```  

### **Ejecución**  
```bash  
mvn spring-boot:run  
```  

---

## **📊 Reportes Disponibles**  
1. **Ranking de clientes** (`/personas/rankingClientes`).  
2. **Ingresos por período** (`/pedidos/movimientosMoneriosByDate`).  
3. **Productos más vendidos** (`/articulos/insumos/searchSoldest`).  

---

## **📚 Documentación Postman**  
- Colección Postman incluida en el repositorio (`Api Buen Sabor.postman_collection.json`).  
- **Variables globales**:  
  - `{{token}}`: JWT obtenido al hacer login.  

---

## **🛠 Tecnologías Utilizadas**  
- **Backend**: Java + Spring Boot.  
- **Base de Datos**: MySQL.  
- **Autenticación**: JWT.  
- **Documentación**: Postman.  

---

## **📄 Licencia**  
© 2023 - Equipo de desarrollo "El Buen Sabor". Proyecto académico.  

---  
**🔗 ¿Consultas?** Abre un *issue* en el repositorio.
