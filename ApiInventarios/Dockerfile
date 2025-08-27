FROM openjdk:17-jdk-slim
ENV JAVA_OPTS=""
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ApiInventarios.jar
EXPOSE 8081
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /ApiInventarios.jar"]