package ru.netology;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenidTestV1 {
    LocalDateTime currentData = LocalDateTime.now();
    LocalDateTime newData = LocalDateTime.now().plusDays(3);
    DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd MM yyyy");

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").sendKeys(Keys.CONTROL,"a" + Keys.DELETE);
    }

    @Test
    void shouldValidValues() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"notification\"] [class=\"notification__title\"]").shouldBe(text("Успешно!"), Duration.ofSeconds(15));
    }

    @Test
    void shouldInvalidValueOfTheCity() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Таганрог");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"city\"].input_invalid [class=\"input__sub\"]").shouldHave(ownText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldInvalidTimeValue() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(currentData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"date\"] .input_invalid [class=\"input__sub\"]").shouldHave(ownText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldInvalidNameValue() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Vasia Pupkin");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"name\"].input_invalid [class=\"input__sub\"]").shouldBe(ownText("Имя и Фамилия указаные неверно."));
    }

    @Test
    void shouldInvalidPhoneValue() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"phone\"].input_invalid [class=\"input__sub\"]").shouldBe(ownText("Телефон указан неверно."));
    }

    @Test
    void shouldUncheckedCheckBox() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $(".button").click();
        $(" [data-test-id=\"agreement\"].input_invalid [class=\"checkbox__text\"]").shouldHave(ownText("Я соглашаюсь с условиями обработки"));
    }

    @Test
    void shouldDropdownListOfCities() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ро");
        ElementsCollection listOfCities = $$("[class='popup__container'] span");
        listOfCities.findBy(text("Ростов-на-Дону")).click();
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"notification\"] [class=\"notification__title\"]").shouldBe(text("Успешно!"), Duration.ofSeconds(15));
    }

    @Test
    void shouldDropDownCalendar() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").click();
        ElementsCollection dates = $$("[class='popup__container'] [data-day]");
        dates.get(6).click();
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"notification\"] [class=\"notification__title\"]").shouldBe(text("Успешно!"), Duration.ofSeconds(15));
    }

    @Test
    void shouldEmptyCityField() {
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"city\"].input_invalid [class=\"input__sub\"]").shouldHave(ownText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyDateField() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"date\"] .input_invalid [class=\"input__sub\"]").shouldHave(ownText("Неверно введена дата"));
    }

    @Test
    void shouldEmptyNameField() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+12345678901");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"name\"].input_invalid [class=\"input__sub\"]").shouldBe(ownText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyPhoneField() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Ростов-на-Дону");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(formatData.format(newData));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Вяся Пупкин");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"phone\"].input_invalid [class=\"input__sub\"]").shouldBe(ownText("Поле обязательно для заполнения"));
    }
}
