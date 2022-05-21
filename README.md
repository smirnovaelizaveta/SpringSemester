# Порядок запуска приложения
1. Для работы требуются запущенные Kafka и MySQL, параметры подключения к ним прописываются в файлах:
```
# backend/src/main/resources/application.properties
spring.kafka.consumer.bootstrap-servers=localhost:29092
spring.kafka.consumer.group-id=group_id
spring.datasource.url=jdbc:mysql://localhost:3306/springsemester
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name =com.mysql.jdbc.Driver

# taskChecker/src/main/resources/application.properties

spring.kafka.consumer.bootstrap-servers=localhost:29092
spring.kafka.consumer.group-id=group_id
```
2. Запустить два Java-приложения: Backend (`/backend`) и TaskChecker (`/taskChecker`). Каждое собирается отдельно с помощью Maven.
3. Фронтенд запускается в `npm`:
```
cd webapp
npm start
```
