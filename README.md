# CI
[![Build status](https://ci.appveyor.com/api/projects/status/5rbxrxeow428by9d?svg=true)](https://ci.appveyor.com/project/EugenyVinogradov/courseprojectautomatisation)

#   ПРЕДВАРИТЕЛЬНЫЕ УСЛОВИЯ ЗАПУСКА АВТОТЕСТОВ
1.  Установить окружение:
    * Git
    * Docker Desktop
    * IDE IntelliJ IDEA
    * Google Chrome
1.  Клонировать [репозиторий](https://github.com/EugenyVinogradov/CourseProjectAutomatisation) в папку проекта на локальный компьютер
1.  Запустить Docker Desktop
#   ЗАПУСК АВТОТЕСТОВ
1.  Открыть папку проекта в IDE IntelliJ IDEA
1.  Поднять docker-контейнер MySQL командой в терминале *docker-compose up -d*
1.  Запустить SUT командой в терминале *java -jar artifacts/aqa-shop.jar*
1.  Открыть новый терминал и запустить автотесты командой *gradlew clean test allureReport -Dheadless=true* (*./gradlew clean test allureReport -Dheadless=true* для Windows), где *allureReport* - подготовка данных для отчета Allure
#   CОЗДАНИЕ ОТЧЕТОВ
* Создать отчет Allure и автоматически открыть его в браузере, выполнив команду в терминале *gradlew allureServe* (*./gradlew allureServe* для Windows)
#   ДОКУМЕНТАЦИЯ
* [План автоматизации](https://github.com/EugenyVinogradov/CourseProjectAutomatisation/blob/c7a208c570935cb098b3c667422883248d341d46/Plan.md)
* [Отчет по тестированию](https://github.com/EugenyVinogradov/CourseProjectAutomatisation/blob/main/Report.md)
* [Отчет по автоматизации](https://github.com/EugenyVinogradov/CourseProjectAutomatisation/blob/main/Summary.md)