package ru.netology.courseProject.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.courseProject.data.DataHelper;
import ru.netology.courseProject.data.SqlHelper;
import ru.netology.courseProject.page.StartPage;

import static com.codeborne.selenide.Selenide.open;

public class Tests {


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        SqlHelper.cleanDataBase();
    }
    // Сценарии покупки тура
    @Test // #1 Успешная покупка тура Passed
    public void shouldPayTourApproved() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "correctCvc");
        payPage.operationApproved();
        String actual = SqlHelper.statusFromPaymentEntity();
        String expected = DataHelper.getApproved();
        Assertions.assertEquals(expected, actual);
    }
    @Test // #2  Failed
    public void shouldPayTourDeclinedWithCorrectNumber() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberDeclined", "correctMonth", "correctYear", "correctName", "correctCvc");
        payPage.operationDeclined();
        String actual = SqlHelper.statusFromPaymentEntity();
        String expected = DataHelper.getDeclined();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #3 Passed
    public void shouldPayTourWithIncorrectNumberNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("inCorrectNumber", "correctMonth", "correctYear", "correctName", "correctCvc");
        payPage.operationDeclined();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #4 Passed
    public void shouldPayTourWithInCompleteNumberNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("inCompleteNumber", "correctMonth", "correctYear", "correctName", "correctCvc");
        payPage.incorrectFormatCardNumber();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #5 Passed
    public void shouldPayTourWithEmptyNumberNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("emptyNumber", "correctMonth", "correctYear", "correctName", "correctCvc");
        payPage.incorrectFormatCardNumber();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #6 Passed
    public void shouldPayTourWithExpiredCardMonthNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "monthLessThanCurrent", "currentYear", "correctName", "correctCvc");
        payPage.incorrectMonth();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #7 Passed
    public void shouldPayTourWithExpiredCardYearNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "previousYear", "correctName", "correctCvc");
        payPage.expiredCardYear();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #8 Passed
    public void shouldPayTourWithEmptyMonthNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "emptyMonth", "currentYear", "correctName", "correctCvc");
        payPage.incorrectFormatMonth();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #9 Passed
    public void shouldPayTourWithEmptyYearNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "emptyYear", "correctName", "correctCvc");
        payPage.incorrectFormatYear();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #10 Passed
    public void shouldPayTourWithEmptyOwnerNameNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "emptyName", "correctCvc");
        payPage.emptyOwnerField();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }
    @Test // #11 Failed
    public void shouldPayTourWithOwnerNameContainCyrillicNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "сyrillicName", "correctCvc");
        payPage.incorrectFormatOwner();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #12 Failed
    public void shouldPayTourWithOwnerNameContainSpecialSymbolsNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "nameContainSpecialSymbol", "correctCvc");
        payPage.incorrectFormatOwner();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #13 Passed
    public void shouldPayTourWithOwnerNameContainHyphenApproved() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "nameContainHyphen", "correctCvc");
        payPage.operationApproved();
        String actual = SqlHelper.statusFromPaymentEntity();
        String expected = DataHelper.getApproved();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #14 Failed
    public void shouldPayTourWithOwnerNameContainOnlyHyphensNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "nameContainOnlyHyphens", "correctCvc");
        payPage.incorrectFormatOwner();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #15 Failed
    public void shouldPayTourWithOwnerNameContainDigitNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "nameContainDigit", "correctCvc");
        payPage.incorrectFormatOwner();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #16 Passed
    public void shouldPayTourWithEmptyCvcNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "emptyCvc");
        payPage.incorrectFormatCvc();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #17 Passed
    public void shouldPayTourWithIncorrectCvcNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "inCorrectCvc");
        payPage.incorrectFormatCvc();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #18 Failed
    public void shouldPayTourWithCvcIsTripleNullNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "tripleNull");
        payPage.incorrectFormatCvc();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #19 Failed
    public void shouldPayTourWithCardNumberIsAllNullsNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("allNullsNumber", "correctMonth", "correctYear", "correctName", "correctCvc");
        payPage.incorrectFormatCardNumber();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #20 Failed
    public void shouldPayTourWithMonthIsAllNullsNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "allNullsMonth", "correctYear", "correctName", "correctCvc");
        payPage.incorrectFormatMonth();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #21 Passed
    public void shouldPayTourWithYearIsAllNullsNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "allNullsYear", "correctName", "correctCvc");
        payPage.expiredCardYear();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #22 Passed
    public void shouldPayTourWithYearMoreCurrentBySixNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "yearPlusSix", "correctName", "correctCvc");
        payPage.incorrectYear();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #23 Passed
    public void shouldPayTourWithMonthIsWrongNotPay() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "wrongMonth", "correctYear", "correctName", "correctCvc");
        payPage.incorrectMonth();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #24 Failed
    public void shouldPayTourApprovedPaymentCorrespondsAdvertisement() {
        var startPage = new StartPage();
        var payPage = startPage.goPaymentPage();
        payPage.isExist();
        payPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "correctCvc");
        payPage.operationApproved();
        int expected = DataHelper.getCost();
        int actual = SqlHelper.amountFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    // Сценарии покупки тура в кредит
    @Test // #25 Passed
    public void shouldPayTourInCreditApproved() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "correctCvc");
        creditPaymentPage.operationApproved();
        String actual = SqlHelper.statusFromCreditRequestEntity();
        String expected = DataHelper.getApproved();
        Assertions.assertEquals(expected, actual);
    }
    @Test // #26 Failed
    public void shouldPayTourInCreditDeclinedWithCorrectNumber() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberDeclined", "correctMonth", "correctYear", "correctName", "correctCvc");
        creditPaymentPage.operationDeclined();
        String actual = SqlHelper.statusFromCreditRequestEntity();
        String expected = DataHelper.getDeclined();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #27 Passed
    public void shouldPayTourInCreditWithIncorrectNumberNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("inCorrectNumber", "correctMonth", "correctYear", "correctName", "correctCvc");
        creditPaymentPage.operationDeclined();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromCreditRequestEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #28 Passed
    public void shouldPayTourInCreditWithInCompleteNumberNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("inCompleteNumber", "correctMonth", "correctYear", "correctName", "correctCvc");
        creditPaymentPage.incorrectFormatCardNumber();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #29 Passed
    public void shouldPayTourInCreditWithEmptyNumberNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("emptyNumber", "correctMonth", "correctYear", "correctName", "correctCvc");
        creditPaymentPage.incorrectFormatCardNumber();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #30 Passed
    public void shouldPayTourInCreditWithExpiredCardMonthNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "monthLessThanCurrent", "currentYear", "correctName", "correctCvc");
        creditPaymentPage.incorrectMonth();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #31 Passed
    public void shouldPayTourInCreditWithExpiredCardYearNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "previousYear", "correctName", "correctCvc");
        creditPaymentPage.expiredCardYear();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #32 Passed
    public void shouldPayTourInCreditWithEmptyMonthNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "emptyMonth", "currentYear", "correctName", "correctCvc");
        creditPaymentPage.incorrectFormatMonth();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #33 Passed
    public void shouldPayTourInCreditWithEmptyYearNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "emptyYear", "correctName", "correctCvc");
        creditPaymentPage.incorrectFormatYear();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #34 Passed
    public void shouldPayTourInCreditWithEmptyOwnerNameNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "emptyName", "correctCvc");
        creditPaymentPage.emptyOwnerField();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }
    @Test // #35 Failed
    public void shouldPayTourInCreditWithOwnerNameContainCyrillicNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "сyrillicName", "correctCvc");
        creditPaymentPage.incorrectFormatOwner();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #36 Failed
    public void shouldPayTourInCreditWithOwnerNameContainSpecialSymbolsNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "nameContainSpecialSymbol", "correctCvc");
        creditPaymentPage.incorrectFormatOwner();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #37 Passed
    public void shouldPayTourInCreditWithOwnerNameContainHyphenApproved() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "nameContainHyphen", "correctCvc");
        creditPaymentPage.operationApproved();
        String actual = SqlHelper.statusFromCreditRequestEntity();
        String expected = DataHelper.getApproved();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #38 Failed
    public void shouldPayTourInCreditWithOwnerNameContainOnlyHyphensNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "nameContainOnlyHyphens", "correctCvc");
        creditPaymentPage.incorrectFormatOwner();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #39 Failed
    public void shouldPayTourInCreditWithOwnerNameContainDigitNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "nameContainDigit", "correctCvc");
        creditPaymentPage.incorrectFormatOwner();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #40 Passed
    public void shouldPayTourInCreditWithEmptyCvcNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "emptyCvc");
        creditPaymentPage.incorrectFormatCvc();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #41 Passed
    public void shouldPayTourInCreditWithIncorrectCvcNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "inCorrectCvc");
        creditPaymentPage.incorrectFormatCvc();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #42 Failed
    public void shouldPayTourInCreditWithCvcIsTripleNullNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "correctYear", "correctName", "tripleNull");
        creditPaymentPage.incorrectFormatCvc();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #43 Failed
    public void shouldPayTourInCreditWithCardNumberIsAllNullsNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("allNullsNumber", "correctMonth", "correctYear", "correctName", "correctCvc");
        creditPaymentPage.incorrectFormatCardNumber();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #44 Failed
    public void shouldPayTourInCreditWithMonthIsAllNullsNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "allNullsMonth", "correctYear", "correctName", "correctCvc");
        creditPaymentPage.incorrectFormatMonth();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #45 Passed
    public void shouldPayTourInCreditWithYearIsAllNullsNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "allNullsYear", "correctName", "correctCvc");
        creditPaymentPage.expiredCardYear();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #46 Passed
    public void shouldPayTourInCreditWithYearMoreCurrentBySixNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "correctMonth", "yearPlusSix", "correctName", "correctCvc");
        creditPaymentPage.incorrectYear();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

    @Test // #47 Passed
    public void shouldPayTourInCreditWithMonthIsWrongNotPay() {
        var startPage = new StartPage();
        var creditPaymentPage = startPage.goCreditPaymentPage();
        creditPaymentPage.isExist();
        creditPaymentPage.setValues("correctNumberApproved", "wrongMonth", "correctYear", "correctName", "correctCvc");
        creditPaymentPage.incorrectMonth();
        long expected = 0;
        long actual = SqlHelper.countOfRecordsFromPaymentEntity();
        Assertions.assertEquals(expected, actual);
    }

}

