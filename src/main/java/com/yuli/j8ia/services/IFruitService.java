//: com.yuli.j8ia.services.IFruitService.java


package com.yuli.j8ia.services;


import com.yuli.j8ia.domain.model.*;

import java.util.*;


public interface IFruitService {

    <T extends Fruit> List<T> sort(List<T> fruits);

}///:~