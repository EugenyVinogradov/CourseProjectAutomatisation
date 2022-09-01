package ru.netology.courseProject.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {
    SelenideElement heading = $("//*[text()='Оплата по карте']");
    SelenideElement cardNumberField = $("//*[text()='Номер карты']/..//input");
    SelenideElement monthField = $("//*[text()='Месяц']/..//input");
    SelenideElement yearField = $("//*[text()='Год']/..//input");
    SelenideElement ownerField = $("//*[text()='Владелец']/..//input");
    SelenideElement cvcField = $("//*[text()='CVC/CVV']/..//input");
    SelenideElement buyButton = $("//*[text()='Купить']/../..");
    SelenideElement buyOnCreditButton = $("//*[text()='Купить в кредит']/../..");
    SelenideElement continueButton = $("//*[text()='Продолжить']/../..");
    SelenideElement incorrectFormatCardNumber = $("//*[text()='Неверный формат']");
    SelenideElement incorrectFormatMonth = $("//*[text()='Месяц']/../..//*[text()='Неверный формат']");
    SelenideElement incorrectMonth = $("//*[text()='Месяц']/../..//*[text()='Неверно указан срок действия карты']");
    SelenideElement incorrectFormatYear = $("//*[text()='Год']/../..//*[text()='Неверный формат']");
    SelenideElement incorrectYear = $("//*[text()='Год']/../..//*[text()='Неверно указан срок действия карты']");
    SelenideElement expiredCardYear = $("//*[text()='Год']/../..//*[text()='Истёк срок действия карты']");
    SelenideElement incorrectFormatCvc = $("//*[text()='CVC/CVV']/../..//*[text()='Неверный формат']");
    SelenideElement operationApprovedMessage = $("//*[text()='Операция одобрена Банком.']");
    SelenideElement operationRejectedMessage = $("//*[text()='Ошибка! Банк отказал в проведении операции.']");
}
