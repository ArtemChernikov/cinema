# Кинотеатр

## О проекте

**Проект представляет собой микросервисное web-приложение по покупке билетов в кинотеатр. Сервис состоит:**

* сервиса, который получает фильмы из kinopoisk API и сохраняет фильмы в БД
* сервиса, который отвечает за UI часть web-приложения

### UI приложения

UI приложения включает в себя следующие компоненты:

- **Главная страница**
  - Выводит общую информацию о ресурсе.
- **Кинотека**
  - Содержит список всех фильмов, находящихся в кинопрокате.
- **Список залов**
  - Позволяет ознакомиться с информацией о залах в кинотеатре.
- **Расписание**
  - Позволяет выбрать сеанс и связанный с ним фильм.
  - Переход на страницу покупки билета, где можно указать предпочтительный ряд и место для покупки билета или отказаться от покупки.
- **Страница регистрации и авторизации пользователя**
  - Позволяет пользователю зарегистрироваться или авторизоваться в системе.

## Инструменты

- **Java 17**
- **Spring Boot 2.7.6**
- **PostgreSQL 14**
- **Spring Security 5**
- **Hibernate**
- **RestTemplate**
- **Mapstruct 1.5.5**
- **HTML 5**
- **CSS**
- **Thymeleaf 2.7.6**
- **Bootstrap 4.4.1**
- **H2database 1.4.2**
- **Commons-dbcp2 2.9.0**
- **Slf4j**
- **Lombok**
- **Junit 5**
- **Mockito 4.8.0**
- **Liquibase 4.15.0**
- **Maven 3.8**
- **Git**

## Сборка и запуск<br>

- **Создать БД**

``` shell 
create database films;
create database cinema;
```

- **Запустить проект по команде**

``` shell 
mvn spring-boot:run
```

- **Перейти в браузере по ссылке**

``` shell 
http://localhost:8080/
```

## Взаимодействие с приложением<br>

### Главная страница web-приложения:

![home_page](cinema-main-service/src/main/resources/static/application_pictures/home_page.png)

### Страница со всеми доступными фильмами и их описание:

- При нажатии на постер фильма будет выполнен переход страницу с подробным описанием фильма

![films](cinema-main-service/src/main/resources/static/application_pictures/films.png)

### Страница с подробным описанием фильма:

![film](cinema-main-service/src/main/resources/static/application_pictures/film.png)

### Страница с доступными киносеансами:

- При нажатии на "купить билет", будет выполнен переход на страницу с покупкой билета (покупка возможна только после
  авторизации пользователя).

![film_sessions](cinema-main-service/src/main/resources/static/application_pictures/film_sessions.png)

### Страница с кинозалами и их описание:

![halls](cinema-main-service/src/main/resources/static/application_pictures/halls.png)

### Страница с регистрацией нового пользователя:

![registration](cinema-main-service/src/main/resources/static/application_pictures/registration.png)

### Страница с авторизацией пользователя:

![login](cinema-main-service/src/main/resources/static/application_pictures/login.png)

### Страница с возможностью покупки билета:

- Покупка билета возможна только после авторизации пользователя!
- При выборе ряда или места отображается выпадающий список.
- Покупка билета успешна, только если место свободно. <br/>

![ticket_buy](cinema-main-service/src/main/resources/static/application_pictures/ticket_buy.png)

### Страница с успешной покупкой билета:

![ticket_buy_success](cinema-main-service/src/main/resources/static/application_pictures/ticket_buy_success.png)

### Страница с покупкой билета, если данное место уже занято:

![ticket_buy_failed](cinema-main-service/src/main/resources/static/application_pictures/ticket_buy_failed.png)

## Контакты для связи<br>

<a href="https://t.me/OvercomingJunk" target="blank"><img src="https://img.icons8.com/clouds/50/000000/telegram-app.png"/></a>

