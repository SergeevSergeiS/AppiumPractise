package ru.internet.sergeevss90.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ru.internet.sergeevss90.drivers.BrowserstackMobileDriver;
import ru.internet.sergeevss90.drivers.LocalMobileDriver;
import ru.internet.sergeevss90.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static ru.internet.sergeevss90.helpers.Attach.sessionId;
import static io.qameta.allure.Allure.step;

public class TestBase {
    static String deviceHost = System.getProperty("deviceHost", "local");
    @BeforeAll
    public static void setup() {

        if (deviceHost.equals("local")) {
            Configuration.browser = LocalMobileDriver.class.getName();
        } else if (deviceHost.equals("browserstack")) {
            Configuration.browser = BrowserstackMobileDriver.class.getName();
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = sessionId();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        step("Close driver", Selenide::closeWebDriver);
        if (Objects.equals(deviceHost, "browserstack")) {
            Attach.video(sessionId);
        }
    }
}