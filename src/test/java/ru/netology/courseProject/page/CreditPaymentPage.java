package ru.netology.courseProject.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CreditPaymentPage extends StartPage {
    SelenideElement heading = $x("//*[text()='Кредит по данным карты']");

    public void isExist() {
        heading.click();
    }
}
