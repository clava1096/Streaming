## Rest-api endpoints

- Включает: SpringBoot v3, Spring Data JPA, Spring Validation, Spring Security (используется JWT Token), Mapstruct, Lombok, Swagger, Minio s3

Swagger документация - http://localhost:8080/swagger-ui/index.html

```
registration-controller (ALL)
    POST /register - создать пользователя

login-controller (ALL)
    POST /login - авторизоваться за пользователя
  
track-controller
    POST /track - Загрузка метаданных аудиозаписи,
    в случае успешного выполнения возвращает ссылку для загрузки трека в хранилище S3 (ARTIST, ADMIN, MOD)
    PUT /{id}/upload - Загружает аудиофайл для существующего трека (ARTIST, ADMIN, MOD)
    GET /tracks/{id} - Получить аудиозапись по id (min USER)
    GET /{id}/stream-url - Получает временную ссылку для прослушивания трека (min USER)
  
genre-controller
    POST /genres - Создание жанра (ADMIN)
    GET /genres/{id} - Получить жанр по id (ADMIN)

albums-controller
    POST /albums - Создание альбома (ARTIST, ADMIN, MOD)
    GET /albums/{id} - Получить альбом по id (min USER)
  
artists-controller
    POST /artists - Создание артиста (ADMIN) 
    POST /artists/requests - Создает запрос на повышение пользователя до артиста,
    после верификации (USER)
    GET /artists/{id} - Получить артиста по id (USER)

mediatype-controller:
    POST /media-type/create - Создает медиа-тип (ARTIST)
    GET /media-type/{id} - Получить медиа-тип по id (min USER)

admin-panel:
    GET /admin/artist-requests - Возвращает список всех пользоваталей, которые запросили доступ стать артистом, а так же необходимые данные
    POST /admin/approve-artist/{id} - Повышает пользователей до роли артиста
    GET /admin/artists-to-delete - Возвращает список всех пользователей-артистов, которые запросили удаление
    DELETE /admin/artists/{id} - Удаляет артиста, все его треки и все записи в БД.
    DELETE /admin/tracks/{id} - Удаление трека. Запись в БД остается, но трек не доступен.
```