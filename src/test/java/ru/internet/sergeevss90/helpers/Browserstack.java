package ru.internet.sergeevss90.helpers;

import org.aeonbits.owner.ConfigFactory;
import ru.internet.sergeevss90.owner.BrowserStackInterface;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String videoUrl(String sessionId) {
        BrowserStackInterface config = ConfigFactory.create(BrowserStackInterface.class, System.getProperties());
        String login = config.username();
        String password = config.access_key();
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic("sergei_DQJEY1", "pzwEywBsz6REmsz5qzUh")
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}