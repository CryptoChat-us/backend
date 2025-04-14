# Build stage
FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /workspace/app

# Copiar o projeto
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Dar permissão de execução ao mvnw
RUN chmod +x ./mvnw

# Build do projeto
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copiar o JAR do estágio de build
COPY --from=build /workspace/app/target/*.jar app.jar

# Variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8080

# Expor a porta
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
