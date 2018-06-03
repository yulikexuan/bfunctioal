//: com.yuli.bfunctional.j8ia.domain.services.IMenuService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;

import java.util.*;
import java.util.stream.IntStream;
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

	List<int[]> getAllPairs(int[] nums1, int[] nums2);

	List<int[]> getAdvancedPairs(int[] nums1, int[] nums2);

	boolean hasVegetarianDish();

	boolean hasHealthyDish();

	boolean isHealthyMenu();

	Dish getAnyVegetarianDish();

	int firstSquareDivisibleBy3(IntStream data);

	int getTotalCalories();

	int getTheHeightestCalories();

	int getTheLowestCalories();

	Dish getTheHighestCalorieDish();

	Dish getTheLowestCalorieDish();

	int getDishCount();

	int getCaloriesSum();

	int getMaxCalories();

	int[] getEvenNumbers();

	Stream<double[]> getPythagoreanTriples();

	double getPythagoreanSqrt(int a, int b);

	Stream<String> getStringFromStreamOf();

	Stream<String> getEmptyStream();

	IntStream convertToIntStream(int[] arr);

	long getNumberOfUniqueWordsFromFile(String fileName);

	int[] getEvenNumbersWithIteration(int count);

	List<Integer> getFibonaccis(int count);

	double[] getDoubleRandom(int count);

	int[] getArrayOfOne(int count);

	long getNumberOfDishes();

	Optional<Dish> collectHighestCaloriesDish();

	Optional<Dish> collectLowestCaloriesDish();

	int collectTotalCalories();

	double collectAverageOfCalories();

	IntSummaryStatistics collectStatistsiceOfCalories();

	String collectDishNames(CharSequence delimiter,
	                        CharSequence prefix,
	                        CharSequence suffix);

	long reducingTotalCalories();

	Optional<Dish> reducingHighestCaloriesDish();

	String joinStringsWithReducing();

	Map<Dish.Type, List<Dish>> getDishedByType();

	Map<Dish.CALORIC_LEVEL, List<Dish>> getDishedByCaloricLevel();

	Map<Dish.Type, Map<Dish.CALORIC_LEVEL, List<Dish>>>
	getDishByTypeThenCaloricLevel();

	Map<Dish.Type, Long> getDishCountForType();

	Map<Dish.Type, Optional<Dish>> getHighestCaloriesByType();

	Map<Dish.Type, Dish> getOneKCaloriesByType();

	Map<Dish.Type, Set<Dish.CALORIC_LEVEL>>
	getCaloricLevelsForEachTypeInHashSet();

	Map<Dish.Type, Set<Dish.CALORIC_LEVEL>> getCaloricLevelsForEachType();

	List<Dish> getAllVegetarianDishesByPartitioning();

	Map<Boolean, Map<Dish.Type, List<Dish>>> getVegetarianDishesByType();

	Map<Boolean, Dish> getTheMostCaloricDishAmongBothVegenAndNonvegen();

	Map<Boolean, Map<Boolean, List<Dish>>> getDishHavingSpecificCaloriesAmongVegenAndNonvegen(
			int calories);

	Map<Boolean, Long> getDishCountAmongVegenAndNonVegen();

	Map<Boolean, List<Integer>> getPrimesAndNonPrimesBelow(int candidate);

	static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot)
				.noneMatch(i -> candidate % i == 0);
	}

}///:~