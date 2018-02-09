//: com.yuli.bfunctional.j8ia.domain.repositories.MenuRepository.java


package com.yuli.bfunctional.j8ia.domain.repositories;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public class MenuRepository implements IMenuRepository {

	@Override
	public List<Dish> getMenu() {
		List menu = Arrays.asList(
				new Dish("pork", false, 800,
						Dish.Type.MEAT),
				new Dish("beef", false, 700,
						Dish.Type.MEAT),
				new Dish("chicken", false, 400,
						Dish.Type.MEAT),
				new Dish("french fries", true, 530,
						Dish.Type.OTHER),
				new Dish("rice", true, 350,
						Dish.Type.OTHER),
				new Dish("season fruit", true, 120,
						Dish.Type.OTHER),
				new Dish("pizza", true, 550,
						Dish.Type.OTHER),
				new Dish("prawns", false, 300,
						Dish.Type.FISH),
				new Dish("salmon", false, 450,
						Dish.Type.FISH)
		);

		return menu;
	}

}///:~