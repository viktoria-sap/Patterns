package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {
    @Test
    void shouldRegisterForDelivery1() {
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(date.getTime());
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Волгоград");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(str);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79680000000");
        $("span[class='checkbox__text']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldRegisterForDelivery2() {
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR, 7);
        SimpleDateFormat format1 = new SimpleDateFormat("dd");
        String str = format1.format(date.getTime());

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Вол");
        $("div[class='popup popup_direction_bottom-left popup_target_anchor popup_size_m popup_visible popup_height_adaptive popup_theme_alfa-on-white input__popup']").find(byText("Волгоград")).click();
        $("span[class='input__icon']").click();
        $("table[class='calendar__layout']").find(byText(str)).click();
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79680000000");
        $("span[class='checkbox__text']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 15000);
    }
}