FROM openjdk:11-jre-slim
COPY /target/springSemester-1.0.jar /app/app.jar
EXPOSE 8080
WORKDIR /app

CMD java -jar target/springSemester-1.0.jar