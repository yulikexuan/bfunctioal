//: com.yuli.bfunctional.j8ia.domain.services.InsuranceService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.optional.Car;
import com.yuli.bfunctional.j8ia.domain.model.optional.Insurance;
import com.yuli.bfunctional.j8ia.domain.model.optional.Person;
import lombok.NonNull;

import java.util.Optional;


public class InsuranceService {

	public Optional<Insurance> getCheapestInsurance(
			Optional<Person> optPerson, Optional<Car> optCar) {

		return optPerson.flatMap(
				p -> optCar.map(c -> {
					System.out.println("In map function of car");
					return findCheapestInsurance(p, c);
				}));
	}

	private Insurance findCheapestInsurance(@NonNull Person person,
	                                        @NonNull Car car) {

		return new Insurance("CheapInc");
	}

}///:~