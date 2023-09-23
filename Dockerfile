FROM openjdk:11-jre-slim

RUN mkdir -p /usr/local/app

WORKDIR /usr/local/app

COPY target/*.jar bin.jar

CMD [ "java", "-jar", "/usr/local/app/bin.jar" ]