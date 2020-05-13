package ru.netology.web;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

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
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(date.getTime());

        Calendar date1 = new GregorianCalendar();
        date1.add(Calendar.DAY_OF_YEAR, 8);
        SimpleDateFormat format2 = new SimpleDateFormat("dd");
        String str1 = format2.format(date1.getTime());

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(registrationInfo.getCity());
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(str);
        $("[name='name']").setValue(registrationInfo.getName());
        $("[name='phone']").setValue(registrationInfo.getPhone());
        $("span[class='checkbox__text']").click();
        $$("button").find(exactText("Запланировать")).click();

        $("span[class='input__icon']").click();
        $("table[class='calendar__layout']").find(byText(str1)).click();
        $$("span.button__text").find(exactText("Запланировать")).click();
        $$("span.button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
    }

}