//: com.yuli.bfunctional.j8ia.domain.services.InsuranceServiceTest.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.optional.Car;
import com.yuli.bfunctional.j8ia.domain.model.optional.Insurance;
import com.yuli.bfunctional.j8ia.domain.model.optional.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class InsuranceServiceTest {

    private Optional<Person> optPerson;
    private Optional<Car> optCar;

    private InsuranceService insuranceService;

    @Before
    public void setUp() throws Exception {
        this.insuranceService = new InsuranceService();
    }

    @Test
    public void test_On_Empty_Car() throws Exception {

        // Given
        this.optCar = Optional.empty();
        this.optPerson = Optional.of(new Person());

        // when
        Optional<Insurance> optIns = this.insuranceService.getCheapestInsurance(this.optPerson, this.optCar);

        // Then
        assertThat(optIns.isPresent(), is(false));
    }

    @Test
    public void test_On_Empty_Person() throws Exception {

        // Given
        this.optCar = Optional.of(new Car());
        this.optPerson = Optional.empty();

        // when
        Optional<Insurance> optIns = this.insuranceService.getCheapestInsurance(this.optPerson, this.optCar);

        // Then
        assertThat(optIns.isPresent(), is(false));
    }

    @Test
    public void able_To_Get_Cheapest_Insurance() throws Exception {

        // Given
        this.optCar = Optional.of(new Car());
        this.optPerson = Optional.of(new Person());

        // when
        Optional<Insurance> optIns = this.insuranceService.getCheapestInsurance(this.optPerson, this.optCar);

        // Then
        assertThat(optIns.get().getName(), is("CheapInc"));
    }

}///:~