package ru.eshoprzd.methods.api;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static ru.eshoprzd.tools.CustomAllureRestListener.withCustomTemplates;

public class BaseTestApi {

    public static ResponseSpecification responseSpecOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecError400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpecUnique(int status){
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static void installSpecification(ResponseSpecification response) {
        //RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public static RequestSpecification request = with()
            .filter(withCustomTemplates())
            .header("Type-Shop", "rzd")
            .baseUri("https://eshoprzd.ru/rest")
            .log().uri()
            .contentType(ContentType.JSON);

}
