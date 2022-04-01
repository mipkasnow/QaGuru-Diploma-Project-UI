package ru.eshoprzd.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.eshoprzd.tools.AllureHelpers;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    public static final int TIMEOUT_SMALL = 12;
    public static final int TIMEOUT_MEDIUM = 30;
    public static final int TIMEOUT_LARGE = 60;

    @BeforeAll
    public static void beforeAll(){
        String browser = System.getProperty("browser", "chrome");
        String size = System.getProperty("size", "1590x850");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserSize = size;
        Configuration.browser = browser;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        AllureHelpers.attachAsText("Browser: ", browser);
        AllureHelpers.attachAsText("Size: ", size);

        Configuration.baseUrl = "https://eshoprzd.ru/";
        Configuration.timeout = TIMEOUT_SMALL * 1000;
    }

    @BeforeEach
    public void beforeEach(){
        open("");
        visualizeMouseClick();
    }

    @AfterEach
    void afterEach(){
        AllureHelpers.addVideo();
        AllureHelpers.screenshotAs("Last screen");
        AllureHelpers.pageSource();
        AllureHelpers.browserConsoleLogs();
        closeWebDriver();
    }

    public void visualizeMouseClick(){
        executeJavaScript("function onClick(event) {\n" +
                "        var e = event || window.event;\n" +
                "        var target = e.target || e.srcElement;\n" +
                "        target.style['box-sizing'] = 'border-box';\n" +
                "        target.style['border'] = '2px solid green';\n" +
                "      }\n" +
                "    \n" +
                "      document.addEventListener('click', onClick);");
    }
}
