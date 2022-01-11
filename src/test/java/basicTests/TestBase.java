package basicTests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.*;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static java.lang.String.format;

public class TestBase {

    String BASE_URL = "https://iac.spb.ru/";

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        String login = credentials.login();
        String password = credentials.password();

        String url = System.getProperty("url", "");
        Configuration.remote = format("https://%s:%s@%s", login, password, url);
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";

        String message = format("https://%s:%s@selenoid.autotests.cloud/wd/hub/", login, password);
        System.out.println(message);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void openPracticeForm() {
        open("https://demoqa.com/automation-practice-form");
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}

