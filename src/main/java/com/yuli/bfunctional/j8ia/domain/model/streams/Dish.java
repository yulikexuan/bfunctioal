//: com.yuli.bfunctional.j8ia.domain.model.streams.Dish.java


package com.yuli.bfunctional.j8ia.domain.model.streams;


import lombok.*;


@Data
public class Dish {

	public enum Type {
		MEAT, FISH, OTHER,
	}///:~

	private String name;
	private boolean vegetarian;
	private int calories;
	private Type type;

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

}///:~