package com.endava.petclinic;

import com.endava.petclinic.controllers.OwnerController;
import com.endava.petclinic.controllers.PetController;
import com.endava.petclinic.controllers.PetTypeController;
import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.PetType;
import com.endava.petclinic.utils.EnvReader;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;


public class HomeworkPetClinicTestDay2 {
    @Test
    public void postNewRandomOwner(){
        Faker faker = new Faker();
        Owner owner = OwnerController.generateNewRandomOwner();

        ValidatableResponse response = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .contentType(ContentType.JSON)
                .when()
                .body(owner).log().all()
                .post("/api/owners")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        owner.setId(response.extract().jsonPath().getInt("id"));

        given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("ownerId", owner.getId())
                .when()
                .get("/api/owners/{ownerId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void postPetTypeWithObject(){
        Faker faker = new Faker();
        PetType type = PetTypeController.generateNewRandomPetType();

        ValidatableResponse postResponse = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .contentType(ContentType.JSON)
                .body(type).log().all()
                .post("/api/pettypes")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        type.setId(postResponse.extract().jsonPath().getInt("id"));

        ValidatableResponse getResponse = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("petTypeId", type.getId())
                .when()
                .get("/api/pettypes/{petTypeId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

        PetType typeFromGetResponse = getResponse.extract().as(PetType.class);
        assertThat(typeFromGetResponse, is(type));
    }


    @Test
    public void postPetTestWithObject(){
        Faker fake = new Faker();

        Owner petOwner = new Owner();
        PetType petType = new PetType();


        ValidatableResponse owner = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("ownerId", 1)
                .when()
                .get("/api/owners/{ownerId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

        petOwner.setId(owner.extract().jsonPath().getInt("id"));
        petOwner.setFirstName(owner.extract().jsonPath().getString("firstName"));
        petOwner.setLastName(owner.extract().jsonPath().getString("lastName"));
        petOwner.setAddress(owner.extract().jsonPath().getString("address"));
        petOwner.setCity(owner.extract().jsonPath().getString("city"));
        petOwner.setTelephone(owner.extract().jsonPath().getString("telephone"));

        ValidatableResponse newPetType = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("petTypeId", 1)
                .when()
                .get("/api/pettypes/{petTypeId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

        petType.setId(newPetType.extract().jsonPath().getInt("id"));
        petType.setName(newPetType.extract().jsonPath().getString("name"));

        Pet pet = new PetController().generateNewRandomPet(petOwner, petType);

        ValidatableResponse postResponse = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .contentType(ContentType.JSON)
                .when()
                .body(pet).log().all()
                .post("/api/pets")
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        pet.setId(postResponse.extract().jsonPath().getInt("id"));

        ValidatableResponse getResponse = given().baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .port(EnvReader.getPort())
                .pathParam("petId", pet.getId())
                .when()
                .get("/api/pets/{petId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

        Pet petFromGetResponse = getResponse.extract().as(Pet.class);
        assertThat(petFromGetResponse, is(pet));


    }

}
