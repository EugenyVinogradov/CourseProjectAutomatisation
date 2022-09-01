package ru.netology.courseProject.data;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static Response debitBuying(String card, String month, String year, String owner, String cvc) {
        Gson gson = new Gson();
        String jsonUserData = gson.toJson(DataHelper.getCardInfo(card, month, year, owner, cvc));
        return given()
                .spec(requestSpec)
                .body(jsonUserData)
                .when()
                .post("/api/v1/pay");

    }

    public static Response creditBuying(String card, String month, String year, String owner, String cvc) {
        Gson gson = new Gson();
        String jsonUserData = gson.toJson(DataHelper.getCardInfo(card, month, year, owner, cvc));
        return given()
                .spec(requestSpec)
                .body(jsonUserData)
                .when()
                .post("/api/v1/credit");

    }
}
