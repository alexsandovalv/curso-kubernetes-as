FROM openjdk:17-jdk-alpine AS builder
LABEL authors="sanvic"

# Añade bash para compatibilidad si es necesario
RUN apk add --no-cache bash dos2unix

WORKDIR /app/msv-cursos

COPY ./pom.xml /app
COPY ./msv-cursos/.mvn ./.mvn
COPY ./msv-cursos/mvnw .
COPY ./msv-cursos/pom.xml .

RUN ls -la /app/msv-cursos

# Elimina caracteres '\r' del archivo mvnw, asigna permisos y ejecuta mvnw.
RUN dos2unix ./mvnw && \
    chmod +x ./mvnw && \
    ./mvnw --version && \
    ./mvnw dependency:go-offline

COPY ./msv-cursos/src ./src
RUN ./mvnw clean package -DskipTests


#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msv-cursos/target/msv-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD ["java", "-jar", "msv-cursos-0.0.1-SNAPSHOT.jar"]