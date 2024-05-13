FROM openjdk:8
EXPOSE 8081
ARG JAR_FILE=calculate-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]