//: com.yuli.bfunctional.j8ia.domain.model.streams.Dish.java


package com.yuli.bfunctional.j8ia.domain.model.streams;


import lombok.*;


@Data
public class Dish {

	static final int LOW_CALORY_RULE = 400;

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

	public boolean hasLowCalory() {
		return this.calories >= LOW_CALORY_RULE ? false : true;
	}

	public boolean isMeat() {
		return this.type == Type.MEAT;
	}

	public boolean isHealthy() {
		return this.calories < 1000;
	}

}///:~