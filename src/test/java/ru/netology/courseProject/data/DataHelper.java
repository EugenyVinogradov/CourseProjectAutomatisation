package ru.netology.courseProject.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataHelper {

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String nameOwner;
        String cvc;
    }

    static Faker faker = new Faker();

    public static String getCardNumber(String cardNumberValue) {
        if (cardNumberValue.equals("correctNumberApproved")) {
            return "1111 2222 3333 4444";
        }
        if (cardNumberValue.equals("correctNumberDeclined")) {
            return "5555 6666 7777 8888";
        }
        if (cardNumberValue.equals("inCorrectNumber")) {
            return "1111 2222 3333 4445";
        }
        if (cardNumberValue.equals("inCompleteNumberPay")) {
            return "1111 2222 3333 444";
        }
        return null;
    }

    public static String getMonth(String monthValue) {
        if (monthValue.equals("correctMonth")) {
            return "12";
        }
        if (monthValue.equals("monthLessThanCurrent")) {
            return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        }
        if (monthValue.equals("anyMonth")) {
            return String.valueOf(faker.number().numberBetween(1, 12));
        }
        return null;
    }

    public static String getYear(String yearValue) {
        if (yearValue.equals("correctYear")) {
            return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("YY"));
        }
        if (yearValue.equals("currentYear")) {
            return LocalDate.now().plusYears(0).format(DateTimeFormatter.ofPattern("YY"));
        }
        if (yearValue.equals("previousYear")) {
            return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("YY"));
        }
        return null;
    }

    public static String getNameOwner(String name) {
        if (name.equals("correctName")) {
            return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        }
        if (name.equals("сyrillicName")) {
            Faker faker = new Faker(new Locale("ru-RU"));
            return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        }
        if (name.equals("nameContainHyphen")) {
            return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase() +
                    "-" + faker.name().lastName().toUpperCase();
        }
        if (name.equals("nameContainOnlyHyphens")) {
            return "---";
        }
        if (name.equals("nameContainSpecialSymbol")) {
            return faker.name().firstName().toUpperCase() + "_" + faker.name().lastName().toUpperCase();
        }
        return null;
    }

    public static String getCvc(String cvc) {
        if (cvc.equals("correctCvc")) {
            return String.valueOf(faker.number().numberBetween(001, 999));
        }
        if (cvc.equals("inCorrectCvc")) {
            return String.valueOf(faker.number().numberBetween(01, 99));
        }
        return null;
    }

    public static CardInfo getCardInfo(String card, String month, String year, String owner, String cvc) {
        return new CardInfo(getCardNumber(card), getMonth(month), getYear(year), getNameOwner(owner), getCvc(cvc));
    }

    public static String getApproved() {
        return "APPROVED";
    }

    public static String getDeclined() {
        return "DECLINED";
    }
}
