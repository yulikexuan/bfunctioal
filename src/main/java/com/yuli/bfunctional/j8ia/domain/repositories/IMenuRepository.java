//: com.yuli.bfunctional.j8ia.domain.repositories.IMenuRepository.java


package com.yuli.bfunctional.j8ia.domain.repositories;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;

import java.util.stream.Stream;


public interface IMenuRepository {

	Stream<Dish> getMenu();

}///:~