FROM openjdk:11
ADD target/UserWebService-0.0.1-SNAPSHOT.jar UserWebService-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","UserWebService-0.0.1-SNAPSHOT.jar"]