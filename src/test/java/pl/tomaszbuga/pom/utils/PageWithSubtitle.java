package pl.tomaszbuga.pom.utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public abstract class PageWithSubtitle extends BasePage {
    protected final SelenideElement subtitle = $(".subtitle-content");

    public PageWithSubtitle(Class<?> cls) {
        super(cls);
    }
}
