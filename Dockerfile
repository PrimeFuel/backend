# ---- Build stage ----
FROM eclipse-temurin:26-jdk AS build
WORKDIR /app

# Maven wrapper + pom primero para aprovechar la cache de capas
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Codigo y empaquetado (sin tests para acelerar el deploy)
COPY src ./src
RUN ./mvnw -B clean package -DskipTests

# ---- Runtime stage ----
FROM eclipse-temurin:26-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Render inyecta el puerto via $PORT; Spring lo lee con server.port=${PORT:8080}
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
