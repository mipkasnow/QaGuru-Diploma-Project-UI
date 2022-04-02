package ru.eshoprzd.methods.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static ru.eshoprzd.tools.CustomAllureRestListener.withCustomTemplates;

public class Rest extends BaseTestApi{

    @Step("post запрос ccwe/news/newsPublishedList")
    public Response postNewsPublishedList(Map<String, String> body){
        installSpecification(responseSpecOK200());

        Response response = request
                .body(body)
                .log().body()
                .when()
                .post("ccwe/news/newsPublishedList")
                .then()
                .extract().response();

        return response;
    }

    @Step("post запрос elsearch/api/v1/searchPurchase")
    public Response postSearchPurchase(Map<String, String> body){
        installSpecification(responseSpecOK200());

        Response response = request
                .body(body)
                .log().body()
                .when()
                .post("elsearch/api/v1/searchPurchase")
                .then()
                .extract().response();

        return response;
    }

    @Step("post запрос ccwe/publicity/sendFeedback")
    public Response postSendFeedback(Map<String, String> body){
        installSpecification(responseSpecOK200());

        Response response = request
                .body(body)
                .log().body()
                .when()
                .post("ccwe/publicity/sendFeedback")
                .then()
                .extract().response();

        return response;
    }

    @Step("post запрос auth/api/user/login")
    public Response postLogin(Map<String, String> body){
        installSpecification(responseSpecOK200());

        Response response = request
                .body(body)
                .log().body()
                .when()
                .post("auth/api/user/login")
                .then()
                .extract().response();

        return response;
    }

}
