package ru.netology.courseProject.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage extends StartPage {
    SelenideElement heading = $x("//*[text()='Оплата по карте']");

    public void isExist() {
        heading.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}
