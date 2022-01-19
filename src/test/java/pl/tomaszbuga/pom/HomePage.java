package pl.tomaszbuga.pom;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import pl.tomaszbuga.pom.utils.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class HomePage extends BasePage {
    private final SelenideElement yellowButton = $(".yellow-button");

    public HomePage openHomePage() {
        log.info("Open HomePage");
        open(baseUrl);
        return this;
    }

    public CategoriesPage clickYellowButton() {
        log.info("Click yellow button");
        yellowButton.click();
        return new CategoriesPage();
    }

}
