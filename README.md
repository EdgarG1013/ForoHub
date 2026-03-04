# 🌐 ForoHub - API REST para Gestión de Tópicos del Foro

## 📌 Descripción

**ForoHub** es una API REST desarrollada en Spring Boot que implementa un sistema completo de gestión de tópicos para un foro. Permite crear, listar, actualizar y eliminar tópicos de discusión con autenticación y seguridad mediante tokens JWT.

La aplicación implementa un flujo seguro donde:

- ✅ Registrar nuevos usuarios
- ✅ Autenticarse y obtener token JWT
- ✅ Crear, listar, actualizar y eliminar tópicos (protegido con JWT)
- ✅ Base de datos relacional para persistencia de datos
- ✅ Validaciones de integridad y reglas de negocio

---

## 📚 Tecnologías Utilizadas

| Tecnología | Versión | Descripción |
|-----------|---------|-------------|
| **Java** | 21 | Lenguaje de programación |
| **Spring Boot** | 4.0.3 | Framework web y aplicación |
| **Spring Security** | - | Seguridad y autenticación |
| **Spring Data JPA** | - | ORM y acceso a datos |
| **MySQL** | - | Base de datos relacional |
| **Flyway** | - | Migraciones de base de datos |
| **JWT (java-jwt)** | 4.5.0 | Tokens para autenticación |
| **Lombok** | - | Reducir código boilerplate |
| **Validation** | - | Validación de datos |

---

## 📂 Estructura del Proyecto

```
ForoHub/
├── src/
│   ├── main/
│   │   ├── java/ForoHub/
│   │   │   ├── ChallengeForoHubApplication.java      (Punto de entrada)
│   │   │   ├── Controller/
│   │   │   │   ├── AutenticacionController.java      (Endpoint de login)
│   │   │   │   └── TopicoController.java             (Endpoints de tópicos)
│   │   │   ├── Domain/
│   │   │   │   ├── topico/
│   │   │   │   │   ├── Topico.java                   (Entidad)
│   │   │   │   │   ├── DatosRegistroTopico.java      (DTO para crear)
│   │   │   │   │   ├── DatosActualizarTopico.java    (DTO para actualizar)
│   │   │   │   │   └── DatosDetalleTopico.java       (DTO para respuesta)
│   │   │   │   └── usuario/
│   │   │   │       └── Usuario.java                  (Entidad)
│   │   │   ├── Infrastructure/
│   │   │   │   ├── SecurityConfigurations.java       (Configuración de seguridad)
│   │   │   │   ├── SecurityFilter.java               (Filtro JWT)
│   │   │   │   └── AutenticacionService.java         (Servicio de autenticación)
│   │   │   ├── Repository/
│   │   │   │   ├── TopicoRepository.java             (Acceso a datos - tópicos)
│   │   │   │   └── UsuarioRepository.java            (Acceso a datos - usuarios)
│   │   │   └── Services/
│   │   │       ├── TokenService.java                 (Generación y validación de JWT)
│   │   │       └── DatosAutenticacion.java           (Record de autenticación)
│   │   └── resources/
│   │       ├── application.properties                (Configuración)
│   │       └── db/migration/
│   │           ├── V1__create-table-topicos.sql      (Migración 1)
│   │           └── V2__create-table-usuarios.sql     (Migración 2)
│   └── test/
│       └── java/ForoHub/
│           └── ChallengeForoHubApplicationTests.java
├── pom.xml                                           (Dependencias Maven)
└── README.md                                         (Este archivo)
```

---

## 🎯 Funcionalidades Principales

### 1. **Autenticación con JWT** 🔐
```
POST /login
Content-Type: application/json

{
  "login": "usuario",
  "password": "contraseña"
}
```
**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```
- Genera token JWT válido por 2 horas
- Solo usuarios registrados pueden autenticarse
- Token requerido para acceder a endpoints protegidos

### 2. **Crear Tópico** ✍️
```
POST /topicos
Authorization: Bearer {token}
Content-Type: application/json

{
  "titulo": "Question about Spring Security",
  "mensaje": "How to implement JWT in Spring Boot?",
  "autor": "test"
}
```
**Validaciones:**
- ✅ Requiere autenticación JWT
- ✅ No permite tópicos duplicados (mismo título y mensaje)
- ✅ Todos los campos son obligatorios

### 3. **Listar Tópicos** 📋
```
GET /topicos
Authorization: Bearer {token}
```
**Respuesta:**
```json
[
  {
    "id": 1,
    "titulo": "Question about Spring Security",
    "mensaje": "How to implement JWT in Spring Boot?",
    "autor": "test"
  }
]
```

### 4. **Obtener Detalle de Tópico** 🔍
```
GET /topicos/{id}
Authorization: Bearer {token}
```
**Retorna:**
- Información completa del tópico
- Error 404 si no existe

### 5. **Actualizar Tópico** 🔄
```
PUT /topicos/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "titulo": "Updated Title",
  "mensaje": "Updated message"
}
```
**Validaciones:**
- ✅ Solo usuarios autenticados
- ✅ Verifica que el tópico exista

### 6. **Eliminar Tópico** 🗑️
```
DELETE /topicos/{id}
Authorization: Bearer {token}
```
**Validaciones:**
- ✅ Solo usuarios autenticados
- ✅ Verifica que el tópico exista

---

## ⚙️ Cómo Ejecutar

### Requisitos Previos
- ✅ Java 21 instalado
- ✅ Maven instalado
- ✅ MySQL instalado y ejecutándose
- ✅ Insomnia o Postman para probar

### Pasos de Instalación

**1. Clonar o descargar el proyecto**
```bash
cd "c:\Users\edgar\Desktop\Curso Oracle\4-Challenge-ForoHub"
```

**2. Crear la base de datos en MySQL**
```sql
CREATE DATABASE forohub_db;
```

**3. Configurar credenciales en `application.properties`**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forohub_db
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```

**4. Compilar y ejecutar**
```bash
mvn clean install
mvn spring-boot:run
```

**5. Verificar que se inicia correctamente**
```
¡La aplicación estará disponible en http://localhost:8080
```

---

## 🧪 Ejemplos de Uso con Insomnia

### Paso 1: Crear Usuario en la Base de Datos
```sql
-- Ejecutar en MySQL
INSERT INTO usuarios (login, password) 
VALUES ('test', '$2a$10$oA9cXFXJXcpC4W0Z0ZEWCuyLaJ4VfjYgLd9V6c.LKIy6E3p5G8ybe');
-- Usuario: test
-- Contraseña: 123456
```

### Paso 2: Autenticarse y Obtener Token
**Solicitud Insomnia:**
```
POST http://localhost:8080/login
Content-Type: application/json

{
  "login": "test",
  "password": "123456"
}
```

**Respuesta esperada (Status 200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJmb3JvaHViIiwic3ViIjoidGVzdCIsImV4cCI6MTcwMDAzMjAwMH0...."
}
```

### Paso 3: Crear un Tópico
**Solicitud Insomnia:**
```
POST http://localhost:8080/topicos
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json

{
  "titulo": "¿Cómo implementar JWT en Spring Boot?",
  "mensaje": "Necesito ayuda para implementar autenticación con JWT",
  "autor": "test"
}
```

**Respuesta esperada (Status 200):**
```json
{
  "id": 1,
  "titulo": "¿Cómo implementar JWT en Spring Boot?",
  "mensaje": "Necesito ayuda para implementar autenticación con JWT",
  "autor": "test"
}
```

### Paso 4: Listar Todos los Tópicos
**Solicitud Insomnia:**
```
GET http://localhost:8080/topicos
Authorization: Bearer {token_aqui}
```

### Paso 5: Actualizar un Tópico
**Solicitud Insomnia:**
```
PUT http://localhost:8080/topicos/1
Authorization: Bearer {token_aqui}
Content-Type: application/json

{
  "titulo": "¿Cómo implementar JWT en Spring Security?",
  "mensaje": "Versión actualizada de la pregunta"
}
```

### Paso 6: Eliminar un Tópico
**Solicitud Insomnia:**
```
DELETE http://localhost:8080/topicos/1
Authorization: Bearer {token_aqui}
```

---

## 🔒 Arquitectura de Seguridad

### Flujo de Autenticación

```
1. Usuario → POST /login (credenciales)
                    ↓
2. AuthenticationManager valida credenciales
                    ↓
3. TokenService genera JWT válido
                    ↓
4. Devuelve token al cliente
                    ↓
5. Cliente envía token en header Authorization
                    ↓
6. SecurityFilter interceda y valida JWT
                    ↓
7. Si es válido → acceso permitido ✅
   Si es inválido → acceso denegado ❌
```

### Configuración de Seguridad

- ✅ CSRF deshabilitado (API REST)
- ✅ Política de sesiones STATELESS
- ✅ Endpoint `/login` permitido sin autenticación
- ✅ Todos los demás endpoints requieren JWT
- ✅ Password codificado con BCrypt
- ✅ Token expira en 2 horas

---

## 📊 Esquema de Base de Datos

### Tabla: usuarios
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | BIGINT | PK - Auto increment |
| login | VARCHAR(100) | Username único |
| password | VARCHAR(255) | Contraseña codificada BCrypt |

### Tabla: topicos
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | BIGINT | PK - Auto increment |
| titulo | VARCHAR(255) | Título del tópico |
| mensaje | TEXT | Contenido del tópico |
| fecha_creacion | DATETIME | Cuándo se creó |
| status | VARCHAR(50) | Estado del tópico |

---

## 🛠️ Referencias de Código

### **AutenticacionController.java**
- **Método:** `autenticar(@RequestBody @Valid DatosAutenticacion datos)`
- **Propósito:** Valida credenciales y genera JWT
- **Seguridad:** Sin protección (endpoint público)

### **TopicoController.java**
- **POST /topicos:** Crear nuevo tópico
- **GET /topicos:** Listar todos los tópicos
- **GET /topicos/{id}:** Obtener detalle de tópico
- **PUT /topicos/{id}:** Actualizar tópico
- **DELETE /topicos/{id}:** Eliminar tópico
- **Protección:** Todos requieren autenticación JWT

### **TokenService.java**
- **generarToken(Usuario usuario):** Crea JWT con datos del usuario
- **getSubject(String token):** Extrae el usuario del token
- **Algoritmo:** HMAC256 con secreto configurado

### **SecurityFilter.java**
- **Intercepta:** Cada solicitud HTTP
- **Valida:** Token JWT en header Authorization
- **Autoriza:** Usuario si el token es válido

---

## ✨ Mejoras Futuras

- 📝 Agregar validacion de errores personalizada (Exception Handler)
- 📝 Implementar paginación en listados
- 📝 Agregar filtros avanzados (por fecha, autor, etc.)
- 📝 Documentación Swagger/OpenAPI
- 📝 Tests automatizados (JUnit, Mockito)
- 📝 Logs detallados con Logback
- 📝 Rate limiting para prevenir abuso
- 📝 Refresh tokens para mejor seguridad
- 📝 Roles y permisos (Admin, Moderator, User)
- 📝 Notificaciones en tiempo real (WebSocket)

---

## 📝 Notas Importantes

- 🔐 **Seguridad:** Nunca guardes o compartas tokens JWT
- 🔐 **JWT_SECRET:** Cambia el valor predeterminado en producción
- 🔐 **Contraseñas:** Siempre codifica con BCrypt, nunca en texto plano
- 📊 **Base de datos:** Usa PostgreSQL/MySQL en producción
- 🚀 **Flyway:** Las migraciones se ejecutan automáticamente al iniciar
- 📡 **CORS:** Configura si consumes desde frontend diferente

---

## 📋 Ejemplo Completo de Flujo

```bash
# 1. Iniciar la aplicación
mvn spring-boot:run

# 2. En Insomnia: Autenticarse
POST http://localhost:8080/login
{
  "login": "test",
  "password": "123456"
}
# Respuesta: { "token": "..." }

# 3. Copiar el token

# 4. Crear tópico
POST http://localhost:8080/topicos
Header: Authorization: Bearer {token}
{
  "titulo": "Mi primer tópico",
  "mensaje": "Este es mi primer tópico en ForoHub",
  "autor": "test"
}

# 5. Listar tópicos
GET http://localhost:8080/topicos
Header: Authorization: Bearer {token}

# 6. Actualizar
PUT http://localhost:8080/topicos/1
Header: Authorization: Bearer {token}
{
  "titulo": "Tópico actualizado",
  "mensaje": "Contenido actualizado"
}

# 7. Eliminar
DELETE http://localhost:8080/topicos/1
Header: Authorization: Bearer {token}
```

---

## 📄 Licencia

Este proyecto es parte del Challenge de ForoHub de **Oracle Next Education (ONE)**.

---

## 👤 Autor

**Edgar Stiven Garcia**

Desarrollado como parte del Challenge de ForoHub en Oracle Next Education.

