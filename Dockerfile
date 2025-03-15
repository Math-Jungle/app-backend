# ----------------------------
# Stage 1: Build
# ----------------------------
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /opt/app

# Copy the Maven wrapper, POM, and Maven settings to cache dependencies
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Pre-download all Maven dependencies
RUN ./mvnw dependency:go-offline

# Now copy the source code
COPY src/ src/

# Build the application (use 'package' or 'install' as needed)
RUN ./mvnw clean package -DskipTests

# ----------------------------
# Stage 2: Runtime
# ----------------------------
# If eclipse-temurin:21-jre-jammy doesn't exist or you prefer a single JDK-based image,
# you can also use '21-jdk-jammy'.
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /opt/app

# Expose port 8080 (Spring Boot's default)
EXPOSE 8080

# Copy the final JAR from the builder stage
COPY --from=builder /opt/app/target/*.jar app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
