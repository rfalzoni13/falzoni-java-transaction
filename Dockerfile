FROM openjdk:17-alpine
LABEL authors="Renato Falzoni"
LABEL application="Falzoni Spring API de Transações"
LABEL version="1.0.0"
WORKDIR /app
COPY target/falzoni-java-transaction-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]