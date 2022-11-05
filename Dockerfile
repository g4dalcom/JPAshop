FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=./bulid/libs/jpashop-0.0.1-SNAPSHOT.jar
COPY $(JAR_FILE) app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

