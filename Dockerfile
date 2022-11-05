FROM openjdk:11
EXPOSE 8081
ARG JAR_FILE=build/libs/jpashop-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
VOLUME /tmp
ENTRYPOINT ["java", "-jar", "/app.jar"]
