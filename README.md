## Rest-api endpoints

Swagger документация - http://localhost:8181/api/swagger-ui/index.html


```
registration-controller
  POST /register - создать пользователя

login-controller
  POST /login - авторизоваться за пользователя
  
track-controller
  POST /track-upload - Загрузка аудиозаписи
  POST /tracks-upload - Загрузка аудиозаписей -
  GET /tracks/{id} - Получить аудиозапись по id
  
genre-controller
  POST /genre/genre-upload - Создание жанра
  GET /genre/{id} - Получить жанр по id

albums-controller
  POST /albums/album-upload - Создание альбома
  GET /albums/{id} - Получить альбом по id
  
artists-controller
  POST /artists/artist-create - Создание артиста
  GET /artists/{id}
```