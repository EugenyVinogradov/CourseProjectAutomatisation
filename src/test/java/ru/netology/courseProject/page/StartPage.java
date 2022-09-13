package ru.netology.courseProject.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.courseProject.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class StartPage {
    SelenideElement cardNumberField = $x("//*[text()='Номер карты']/..//input");
    SelenideElement monthField = $x("//*[text()='Месяц']/..//input");
    SelenideElement yearField = $x("//*[text()='Год']/..//input");
    SelenideElement ownerField = $x("//*[text()='Владелец']/..//input");
    SelenideElement cvcField = $x("//*[text()='CVC/CVV']/..//input");
    SelenideElement buyButton = $x("//*[text()='Купить']/../..");
    SelenideElement buyOnCreditButton = $x("//*[text()='Купить в кредит']/../..");
    SelenideElement continueButton = $x("//*[text()='Продолжить']/../..");
    SelenideElement incorrectFormatCardNumber = $x("//*[text()='Номер карты']/../..//*[text()='Неверный формат']");
    SelenideElement incorrectFormatMonth = $x("//*[text()='Месяц']/../..//*[text()='Неверный формат']");
    SelenideElement incorrectMonth = $x("//*[text()='Месяц']/../..//*[text()='Неверно указан срок действия карты']");
    SelenideElement incorrectFormatYear = $x("//*[text()='Год']/../..//*[text()='Неверный формат']");
    SelenideElement emptyOwnerField = $x("//*[text()='Владелец']/../..//*[text()='Поле обязательно для заполнения']");
    SelenideElement incorrectFormatOwner = $x("//*[text()='Владелец']/../..//*[text()='Неверный формат']");
    SelenideElement incorrectYear = $x("//*[text()='Год']/../..//*[text()='Неверно указан срок действия карты']");
    SelenideElement expiredCardYear = $x("//*[text()='Год']/../..//*[text()='Истёк срок действия карты']");
    SelenideElement incorrectFormatCvc = $x("//*[text()='CVC/CVV']/../..//*[text()='Неверный формат']");
    SelenideElement operationApprovedMessage = $x("//*[text()='Операция одобрена Банком.']");
    SelenideElement operationDeclinedMessage = $x("//*[text()='Ошибка! Банк отказал в проведении операции.']");

    public PaymentPage goPaymentPage() {
        buyButton.click();
        return new PaymentPage();
    }

    public CreditPaymentPage goCreditPaymentPage() {
        buyOnCreditButton.click();
        return new CreditPaymentPage();
    }

    public void setValues(String cardNumber, String month, String year, String owner, String cvc) {
        cardNumberField.setValue(DataHelper.getCardNumber(cardNumber));
        monthField.setValue(DataHelper.getMonth(month));
        yearField.setValue(DataHelper.getYear(year));
        ownerField.setValue(DataHelper.getNameOwner(owner));
        cvcField.setValue(DataHelper.getCvc(cvc));
        continuePay();
    }

    public void continuePay() {
        continueButton.click();
    }

    public void operationApproved() {
        operationApprovedMessage.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void operationDeclined() {
        operationDeclinedMessage.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void incorrectFormatCardNumber() {
        incorrectFormatCardNumber.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void incorrectMonth() {
        incorrectMonth.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void expiredCardYear() {
        expiredCardYear.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void incorrectFormatMonth() {
        incorrectFormatMonth.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void incorrectFormatYear() {
        incorrectFormatYear.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void emptyOwnerField() {
        emptyOwnerField.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void incorrectFormatOwner() {
        incorrectFormatOwner.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void incorrectFormatCvc() {
        incorrectFormatCvc.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void incorrectYear() {
        incorrectYear.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

}
