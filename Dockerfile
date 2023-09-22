FROM openjdk:11-jre-slim

RUN mkdir -p /usr/local/app

ENV DATABASE_URI
ENV DBUSER
ENV DBPASSWORD

WORKDIR /usr/local/app

COPY target/*.jar bin.jar

CMD [ "java", "-jar", "/usr/local/app/bin.jar" ]
