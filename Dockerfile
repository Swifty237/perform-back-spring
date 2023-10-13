FROM openjdk:11-jre-slim

EXPOSE 5000

ENV DBURI ""
ENV DBNAME ""
ENV DBUSER ""
ENV DBPASS ""
ENV DBPORT ""

RUN mkdir -p /usr/local/app

WORKDIR /usr/local/app

COPY target/*.jar bin.jar

CMD [ "java", "-jar", "/usr/local/app/bin.jar" ]