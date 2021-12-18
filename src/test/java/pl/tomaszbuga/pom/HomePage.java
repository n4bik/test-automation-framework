package pl.tomaszbuga.pom;

import com.codeborne.selenide.SelenideElement;
import pl.tomaszbuga.pom.utils.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage extends BasePage {
    private final SelenideElement yellowButton = $(".yellow-button");

    public HomePage() {
        super(HomePage.class);
    }

    public HomePage openHomePage() {
        LOGGER.info("Open HomePage");
        open(baseUrl);
        return this;
    }

    public CategoriesPage clickYellowButton() {
        LOGGER.info("Click yellow button");
        yellowButton.click();
        return new CategoriesPage();
    }
}
