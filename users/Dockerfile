FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Solo copiamos el JAR que YA existe en tu carpeta target
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx512M", "-jar", "app.jar"]