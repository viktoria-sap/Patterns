package ru.netology.web;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {

    private RegistrationByCardInfo registrationInfo;

    @BeforeAll
    static void setUpBeforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

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

        $("input[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").sendKeys(DataGenerator.Registration.generateDate8());
        $$("span.button__text").find(exactText("Запланировать")).click();
        $$("span.button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
    }

}