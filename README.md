# RepairShop Service

En el presente repositorio se presenta un servicio que gestiona las entradas de vehículos a un taller mecánico siguiendo una arquitectura MVC, relaciones bidireccionales y tecnologías Java, Spring Boot, Spring Data JPA, entre otras.

![API REST del Taller](./docs/apiresttaller.png)

## Tabla de contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos previos](#requisitos-previos)
- [Instalación y ejecución](#instalación-y-ejecución)
- [Configuración](#configuración)
- [Puntos finales (endpoints)](#puntos-finales-endpoints)
- [Arquitectura](#arquitectura)
- [Pruebas](#pruebas)
- [Contribución](#contribución)
- [Licencia](#licencia)

## Descripción

Este proyecto implementa un servicio REST para la gestión de entradas de vehículos a un taller mecánico. Permite registrar ingresos de vehículos, consultar estados y gestionar la información relacionada (clientes, vehículos, reparaciones, etc.) utilizando Spring Boot y JPA para persistencia.

## Características

- API REST para gestión de entradas de taller
- Persistencia con Spring Data JPA
- Arquitectura MVC
- Relaciones bidireccionales entre entidades cuando aplica
- Configuración fácilmente adaptable para distintas bases de datos

## Tecnologías

- Java
- Spring Boot
- Spring Data JPA
- Maven

## Requisitos previos

- Java 11+ (o la versión que use el proyecto)
- Maven 3.6+ (o usar el wrapper `./mvnw` si está incluido)
- Una base de datos (H2, MySQL, PostgreSQL, etc.) o usar la configuración en memoria para desarrollo

## Instalación y ejecución

1. Clona el repositorio:

   git clone https://github.com/victordaniel123rt-lang/repairShop-service.git
   cd repairShop-service

2. Construye el proyecto con Maven:

   mvn clean package

   O si tienes el wrapper incluido:

   ./mvnw clean package

3. Ejecuta la aplicación:

   java -jar target/*.jar

   O con Spring Boot desde Maven:

   mvn spring-boot:run

La aplicación arrancará en el puerto configurado (por defecto Spring Boot usa el 8080 si no se ha cambiado).

## Configuración

Configura la conexión a la base de datos en `src/main/resources/application.properties` o `application.yml`. Ejemplo con H2 en memoria (útil para desarrollo):

spring.datasource.url=jdbc:h2:mem:repairshopdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update

Para usar MySQL o PostgreSQL, modifica `spring.datasource.url`, `spring.datasource.username` y `spring.datasource.password` acorde a tu entorno.

## Puntos finales (endpoints)

Los endpoints expuestos dependen de los controladores implementados en el proyecto. Ejemplos típicos que podrías encontrar:

- `GET /api/entradas` - listar entradas de taller
- `GET /api/entradas/{id}` - obtener detalle de una entrada
- `POST /api/entradas` - registrar una nueva entrada
- `PUT /api/entradas/{id}` - actualizar una entrada
- `DELETE /api/entradas/{id}` - eliminar una entrada

Revisa los controladores en `src/main/java` para confirmar las rutas exactas y los modelos de petición/respuesta.

## Arquitectura

El proyecto sigue el patrón arquitectónico MVC (Model-View-Controller) aplicado a una API REST:

- Model: Entidades JPA que representan Cliente, Vehículo, Entrada, Reparación, etc.
- Repository: Interfaces Spring Data JPA para acceso a datos.
- Service: Lógica de negocio y reglas de aplicación.
- Controller: Endpoints REST que exponen la funcionalidad.

Se mencionan relaciones bidireccionales entre entidades donde sea necesario (por ejemplo, una Entrada puede relacionarse con un Vehículo y un Cliente).

## Pruebas

Si el proyecto incluye pruebas unitarias o de integración, puedes ejecutarlas con:

mvn test

Revisa el directorio `src/test` para ver las pruebas disponibles.

## Contribución

Si deseas contribuir:

1. Haz un fork del repositorio.
2. Crea una rama para tu cambio: `git checkout -b feature/mi-cambio`.
3. Haz commits claros y descriptivos.
4. Abre un Pull Request describiendo los cambios y el motivo.

## Licencia

Indica aquí la licencia del proyecto si corresponde (por ejemplo, MIT). Si no hay licencia, añade una para dejarlo claro.

---

Si quieres, puedo añadir ejemplos de peticiones cURL, detallar los endpoints leyendo el código del proyecto para incluir rutas exactas, o crear un archivo `application-example.properties` con variables de configuración. Dime qué prefieres.