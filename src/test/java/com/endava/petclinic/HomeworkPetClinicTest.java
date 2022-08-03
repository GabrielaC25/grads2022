package com.endava.petclinic;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.specification.ProxySpecification.port;
import static org.hamcrest.Matchers.is;

public class HomeworkPetClinicTest {
    @Test
    public void getPet(){
        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .when().log().all()
                .get("/api/owners")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getPetById (){
        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("petId", 3)
                .when().log().all()
                .get("/api/pets/{petId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test

    public void postPetTest(){
        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"birthDate\": \"2022/08/03\",\n" +
                        "  \"id\": null,\n" +
                        "  \"name\": \"Ozzy\",\n" +
                        "  \"owner\": {\n" +
                        "    \"address\": \"Libertatii 7\",\n" +
                        "    \"city\": \"Bucharest\",\n" +
                        "    \"firstName\": \"Gabriela\",\n" +
                        "    \"id\": 18,\n" +
                        "    \"lastName\": \"Costache\",\n" +
                        "    \"pets\": [\n" +
                        "      null\n" +
                        "    ],\n" +
                        "    \"telephone\": \"0953562384\"\n" +
                        "  },\n" +
                        "  \"type\": {\n" +
                        "    \"id\": 2,\n" +
                        "    \"name\": \"Dog\"\n" +
                        "  },\n" +
                        "  \"visits\": [\n" +
                        "    {\n" +
                        "      \"date\": \"2022/09/05\",\n" +
                        "      \"description\": \"Monthly Control\",\n" +
                        "      \"id\": null\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when().log().all()
                .post("/api/pets/")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer petId = response.extract().jsonPath().getInt("id");

        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("petId", petId)
                .when()
                .get("/api/pets/{petId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(petId));
    }

    @Test

    public void AllPets(){
        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .when()
                .get("/api/pets")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

        }



    @Test
    public void postVisit(){
        ValidatableResponse visitResponse = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"date\": \"2022/12/21\",\n" +
                        "  \"description\": \"Control\",\n" +
                        "  \"id\": null,\n" +
                        "  \"pet\": {\n" +
                        "    \"birthDate\": \"2022/08/03\",\n" +
                        "    \"id\": 29,\n" +
                        "    \"name\": \"Ozzy\",\n" +
                        "    \"owner\": {\n" +
                        "      \"address\": \"Libertatii 7\",\n" +
                        "      \"city\": \"Bucharest\",\n" +
                        "      \"firstName\": \"Gabriela\",\n" +
                        "      \"id\": 18,\n" +
                        "      \"lastName\": \"Costache\",\n" +
                        "      \"pets\": [\n" +
                        "        null\n" +
                        "      ],\n" +
                        "      \"telephone\": \"0953562384\"\n" +
                        "    },\n" +
                        "    \"type\": {\n" +
                        "      \"id\": 2,\n" +
                        "      \"name\": \"Dog\"\n" +
                        "    },\n" +
                        "    \"visits\": [\n" +
                        "      null\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}")
                .when().log().all()
                .post("/api/visits")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer visitId = visitResponse.extract().jsonPath().getInt("id");

        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("visitId", visitId)
                .when()
                .get("/api/visits/{visitId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(visitId));
    }
}

