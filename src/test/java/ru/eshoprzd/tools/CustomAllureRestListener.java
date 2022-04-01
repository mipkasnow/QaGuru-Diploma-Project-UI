package ru.eshoprzd.tools;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomAllureRestListener {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");

        return FILTER;
    }
}
