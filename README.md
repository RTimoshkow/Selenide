[![Build status](https://ci.appveyor.com/api/projects/status/v3c3c6nwrk5gh1vm?svg=true)](https://ci.appveyor.com/project/RTimoshkow/selenide)

# Задача №1: заказ доставки карты
Вам необходимо автоматизировать тестирование формы заказа доставки карты:

![order](https://user-images.githubusercontent.com/89451067/197764084-0c3c81c9-8cfa-4a31-a7f4-eab030643056.png)


Требования к содержимому полей:

1. Город — один из административных центров субъектов РФ.
1. Дата — не ранее трёх дней с текущей даты.
1. В поле фамилии и имени разрешены только русские буквы, дефисы и пробелы.
1. В поле телефона — только 11 цифр, символ + на первом месте.
1. Флажок согласия должен быть выставлен.

Тестируемая функциональность: отправка формы.

Поля «Город» и «Дата» заполняются через прямой ввод значений без использования выбора из выпадающего списка и всплывающего календаря.

Условия: если все поля заполнены корректно, то форма переходит в состояние загрузки:

![loading](https://user-images.githubusercontent.com/89451067/197764305-4a6dce5d-2493-4a87-8967-f1ffa641071f.png)


Важно: состояние загрузки не должно длиться более 15 секунд.

После успешной отправки формы появится всплывающее окно об успешном завершении бронирования:

![popup](https://user-images.githubusercontent.com/89451067/197764378-aab2a487-b3a4-4a12-86ba-0e0c2d21cf3c.png)


Вам необходимо самостоятельно изучить элементы на странице, чтобы подобрать правильные селекторы. Обратите внимание, что элементы могут быть как скрыты, так и динамически добавляться или удаляться из DOM.

# Задача №2: взаимодействие с комплексными элементами (необязательная)
Большинство систем старается помогать пользователям ускорить выполнение операций: для этого предоставляются формы с автодополнением и элементы вроде календарей.

Проверьте отправку формы, используя следующие условия:

1. Ввод двух букв в поле «Город», после чего выбор нужного города из выпадающего списка:
![dropdown](https://user-images.githubusercontent.com/89451067/197764483-42bff429-9d37-4b15-80f0-2fca76b4e67e.png)


2. Выбор даты на неделю вперёд, начиная от текущей даты, через инструмент календаря:
![calendar](https://user-images.githubusercontent.com/89451067/197764570-605ded30-4275-4c20-97ee-805a42a7a8f3.png)


Важно: предлагаемая вам задача действительно сложная и потребует от вас достаточно много усилий для решения. Именно поэтому мы перенесли её в разряд необязательных.

P.S. Стоит отметить, что перед автоматизацией вы должны попробовать оценить стоимость автоматизации, в неё же входит и сложность. Но оценивать вы не научитесь, не попробовав автоматизировать.
