//: com.yuli.bfunctional.j8ia.domain.model.optional.Car.java


package com.yuli.bfunctional.j8ia.domain.model.optional;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@NoArgsConstructor
public class Car {

	private Insurance insurance;

	public Car(Insurance insurance) {
		this.insurance = insurance;
	}

	public Optional<Insurance> getInsuranceAsOptoinal() {
		return Optional.ofNullable(this.insurance);
	}

}///:~