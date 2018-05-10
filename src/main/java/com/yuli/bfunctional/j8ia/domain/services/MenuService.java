//: com.yuli.bfunctional.j8ia.domain.services.MenuService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;
import com.yuli.bfunctional.j8ia.domain.repositories.IMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.*;
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
 * 12. Given two lists of numbers, how would you return all pairs of numbers?
 *     For example, given a list [1, 2, 3] and a list [3, 4] you should return
 *     [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]?
 *     For simplicity, you can represent a pair as an array with two elements
 *     P99
 *
 * 13. Extend the previous example to return only pairs whose sum is divisible
 *     by 3? For example, (2, 4) and (3, 3) are valid P100
 *
 * 14. Find out whether the menu has a vegetarian option
 *
 * 15. Find out whether the menu is healthy (that is, all dishes are below 1000
 *     calories)
 *
 * 16. Calculate the sum of all calories in the menu P103
 *
 * 17. Find the highest calorie dish in the menu P105
 *
 * 18. Find the lowest calorie dish in the menu P105
 *
 * 19. Count the number of dishes in a stream using the map and reduce methods?
 *
 * 20. Use mapToInt to calculate the sum of calories in the menu P112
 *
 * 21. Use mapToInt to find the max calories in the menu P113
 *
 * 22. Use IntStream to generate a stream of even numbers from 1 to 100 P114
 *
 * 23. Create a stream of Pythagorean triples P114
 *
 * 24. Create a stream of strings directly using Stream.of P117
 *
 * 25. Get an empty stream using Stream.empty P117
 *
 * 26. Convert an array of primitive ints into an IntStream P117
 *
 * 27. Find out the number of unique words in a file P118
 *
 * 28. Use the Stream.iterate method to produces a stream of all even numbers
 *     P118
 *
 * 29. Create infinite streams: Stream.iterate & Stream.generate
 *     Use Stream.iterate to generate Fibonacci tuples series P119
 *
 * 30. Use Stream.generate to generate a stream of five random double numbers
 *     from 0 to 1 P120
 *
 * 31. Use IntStream.generate to generate an infinite stream of ones (1) P120
 *
 * 32. Collectors for Reducing and summarizing:
 *     Count the number of dishes in the menu, using Collectors.counting P127
 *
 * 33. The counting collector can be especially useful when used in combination
 *     with other collectors
 *     Find the highest-calorie dish in the menu using Collectors.maxBy P127
 *     Find the lowest-calorie dish in the menu using Collectors.minBy P127
 *
 *     Summarization
 * 34. Find the total number of calories in your menu list with
 *     Collectors.summingInt P128
 *
 * 35. Calculate the average of the same set of calories P129
 *
 * 36. Count the elements in the menu and obtain the sum, average, maximum,
 *     and minimum of the calories contained in each dish with a single
 *     summarizing operation P1293
 *
 *     Joining strings
 *
 * 37. Concatenate the names of all the dishes in the menu, using commas to
 *     separate each P129
 *
 *     Generalized summarization with reduction
 *
 * 38. Collectors.reducing factory method is a generalization of all
 *     <T,U> Collector<T, ?, U> reducing(U identity,
               Function<? super T,? extends U> mapper, BinaryOperator<U> op)

 *     Calculate the total calories in the menu with a collector created from
 *     the Collectors.reducing method P130
 *
 * 39. Find the highest-calorie dish using the one-argument version of
 *     Collectors.reducing P130
 *     Calculate the total calories of the menu by using Collectors.reducing
 *     and Integer.sum P131
 *     Calculate the total calories of the menu by using Stream.reduce and
 *     Integer.sum P132
 *     Calculate the total calories of the menu by using Stream.mapToInt and
 *     IntStream.sum P133
 *
 * 40. Join all menu item names in one string by using Collectors.joining P133
 *     Join all menu item names in one string by using Collectors.reducing
 *     (one arg and two arts ) P133
 *
 *     Grouping
 * 41. Classify the dishes in the menu according to their type, putting the
 *     ones containing meat in a group, the ones with fish in another group,
 *     and all others in a third group by using Collectors.groupingBy P134
 *
 *     Classify as “diet” all dishes with 400 calories or fewer, set to
 *     “normal” the dishes having between 400 and 700 calories, and set to
 *     “fat” the ones with more than 700 calories P135
 *
 *     Muli-level Grouping
 *     Classify the dishes in the menu according to their type and calories
 *     with two-argument version of the Collectors.groupingBy factory method
 *     P135
 *
 *     Sub-Grouping
 *     Count the number of Dishes in the menu for each type, by passing the
 *     counting collector as a second argument to the groupingBy collector
 *     P137
 *     Find the highest-calorie dish (in Optional) in each type P137
 *     Find the highest-calorie dish in each type (not in Optional) P138
 *     Count total calories in each type P138
 *


 *
Reducing:

Grouping



Multilevel Grouping

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
				.sorted(comparingInt(Dish::getCalories))
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

	@Override
	public List<int[]> getAllPairs(int[] nums1, int[] nums2) {

		if ((nums1 == null) || (nums2 == null)) {
			return new ArrayList<>();
		}

		return Arrays.stream(nums1)
				.boxed()
				.flatMap(n1 -> Arrays.stream(nums2)
						.boxed()
						.map(n2 -> new int[] {n1, n2}))
				.collect(toList());
	}

	@Override
	public List<int[]> getAdvancedPairs(int[] nums1, int[] nums2) {
		if ((nums1 == null) || (nums2 == null)) {
			return new ArrayList<>();
		}

		return Arrays.stream(nums1)
				.boxed()
				.flatMap(n1 -> Arrays.stream(nums2)
						.boxed()
						.filter(n2 -> (n2 + n1) % 3 == 0)
						.map(n2 -> new int[] {n1, n2}))
				.collect(toList());
	}

	@Override
	public boolean hasVegetarianDish() {
		return this.getMenu()
				.anyMatch(Dish::isVegetarian);
	}

	/*
	 * Find out whether the menu is healthy (that is, all dishes are below 1000
	 * calories)
	 */
	@Override
	public boolean hasHealthyDish() {
		return this.getMenu().anyMatch(Dish::isHealthy);
	}

	@Override
	public Dish getAnyVegetarianDish() throws RuntimeException {
		return this.getMenu()
				.filter(Dish::isVegetarian)
				.findAny()
				.orElseThrow(RuntimeException::new);
	}

	@Override
	public boolean isHealthyMenu() {
//		return this.getMenu()
//				.allMatch(Dish::isHealthy);
		return this.getMenu()
				.noneMatch(d -> d.getCalories() >= 1000);
	}

	@Override
	public int firstSquareDivisibleBy3(IntStream data) {

		return data
				.filter(i -> (i * i) % 3 == 0)
				.findFirst()
				.getAsInt();
	}

	@Override
	public int getTotalCalories() {
//		return this.getMenu()
//				.mapToInt(Dish::getCalories)
//				.reduce(0, Integer::sum);

		return this.getMenu()
				.mapToInt(Dish::getCalories)
				.sum();
	}

	@Override
	public Dish getTheHighestCalorieDish() {
//		return this.getMenu()
//				.reduce((d1, d2) ->
//						d1.getCalories() > d2.getCalories() ? d1 : d2)
//				.get();
		return this.getMenu()
				.max(Comparator.comparing(Dish::getCalories))
				.get();
	}

	@Override
	public Dish getTheLowestCalorieDish() {
//		return this.getMenu()
//				.reduce((d1, d2) ->
//						d1.getCalories() < d2.getCalories() ? d1 : d2)
//				.get();
		return this.getMenu()
				.min(Comparator.comparing(Dish::getCalories))
				.get();
	}

	@Override
	public int getDishCount() {
		return this.getMenu()
				.mapToInt(d -> 1)
				.reduce(0, Integer::sum);
	}

	@Override
	public int getTheHeightestCalories() {
		return this.getMenu()
				.mapToInt(Dish::getCalories)
				.reduce(Integer::max)
				.getAsInt();
	}

	@Override
	public int getTheLowestCalories() {
		return this.getMenu()
				.mapToInt(Dish::getCalories)
				.reduce(Integer::min)
				.getAsInt();
	}

	@Override
	public int getCaloriesSum() {
		return this.getMenu()
				.mapToInt(Dish::getCalories)
				.sum();
	}

	@Override
	public int getMaxCalories() {
		return this.getMenu()
				.mapToInt(Dish::getCalories)
				.max()
				.getAsInt();
	}

	@Override
	public int[] getEvenNumbers() {

		return IntStream.rangeClosed(1, 100)
				.filter(i -> i % 2 == 0)
				.toArray();
	}

	@Override
	public Stream<double[]> getPythagoreanTriples() {
		return IntStream.rangeClosed(1, 100)
				.boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						//.filter(b -> getPythagoreanSqrt(a, b) % 1 == 0)
				        .mapToObj(b -> new double[] {
				                a, b, Math.sqrt(a * a + b * b)})
						.filter(t -> t[2] % 1 == 0)
				);
	}

	@Override
	public double getPythagoreanSqrt(int a, int b) {
		return Math.sqrt(a * a + b * b);
	}

	@Override
	public Stream<String> getStringFromStreamOf() {
		return Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
	}

	@Override
	public Stream<String> getEmptyStream() {
		return Stream.empty();
	}

	@Override
	public IntStream convertToIntStream(int[] arr) {
		return Arrays.stream(arr);
	}

	@Override
	public long getNumberOfUniqueWordsFromFile(String fileName) {

		Path filePath = Paths.get(fileName);

		try (Stream<String> lines = Files.lines(filePath,
				Charset.defaultCharset())) {

			return lines.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int[] getEvenNumbersWithIteration(int count) {
		return Stream.iterate(0, i -> i + 2)
				.limit(count)
				.mapToInt(i -> i)
				.toArray();
	}

	// Use Stream.iterate to generate Fibonacci tuples series
	@Override
	public List<Integer> getFibonaccis(int count) {
		int[] init = {0, 1};
		return Stream.iterate(init, arr ->
				new int[] {arr[1], arr[0] + arr[1]})
				.limit(10)
				.map(t -> t[0])
				.collect(toList());
	}

	/*
	 * Use Stream.generate to generate a stream of five random double numbers
	 * from 0 to 1
	 */
	@Override
	public double[] getDoubleRandom(int count) {
		return Stream.generate(Math::random)
				.limit(count)
				.mapToDouble(d -> d)
				.toArray();
	}

	@Override
	public int[] getArrayOfOne(int count) {
		return IntStream
				.generate(() -> 1)
				.limit(count)
				.toArray();
	}

	// COLLECT COLLECTORS ////////////////////////////////////////////////////

	// Count the number of dishes in the menu
	@Override
	public long getNumberOfDishes() {

//		this.getMenu()
//				.mapToInt(m -> 1)
//				.reduce(Integer::sum);
//
//		this.getMenu().count();

		/*
		 * Returns a Collector accepting elements of type T that counts the
		 * number of input elements. If no elements are present, the result is
		 * 0.
		 */
		return this.getMenu()
				.collect(Collectors.counting());
	}

	@Override
	public Optional<Dish> collectHighestCaloriesDish() {

		/*
		 * The reduce
		 */
//		return this.getMenu()
//				.max(Comparator.comparing(Dish::getCalories));

		/*
		 * <T>
		 * Collector<T, ?, Optional<T>> maxBy(Comparator<? super T> comparator)
		 *
		 * Returns a Collector that produces the maximal element according to a
		 * given Comparator, described as an Optional<T>
		 */
		return this.getMenu()
				.collect(Collectors.maxBy(Comparator.comparing(
						Dish::getCalories)));
	}

	@Override
	public Optional<Dish> collectLowestCaloriesDish() {

//		return this.getMenu()
//				.min(Comparator.comparing(Dish::getCalories));

		/*
		 * <T>
		 * Collector<T, ?, Optional<T>> minBy(Comparator<? super T> comparator)
		 *
		 * Returns a Collector that produces the minimal element according to a
		 * given Comparator, described as an Optional<T>.
		 */
		return this.getMenu()
				.collect(Collectors.minBy(Comparator.comparing(
						Dish::getCalories)));
	}

	@Override
	public int collectTotalCalories() {

//		return this.getMenu()
//				.mapToInt(Dish::getCalories)
//				.reduce(0, Integer::sum);

		return this.getMenu()
				.collect(Collectors.summingInt(Dish::getCalories));
	}

	/*
	 * <T> Collector<T,?,Double> averagingInt(ToIntFunction<? super T> mapper)
	 */
	@Override
	public double collectAverageOfCalories() {
		return this.getMenu()
				.collect(Collectors.averagingInt(Dish::getCalories));
	}

	@Override
	public IntSummaryStatistics collectStatistsiceOfCalories() {
		return this.getMenu()
				.collect(Collectors.summarizingInt(
						Dish::getCalories));
	}

	@Override
	public String collectDishNames(CharSequence delimiter,
	                               CharSequence prefix,
	                               CharSequence suffix) {
		return this.getMenu()
				.map(Dish::getName)
				.collect(Collectors.joining(delimiter, prefix, suffix));
	}

	@Override
	public long reducingTotalCalories() {
		return this.getMenu()
				.collect(Collectors.reducing(0, Dish::getCalories,
						Integer::sum));
	}

	@Override
	public Optional<Dish> reducingHighestCaloriesDish() {
		return this.getMenu()
				.collect(reducing((d1, d2) ->
						d1.getCalories() > d2.getCalories() ? d1 : d2));
	}

	@Override
	public String joinStringsWithReducing() {
		return this.getMenu()
				.map(Dish::getName)
				.collect(Collectors.reducing(
						"", (n1, n2) -> n1 + n2));
	}

	@Override
	public Map<Dish.Type, List<Dish>> getDishedByType() {
		return this.getMenu()
				.collect(Collectors.groupingBy(Dish::getType));
	}

	@Override
	public Map<Dish.CALORIC_LEVEL, List<Dish>> getDishedByCaloricLevel() {
		return this.getMenu()
				.collect(Collectors.groupingBy(
						d -> {
							if (d.getCalories() <= Dish.LOW_CALORY_RULE ) {
								return Dish.CALORIC_LEVEL.DIET;
							} else if ((d.getCalories() > Dish.LOW_CALORY_RULE)
							        && (d.getCalories() < 700)) {
								return Dish.CALORIC_LEVEL.NORMAL;
							} else {
								return Dish.CALORIC_LEVEL.FAT;
							}
						}
				));
	}

	@Override
	public Map<Dish.Type, Map<Dish.CALORIC_LEVEL, List<Dish>>>
			getDishByTypeThenCaloricLevel() {

		return this.getMenu()
				.collect(Collectors.groupingBy(Dish::getType,
						Collectors.groupingBy(d -> {
							if (d.isFat()) {
								return Dish.CALORIC_LEVEL.FAT;
							} else if (d.isNormal()) {
								return Dish.CALORIC_LEVEL.NORMAL;
							} else {
								return Dish.CALORIC_LEVEL.DIET;
							}
						})));
	}

	@Override
	public Map<Dish.Type, Long> getDishCountForType() {
		return this.getMenu()
				.collect(groupingBy(Dish::getType, counting()));
	}

	@Override
	public Map<Dish.Type, Optional<Dish>> getHighestCaloriesByType() {
		return this.getMenu()
				.collect(Collectors.groupingBy(Dish::getType,
						maxBy(comparingInt(Dish::getCalories))));
	}

	@Override
	public Map<Dish.Type, Dish> getOneKCaloriesByType() {
		return this.getMenu().
				collect(groupingBy(Dish::getType,
						collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
								Optional::get)));
	}

}///:~
