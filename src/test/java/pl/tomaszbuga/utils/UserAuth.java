package pl.tomaszbuga.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class UserAuth {
    private static String token = "";

    public static void addAuthCookiesToDriver() {
        generateTokenIfNeeded();
        open("http://localhost:4200");
        addCookie();
    }

    private static void generateTokenIfNeeded() {
        // Maybe an issue if the token expiration date is overdue
        // if Test Suites that takes longer than 15-30 minutes per Run
        //
        // Solution Idea: Token Validation API in backend and apply
        // verification in tests if the Test Run takes longer than expiration date
        if (token.equals("")) {
            setCurrentToken();
        }
    }

    private static void addCookie() {
        Cookie cookie = new Cookie("token", getCurrentToken());
        WebDriverRunner
                .getWebDriver()
                .manage()
                .addCookie(cookie);
    }

    public static String getJwtToken() {
        String json = "{\"username\": \"tbuga\",\"password\": \"4dm1n?!\"}";

        return given()
                .baseUri("http://localhost:8082/") // We're setting the API address
                .contentType("application/json") // We're setting the Content-Type header
                .body(json) // We're assigning the json variable as Request Body
                .post("login") // We're performing POST call to http://localhost:8082/login
                .jsonPath() // We're parsing the Response to the form of a JsonPath
                .get("token"); // We're returning the value of token from the response JsonPath
    }

    private static String getCurrentToken() {
        return token;
    }

    private static void setCurrentToken() {
        token = getJwtToken();
    }

}
