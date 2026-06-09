# Alerta Vecinal - API REST (Backend)

Hola, este es el backend de mi proyecto "Alerta Vecinal". Es una API RESTFul desarrollada con Spring Boot aplicando la arquitectura MVC y protegida con Spring Security y JWT.

## Tecnologías que usé en el proyecto
* Java 25
* Spring Boot 4.0.6 (Spring Web, Spring Data JPA)
* Spring Security + JWT
* MySQL (Base de datos principal)
* H2 Database (Integrado exclusivamente para poder correr las pruebas unitarias en memoria)
* Lombok
* Maven

## Arquitectura
El proyecto aplica la arquitectura MVC y está estructurado en capas (Controladores, Servicios y Repositorios).

## ¿Qué hace el sistema?
El sistema permite a los vecinos reportar incidentes de su zona (robos, sospechosos, accidentes, etc.). Tiene dos partes principales:

### 1. Sistema de Login y Registro
* **Registro:** Te permite crear un usuario (con soporte para roles como `ROLE_VECINO` o `ROLE_ADMIN`). La contraseña se encripta automáticamente en la base de datos usando `BCryptPasswordEncoder`.
* **Login:** Al enviar tus credenciales válidas, la API te responde con un token JWT. Este token es obligatorio para usar los demás servicios.

### 2. CRUD de Incidentes
Los siguientes métodos HTTP están protegidos y requieren enviar el JWT en el header de autorización:
* `GET /api/incidents`: Lista todos los incidentes registrados en la base de datos.
* `POST /api/incidents`: Registra un nuevo incidente.
* `GET /api/incidents/{id}`: Busca los detalles de un incidente específico por su ID.
* `PUT /api/incidents/{id}`: Actualiza los datos o el estado de un incidente.
* `DELETE /api/incidents/{id}`: Elimina el registro de un incidente.

## Instrucciones para probar la API

1. **Base de Datos:** Primero, asegúrate de tener una base de datos en MySQL llamada `alerta_vecinal`. Mi archivo `application.properties` está configurado con el usuario `root` y la clave `hectorpalacios`, así que podrías necesitar cambiar esas credenciales según tu entorno local.
2. **Levantar el servidor:** Ejecuta la clase principal desde tu IDE. El proyecto levantará en el puerto 8080 y creará las tablas automáticamente gracias a Hibernate (`ddl-auto=update`).
3. **Pruebas (Postman):** En la raíz del proyecto dejé un archivo llamado `AlertaVecinal_Postman_Collection.json`. Lo puedes importar directamente a tu programa Postman.
   - Primero ve a la carpeta "Auth" en el Postman importado y ejecuta el "Register" o "Login".
   - Copia el token larguísimo que te devuelve el sistema.
   - Pégalo en la pestaña "Variables" de la colección (en la variable `jwt_token`).
   - Con eso configurado ya podrás probar todas las rutas del CRUD de la carpeta Incidentes sin que te salga error de autenticación.

## Pruebas Unitarias
También dejé implementadas las pruebas de la capa de acceso a datos (`IncidenteRepositoryTest`). Usé la base de datos en memoria H2 para que las pruebas corran limpias sin ensuciar la base de datos real de MySQL. Estas pruebas validan de forma automatizada las operaciones de Insertar, Actualizar, Eliminar y Listar.

---
