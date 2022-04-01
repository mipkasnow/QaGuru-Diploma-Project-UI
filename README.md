# Проект по автоматизации тестирования сайта электронного магазина ОАО "РЖД"
<a target="_blank" href="https://eshoprzd.ru/">Сайт проекта</a>

## :world_map: Содержание
- [Технологии и инструменты](#earth_africa-технологии-и-инструменты)
- [Примеры UI тестов](#pager-Примеры-UI-тестов)
- [Примеры API тестов](#scroll-Примеры-API-тестов)
- [Сборка в Jenkins с параметрами](#-Сборка-в-Jenkins-с-параметрами)
- [Allure отчет](#-Allure-отчет)
- [Отчет в Telegram с помощью бота](#-Отчет-в-Telegram-с-помощью-бота)
- [Видео прохождения тестов на Selenoid](#film_projector-Видео-прохождения-тестов-на-Selenoid)

## :earth_africa: Технологии и инструменты
<p>
<a href="https://www.jetbrains.com/idea/"><img src="images/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA" title="IntelliJ IDEA"/></a>
<a href="https://www.java.com/"><img src="images/Java.svg" width="50" height="50"  alt="Java" title="Java"/></a>
<a href="https://github.com/"><img src="images/Github.svg" width="50" height="50"  alt="Github" title="GitHub"/></a>
<a href="https://junit.org/junit5/"><img src="images/JUnit5.svg" width="50" height="50"  alt="JUnit 5" title="JUnit5"/></a>
<a href="https://gradle.org/"><img src="images/Gradle.svg" width="50" height="50"  alt="Gradle" title="Gradle"/></a>
<a href="https://selenide.org/"><img src="images/Selenide.svg" width="50" height="50"  alt="Selenide" title="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="images/Selenoid.svg" width="50" height="50"  alt="Selenoid" title="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/Allure_Report.svg" width="50" height="50"  alt="Allure" title="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images/Jenkins.svg" width="50" height="50"  alt="Jenkins" title="Jenkins"/></a>
<a href="https://rest-assured.io/"><img src="images/Rest-Assured.svg" width="50" height="50"  alt="Rest-Assured" title="Rest-Assured"/></a>
</p>

В данном проекте автотесты написаны на <code>Java</code> с использованием <code>Selenide</code> для UI-тестов и <code>Rest Assured</code> для API тестов
>
> <code>Selenoid</code> выполняет запуск браузеров в контейнерах <code>Docker</code>.
>
> <code>Allure Report</code> формирует отчет о запуске тестов.
>
> Для автоматизированной сборки проекта используется <code>Gradle</code>.
>
> В качестве библиотеки для модульного тестирования используется <code>JUnit 5</code>.
>
> <code>Jenkins</code> выполняет запуск тестов.
> После завершения прогона отправляются уведомления с помощью бота в <code>Telegram</code>.


## :pager: Примеры UI тестов
- Проверка работоспособности ссылок футера страницы
- Проверка авторизации на сайте
- Проверка поиска сущностей на сайте
- Проверка работы пагинации
- Проверка работоспособности капчи

## :scroll: Примеры API тестов
- Проверка поиска сущностей и разбор тела ответа

## <img src="images/Jenkins.svg" width="25" height="25"  alt="Jenkins" title="Jenkins"/></a> Сборка в Jenkins с параметрами
>
> В сборке присутствуют настраиваемые параметры.
>
> Например размер запускаемого браузера или тип браузера. Сами тесты запускаются удаленно с помощью <code>Selenoid</code>
<p align="center">
<img title="Сборка в Jenkins с параметрами" src="images/Jenkins.PNG">
</p>

## <img src="images/Allure_Report.svg" width="25" height="25"  alt="Allure_Report" title="Allure_Report" title="Allure_Report"/></a> Allure отчет
>
> Allure формирует подробный отчет о прогоне тестов. Кастомные фильтры и листенеры делают отчет максимально понятным
>
> Например в отчет пишутся все селекторы и методы <code>Selenide</code>, отчеты формируются по категориям, в конце приложен скриншот, видео запись прогона теста и логи
Для API тестов полностью указаны данные запроса/ответа
<p align="center">
<img title="Allure отчет" src="images/Allure.PNG">
</p>
<p align="center">
<img title="Allure отчет" src="images/Allure2.PNG">
</p>

## <img width="4%" title="Telegram" src="images/Telegram.svg"> Отчет в Telegram с помощью бота
<p>
<img title="Отчет в Telegram с помощью бота" src="images/Telegram.PNG">
</p>

## :film_projector: Видео прохождения тестов на Selenoid
<p>
<img title="Selenoid Video" src="images/Selenoid.gif" alt="video">
</p>

<h2>
  :crystal_ball: Всю сборку можно запустить самостоятельно https://jenkins.autotests.cloud/job/mipkasnow-QaGuru-Diploma-Project-UI/ (необходимо предварительно  зарегистрироваться)
  Отчет в телеграм придет в чат https://t.me/+z08EK641X7dmNjZi
</h2>
