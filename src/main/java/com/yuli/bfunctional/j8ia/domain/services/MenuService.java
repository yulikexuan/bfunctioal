//: com.yuli.bfunctional.j8ia.domain.services.MenuService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;
import com.yuli.bfunctional.j8ia.domain.repositories.IMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


/*
 * 1.  Names of dishes that are low in calories, sorted by number of calories
 *     P79
 *
 * 2.  Create a vegetarian menu by filtering all vegetarian dishes
 *     P93
 *
 * 3*. Filter all even numbers from a list and makes sure that there are no
 *     duplicates
 *     P94
 *
 * 4.  Create a List by selecting the first three dishes that have more than 300
 *     calories as follows
 *     P94
 *
 * 5.  Skips the first two dishes that have more than 300 calories and returns
 *     the rest P95
 *
 * 6.  Filter the first two meat dishes P96
 *
 * 7.  Extract the names of the dishes in the stream P96
 *
 * 8.  Given a list of words, return a list of the number of characters for
 *     each word P96
 *
 * 9.  Find out the length of the name of each dish P97
 *
 * 10. Return a list of all the unique characters for a list of words P98
 *
 * 11. Given a list of numbers, how would you return a list of the square of
 *     each number? For example, given [1, 2, 3, 4, 5] you should return
 *     [1, 4, 9, 16, 25] P99
 *



Given two lists of numbers, how would you return all pairs of numbers? For example, given a list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]. For simplicity, you can represent a pair as an array with two elements P99

Extend the previous example to return only pairs whose sum isdivisible by 3? For example, (2, 4) and (3, 3) are valid P100

Finding and matching: allMatch, anyMatch, noneMatch, findFirst, and findAny
Find out whether the menu has a vegetarian option P100
Find out whether the menu is healthy (that is, all dishes are below 1000 calories) Can use allMatch or noneMatch P101
Find a dish that’s vegetarian P101
Find the first square that’s divisible by 3 P102

Reducing:
Calculate the sum of all calories in the menu P103
Find the highest calorie dish in the menu P105
Find the lowest calorie dish in the menu P105
How to count the number of dishes in a stream using the map and reduce methods?

Numeric streams:
Use mapToInt to calculate the sum of calories in the menu P112
Use mapToInt to find the max calories in the menu P113
Use IntStream to generate a stream of even numbers from 1 to 100 P114
Create a stream of Pythagorean triples P114

Building streams:
Create a stream of strings directly using Stream.of P117
Get an empty stream using Stream.empty P117
Convert an array of primitive ints into an IntStream P117
Find out the number of unique words in a file P118, Files.lines to return a stream where each element is a line in the given file; Many static methods in java.nio.file.Files return a stream

Create infinite streams: Stream.iterate & Stream.generate
Use the Stream.iterate method to produces a stream of all even numbers P118
Use Stream.iterate to generate Fibonacci tuples series P119
Use Stream.generate to generate a stream of five random double numbers from 0 to 1 P120
Use IntStream.generate to generate an infinite stream of ones (1) P120

Collectors for Reducing and summarizing
Count the number of dishes in the menu, using Collectors.counting P127
The counting collector can be especially useful when used in combination with other collectors
Count the number of dishes in the menu, using Stream.count P127
Find the highest-calorie dish in the menu using Collectors.maxBy P127
Find the lowest-calorie dish in the menu using Collectors.minBy P127

Summarization
Find the total number of calories in your menu list with Collectors.summingInt P128
Calculate the average of the same set of calories P129
Count the elements in the menu and obtain the sum, average, maximum, and minimum of the calories contained in each dish with a single summarizing operation P129
Joining strings
Concatenate the names of all the dishes in the menu, using commas to separate each P129

Generalized summarization with reduction
Collectors.reducing factory method is a generalization of all
Calculate the total calories in the menu with a collector created from the Collectors.reducing method P130
Find the highest-calorie dish using the one-argument version of Collectors.reducing P130
Calculate the total calories of the menu by using Collectors.reducing and Integer.sum P131
Calculate the total calories of the menu by using Stream.reduce and Integer.sum P132
Calculate the total calories of the menu by using Stream.mapToInt and IntStream.sum P133
Join all menu item names in one string by using Collectors.joining P133
Join all menu item names in one string by using Collectors.reducing (one arg and two arts ) P133

Grouping
Classify the dishes in the menu according to their type, putting the ones containing meat in a group, the ones with fish in another group, and all others in a third group by using Collectors.groupingBy P134
Classify as “diet” all dishes with 400 calories or fewer, set to “normal” the dishes having between 400 and 700 calories, and set to “fat” the ones with more than 700 calories P135

Multilevel Grouping
Classify the dishes in the menu according to their type and calories with two-argument version of the Collectors.groupingBy factory method P135
Count the number of Dishes in the menu for each type, by passing the counting collector as a second argument to the groupingBy collector P137
Find the highest-calorie dish (in Optional) in each type P137
Find the highest-calorie dish in each type (not in Optional) P138
Count total calories in each type P138
Which CaloricLevels (in a Set) are available in the menu for each type of Dish P139
Which CaloricLevels (in a HashSet) are available in the menu for each type of Dish P140


Partitioning
You are a vegetarian or have invited a vegetarian friend to have dinner with you, you may be interested in partitioning the menu into vegetarian and nonvegetarian dishes and then retrieve all the vegetarian dishes P140
Achieve the same result of above question by just filtering the stream created from the menu List with the same predicate used for partitioning and then collecting the result in an additional List P141
Group the dishes by their type in both of the substreams of vegetarian and nonvegetarian dishes resulting from the partitioning previously P141
Find the most caloric dish among both vegetarian and nonvegetarian dishes P141
Find dishes which have more than 500 calories among both vegetarian and nonvegetarian dishes P142
Find the count of dishes among both vegetarian and nonvegetarian dishes P142
Write a method accepting as argument an int n and partitioning the first n natural numbers into prime and nonprime P142
*/
@Service
public class MenuService implements IMenuService {

	private final IMenuRepository menuRepository;

	@Autowired
	public MenuService(IMenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public Stream<Dish> getMenu() {
		return this.menuRepository.getMenu();
	}

	@Override
	public List<String> getLowCaloryDishNames() {

		return this.getMenu()
				.filter(Dish::hasLowCalory)
				.sorted(Comparator.comparingInt(Dish::getCalories))
				.map(d -> d.getName())
				.collect(toList());
	}

	@Override
	public List<String> getAllVegetarianDishes() {
		return this.getMenu()
				.filter(Dish::isVegetarian)
				.map(Dish::getName)
				.collect(toList());
	}

	@Override
	public List<String> getFirstThree300CalDishes() {
		return this.getMenu()
				.filter(d -> d.getCalories() > 300)
				.limit(3)
				.map(Dish::getName)
				.collect(toList());
	}

	@Override
	public List<String> getAll300CalDishesExceptFirstTwo() {

		return this.getMenu()
				.filter(d -> d.getCalories() > 300)
				.skip(2)
				.map(Dish::getName)
				.collect(toList());
	}

	@Override
	public List<String> getFirstTwoMeatDishes() {
		return this.getMenu()
				.filter(Dish::isMeat)
				.limit(2)
				.map(Dish::getName)
				.collect(toList());
	}

	@Override
	public List<String> getAllDishNames() {
		return this.getMenu()
				.map(Dish::getName)
				.collect(toList());
	}

	@Override
	public List<Integer> countWordLength(List<String> words) {

		if (words == null) {
			return new ArrayList<>();
		}

		return words.stream()
				.map(String::length)
				.collect(toList());
	}

	@Override
	public List<String> getUniqueCharactors(List<String> words) {

		if (words == null) {
			return new ArrayList<>();
		}

		return words.stream()
				.map(w -> w.split(""))
				.flatMap(Arrays::stream) // To avoid Stream<Stream<String>>
				.distinct()
				.collect(toList());
	}

	@Override
	public int[] getSquares(int[] numbers) {
		return Arrays.stream(numbers)
				.map(i -> i * i)
				.toArray();
	}

	@Override
	public int[] filterEvenNumbers(int max) {

		if (max > 100) {
			System.out.println("Too many numbers to produce!");
			return new int[0];
		}

		return IntStream.rangeClosed(0, max)
				.filter(i -> i % 2 == 0)
				.toArray();
	}

}///:~