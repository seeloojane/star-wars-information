FROM eclipse-temurin:20.0.1_9-jre-alpine
MAINTAINER seeloojane@gmail.com

COPY build/libs/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]
