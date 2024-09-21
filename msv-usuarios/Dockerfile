FROM openjdk:17-jdk-alpine AS builder
LABEL authors="sandovlu"

WORKDIR /app/msv-usuarios

COPY ./pom.xml /app
COPY ./msv-usuarios/.mvn ./.mvn
COPY ./msv-usuarios/mvnw .
COPY ./msv-usuarios/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
#RUN ./mvnw dependency:go-offline

COPY ./msv-usuarios/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msv-usuarios/target/msv-usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

CMD ["java", "-jar", "msv-usuarios-0.0.1-SNAPSHOT.jar"