FROM ubuntu:18.04
MAINTAINER g4dalcom <g4dalcom@gmail.com>

# --build-arg 옵션을 통해 넘길 수 있는 인자를 정의
ARG DEBIAN_FRONTEND=noninteractive

ENV TZ=Asia/Seoul

RUN apt-get update
RUN apt-get install -y apache2 # Install Apache web server (Only 'yes')
RUN apt-get update
RUN apt-get install -y openjdk-11-jdk
RUN apt-get install -y default-jdk

WORKDIR JPAshop
RUN ./gradlew build
ARG JAR_FILE=./bulid/libs/*.jar
RUN cp ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 80

CMD ["apachectl", "-D", "FOREGROUND"]

