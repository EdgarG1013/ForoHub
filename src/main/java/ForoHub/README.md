# 📌 ForoHub API

Proyecto desarrollado como challenge de alura para implementar una API REST utilizando Spring Boot 3, aplicando autenticación con Spring Security y generación de tokens JWT.

---

## 📖 Descripción del Proyecto

ForoHub es una API REST que permite gestionar tópicos dentro de un foro.  
Incluye operaciones CRUD protegidas mediante autenticación con JWT.

Solo los usuarios autenticados pueden crear, listar, actualizar o eliminar tópicos.

---

## 🚀 Tecnologías Utilizadas

- Java 21
- Spring Boot 3
- Spring Security
- JWT (Auth0 java-jwt)
- JPA / Hibernate
- Flyway
- MySQL
- Maven
- Insomnia (para pruebas)

---

## 🗄 Base de Datos

La base de datos se gestiona con Flyway para versionar migraciones.

### Tablas principales:

### 📌 usuarios
- id
- login
- password (encriptada con BCrypt)

### 📌 topicos
- id
- titulo
- mensaje
- fecha_creacion
- status
- autor
- curso

---

## 🔐 Autenticación

La API utiliza autenticación basada en JWT.
