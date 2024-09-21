FROM openjdk:17-jdk-alpine AS builder
LABEL authors="sanvic"

WORKDIR /app/msv-cursos

COPY ./pom.xml /app
COPY ./msv-cursos/.mvn ./.mvn
COPY ./msv-cursos/mvnw .
COPY ./msv-cursos/pom.xml .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
RUN ./mvnw dependency:go-offline

COPY ./msv-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msv-cursos/target/msv-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

CMD ["java", "-jar", "msv-cursos-0.0.1-SNAPSHOT.jar"]