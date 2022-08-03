package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.PetType;
import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class PetController {
    public Pet generateNewRandomPet(Owner owner, PetType petType){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        Faker faker = new Faker();
        Pet pet = new Pet();


        pet.setName(faker.name().name());
        pet.setOwner(new Owner());
        pet.setType(new PetType());
        pet.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        pet.setOwner(owner);
        pet.setType(petType);
        return pet;

    }
}
