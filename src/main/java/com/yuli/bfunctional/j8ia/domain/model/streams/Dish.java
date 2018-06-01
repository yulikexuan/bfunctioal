//: com.yuli.bfunctional.j8ia.domain.model.streams.Dish.java


package com.yuli.bfunctional.j8ia.domain.model.streams;


import lombok.*;


@Data
public class Dish {

	public static final int LOW_CALORY_RULE = 400;
	public static final int FAT_CALORY_RULE = 700;

	public enum Type {
		MEAT, FISH, OTHER,
	}//:~

	public enum CALORIC_LEVEL {
		DIET, NORMAL, FAT,
	}

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
	public boolean isFish() {
		return this.type == Type.FISH;
	}
	public boolean isOtherType() {
		return this.type == Type.OTHER;
	}
	public boolean isHealthy() {
		return this.calories < 1000;
	}

	public boolean isDiet() {
		return this.getCalories() < LOW_CALORY_RULE;
	}

	public boolean isNormal() {
		return this.getCalories() >= LOW_CALORY_RULE &&
				this.getCalories() < FAT_CALORY_RULE;
	}

	public boolean isFat() {
		return this.getCalories() >= FAT_CALORY_RULE;
	}

	public CALORIC_LEVEL getCaloricLevel() {
		if (this.isDiet()) {
			return CALORIC_LEVEL.DIET;
		} else if (this.isNormal()) {
			return CALORIC_LEVEL.NORMAL;
		} else {
			return CALORIC_LEVEL.FAT;
		}
	}

	public int getCalories() {
		return this.calories;
	}

}///:~