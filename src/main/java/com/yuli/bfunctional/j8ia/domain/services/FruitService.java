//: com.yuli.bfunctional.j8ia.domain.services.FruitService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.Fruit;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FruitService implements IFruitService {

    @Override
    public <T extends Fruit> List<T> sort(List<T> fruits) {
        fruits.sort(Comparator.comparing(T::getWeight));
        return fruits.stream().collect(Collectors.toList());
    }

}///:~