# SpringBoot_CRUD_v1
Aplicación en Java utilizando Spring Boot junto con Spring Security para la protección de rutas.

## Contenido
* Controladores
* Servicios
* Dtos personalizados
* Manejador de excepciones globales
* Excepciones personalizadas
* Entidades
* Repositorios
* Testing
* Seguridad (JWT)
* Autentificación y autorización
* Inyección de dependencias

La aplicación funciona de manera muy simple, con dos controladores principales:

## Auth
Este controlador se encarga del inicio de sesión y el registro de la aplicación.

## User
El controlador de usuarios permite realizar las siguientes acciones:

* Listar usuarios
* Encontrar un usuario por su ID, nombre o correo electrónico
* Actualizar información de usuario
* Eliminar un usuario
* Todas las rutas de User están protegidas. Si no se proporciona un token válido del lado del cliente, se deniega el acceso. Si se proporciona un token válido, se permite el acceso a las rutas mencionadas.
