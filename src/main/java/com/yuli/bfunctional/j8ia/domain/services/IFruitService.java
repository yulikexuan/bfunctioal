//: com.yuli.bfunctional.j8ia.domain.services.IFruitService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.*;

import java.util.*;


public interface IFruitService {

    <T extends Fruit> List<T> sort(List<T> fruits);

}///:~