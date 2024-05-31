### Documentación de la API - Gestión de Autenticación y Publicaciones

## Descripción General

Esta API permite a los usuarios registrarse, iniciar sesión, gestionar publicaciones y proporciona herramientas adicionales para los administradores para aprobar, desaprobar y eliminar publicaciones. La autenticación es requerida para todas las operaciones de publicación.

### Rutas de Autenticación

- **POST /register**: Registro de un nuevo usuario.
- **POST /login**: Inicio de sesión.
- **POST /logout**: Cierre de sesión.
- **GET /profile**: Obtener el perfil del usuario autenticado.

### Rutas de Publicaciones

- **POST /publication/create**: Crear una nueva publicación.
- **GET /publication/all**: Obtener todas las publicaciones aprobadas.
- **GET /publication/user/all**: Obtener todas las publicaciones del usuario autenticado.
- **DELETE /publication/user/delete/:id**: Eliminar una publicación del usuario autenticado.
- **PUT /publication/admin/status/:id**: Actualizar el estado de una publicación (solo administradores).
- **GET /publication/admin/pending**: Obtener todas las publicaciones pendientes (solo administradores).
- **GET /publication/admin/unapproved**: Obtener todas las publicaciones desaprobadas (solo administradores).
- **DELETE /publication/admin/delete/:id**: Eliminar cualquier publicación (solo administradores).

## Endpoints de Autenticación

### Registro de Usuario

- **URL**: `/register`
- **Método**: `POST`
- **Descripción**: Registro de un nuevo usuario.
- **Cuerpo de la Solicitud**:
  ```json
  {
    "name": "string",
    "lastName": "string",
    "email": "string",
    "password": "string"
  }
  ```
- **Respuestas**:
  - **200 OK**:
    ```json
    {
      "message": "User created successfully"
    }
    ```
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Registration failed"
    }
    ```

### Inicio de Sesión

- **URL**: `/login`
- **Método**: `POST`
- **Descripción**: Inicio de sesión.
- **Cuerpo de la Solicitud**:
  ```json
  {
    "email": "string",
    "password": "string"
  }
  ```
- **Respuestas**:
  - **200 OK**:
    ```json
    {
      "id": "string",
      "userName": "string",
      "email": "string",
      "createdAt": "string",
      "updatedAt": "string"
    }
    ```
  - **400 Bad Request**:
    ```json
    {
      "message": "Invalid credential"
    }
    ```
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Registration failed"
    }
    ```

### Cierre de Sesión

- **URL**: `/logout`
- **Método**: `POST`
- **Descripción**: Cierre de sesión.
- **Respuestas**:
  - **200 OK**:
    ```json
    {
      "message": "Cookie cleared"
    }
    ```

### Perfil del Usuario

- **URL**: `/profile`
- **Método**: `GET`
- **Descripción**: Obtener el perfil del usuario autenticado.
- **Middleware**: `authRequired` (Requiere autenticación)
- **Respuestas**:
  - **200 OK**:
    ```json
    {
      "id": "string",
      "email": "string",
      "role": "string",
      "createdAt": "string",
      "updatedAt": "string"
    }
    ```
  - **400 Bad Request**:
    ```json
    {
      "message": "User not found"
    }
    ```

## Endpoints de Publicaciones

### Crear Publicación

- **URL**: `/publication/create`
- **Método**: `POST`
- **Descripción**: Crear una nueva publicación.
- **Middleware**: `authRequired` (Requiere autenticación)
- **Cuerpo de la Solicitud**:
  ```json
  {
    "type": "string",
    "state": "string",
    "city": "string",
    "town": "string",
    "address": "string",
    "area": "number",
    "rooms": "number",
    "floors": "number",
    "other": ["string"],
    "description": "string",
    "price": "number",
    "availability": {"date": ["string"]},
    "images": ["string"]
  }
  ```
- **Respuestas**:
  - **201 Created**:
    ```json
    {
      "message": "Publication created successfully",
      "publication": { ... }
    }
    ```
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Publication creation failed"
    }
    ```

### Obtener Todas las Publicaciones Aprobadas

- **URL**: `/publication/all`
- **Método**: `GET`
- **Descripción**: Obtener todas las publicaciones aprobadas.
- **Middleware**: `authRequired` (Requiere autenticación)
- **Respuestas**:
  - **200 OK**: Array de publicaciones aprobadas.
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Failed to retrieve approved publications"
    }
    ```

### Obtener Todas las Publicaciones del Usuario Autenticado

- **URL**: `/publication/user/all`
- **Método**: `GET`
- **Descripción**: Obtener todas las publicaciones del usuario autenticado.
- **Middleware**: `authRequired` (Requiere autenticación)
- **Respuestas**:
  - **200 OK**: Array de publicaciones del usuario.
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Failed to retrieve user's publications"
    }
    ```

### Eliminar Publicación del Usuario

- **URL**: `/publication/user/delete/:id`
- **Método**: `DELETE`
- **Descripción**: Eliminar una publicación del usuario autenticado.
- **Middleware**: `authRequired` (Requiere autenticación)
- **Respuestas**:
  - **200 OK**:
    ```json
    {
      "message": "Publication deleted successfully"
    }
    ```
  - **404 Not Found**:
    ```json
    {
      "error": "Publication not found"
    }
    ```
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Failed to delete publication"
    }
    ```

### Actualizar Estado de Publicación (Solo Administradores)

- **URL**: `/publication/admin/status/:id`
- **Método**: `PUT`
- **Descripción**: Actualizar el estado de una publicación.
- **Middleware**: `authRequired` (Requiere autenticación, solo administradores)
- **Cuerpo de la Solicitud**:
  ```json
  {
    "status": "string" // puede ser "approved", "unapproved", "pending"
  }
  ```
- **Respuestas**:
  - **200 OK**:
    ```json
    {
      "message": "Publication status updated successfully",
      "publication": { ... }
    }
    ```
  - **403 Forbidden**:
    ```json
    {
      "error": "Forbidden: Only admin users can update publication status"
    }
    ```
  - **404 Not Found**:
    ```json
    {
      "error": "Publication not found"
    }
    ```
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Failed to update publication status"
    }
    ```

### Obtener Publicaciones Pendientes (Solo Administradores)

- **URL**: `/publication/admin/pending`
- **Método**: `GET`
- **Descripción**: Obtener todas las publicaciones pendientes.
- **Middleware**: `authRequired` (Requiere autenticación, solo administradores)
- **Respuestas**:
  - **200 OK**: Array de publicaciones pendientes.
  - **403 Forbidden**:
    ```json
    {
      "error": "Forbidden: Only admin users can view pending publications"
    }
    ```
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Failed to retrieve pending publications"
    }
    ```

### Obtener Publicaciones Desaprobadas (Solo Administradores)

- **URL**: `/publication/admin/unapproved`
- **Método**: `GET`
- **Descripción**: Obtener todas las publicaciones desaprobadas.
- **Middleware**: `authRequired` (Requiere autenticación, solo administradores)
- **Respuestas**:
  - **200 OK**: Array de publicaciones desaprobadas.
  - **403 Forbidden**:
    ```json
    {
      "error": "Forbidden: Only admin users can view unapproved publications"
    }
    ```
  - **500 Internal Server Error**:
    ```json
    {
      "error": "Failed to retrieve unapproved publications"
    }
    ```

### Eliminar Publicación (Solo Administradores)

- **URL**: `/publication/admin/delete/:id`
- **Método**: `DELETE`
- **Descripción**: Eliminar cualquier publicación.
- **Middleware**: `authRequired` (Requiere autenticación, solo administradores)
- **Respuestas**:
  - **200 OK**:
    ```json
    {
      "message": "Publication deleted successfully"
    }
    ```
 
