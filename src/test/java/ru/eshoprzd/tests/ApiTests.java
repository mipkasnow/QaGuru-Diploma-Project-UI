package ru.eshoprzd.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.eshoprzd.data.Data.purchase1;
import static ru.eshoprzd.data.Data.purchase2;
import static ru.eshoprzd.tools.CustomAllureRestListener.withCustomTemplates;

public class ApiTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://eshoprzd.ru/rest";
    }

    @Feature("Апи тесты")
    @Owner("Mikhail")
    @DisplayName("Поиск единственной закупки")
    @ValueSource(strings = {purchase1, purchase2})
    @ParameterizedTest(name = "Ценовой запрос {0}")
    public void elsearchPurchaseOneResult(String searchValue){
        Map<String, String> searchBody = new HashMap<>();
        searchBody.put("search", searchValue);

        Response response = (Response) given().relaxedHTTPSValidation()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .header("Type-Shop", "rzd")
                .body(searchBody)
                .when()
                .post("elsearch/api/v1/searchPurchase")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Boolean success = jsonPath.get("success");
        int total = jsonPath.get("total");

        assertTrue(success);
        assertThat(total).isEqualTo(1);
    }
}
