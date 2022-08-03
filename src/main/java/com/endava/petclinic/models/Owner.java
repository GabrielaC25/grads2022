package com.endava.petclinic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Owner {
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;

    public Owner() {
    }

    public Owner(Integer id, String firstname, String lastName, String address, String city, String telephone) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    public Owner(String firstname, String lastName, String address, String city, String telephone) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(getFirstName(), owner.getFirstName()) && Objects.equals(getLastName(), owner.getLastName()) && Objects.equals(getAddress(), owner.getAddress()) && Objects.equals(getCity(), owner.getCity()) && Objects.equals(getTelephone(), owner.getTelephone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAddress(), getCity(), getTelephone());
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adress='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
