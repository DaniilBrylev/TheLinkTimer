# The Link Tamer

Сервис для сокращения ссылок на Java с использованием Spring Boot.

## Как пользоваться сервисом

1. Запустите проект через вашу IDE (например, IntelliJ IDEA).
2. Сервис будет доступен по адресу: `http://localhost:8080`.
3. 

### Поддерживаемые команды

#### 1. Сокращение ссылки
**URL:** `POST http://localhost:8080/api/urls/shorten`  
**Тело запроса (JSON):**
```json
{
  "originalUrl": "https://www.avito.ru/",
  "visitLimit": 5,
  "hoursToLive": 24,
  "userUuid": "test-user-uuid"
}

##### Пример команды с Curl

curl -X POST "http://localhost:8080/api/urls/shorten" \
-H "Content-Type: application/json" \
-d "{\"originalUrl\":\"https://example.com\", \"visitLimit\":5, \"hoursToLive\":24, \"userUuid\":\"test-user-uuid\"}"

##Но если будете запускать  на терминале windows вставляйте вот эту ссылку, так как нужно чтобы она была в одну строку и без слэшей
curl -X POST "http://localhost:8080/api/urls/shorten" -H "Content-Type: application/json" -d "{\"originalUrl\":\"https://www.avito.ru\", \"visitLimit\":5, \"hoursToLive\":24, \"userUuid\":\"test-user-uuid\"}"
###### Переход по сокращенной ссылке
URL: GET http://localhost:8080/api/urls/{shortUrl}
                            
                            #####Как протестировать код?

#####Установите зависимости с помощью Maven:
#####Запустите приложение
#####Используйте Postman или curl для тестирования API.
#####Установите внешние библиотеки
#####Проект использует следующие библиотеки:
#####Spring Boot
#####Spring Data JPA
#####H2 Database
#####Убедитесь, что все зависимости указаны в pom.xml. 
#####Maven автоматически установит их.