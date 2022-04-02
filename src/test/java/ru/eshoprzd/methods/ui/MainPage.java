package ru.eshoprzd.methods.ui;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.joda.time.DateTime;
import ru.eshoprzd.tools.LoadingBar;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    Faker faker = new Faker();
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

    @Step("Заполнение формы обратной связи")
    public void fillContactsForm(){
        String phone = Long.toString(faker.number().numberBetween(1, 1000000000));
        String text = faker.witcher().monster() + " " + faker.witcher().school();
        String email = "autotest+" + new DateTime().toString("ddMMHHmmss") + "@yandex.ru";

        $("#theme").selectOptionByValue("Прочие вопросы");
        $("#name").setValue(faker.name().fullName());
        $("#email").setValue(email);
        $("#phone").setValue(phone);
        $("#consentProcessing").click();
        $("#consentDissemination").click();
        $("#text").setValue(text);
        $("#captcha").setValue("123");
    }
}
