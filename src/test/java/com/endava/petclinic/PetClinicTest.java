package com.endava.petclinic;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetClinicTest {

    @Test
    public void getOwnerById(){
        given().baseUri("http://api.petclinic.mywire.org") //preconditii
                .basePath("/petclinic")
                .port(80)
                .pathParam("ownerId", 1)
                .when()
                .get("/api/owners/{ownerId}")
                .prettyPeek() //pt afisare in consola
                .then()  // verificari
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(1))
                .body("firstName", is("George"))
                .body("firstName", containsString("org"))
                .body("lastName", startsWith("Fr"))
                .body("city", equalToIgnoringCase("madiSon"))
                .body("telephone", hasLength(10));
    }

    @Test
    public void postOwnerTest(){
        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"address\": \"Libertatii 7\",\n" +
                        "  \"city\": \"Bucharest\",\n" +
                        "  \"firstName\": \"Gabriela\",\n" +
                        "  \"id\": null,\n" +
                        "  \"lastName\": \"Costache\",\n" +
                        "  \"telephone\": \"0953562384\"\n" +
                        "}")
                .when().log().all()
                .post("/api/owners")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer ownerId = response.extract().jsonPath().getInt("id");

        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("ownerId", ownerId)
                .when()
                .get("/api/owners/{ownerId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(ownerId));
    }

}
