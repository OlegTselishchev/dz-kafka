### HttpLoggerStarter - Spring Boot Starter для логирования HTTP запросов

### Применение:

Система мониторинга отслеживает работу приложения (cpu, memory) и сохраняет данные в БД.

KafkaProducerService - запускается по расписанию, собирает занные и отправляет в кафку в metrics-topic

отправка метрик осуществляется по расписанию раз в минуту.

для получения данных о приложении исп. spring-actuator

для доступа к данным исп. FiegnClient

KafkaConsumerService - получает данные из топика, сохраняет их в бд

контроллер:

POST http://localhost:8081/v1/rest/send - возвращает пример метрик отправленный в кафку

GET http://localhost:8081/v1/rest/metrics - данные о всех метриках 

GET http://localhost:8081/v1/rest/metric/{id} - данные о метрике по id

### 🏄 Стек:
Java 21, SpringBoot 3, Maven, Hibernate, Kafka, Actuator, Openfeign, БД PostgreSQL.

