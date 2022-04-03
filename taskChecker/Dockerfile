FROM openjdk:11-jre-slim
COPY /target/springsemester-task-checker-1.0.jar  /app/app.jar
COPY ComponentAnnotation  /app/resources/ComponentAnnotation
WORKDIR /app
CMD java -jar /app/app.jar