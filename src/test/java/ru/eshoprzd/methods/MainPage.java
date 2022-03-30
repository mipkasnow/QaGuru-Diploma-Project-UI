package ru.eshoprzd.methods;

import io.qameta.allure.Step;
import ru.eshoprzd.tools.LoadingBar;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class MainPage {

    public static final String ENTER_SITE = "#login-btn";

    @Step("Открытие раздела новостей")
    public void newsPageOpen() {$("#all-news").click();}

    @Step("Открытие раздела закупок РЖД до 500тыс")
    public void open223FzSection(){
        $(withText("Закупки")).click();
        $(withText("Закупки ОАО «РЖД»")).click();
    }

    @Step("Поиск новости")
    public void searchNews(String news){
        $("#filterInput").setValue(news);
        $(".headerSearch").click();
        new LoadingBar().waitForLoad();
    }

    @Step("Поиск закупки")
    public void searchPurchase(String purchase){
        $("#filterInput").setValue(purchase);
        $(".glyphicon-search").click();
    }

    @Step("Авторизация на сайте")
    public void login(String loginName, String password) {
        $(ENTER_SITE).click();
        $("[ng-click='showLoginForm = !showLoginForm']").click();
        $("#login").setValue(loginName);
        $("#pass").setValue(password);
        $("#login-btn-form").click();
    }

    @Step("Открытие раздела удаленной поддержки")
    public void openRemoteSupportFromFooter(){
        $(".footerLinks a[href*='support']").click();
        $("[ng-model='descChecked']").click();
    }

    @Step("Открытие раздела политики конфиденциальности")
    public void openPrivacyFromFooter() {$("a[href*='privacy']").click();}

    @Step("Открытие раздела компаний")
    public void openCompanySection(){
        $("#mainMenu [ui-sref='companies']").click();
    }
}
