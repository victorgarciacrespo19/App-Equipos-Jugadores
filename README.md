# Gestión de Equipos y Jugadores (DASI)

Este proyecto académico es un sistema basado en **microservicios** desarrollado con **Java 21**, **Maven** y el framework **Quarkus**. Su objetivo principal es la gestión de equipos deportivos, jugadores y los contratos que los vinculan.

## 🏗️ Arquitectura y Módulos

El proyecto sigue una arquitectura distribuida y se compone de los siguientes módulos principales:

- **`ws-equipos` (WebService)**: Microservicio encargado de las operaciones CRUD de equipos (gestiona atributos como nombre, ciudad, estadio, año de fundación, presupuesto y escudo). *(Puerto 8082)*
- **`ws-jugadores` (WebService)**: Microservicio enfocado en los perfiles de los jugadores (gestiona atributos como nombre, nacionalidad, valor de mercado y fotografía). *(Puerto 8083)*
- **`ws-contratos` (WebService)**: Microservicio que relaciona jugadores y equipos, registrando los detalles de sus contratos (ej. año de finalización). *(Puerto 8084)*
- **`gateway` / `aggregator`**: Puerta de enlace (API Gateway) y agregador de peticiones para unificar el acceso desde el exterior hacia los microservicios internos.
- **`domain`**: Contiene las entidades fundamentales y los modelos de dominio.
- **`shared-quarkus`**: Lógica de negocio, utilidades o configuraciones transversales compartidas por todos los microservicios.
- **`web`**: Interfaz de usuario o consumidor de las APIs (Front-End/BFF).

## 🚀 Stack Tecnológico

- **Lenguaje:** Java 21
- **Framework Core:** Quarkus (optimizado para entornos Cloud-Native y contenedores)
- **Build Tool:** Maven
- **Estilo de Arquitectura:** Microservicios y API REST

## 📁 Estructura del Repositorio

- `app/` - Contiene el código fuente completo del proyecto multi-módulo Maven (`trabajo2`).
- `sql/` - Scripts de bases de datos para inicializar el esquema y los datos del sistema.
- `documentos/` - Documentación adicional de diseño o requerimientos.

## ⚙️ Ejecución en Desarrollo

Puedes arrancar cada microservicio individualmente en modo desarrollo usando el plugin de Quarkus. Por ejemplo, para arrancar el servicio de jugadores:

```bash
cd app/trabajo2/ws-jugadores
mvn quarkus:dev
```
👨‍💻 Autor

Víctor García Crespo
