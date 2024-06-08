# Кинотеатр

## О проекте

**Проект представляет собой микросервисное web-приложение по покупке билетов в кинотеатр. Сервис состоит:**

* микросервиса, который получает фильмы из kinopoisk API и сохраняет фильмы в БД
* микросервиса, который отвечает за UI часть web-приложения
* микросервиса eureka-server для Spring Cloud окружения
* микросервиса api-gateway для единой точки входа всех REST сервисов

### UI приложения

UI приложения включает в себя следующие компоненты:

- **Главная страница**
  - Выводит общую информацию о ресурсе.
- **Кинотека (Пользователь)**
  - Содержит список всех фильмов, находящихся в кинопрокате.
- **Кинотека (Админ)**
  - Содержит список всех фильмов, находящихся в кинопрокате.
  - Кнопка админа для создания нового киносеанса
- **Список залов**
  - Позволяет ознакомиться с информацией о залах в кинотеатре.
- **Расписание**
  - Позволяет выбрать сеанс и связанный с ним фильм.
  - Переход на страницу покупки билета, где можно указать предпочтительный ряд и место для покупки билета или отказаться от покупки.
- **Страница регистрации и авторизации пользователя**
  - Позволяет пользователю зарегистрироваться или авторизоваться в системе.

## Инструменты

- **Java 20**
- **Spring Boot 3.3.0**
- **PostgreSQL 14**
- **Spring Security 6**
- **Spring Cloud**
- **Eureka**
- **Hibernate**
- **RestTemplate**
- **Docker**
- **Mapstruct**
- **HTML 5**
- **CSS**
- **Thymeleaf**
- **Bootstrap**
- **H2database**
- **Commons-dbcp2**
- **Slf4j**
- **Lombok**
- **Junit 5**
- **Mockito**
- **Liquibase**
- **Maven 3.8**
- **Git**

## Сборка и запуск<br>

### Docker:

- **В терминале (в корне проекта) запустить команду для выполнения сборки проекта**

``` shell 
./mvnw clean install
```

- **В терминале (в корне проекта) запустить команду для старта приложения в docker контейнерах**

``` shell 
docker-compose up
```

- **Перейти в браузере по ссылке**

``` shell 
http://localhost:8080/
```

### Если отсутствует Docker:

- **Создать БД PostgreSQL**

``` shell 
create database films;
create database cinema;
```

- **В терминале (в корне проекта) запустить команду для выполнения сборки проекта**

``` shell 
./mvnw clean install
```

- **Изменить параметры конфигурации БД под свои**

    **Пути к файлам для изменений:**  
    ***./film-service/src/main/resources/application.properties***  
    ***./cinema-main-service/src/main/resources/application.properties***<br></br>

    Заменяем знаки вопросов на свои параметры (username и password):
``` shell 
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:???}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:???}
```

- **В терминале (по пути ./film-service) запустить команду для старта приложения**

``` shell 
./mvnw spring-boot:run
```

- **В терминале (по пути ./cinema-main-service) запустить команду для старта приложения**

``` shell 
./mvnw spring-boot:run
```

- **Перейти в браузере по ссылке**

``` shell 
http://localhost:8080/
```

## Взаимодействие с приложением<br>

### Главная страница web-приложения:

![home_page](cinema-main-service/src/main/resources/static/application_pictures/home_page.png)

### Страница со всеми доступными фильмами и их описание (Пользователь):

- При нажатии на постер фильма будет или на кнопку "подробнее" будет выполнен переход страницу с подробным описанием фильма

![user_films](cinema-main-service/src/main/resources/static/application_pictures/user_films.png)

### Страница со всеми доступными фильмами и их описание (Админ):

- При нажатии на постер фильма будет или на кнопку "подробнее" будет выполнен переход страницу с подробным описанием фильма
- Админ имеет возможность создать новый киносеанс к фильму, кликнув на кнопку "создать киносеанс"

![admin_films](cinema-main-service/src/main/resources/static/application_pictures/admin_films.png)

### Модальное окно с возможностью добавления киносеанса (Админ):

![admin_create_film_session](cinema-main-service/src/main/resources/static/application_pictures/admin_create_film_session.png)


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

