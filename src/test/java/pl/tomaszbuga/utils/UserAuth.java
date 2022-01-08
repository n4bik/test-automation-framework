package pl.tomaszbuga.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class UserAuth {

    public static String getJwtToken() {
        String json = "{\"username\": \"tbuga\",\"password\": \"4dm1n?!\"}";
        return given()
                .baseUri("http://localhost:8082/")
                .contentType("application/json")
                .body(json)
                .post("login")
                .jsonPath()
                .get("token");
    }

    public static void addAuthCookiesToDriver() {
        open("http://localhost:4200");
        Cookie cookie = new Cookie("token", getJwtToken());
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }
}
