//: com.yuli.bfunctional.j8ia.domain.services.IMenuService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;

import java.util.List;
import java.util.stream.Stream;


public interface IMenuService {

	Stream<Dish> getMenu();

	List<String> getLowCaloryDishNames();
	List<String> getAllVegetarianDishes();
	List<String> getFirstThree300CalDishes();
	List<String> getAll300CalDishesExceptFirstTwo();
	List<String> getFirstTwoMeatDishes();
	List<String> getAllDishNames();
	List<Integer> countWordLength(List<String> words);
	List<String> getUniqueCharactors(List<String> words);
	int[] getSquares(int[] numbers);
	int[] filterEvenNumbers(int max);

}///:~