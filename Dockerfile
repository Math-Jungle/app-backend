# --- Stage 1: Build the JAR ---
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy Maven wrapper and POM
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

# Copy source and build
COPY src/ src/
RUN ./mvnw clean package -DskipTests

# --- Stage 2: Run the JAR ---
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
