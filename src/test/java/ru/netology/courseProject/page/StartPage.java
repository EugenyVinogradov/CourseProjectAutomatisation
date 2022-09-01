package ru.netology.courseProject.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class StartPage {
    SelenideElement buyButton = $("//*[text()='Купить']/../..");
    SelenideElement buyOnCreditButton = $("//*[text()='Купить в кредит']/../..");

    public PaymentPage goPaymentPage() {
        buyButton.click();
        return new PaymentPage();
    }
    public CreditPaymentPage goCreditPaymentPage() {
        buyOnCreditButton.click();
        return new CreditPaymentPage();
    }

}
