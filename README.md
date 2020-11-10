# Real estate price notification and collection of current offers. 
The service will allow users to receive notifications about the appearance of new ads from popular real estate portals by parameters.

## Main functional: 
* Monitoring of several major real estate portals;
* Parse ads and add to database;
* Other additional features;
* Quick search new ads;
* Notifications;
* Search for duplicate ads and sort by a good price.

## Project schema:

<img src="https://github.com/3dartix/notification/blob/master/pic/scheme.jpg" alt="project schema"/>

## Настройки для запуска
Для запуска парсера cian.ru необходимо накатить 2 служебные таблицы с кодами городов
`psql -U <Логин_в_постгрес> <имя_базы> < C:\Users\Atrem\Desktop\del\10\cian_service_tables\cian_service_tables.dump`
[Ссылка на dump таблиц](https://drive.google.com/file/d/1P4nj3DT3R6SU4NosVGglkYrQngoxC_mx/view?usp=sharing)

## Project description:

#### Как это работает. 
 * Пользователь регистрируется на сервисе.
 * Настраивает удобные параметры для уведомлений и задает сервису необходимые настройки поиска
 * Получает уведомления.

> Пример UI интерфейса `Settings`

<img src="https://github.com/3dartix/notification/blob/master/pic/settings.jpg" alt="settings" />



### Common interface of parse services 
HashMap<`Address`, `List<Ad>`> findAdsByFilter (`Filter` filter) – find all ads by filter.
`Filter` -> `Address`, `Number of rooms`, `Quadrature`, `Lease period`, `PriceFrom`, `PriceTo` 
Ресурсы для парсинга: [cian.ru](cian.ru) и [avito.ru](avito.ru)

### Этапы проекта.
1. #### Создание модулей-парсеров для ресурсов cian.ru и avito.ru
    1. Aнализ html кода сайта cian.ru. Настройка парсера и написание бизнес кода для конвертации html кода в список сущностей с учетом параметров фильтра.
    2. Анализ html кода сайта avito.ru. Настройка парсера и написание бизнес кода для конвертации html кода в список сущностей с учетом параметров фильтра.
    3. Написание общего интерфейса для взаимодействия с любым из модулей-парсеров.
2. #### Создание модуля database (`hibernate`)
    1. Написание скрипта для формирования таблиц и связей в базе данных для хранения объявлений, пользователей и их настроек. (`liquibase` или `flyway`)
    2. Создание сущностей и настройка их связей для работы с базой данных (`hibernate`).
    3. Создание интерфейса JPA репозитория для работы с базой данных.
3. #### Создание сервиса очистки неактуальных объявлений.
    1. Написание кода, который будет запускаться по времени и вычищать базу данных от устаревших объявлений.
4. #### Создание сервиса «контроллер» – это rest сервис, который будет взаимодействовать с базой данных и отвечать на запросы пользователей.
    1. настройка аутентификации через токины (`spring security`)
    2. создание контроллеров
       1. Контроллер для запросов пользователей. Контроллер должен получить запрос, обработать его (превратить в класс `Filter`) и передать его парсерам. Когда парсеры выполнили задачу Создание сервисного класса для обработки бизнес логики.
       2. Контроллер для аутентификации пользователей. Создание сервисного класса для обработки бизнес логики.
       3. Контроллер для регистрации пользователей. Создание сервисного класса для обработки бизнес логики.
5. #### Сервис уведомлений. Будет получать объявления от сервера и отправлять клиентам уведомления (в мессенджеры / на почту).  
6. #### Разработка web интерфейса для регистрации пользователей, настройки профиля, поиска объявлений.
    1. Создание html шаблона.
       1. главная страница с описанием сервиса
       2. страница с регистрацией
       3. страница с профилем и различными настройками.
    2. создание контроллеров
       1. User контроллер для регистрации пользователей и их аутентификации. Настройка связей с html (thymeleaf)
       2. Контроллер для работы с профилем и настройками пользователей. Сервисный класс для создания соответствующей бизнес логики и связи с репозиторием. Настройка связей с html (`thymeleaf`)

### Tests.
Автоматическое тестирование приложения будет осуществляться с помощью интеграционных и модульных тестов, которые покроют 70% кода. Библиотеки `JUnit`, `Spring Test` & `Spring Boot Test`, `Mockito`. 
В тестирование UI части приложения будет использоваться библиотеки (`Selenium` и `Cucumber`). 
База данных – `H2`. 

### Notification service.
У сервиса нотификаций есть контроллер и различные модули, каждый из которых будет отправлять сообщения в мессенджеры (вотсап, телеграм, фейсбук) и на почту.
У всех этих модулей есть общий интерфейс, который будет ожидать персональную информацию для отправки объявлений пользователю.
void sendMessage (`PersonalData` data, `List<Ad>`) – отправить результат пользователю, где 
PersonalData – информация с персональными данными, например, телефон пользователя или емаил. 

### Детали хостинга.
Приложение будет развернуто на бесплатном сервере heroku. (https://www.heroku.com/java ) База данных mysql.

### Database description:

<table>   
   <tr>
    <th width ="10%">name entity</th>
    <th width ="10%">Fields</th>
    <th width ="30%" >Description</th>
   </tr>
   <tr>
     <td> User</td>
     <td>Id <br>Login <br>Password <br>Name <br>Surname <br>Settings <br>List[Role]</td>
     <td></td>
   </tr>
   <tr>
     <td> Settings</td>
     <td>Id <br>Personal data</td>
     <td>Personal data for supported messengers</td>
   </tr>
   <tr>
     <td> Personal data</td>
     <td>Id <br>WhatsApp <br>Facebook <br>Telegram <br>Email</td>
     <td></td>
   </tr>
<tr>
     <td> Country</td>
     <td>Id <br>name <br>list[region]</td>
     <td>Country</td>
   </tr>
   <tr>
     <td> Region</td>
     <td>Id <br>country <br>list[city]</td>
     <td>Region of country.</td>
   </tr>
   <tr>
     <td> City</td>
     <td>Id <br>name <br>country <br>region <br>list[District] </td>
     <td>City should always have a country but not always have a region, in this case you should to use pre-created an empty region.</td>
   </tr>
   <tr>
     <td> District</td>
     <td>id <br>name <br>city <br>list[street] </td>
     <td>District of city.</td>
   </tr>
   <tr>
     <td> Street</td>
     <td>id <br>name <br>district <br>list[group ad] <br>list[ad] </td>
     <td>In list[object] will be contains ads with the same address.<br>In list[ad] will be contains ads with a unique address.</td>
   </tr>
<tr>
     <td> Address</td>
     <td>Id <br>country <br>region <br>city <br>district <br>street</td>
     <td>unifying class</td>
   </tr>
<tr>
     <td> Group ad</td>
     <td>Id <br>Address <br>District <br>List[ad]</td>
     <td>if there are many proposals in one building, they will be in one group.</td>
   </tr>
   <tr>
     <td> Ad</td>
     <td>Id <br>Address <br>Quantity room <br>Quadrature <br>Lease period <br>Price <br>Details price <br>Title <br>Description <br>Link to resource </td>
     <td>Publication date – when the ad was added to database. <br>Obsolete ad and not found again must be removed </td>
   </tr>
  </table>

> Because fields of address entities cannot be null it must be filled with pre-created empty objects.