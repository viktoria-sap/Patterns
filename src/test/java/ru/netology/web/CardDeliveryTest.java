package ru.netology.web;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
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

    private Faker faker;

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
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

        String cityName = faker.address().cityName();
        String name = faker.name().fullName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(cityName);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(str);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phoneNumber);
        $("span[class='checkbox__text']").click();
        $$("button").find(exactText("Запланировать")).click();

        $("span[class='input__icon']").click();
        $("table[class='calendar__layout']").find(byText(str1)).click();
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Перепланировать")).click();
//        $$("[data-test-id='replan-notification']").find(exactText("Перепланировать")).click();
        $(byText("Успешно")).shouldBe(visible);
    }

}