FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-produto.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ms-produto.jar"]