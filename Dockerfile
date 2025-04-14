# Imagem base com Java 21
FROM eclipse-temurin:21-jdk-alpine

# Argumentos para configuração
ARG JAR_FILE=target/*.jar
ARG PROFILE=prod

# Variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=${PROFILE}
ENV SERVER_PORT=8080

# Diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR
COPY ${JAR_FILE} app.jar

# Expor a porta
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
