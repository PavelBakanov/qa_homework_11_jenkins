package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Map;

public class TestBase {
    private final RegistrationPage registrationPage = new RegistrationPage();
    private static final String browserTypeAndVersion = System.getProperty("browserTypeAndVersion");
    private static final String[] browserTypeAndVersionArray = browserTypeAndVersion.split(" ");

    @BeforeAll
    static void setUp() {
        final String browserType = browserTypeAndVersionArray[0];
        final String browserVersion = browserTypeAndVersionArray[1];
        Configuration.browserSize = browserType;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = browserVersion;
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://" + System.getProperty("login") + "@" + System.getProperty("remote");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.timeout = 10000;
    }

    @BeforeEach
    protected void doBeforeEach() {
        registrationPage.openPage();
        registrationPage.removeBanner();
    }

    @AfterEach
    protected void tearDown() {
        Attach.screenshotAs("Последний скриншот");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }

}
