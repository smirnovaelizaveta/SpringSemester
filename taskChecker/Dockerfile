FROM maven:3.8.5-jdk-11-slim
COPY /target/springsemester-task-checker-1.0.jar  /app/app.jar
WORKDIR /app
CMD java -jar /app/app.jar