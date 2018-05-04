//: com.yuli.bfunctional.j8ia.domain.model.optional.CarTest.java


package com.yuli.bfunctional.j8ia.domain.model.optional;


import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(Theories.class)
public class CarTest {

	private Optional<Car> optCar;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void optCar_Can_Be_Empty() throws Exception {

		// When
		this.optCar = Optional.empty();

		// Then
		assertThat(this.optCar.isPresent(), is(false));
	}

	@DataPoint public static Car car = new Car();
	@DataPoint public static Car nullCar = null;
	@Theory
	public void optCar_Can_Be_From_Nullable_Value(Car car) throws Exception {

		// Given

		// When
		this.optCar = Optional.ofNullable(car);

		// Then
	}

}///:~