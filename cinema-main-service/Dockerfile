FROM amazoncorretto:20
COPY target/*.jar app.jar
COPY src/main/resources/static/halls static/halls
ENTRYPOINT ["java", "-jar", "/app.jar"]