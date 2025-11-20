# NBRB API Wrapper

## Description
The project is a wrapper on NBRB rate API

## Tech Stack and Technologies
- Java 24
- Spring Boot 3+
- Mapstruct
- Swagger
- Feign Client
- Gradle
- Caffeine Cache
- JUnit5
- Mockito
- Docker

## Operations 

### How to build the service
***Important note***: *to build the service you need Java 24 installed on your machine.*

To build the service .jar run the command:

```bash
./gradlew clean build
```

***Important note***: *to build the service Docker image you need Docker installed on your machine.*

To build the service Docker image use the command:

```bash
docker build -t swagger-api .
```

### How to run the service locally
***Important note***: *to run the service locally using the command below you need Docker installed on your machine.*

Run the command to build the docker image and run the service with Prometheus and Grafana on your machine:

```bash
docker compose up --build
```

After that you will have the next functionality available:
- Swagger UI on [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) 
- Grafana on [http://localhost:3000](http://localhost:3000). Just basic metrics, nothing special. Use *admin/admin* credentials to sign in.

**OR**

Just run the [SwaggerApiApplication.main()](service/src/main/java/com/katsiankou/swagger/SwaggerApiApplication.java) method from your IDE. The service will start on http://localhost:8080

### How to stop the service locally
Run the command to stop and remove the container:

```bash
docker compose down --rmi local
```