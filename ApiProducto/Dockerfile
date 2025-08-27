# Usamos la imagen oficial de Java
FROM openjdk:17-jdk-slim

# Variable de entorno para la JVM
ENV JAVA_OPTS=""

# Copiamos el jar compilado al contenedor
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ApiProducto.jar

# Puerto expuesto
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /ApiProducto.jar"]