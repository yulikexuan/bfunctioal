//: com.yuli.bfunctional.j8ia.domain.model.optional.PersonTest.java


package com.yuli.bfunctional.j8ia.domain.model.optional;


import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class PersonTest {

    private Person person;
    private Car car;
    private Insurance insurance;
    private String instranceName;

    private Optional<Person> optPerson;

    @Before
    public void setUp() throws Exception {
        this.instranceName = UUID.randomUUID().toString();
        this.insurance = new Insurance(this.instranceName);
        this.car = new Car(this.insurance);
        this.person = new Person(this.car);
        this.optPerson = Optional.ofNullable(this.person);
    }

    @Test
    public void able_To_Get_Insurance_Name_From_Person() throws Exception {

        // Given

        // When
        String name = this.optPerson.flatMap(Person::getCarAsOptional).flatMap(Car::getInsuranceAsOptoinal).map(Insurance::getName).orElse("Unknown");

        // Then
        assertThat(name, is(this.instranceName));
    }

}///:~