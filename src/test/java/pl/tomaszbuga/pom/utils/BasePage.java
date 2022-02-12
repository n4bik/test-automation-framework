package pl.tomaszbuga.pom.utils;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public abstract class BasePage {

    protected final String baseUrl = "http://localhost:4200/";

    public BasePage() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(false));
    }

}
