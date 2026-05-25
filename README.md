API Proveedores Caso BookPoint
 SpringBoot + JPA + MYSQL


Query para crear Base de Datos requerida:
CREATE DATABASE proveedores_db;


Endpoints de los metodos existentes

Crear:
POST /api/v1/proveedores
http://localhost:8090/api/v1/proveedores

{
    "nombre":"Editorial Planeta",
    "telefono":"987654321",
    "correo":"contacto@planeta.cl",
    "direccion":"Santiago Centro"
}

Listar:
GET /api/v1/proveedores
http://localhost:8090/api/v1/proveedores

Buscar por ID:
GET /api/v1/proveedores/{id}
http://localhost:8090/api/v1/proveedores/1

Modificar:
PUT /api/v1/proveedores/{id}
http://localhost:8090/api/v1/proveedores/1

Eliminar:
DELETE /api/v1/proveedores/{id}
http://localhost:8090/api/v1/proveedores/1