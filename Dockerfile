FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY pom.xml .

COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]


