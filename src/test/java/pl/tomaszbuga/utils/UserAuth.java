package pl.tomaszbuga.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class UserAuth {
    private static String token = "";

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
        generateTokenIfNeeded();
        open("http://localhost:4200");
        Cookie cookie = new Cookie("token", getCurrentToken());
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }

    private static void generateTokenIfNeeded() {
        // maybe an issue if the token expiration date is overdue
        // in case of Test Suites that takes longer than 15-30 minutes per Run
        // probable solution: make Token Validation API in backend and apply
        // verification in tests if the Run takes longer than expiration date
        if (token.equals("")) {
            setCurrentToken();
        }
    }

    private static String getCurrentToken() {
        return token;
    }

    private static void setCurrentToken() {
        token = getJwtToken();
    }
}
