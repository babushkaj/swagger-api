FROM eclipse-temurin:24-alpine AS jar_builder
ARG JAR_FILE=service/build/libs/service-*.jar
COPY interface interface/
COPY service service/
COPY gradle gradle/
COPY gradlew .
COPY build.gradle gradle.properties settings.gradle ./
RUN chmod +x gradlew && ./gradlew --version #run wrapper to download and cache it
RUN ./gradlew clean build

FROM eclipse-temurin:24-alpine AS builder
ARG JAR_FILE=service/build/libs/service-*.jar
COPY --from=jar_builder ${JAR_FILE} ./application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:24-alpine
LABEL authors="Aliaksei_Katsiankou"
ARG JAR_FILE=service/build/libs/service-*.jar
EXPOSE 8080
WORKDIR /app
COPY --from=jar_builder ${JAR_FILE} ./
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]