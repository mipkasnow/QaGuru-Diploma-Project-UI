package ru.eshoprzd.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.eshoprzd.data.Data.*;
import static ru.eshoprzd.tools.CustomAllureRestListener.withCustomTemplates;
@DisplayName("Класс API тестов открытой части сайта")
public class ApiTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://eshoprzd.ru/rest";
    }

    @Tag("regress")
    @Feature("Апи тесты")
    @Owner("Mikhail")
    @DisplayName("Поиск закупки")
    @ValueSource(strings = {purchase1, purchase2})
    @ParameterizedTest(name = "Ценовой запрос {0}")
    public void searchPurchaseTest(String searchValue){
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

    @Tag("regress")
    @Feature("Апи тесты")
    @Owner("Mikhail")
    @DisplayName("Поиск новости")
    @Test
    public void searchNewsTest(){
        Map<String, String> searchBody = new HashMap<>();
        searchBody.put("search", news1);

        Response response = (Response) given().relaxedHTTPSValidation()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .header("Type-Shop", "rzd")
                .body(searchBody)
                .when()
                .post("ccwe/news/newsPublishedList")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Boolean success = jsonPath.get("success");
        int total = jsonPath.get("total");

        assertTrue(success);
        assertThat(total).isEqualTo(1);
    }

    @Tag("regress")
    @Feature("Апи тесты")
    @Owner("Mikhail")
    @DisplayName("Отправка контактов без капчи")
    @Test
    public void trySendContactsWithOutCaptcha(){
        Map<String, String> body = new HashMap<>();
        body.put("theme", "Техническая поддержка");
        body.put("name", "Mikhail");
        body.put("email", "Mikhail@yandex.ru");
        body.put("text", "здравствуйте");
        body.put("captcha", "123");

        Response response = (Response) given().relaxedHTTPSValidation()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .header("Type-Shop", "rzd")
                .body(body)
                .when()
                .post("ccwe/publicity/sendFeedback")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Boolean success = jsonPath.get("success");
        String message = jsonPath.get("message");

        assertFalse(success);
        assertThat(message).isEqualTo("Обновите данные на картинке и повторите отправку!");
    }

    @DisplayName("Проверка некорректного логина")
    @Tag("regress")
    @Owner("Mikhail")
    @Test
    public void incorrectLoginTest(){
        Map<String, String> body = new HashMap<>();
        body.put("login", "123");
        body.put("password", "123");

        Response response = (Response) given().relaxedHTTPSValidation()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .header("Type-Shop", "rzd")
                .body(body)
                .when()
                .post("auth/api/user/login")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Boolean success = jsonPath.get("success");
        String message = jsonPath.get("message");

        assertFalse(success);
        assertThat(message).isEqualTo("Не найдено имя пользователя или пароль");

    }
}
