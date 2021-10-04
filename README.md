# Andersen. Итоговый проект.

### Приложение "Films and Serials".

ТЗ на проект можно посмотреть [здесть](/files/ТЗ_Курсовой_проект_Ивакаев.docx).

Процесс разработки фиксировался в Jira, в каждой задаче имеется ссылка на PullRequest - https://ivakaevsemyon-andersenproject.atlassian.net/jira/software/projects/FS/boards/1 .

### Стек технологий:

 - Single Activity;
   
 - MVVM;
   
 - Retrofit;
   
 - Coroutine;
   
 - Парсинг JSON с помощью GSON. Первоначально хотелось использовать Moshi, но оно редко используется в ком.проектах;
   
 - Glide;
   
 - Room.

### Краткое описание:

Приложение для отображения самых популярных и самых рейтинговых фильмов и сериалов. А так же поиск фильмов и сериалов.

И хранение "Понравившихся" фильмов в базе данных.

[Основное api](https://developers.themoviedb.org/)

 - Стартовый экран содержит 2 кнопки для навигации к Фильмам или Сериалам. А так же навигацию по кнопке "Назад", которая
работает аналогично системной кнопке "Back".
   
 - Поиск фильмов и сериалов.

 - Отображение топ 20 популярных/лучших по рейтингу фильмов и сериалов.

 - На экране с детальной информацией о фильме, а так же возможность "Лайкнуть" фильм.

 - Хранение и отображение фильмов с меткой "Понравившиеся" в базе данных.
   



