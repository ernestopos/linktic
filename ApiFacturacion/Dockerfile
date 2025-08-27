FROM openjdk:17-jdk-slim
ENV JAVA_OPTS=""
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ApiFacturacion.jar
EXPOSE 8082
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /ApiFacturacion.jar"]