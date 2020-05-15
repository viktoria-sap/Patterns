package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {

    private RegistrationByCardInfo registrationInfo;

    @BeforeEach
    void setUpAll() {
        registrationInfo = DataGenerator.Registration.generateByCard("ru");
    }

    @Test
    void shouldRegisterForDelivery1() {

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateCity());
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.Registration.generateDate5());
        $("[name='name']").setValue(registrationInfo.getName());
        $("[name='phone']").setValue(registrationInfo.getPhone());
        $("span[class='checkbox__text']").click();
        $$("button").find(exactText("Запланировать")).click();

        $("span[class='input__icon']").click();
        $("table[class='calendar__layout']").find(byText(DataGenerator.Registration.generateDate8())).click();
        $$("span.button__text").find(exactText("Запланировать")).click();
        $$("span.button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
    }

}