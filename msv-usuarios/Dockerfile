FROM openjdk:17-jdk-alpine
LABEL authors="sandovlu"

WORKDIR /app/msvc-usuarios

COPY ./pom.xml /app
COPY ./msv-usuarios/.mvn ./.mvn
COPY ./msv-usuarios/mvnw .
COPY ./msv-usuarios/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
#RUN ./mvnw dependency:go-offline

COPY ./msv-usuarios/src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "msv-usuarios-0.0.1-SNAPSHOT.jar"]