# Кинотеатр

## О проекте

**Проект представляет собой web-приложение по покупке билетов в кинотеатр. Сервис состоит из:**

* главной страницы, которая выводит общую информацию о ресурсе;
* кинотеки, которая содержит список всех фильмов, находящихся в кинопрокате;
* списка залов, где можно ознакомиться с информации о залах в кинотеатре;
* расписания, которое позволяет выбрать сеанс и связанный с ним фильм. Затем перейти на страницу покупки билета, где,
  указав предпочтительный ряд и место, приобрести билет, либо отказаться от покупки;
* страницы регистрации и авторизации пользователя.

## Инструменты

- **Java 17**
- **Spring Boot 2.7.6**
- **PostgreSQL 14**
- **Spring Security 5**
- **Hibernate**
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
create database cinema;
```

- **Запустить проект по команде**

``` shell 
mvn spring-boot:run -Pproduction
```

- **Перейти в браузере по ссылке**

``` shell 
http://localhost:8080/
```

## Взаимодействие с приложением<br>

### Главная страница web-приложения:

![home_page](cinema-main-service/src/main/resources/static/application_pictures/home_page.png)

### Страница с доступными киносеансами:

- При нажатии на название фильма, будет выполнен переход к списку фильмов для ознакомления с описанием фильма.
- При нажатии на название кинозала, будет выполнен преход к списку кинозалов.
- При нажатии на "купить билет", будет выполнен переход на страницу с покупкой билета (покупка возможна только после
  авторизации пользователя).

![film_sessions](cinema-main-service/src/main/resources/static/application_pictures/film_sessions.png)

### Страница со всеми доступными фильмами и их описание:

![films](cinema-main-service/src/main/resources/static/application_pictures/films.png)

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

