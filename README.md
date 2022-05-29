## REST-сервис для хранения данных о городских достопримечательностей.
Использованы технологии: 
- Java 8
- Spring Boot
- Hibernate
- PostgreSQL
- Liquibase
- Maven

Реализовано:
- REST API приложения
- сохранение в базу данных
- обработка некорректных запросов
- тесты для контроллеров
- инициализация и популяция базы при запуске приложения

## Инструкция по запуску
- Склонировать репозиторий git clone https://github.com/Nexus2025/city_attractions.git
- Создать пустую базу данных с названием "city_attractions"
- В /resources/application.properties прописать данные для подключения к базе данных
- Из корневой папки "city_attractions" выполнить команду: mvn spring-boot:run
- При запуске приложения, liquibase создаст таблицы и заполнит их начальными объектами
- Приложение доступно по адресу http://localhost:8080

## Документация по API сервиса
- **POST /rest/cities**  
(Добавляет город)   
*Пример тела запроса:*

        {
            "name":"Barcelona",
            "population":1600000,
            "subwayAvailability":true,
            "country":"Spain"
        }


- **PUT /rest/cities/{id}**  
(Обновляет город. Возможно обновлять только поля: численность населения, наличие метро)   
*Пример тела запроса:* 

        {
            "population": 15500,
            "subwayAvailability": false
        }

- **GET /rest/cities/{cityId}/attractions**  
(Возвращает все достопримечательности конкретного города)

- **GET /rest/attractions?orderByName=true&type=BUILDING**  
(Возвращает все достопримечательности. Опционально можно передать параметр для сортировки по наименованию достопримечательности или параметр для фильтрации по типу достопримечательности)

- **POST /rest/attractions**  
(Добавляет достопримечательность)  
*Пример тела запроса:*  

        {
            "name":"Empire State Building",
            "constructionDate":"1930-03-17",
            "description":"The Empire State Building is a 102-story Art Deco skyscraper in Midtown Manhattan, New York City.",
            "type":"BUILDING",
            "cityId":1
        }

- **PUT /rest/attractions/{id}**  
(Измененяет достопримечательность. Возможно изменять только поле краткое описание)  
*Пример тела запроса:*

        {
            "description": "This is a new description"
        }

- **DELETE /rest/attractions/{id}**  
(Удаляет достопримечательность из справочника)