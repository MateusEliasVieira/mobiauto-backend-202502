# Usa a imagem oficial do OpenJDK 23 (ou troque para uma versão compatível)
FROM openjdk:23-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR para dentro do container
COPY target/MobiautoAPI-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta do aplicativo
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
