//: com.yuli.bfunctional.j8ia.domain.model.optional.Person.java


package com.yuli.bfunctional.j8ia.domain.model.optional;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@NoArgsConstructor
public class Person {

    private Car car;

    public Person(Car car) {
        this.car = car;
    }

    public Optional<Car> getCarAsOptional() {
        return Optional.ofNullable(this.car);
    }

}///:~