FROM openjdk:17-jdk

WORKDIR /app

COPY target/urlshorterner-0.0.1-SNAPSHOT.jar /app/urlshortener.jar

EXPOSE 8080

CMD ["java", "-jar", "urlshortener.jar"]

LABEL maintainer="phucnc2003@gmail.com"