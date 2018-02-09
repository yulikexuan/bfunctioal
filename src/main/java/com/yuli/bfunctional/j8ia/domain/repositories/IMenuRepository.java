//: com.yuli.bfunctional.j8ia.domain.repositories.IMenuRepository.java


package com.yuli.bfunctional.j8ia.domain.repositories;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IMenuRepository {

	List<Dish> getMenu();

}///:~