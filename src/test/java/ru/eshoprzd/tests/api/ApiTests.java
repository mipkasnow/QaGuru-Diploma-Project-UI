package ru.eshoprzd.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.eshoprzd.methods.api.BaseTestApi;
import ru.eshoprzd.methods.api.Rest;

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
public class ApiTests extends BaseTestApi {

    @Tag("regress")
    @Feature("Апи тесты")
    @Owner("Mikhail")
    @DisplayName("Поиск закупки")
    @ValueSource(strings = {purchase1, purchase2})
    @ParameterizedTest(name = "Ценовой запрос {0}")
    public void searchPurchaseTest(String searchValue){
        Map<String, String> body = new HashMap<>();
        body.put("search", searchValue);
        Response response = new Rest().postSearchPurchase(body);

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
        Map<String, String> body = new HashMap<>();
        body.put("search", news1);
        Response response = new Rest().postNewsPublishedList(body);

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

        Response response = new Rest().postSendFeedback(body);

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

        Response response = new Rest().postLogin(body);

        JsonPath jsonPath = response.jsonPath();
        Boolean success = jsonPath.get("success");
        String message = jsonPath.get("message");

        assertFalse(success);
        assertThat(message).isEqualTo("Не найдено имя пользователя или пароль");
    }
}
