FROM openjdk:8
EXPOSE 9092
ADD target/auditbenchmark-docker.jar auditbenchmark-docker.jar
ENTRYPOINT ["java","-jar","auditbenchmark-docker.jar"]