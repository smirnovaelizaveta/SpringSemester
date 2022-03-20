FROM openjdk:11-jre-slim
COPY /target/springSemester-1.0.jar /app/app.jar
EXPOSE 8080
WORKDIR /app
ku
CMD java -jar target/springSemester-1.0.jar