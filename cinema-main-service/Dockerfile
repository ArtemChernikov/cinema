FROM amazoncorretto:17
COPY target/*.jar app.jar
COPY src/main/resources/static/halls cinema-main-service/src/main/resources/static/halls
ENTRYPOINT ["java", "-jar", "/app.jar"]