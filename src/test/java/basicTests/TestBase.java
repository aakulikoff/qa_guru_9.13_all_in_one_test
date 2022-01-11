package basicTests;

import com.codeborne.selenide.*;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class TestBase {

    String BASE_URL = "https://iac.spb.ru/";

    static  DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

    @BeforeAll
    static void setup() {

        addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        Configuration.browser = System.getProperty("web.browser","chrome");

//        gradle clean test -Dremote.web.driver="https://%s:%s@selenoid.autotests.cloud/wd/hub/"

        String remoteWebDriver = System.getProperty("remote.web.driver","");

        if(remoteWebDriver != null) {
            String user = driverConfig.remoteWebUser();
            String password = driverConfig.remoteWebPassword();
            Configuration.remote = String.format(remoteWebDriver,user,password);

            System.out.println(user);
            System.out.println(password);
            System.out.println(String.format(remoteWebDriver,user,password));
            System.out.println(remoteWebDriver);
        }

    }

    @AfterEach
    void afterEach() {

        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser concole logs", getConsoleLogs());

//        String videoStorage = System.getProperty("video.storage");
        if(System.getProperty("video.storage") != null)
            attachVideo();

        closeWebDriver();
    }

}
