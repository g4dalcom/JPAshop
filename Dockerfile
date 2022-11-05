<<<<<<< HEAD
FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=./bulid/libs/jpashop-0.0.1-SNAPSHOT.jar
COPY $(JAR_FILE) app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

=======
FROM openjdk:11-jre-slim
EXPOSE 8080
ARG JAR_FILE=./build/libs/jpashop-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
>>>>>>> 411a34e4c3fb3071d7fc592682b6bb6df5cc7435
