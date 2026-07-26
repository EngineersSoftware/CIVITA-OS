# CIVITA-OS

API REST para la **gestión integral de activos y tickets** del departamento. Plataforma backend orientada a inventarios institucionales, seguimiento de incidencias y control de acceso basado en roles.

---

## Descripción

**CivitasOS** centraliza el ciclo de vida de activos institucionales y la gestión de tickets asociados. Expone una API segura con autenticación JWT, documentación OpenAPI y persistencia en PostgreSQL.

### Capacidades actuales

- Registro e inicio de sesión de usuarios
- Emisión y validación de tokens JWT (Bearer)
- Creación y consulta de tickets
- Actualización de estado de tickets (roles administrativos)
- Modelo de dominio para activos, roles y permisos
- Documentación interactiva con Swagger / OpenAPI

---

## Stack tecnológico

| Componente        | Tecnología                          |
|-------------------|-------------------------------------|
| Lenguaje          | Java 25                             |
| Framework         | Spring Boot 4.0.7                   |
| Persistencia      | Spring Data JPA + PostgreSQL        |
| Seguridad         | Spring Security + JWT (jjwt 0.12.5) |
| Validación        | Bean Validation                     |
| Mapeo             | MapStruct 1.5.5                     |
| Documentación API | springdoc-openapi 3.0.2             |
| Build             | Maven (wrapper incluido)            |

---

## Arquitectura

```
CIVITA-OS/
└── civitas-os/                    # Módulo Spring Boot
    └── src/main/java/.../civitas_os/
        ├── controller/            # Endpoints REST
        ├── service/               # Lógica de negocio
        ├── entity/                # Modelo JPA
        ├── repository/            # Acceso a datos
        ├── dto/                   # Contratos de entrada/salida
        ├── security/              # Configuración y filtro JWT
        ├── jwt/                   # Emisión y validación de tokens
        ├── mapper/                # MapStruct
        ├── specification/         # Consultas dinámicas
        └── advice/                # Manejo global de excepciones
```

---

## Modelo de dominio

### Entidades

| Entidad      | Descripción                                      |
|--------------|--------------------------------------------------|
| **User**     | Usuario del sistema (credenciales, roles)        |
| **Role**     | Rol con permisos asociados                       |
| **Permission** | Permisos granulares                            |
| **Ticket**   | Incidencia o solicitud vinculada a un creador    |
| **Asset**    | Activo institucional (código, ubicación, estado) |

### Enumeraciones

| Enum             | Valores                                              |
|------------------|------------------------------------------------------|
| `Role`           | `USER`, `ADMIN`, `FUNCTIONARY`                       |
| `TicketStatus`   | `OPEN`, `IN_PROGRESS`, `ON_HOLD`, `RESOLVED`, `CLOSED` |
| `AssetStatus`    | `ACTIVO`, `EN_MANTENIMIENTO`, `FUERA_DE_SERVICIO`    |
| `Priority`       | `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`                  |

---

## API REST

Base URL: `http://localhost:8080`

Todas las respuestas siguen el envelope `ApiResponse` (`timestamp`, `status`, `message`, `data`).

### Autenticación — `/api/v1/auth`

| Método | Endpoint                    | Acceso  | Descripción              |
|--------|-----------------------------|---------|--------------------------|
| `POST` | `/api/v1/auth/register`     | Público | Registro de usuario      |
| `POST` | `/api/v1/auth/login`        | Público | Login → token Bearer     |

### Usuarios — `/api/v1/users`

| Método | Endpoint                    | Acceso         | Descripción           |
|--------|-----------------------------|----------------|-----------------------|
| `POST` | `/api/v1/users/register`    | Público        | Alta de usuario       |
| `GET`  | `/api/v1/users/{id}`        | Autenticado    | Consulta por ID       |

### Tickets — `/api/v1/tickets`

| Método  | Endpoint                              | Acceso                         | Descripción                    |
|---------|---------------------------------------|--------------------------------|--------------------------------|
| `POST`  | `/api/v1/tickets`                     | Autenticado                    | Crear ticket                   |
| `GET`   | `/api/v1/tickets`                     | `ADMIN`, `FUNCTIONARY`         | Listar todos                   |
| `GET`   | `/api/v1/tickets/my-tickets`          | Autenticado                    | Tickets del usuario actual     |
| `PATCH` | `/api/v1/tickets/{id}/status?status=` | `ADMIN`, `FUNCTIONARY`         | Actualizar estado              |

> Las rutas de **activos** (`/api/v1/assets/**`) están reservadas en seguridad; la API REST de activos está en desarrollo.

---

## Seguridad

- Autenticación **stateless** con JWT en cabecera `Authorization: Bearer <token>`
- Contraseñas cifradas con **BCrypt**
- Control de acceso por roles (`ADMIN`, `FUNCTIONARY`, `USER`)
- Endpoints públicos: autenticación, registro, Swagger y health check

| Variable / propiedad              | Descripción                         | Default        |
|-----------------------------------|-------------------------------------|----------------|
| `JWT_SECRET` / `civitas.jwt.secret` | Clave de firma JWT                | (dev default)  |
| `civitas.jwt.access-token-expiration` | Expiración access token (ms)    | `3600000` (1 h)|
| `civitas.jwt.refresh-token-expiration`| Expiración refresh token (ms)   | `604800000` (7 d) |

> En entornos reales, define siempre un `JWT_SECRET` propio y seguro.

---

## Requisitos

- **JDK 25+**
- **PostgreSQL 14+**
- **Maven 3.9+** (o usar el wrapper `mvnw` / `mvnw.cmd`)

---

## Configuración

Variables de entorno opcionales (valores por defecto en `application.properties`):

| Variable       | Default        |
|----------------|----------------|
| `DB_HOST`      | `localhost`    |
| `DB_PORT`      | `5432`         |
| `DB_NAME`      | `civitas_db`   |
| `DB_USER`      | `postgres`     |
| `DB_PASSWORD`  | `root`         |
| `JWT_SECRET`   | (ver properties) |

Puerto de la aplicación: **8080** · Zona horaria: **America/Bogota**

---

## Puesta en marcha

### 1. Base de datos

Crear la base en PostgreSQL:

```sql
CREATE DATABASE civitas_db;
```

### 2. Ejecutar la aplicación

Desde el directorio `civitas-os/`:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

O empaquetar y ejecutar el JAR:

```bash
./mvnw clean package
java -jar target/civitas-os-0.0.1-SNAPSHOT.jar
```

### 3. Verificar

| Recurso     | URL                                      |
|-------------|------------------------------------------|
| API         | http://localhost:8080                    |
| Swagger UI  | http://localhost:8080/api/docs           |
| OpenAPI JSON| http://localhost:8080/api/docs/json      |

---

## Ejemplo de uso

**Login**

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "usuario",
  "password": "Password1!"
}
```

**Crear ticket** (con token)

```http
POST /api/v1/tickets
Authorization: Bearer <token>
Content-Type: application/json

{
  "title": "Falla en equipo de red",
  "description": "El switch del piso 3 no responde desde esta mañana."
}
```

---

## Estado del proyecto

| Módulo              | Estado                          |
|---------------------|---------------------------------|
| Autenticación JWT   | Implementado                    |
| Usuarios            | Implementado                    |
| Tickets             | Implementado                    |
| Activos (modelo)    | Entidad y repositorio listos    |
| Activos (API REST)  | Pendiente                       |
| Prioridad en tickets| Enum definido, integración pendiente |
| Refresh tokens      | Configurado, emisión pendiente  |

---

## Licencia

Proyecto interno — uso institucional.
