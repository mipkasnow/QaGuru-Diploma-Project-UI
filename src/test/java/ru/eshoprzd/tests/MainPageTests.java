package ru.eshoprzd.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.eshoprzd.methods.MainPage;
import ru.eshoprzd.tools.LoadingBar;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.eshoprzd.data.Data.*;

@DisplayName("Класс тестов элементов открытой части сайта")
public class MainPageTests extends BaseTest{

    MainPage step = new MainPage();

    @Tag("regress")
    @Owner("Mikhail")
    @ValueSource(strings = {purchase1, purchase2})
    @DisplayName("Поиск закупки на открытой части сайта")
    @ParameterizedTest(name = "Ценовой запрос {0}")
    public void searchPurchaseTest(String searchValue){
        step.open223FzSection();
        new LoadingBar().waitForLoad();
        step.searchPurchase(searchValue);

        $("[ui-sref*='purchases.view']").$(byText(searchValue)).should(appear);
        $$(".purchase-item").shouldHave(size(1));
    }

    @DisplayName("Поиск новости на открытой части сайта")
    @Tag("regress")
    @Owner("Mikhail")
    @Test
    public void searchNewsTest(){
        step.newsPageOpen();
        step.searchNews(news1);

        $$(".allNews").shouldHave(size(1));
        $(withText("Желаем успешной торговли на УЭТП!")).should(appear);
    }

    @DisplayName("Проверка работы пагинации в разделе закупок")
    @Tag("regress")
    @Owner("Mikhail")
    @Test
    public void checkPaginationTest(){
        step.open223FzSection();

        $(byXpath("//button[normalize-space()='50']")).click();
        $$(".purchase-item").shouldHave(size(50));

        $(byXpath("//button[normalize-space()='100']")).click();
        $$(".purchase-item").shouldHave(size(100));

        $(byXpath("//button[normalize-space()='20']")).click();
        $$(".purchase-item").shouldHave(size(20));
    }

    @DisplayName("Проверка некорректного логина")
    @Tag("regress")
    @Owner("Mikhail")
    @Test
    public void incorrectLoginTest(){
        step.login(fake_login, fake_pswd);

        $(byText("Не найдено имя пользователя или пароль")).should(appear);
    }

    @Tag("regress")
    @DisplayName("Проверка ссылок футера")
    @Owner("Mikhail")
    @Test
    public void checkFooterLinksTest(){
        step.openRemoteSupportFromFooter();
        $(withText("Скачайте программу TeamViewer по одной из следующих ссылок:")).should(appear);
        $("a[href*='.exe']").shouldBe(visible);

        step.openPrivacyFromFooter();
        $(withText("Настоящим в соответствии с Федеральным законом от 27 июля 2006 года № 152 «О персональных данных»")).should(appear);
        $("a[href*='privacy'").shouldBe(visible);
    }

    @Tag("regress")
    @DisplayName("Проверка карточки компании")
    @Owner("Mikhail")
    @Test
    public void checkCompanyCardTest(){
        step.openCompanySection();
        switchTo().window(1);
        $(".cardName ").click();
        $("[logger-ui='COMPANY_REDIRECT']").click();
        switchTo().window(2);

        webdriver().shouldHave(url("https://nwudc.ru/"));
    }

    @Disabled
    @Tag("regress")
    @Owner("Mikhail")
    @DisplayName("Пропущенный тест")
    @Test
    public void skippedTest(){
        System.out.println("Тест пропущен");
    }


    @Tag("regress")
    @Owner("Mikhail")
    @DisplayName("Упавший тест")
    @Test
    public void failedTest(){
        assertTrue(false);
    }

}