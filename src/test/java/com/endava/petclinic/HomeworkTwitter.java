package com.endava.petclinic;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HomeworkTwitter {


    String apiKey = "xDpvjjIfui8Su72Ti1AR1CKzi";
    String apiKeySecret = "HobgaN3k3jn0vk11ItpV1CSWhyzpIm0Nf2FXbF01wZmDNxmyxl";
    String accessToken = "278136668-zJNcE6VzUubCamfnS29EEw1S0HyRUDufFl42AbUk";
    String accessTokeenSecret = "2Q5WhderaUy2Tk8tL7n400L4ddoXm6STWtasc6yKpM3Wf";

    @Test
    public void postTwitt() {

        ValidatableResponse postResponse = given().baseUri("https://twitter.com")
                .basePath("/j3r3my84")
                .contentType(ContentType.JSON)
                .auth()
                .oauth(apiKey, apiKeySecret, accessToken, accessTokeenSecret)
                .queryParam("status", "Hello!").log().all()
                .when()
                .post("https://api.twitter.com/1.1/statuses/update.json")
                .then().log().all()
                .statusCode(200);

    }
    @Test
            public void getTweets (){
        given().baseUri("https://twitter.com")
                .basePath("/j3r3my84")
                .auth()
                .oauth(apiKey, apiKeySecret, accessToken, accessTokeenSecret)
                .queryParam("id", "1554828407279480833").log().all()
                .when()
                .get("https://api.twitter.com/1.1/statuses/lookup.json")
                .then().log().all()
                .statusCode(200);
    }
}
