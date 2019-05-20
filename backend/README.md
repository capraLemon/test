## Запуск приложения и базы данных
```bash
# запуск базы данных postgresql в докер контейнере
docker-compose -f docker-compose.dev.yml up -d
cd backend
mvn clean install exec:java
```
При запуске postgresql в лексикографическом порядке выполняются все скрипты,
указанные в директории backend/src/main/resources/db .

## Работа с тестовым приложением todo
Приложение слушает порт 9999, можно подергать todo api запросами:<br> 
GET localhost:9999/api/todo/list - получить список всех todoшек<br>
POST localhost:9999/api/todo/create + в теле запроса json {"title" : "some stuff" } - добавить todo<br>
DEL localhosqt:9999/api/todo/{id} - удалить todo с #id<br>
PUT localhost:9999/api/todo/{id} + в теле запроса {"title": "other stuff", "completed": true} - изменить todo<br>  
 