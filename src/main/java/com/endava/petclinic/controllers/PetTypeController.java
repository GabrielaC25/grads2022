package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.PetType;
import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PetTypeController {
    public static PetType generateNewRandomPetType(){
        Faker faker = new Faker();
        PetType type = new PetType();


        type.setName(faker.name().name());
        return type;
    }
}
