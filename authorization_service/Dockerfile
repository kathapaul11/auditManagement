FROM openjdk:8
EXPOSE 8080
ADD target/authorization-docker.jar authorization-docker.jar
ENTRYPOINT ["java","-jar","authorization-docker.jar"]