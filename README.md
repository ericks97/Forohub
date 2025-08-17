#  ForoHub API

API REST desarrollada con **Spring Boot** para la gestión de tópicos en un foro. Este proyecto incluye un sistema de autenticación y autorización basado en **JSON Web Tokens (JWT)** y está securizado con **Spring Security**.

##  Características Principales

-   **CRUD Completo de Tópicos**: Operaciones para crear, listar, detallar, actualizar y eliminar tópicos.
    
-   **Base de Datos**: Persistencia de datos gestionada con **MySQL** y Spring Data JPA.
    
-   **Seguridad Robusta**:
    
    -   Autenticación de usuarios (login).
        
    -   Generación de tokens JWT para gestionar las sesiones.
        
    -   Protección de endpoints, requiriendo un token válido para acceder.
        
    -   Verificación automática del token en cada petición.
        
-   **Manejo de Excepciones**: Gestión centralizada de errores y validaciones.
    
-   **Arquitectura Limpia**: El proyecto sigue las mejores prácticas, separando responsabilidades en capas (`Controller`, `Service`, `Repository`) y utilizando DTOs (`Data Transfer Objects`) para la comunicación.
    

##  Requisitos

Para poder ejecutar este proyecto, necesitarás los siguientes componentes instalados en tu entorno de desarrollo:

-   **Java 17**: La versión del JDK utilizada.
    
-   **Docker**: Utilizados para levantar el contenedor de la base de datos de manera sencilla.
    
-   **Postman / Insomnia**: Para probar los endpoints de la API, especialmente los que requieren autenticación.
    

## 🐳 Base de Datos con Docker

Para facilitar el despliegue, la base de datos MySQL se ejecuta en un contenedor de Docker.

**Archivo `docker-compose.yml`:**

```
services:
  forohub_db:
    image: mysql:8.0
    container_name: forohub_db
    restart: always
    environment:
      MYSQL_DATABASE: forohub
      MYSQL_ROOT_PASSWORD: root
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:

```

### Levantar la Base de Datos

Para iniciar el contenedor, ejecuta el siguiente comando en la raíz de tu proyecto:

```
docker-compose up --build

```

Esto creará:

-   Una base de datos llamada `forohub`.
    
-   Un usuario `root` con contraseña `root`.
    
-   El servicio de base de datos accesible en el puerto `3306` de tu máquina local.
    

##  Ejecutar la API

Una vez que la base de datos esté en funcionamiento, puedes ejecutar la aplicación Spring Boot desde tu IDE o usando Maven en la terminal:

```
./mvnw spring-boot:run

```

La API estará disponible en `http://localhost:8080`.

##  Seguridad y Autenticación (JWT)

### Usuario de Prueba

Para facilitar las pruebas, se ha inyectado un usuario en la base de datos con las siguientes credenciales:

-   **Usuario**: `Prueba`
    
-   **Contraseña**: `1234`

Para interactuar con los endpoints protegidos, primero debes obtener un token JWT.

1.  **Login**
    
    Envía una petición `POST` al endpoint de login con las credenciales del usuario.
    
    -   **Endpoint**: `POST /login`
        
    -   **Body (JSON)**:
        
        ```
        {
          "username": "tu_usuario",
          "password": "tu_contraseña"
        }
        
        ```
        
    -   **Respuesta Exitosa (JSON)**:
        
        ```
        {
          "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0..."
        }
        
        ```
        
2.  **Realizar Peticiones Protegidas**
    
    Copia el token recibido y añádelo en el encabezado `Authorization` de las siguientes peticiones.
    
    -   **Header**: `Authorization`
        
    -   **Value**: `Bearer <tu_token_jwt>`
        

## Endpoints de la API

A continuación se detallan los endpoints disponibles en la API de ForoHub. Todos los endpoints de gestión de tópicos, con excepción del login, requieren un token JWT válido en el encabezado `Authorization`.

-   **`POST /login`**
    
    -   **Descripción:** Autentica a un usuario y devuelve un token JWT para la sesión.
        
-   **`GET /topicos`**
    
    -   **Descripción:** Lista todos los tópicos existentes.
        
-   **`POST /topicos`**
    
    -   **Descripción:** Crea un nuevo tópico.
        
-   **`GET /topicos/{id}`**
    
    -   **Descripción:** Obtiene los detalles de un tópico específico.
        
-   **`PUT /topicos/{id}`**
    
    -   **Descripción:** Actualiza un tópico existente.
        
-   **`DELETE /topicos/{id}`**
    
    -   **Descripción:** Elimina un tópico.
## Futuras Implementaciones



### 1. Documentación con Swagger

Se integrará **SpringFox Swagger** para generar una documentación de la API interactiva y amigable. Esto permitirá:

-   Una interfaz visual para explorar y probar cada uno de los endpoints de la API.
    
-   Documentación automática que se mantiene actualizada con el desarrollo del código.
    

### 2. Expansión de Endpoints

Se crearán los endpoints (`POST`, `GET`, `PUT`, `DELETE`) para las siguientes clases, ampliando las funcionalidades de la API:

-   `Curso`
    
-   `Perfil`
    
-   `Respuesta`
    
-   `Usuario`
    

### 3. Mejoras Adicionales

-   Implementar roles y permisos para diferenciar usuarios.
    
-   Añadir paginación a la lista de tópicos para manejar grandes volúmenes de datos de manera eficiente.
