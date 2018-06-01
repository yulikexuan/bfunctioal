//: com.yuli.bfunctional.j8ia.domain.services.MenuServiceTest.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;
import com.yuli.bfunctional.j8ia.domain.repositories.IMenuRepository;
import com.yuli.bfunctional.j8ia.domain.repositories.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;


/*
 * 1.  Names of dishes that are low in calories, sorted by number of calories
 *     P79
 *
 * 2.  Create a vegetarian menu by filtering all vegetarian dishes
 *     P93
 *
 * 3.  Filter all even numbers from a list and makes sure that there are no
 *     duplicates
 *     P94
 *
 * 4.  Create a List by selecting the first three dishes that have more than 300
 *     calories as follows
 *     P94
 */
@Slf4j
public class MenuServiceTest {

	private IMenuRepository menuRepository;
	private MenuService menuService;

	private Random random;
	private List<String> words;

	@Before
	public void setUp() throws Exception {
		this.menuRepository = new MenuRepository();
		this.menuService = new MenuService(this.menuRepository);
		this.random = new Random(System.currentTimeMillis());
		this.words = Arrays.asList("Java8", "Lambdas", "Streams",
				"In", "Action");
	}

	@Test
	public void able_To_List_Dish_Names_Hving_Low_Calories_And_Sorted_By_Calories() {

		// When
		List<String> names = this.menuService.getLowCaloryDishNames();

		log.debug(names.toString());

		// Then
		assertThat(names.size(), is(3));
		assertThat(names.get(0), is("season fruit"));
		assertThat(names.get(1), is("prawns"));
		assertThat(names.get(2), is("rice"));
	}

	@Test
	public void able_To_Get_All_Vegetarian_Dishes() throws Exception {

		// When
		List<String> vegetarianDishes = this.menuService.getAllVegetarianDishes();

		// Then
		assertThat(vegetarianDishes, contains("french fries", "rice",
				"season fruit", "pizza"));
	}

	@Test
	public void able_To_Get_A_List_Of_Dish_Name_Which_Having_More_Than_300_Calories() throws Exception {

		// When
		List<String> dishes = this.menuService.getFirstThree300CalDishes();

		// Then
		assertThat(dishes.size(), is(3));
		assertThat(dishes.indexOf("pork"), is(0));
		assertThat(dishes.indexOf("beef"), is(1));
		assertThat(dishes.indexOf("chicken"), is(2));
	}

	@Test
	public void able_To_Filter_All_Even_Numbers_Without_Duplication() throws Exception {

		// Given
		int max = this.random.nextInt(30);

		// When
		int[] evens = this.menuService.filterEvenNumbers(max);

		// Then
		Arrays.stream(evens)
				.forEach((i) -> System.out.println(i));
	}

	@Test
	public void able_To_Get_Dishes_Which_Have_More_Than_300_Calories_Without_First_Two() {

		// When
		List<String> dishes = this.menuService
				.getAll300CalDishesExceptFirstTwo();

		// Then
		assertThat(dishes.size(), is(5));
		assertThat(dishes.get(4), is("salmon"));
	}

	@Test
	public void able_To_Filter_First_Two_Meat_Dishes() throws Exception {

		// When
		List<String> dishes = this.menuService.getFirstTwoMeatDishes();

		// Then
		assertThat(dishes.size(), is(2));
		assertThat(dishes, contains("pork", "beef"));
	}

	@Test
	public void able_To_Extract_All_Names_Of_Dished() throws Exception {

		// When
		List<String> dishNames = this.menuService.getAllDishNames();

		// Then
		assertThat(dishNames, contains("pork", "beef", "chicken",
				"french fries", "rice", "season fruit", "pizza", "prawns",
				"salmon"));
	}

	@Test
	public void able_To_List_Numbers_Of_Characters_Of_A_List_Of_String() throws Exception {

		// Given

		// When
		List<Integer> wordLengths = this.menuService.countWordLength(this.words);

		// Then
		assertThat(wordLengths, contains(5, 7, 7, 2, 6));
	}

	@Test
	public void able_To_List_Numbers_Of_Characters_Of_Dash_Names() throws Exception {

		// Given
		List<String> words = this.menuService.getMenu()
				.map(Dish::getName)
				.collect(Collectors.toList());

		// When
		List<Integer> nameLengths = this.menuService.countWordLength(words);

		// Then
		assertThat(nameLengths, contains(4, 4, 7, 12, 4, 12, 5, 6, 6));
	}

	//Return a list of all the unique characters for a list of words P98


	@Test
	public void able_To_Get_A_List_Of_All_Unique_Chatactors_From_A_List_Of_Words() throws Exception {

		// When
		List<String> chars = this.menuService.getUniqueCharactors(this.words);

		// Then

		// "Java8", "Lambdas", "Streams", "In", "Action"
		assertThat(chars.size(), is(19));
		assertThat(chars, contains("J", "a", "v", "8", "L", "m", "b", "d", "s",
				"S", "t", "r", "e", "I", "n", "A", "c", "i", "o"));
	}

	@Test
	public void able_To_Get_A_List_Of_Square_Nums_From_A_List_Of_Number() throws Exception {

		// Given
		int[] numbers = {
				1, 2, 3, 4, 5
		};

		// When
		int[] squares = this.menuService.getSquares(numbers);

		// Then
		assertThat(squares, is(new int[] {1, 4, 9, 16, 25}));
	}

	@Test
	public void able_To_Generate_All_Pairs_From_Two_List_Of_Numbers() throws Exception {

		// Given
		int[] nums1 = {1, 2, 3};
		int[] nums2 = {3, 4};

		List<int[]> expected = Arrays.asList(
				new int[] {1, 3},
				new int[] {1, 4},
				new int[] {2, 3},
				new int[] {2, 4},
				new int[] {3, 3},
				new int[] {3, 4}
		);

		// When
		List<int[]> pairs = this.menuService.getAllPairs(nums1, nums2);

		// Then
		assertThat(pairs.size(), is(6));

		for (int i = 0; i < expected.size(); i++) {
			assertArrayEquals(pairs.get(i), expected.get(i));
		}
	}

	@Test
	public void able_To_Generate_Pairs_Which_Sum_Is_Divisible_By_3() throws Exception {

		// Given
		int[] nums1 = {1, 2, 3};
		int[] nums2 = {3, 4};

		List<int[]> expected = Arrays.asList(
				new int[] {1, 3},
				new int[] {1, 4},
				new int[] {2, 3},
				new int[] {2, 4},
				new int[] {3, 3},
				new int[] {3, 4}
		);

		// When
		List<int[]> pairs = this.menuService.getAdvancedPairs(nums1, nums2);

		// Then
		assertThat(pairs.size(), is(2));

		boolean sumIsSix = !pairs.stream()
				.mapToInt(a -> a[0] + a[1])
				.filter(i -> (i - 6) != 0)
				.findAny()
				.isPresent();

		assertThat(sumIsSix, is(true));
	}

	@Test
	public void able_To_Know_If_Having_Any_Vegetarian_Dish() throws Exception {

		// When
		boolean havingVegetarianDish = this.menuService.hasVegetarianDish();

		// Them
		assertThat(havingVegetarianDish, is(true));
	}

	@Test
	public void able_To_Know_If_Having_Healthy_Dish() throws Exception {

		// When
		boolean havingHealthyDish = this.menuService.hasHealthyDish();

		// Them
		assertThat(havingHealthyDish, is(true));
	}

	@Test
	public void able_To_Get_Any_Vegetarian_Dish() throws Exception {

		// When
		Dish vDish = this.menuService.getAnyVegetarianDish();

		// Then
		assertThat(vDish, notNullValue());
	}

	@Test
	public void able_To_Know_If_The_Whole_Menu_Is_Healthy() throws Exception {

		// When
		boolean isAHealthyMenu = this.menuService.isHealthyMenu();

		// Then
		assertThat(isAHealthyMenu, is(true));
	}

	@Test
	public void able_To_Get_The_First_Number_Whose_Square_Is_Divisible_By_3() throws Exception {

		// Given
		IntStream data = IntStream.rangeClosed(1, 10);

		// When
		int number = this.menuService.firstSquareDivisibleBy3(data);

		// Then
		assertThat(number, is(3));
	}

	@Test
	public void able_To_Know_Total_Calories() throws Exception {

		// Given
		int realTotal = 800 + 700 + 400 + 530 + 350 + 120 + 550 + 300 + 450;

		// When
		int total = this.menuService.getTotalCalories();

		// Then
		assertThat(total, is(realTotal));
	}

	@Test
	public void able_To_Know_Which_Dish_Has_The_Heightest_Calories() throws Exception {

		// When
		Dish hcDish = this.menuService.getTheHighestCalorieDish();

		// Then
		assertThat(hcDish.getName(), is("pork"));
		assertThat(hcDish.getCalories(), is(800));
	}

	@Test
	public void able_To_Know_Which_Dish_Has_The_Lowest_Calories() throws Exception {

		// When
		Dish lcDish = this.menuService.getTheLowestCalorieDish();

		// Then
		assertThat(lcDish.getName(), is("season fruit"));
		assertThat(lcDish.getCalories(), is(120));
	}

	@Test
	public void able_To_Know_How_Many_Dishes_In_The_Menu() throws Exception {

		// When
		int count = this.menuService.getDishCount();

		// Then
		assertThat(count, is(9));
	}

	@Test
	public void able_To_Know_The_Heightest_And_Lowest_Calories() throws Exception {

		// When
		int heightest = this.menuService.getTheHeightestCalories();
		int lowest = this.menuService.getTheLowestCalories();

		// Then
		assertThat(heightest, is(800));
		assertThat(lowest, is(120));
	}

	@Test
	public void able_To_Know_Sum_Of_Calories() throws Exception {

		// Given
		int total = this.menuService.getTotalCalories();

		// When
		int sum = this.menuService.getCaloriesSum();

		// Then
		assertThat(total, is(sum));
	}

	@Test
	public void able_To_Know_The_Max_Calories() throws Exception {

		// When
		int maxC = this.menuService.getMaxCalories();

		// Then
		assertThat(maxC, is(800));
	}

	@Test
	public void able_To_Generate_An_Array_Of_Even_Numbers() throws Exception {

		// When
		int[] evenNums = this.menuService.getEvenNumbers();

		// Then
		assertThat(evenNums.length, is(50));
		boolean notAllEven = Arrays.stream(evenNums)
				//.peek(i -> System.out.println(i))
				.anyMatch(i -> i % 2 !=0);
		assertThat(notAllEven, is(false));
	}

	@Test
	public void able_To_Know_Pythagorean_Triples() throws Exception {

		// When
		this.menuService.getPythagoreanTriples()
				.limit(5)
				.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " +
				        t[2]));

	}

	@Test
	public void able_To_Generate_String_From_Stream_Of() throws Exception {

		// When
		String result = this.menuService.getStringFromStreamOf()
				.reduce("", String::concat);

		// Then
		assertThat(result, is("Java 8 Lambdas In Action"));
	}

	@Test
	public void able_To_Get_An_Empty_Stream_Of_String() throws Exception {

		// When
		Stream<String> strStream = this.menuService.getEmptyStream();

		// Then
		assertThat(strStream.count(), is(0L));
	}

	@Test
	public void able_To_Convert_Int_Arr_To_IntStream() throws Exception {

		// Given
		int[] intArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, };

		// When
		IntStream intStream = this.menuService.convertToIntStream(intArr);

		// Then
		intStream.forEach(System.out::print);
	}

	@Test
	public void able_To_Count_Distinct_Words_In_A_File() throws Exception {

		// Given
		String fileName = "data.txt";

		// When
		long num = this.menuService.getNumberOfUniqueWordsFromFile(fileName);

		// Then
		// System.out.println(num);
		assertThat(num, is(43L));
	}

	@Test
	public void able_To_Generate_Even_Numbers_From_Stream_Iterate_Method() throws Exception {

		// Given
		int count = 10;
		int[] evenNums = {
				0, 2, 4, 6, 8, 10, 12, 14, 16, 18,
		};

		// When
		int[] result = this.menuService.getEvenNumbersWithIteration(count);

		// Then
        assertThat(result, equalTo(result));
	}

	@Test
	public void able_To_Generate_Fibonaccis() throws Exception {

		// Given
		int count = 10;
		List<Integer> expected = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);


		// When
		List<Integer> fibonaccis = this.menuService.getFibonaccis(10);

		// Then
		assertThat(fibonaccis, equalTo(expected));
	}

	@Test
	public void able_To_Generate_Randoms() throws Exception {

		// Given
		int count = 10;

		// When
		double[] randoms = this.menuService.getDoubleRandom(count);

		// Then
		assertThat(randoms.length, is(count));
		Arrays.stream(randoms).forEach(d -> System.out.println(d));
	}

	@Test
	public void able_To_Generate_An_Array_Of_One() throws Exception {

		// Given
		int count = 10;
		int[] expected = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, };

		// When
		int[] oneArray = this.menuService.getArrayOfOne(count);

		// Then
		assertThat(oneArray, equalTo(expected));
	}

	@Test
	public void able_To_Know_How_Many_Dishes_In_Menu() throws Exception {

		// When
		int dishCount = this.menuService.getDishCount();
		long dishNum = this.menuService.getMenu()
				.count();
		long result = this.menuService.getNumberOfDishes();

		// Then
		assertThat(result, equalTo(dishNum));
		assertThat(result, equalTo((long)dishCount));
	}

	@Test
	public void able_To_Know_The_Dish_With_Highest_Calories() throws Exception {

		// Given
		Dish expected = this.menuService.getTheHighestCalorieDish();

		// When
		Dish hcDish = this.menuService.collectHighestCaloriesDish().get();

		// Then
		assertThat(expected, is(hcDish));
	}

	@Test
	public void able_To_Know_The_Dish_With_Lowest_Calories() throws Exception {

		// Given
		Dish expected = this.menuService.getTheLowestCalorieDish();

		// When
		Dish lcDish = this.menuService.collectLowestCaloriesDish().get();

		// Then
		assertThat(expected, is(lcDish));
	}

	@Test
	public void able_To_Collect_Total_Calories() throws Exception {

		// Given
		int expected = this.menuService.getTotalCalories();

		// When
		int totalCalcories = this.menuService.collectTotalCalories();

		// Then
		assertThat(expected, is(totalCalcories));
	}

	@Test
	public void able_To_Collect_Average_Of_Calories() throws Exception {

		// Given
		double expected = (double)this.menuService.collectTotalCalories() /
				this.menuService.getDishCount();

		// When
		double aveCalories = this.menuService.collectAverageOfCalories();

		// Then
		assertThat(expected, is(aveCalories));
	}

	@Test
	public void able_To_Collect_Summary_Statistics_Of_Calories() throws Exception {

		// When
		IntSummaryStatistics iss = this.menuService.collectStatistsiceOfCalories();
		System.out.println(iss);

		// Then
		assertThat(iss.getAverage(),
				is(this.menuService.collectAverageOfCalories()));
		assertThat(iss.getMax(), is(this.menuService.getMaxCalories()));
		assertThat(iss.getMin(), is(this.menuService.getTheLowestCalories()));
		assertThat(iss.getCount(), is((long)this.menuService.getDishCount()));
		assertThat(iss.getSum(), is((long)this.menuService.getTotalCalories()));
	}

	@Test
	public void able_To_Collect_All_Dish_Names() throws Exception {

		// Given
		String expected = "Dish names: [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon] ";

		// When
		String dishNames = this.menuService.collectDishNames(
				", ", "Dish names: [", "] ");
//		System.out.println(dishNames);

		// Then
		assertThat(dishNames, is(expected));
	}

	@Test
	public void able_To_Reducing_Calories() throws Exception {

		// When
		long total = this.menuService.reducingTotalCalories();

		// Then
		assertThat(total, is((long)this.menuService.getTotalCalories()));
	}

	@Test
	public void able_To_Reducing_The_Dish_With_Highest_Calories() throws Exception {

		// Given

		// When
		Optional<Dish> hcDish = this.menuService.reducingHighestCaloriesDish();

		// Then
		assertThat(hcDish, is(this.menuService.collectHighestCaloriesDish()));
	}

	@Test
	public void able_To_Join_Dish_Names_With_Reducing_Of_Collectors() throws Exception {

		// Givne
		String expected = "porkbeefchickenfrench friesriceseason fruitpizza" +
				"prawnssalmon";

		// When
		String allNames = this.menuService.joinStringsWithReducing();

		// Then
//		System.out.println(allNames);
		assertThat(allNames, is(expected));
	}

	@Test
	public void able_To_Group_Dishes_By_Type() throws Exception {

		// Given
		List<Dish> meatDishes = this.menuService.getMenu()
				.filter(Dish::isMeat)
				.collect(Collectors.toList());

		List<Dish> fishDished = this.menuService.getMenu()
				.filter(Dish::isFish)
				.collect(Collectors.toList());

		List<Dish> otherDishes = this.menuService.getMenu()
				.filter(Dish::isOtherType)
				.collect(Collectors.toList());

		 // When
		Map<Dish.Type, List<Dish>> dishesByType =
				this.menuService.getDishedByType();

		// Then
		assertThat(dishesByType, IsMapContaining.hasEntry(Dish.Type.MEAT,
				meatDishes));
		assertThat(dishesByType, IsMapContaining.hasEntry(Dish.Type.FISH,
		        fishDished));
		assertThat(dishesByType, IsMapContaining.hasEntry(Dish.Type.OTHER,
				otherDishes));
	}

	@Test
	public void able_To_Group_Dishes_By_Caloric_Level() throws Exception {

		// Given
		List<Dish> dietDishes = this.menuService.getMenu()
				.filter(d -> d.getCalories() <= Dish.LOW_CALORY_RULE)
				.collect(Collectors.toList());

		List<Dish> normalDishes = this.menuService.getMenu()
				.filter(d -> d.getCalories() > Dish.LOW_CALORY_RULE &&
						d.getCalories() < 700)
				.collect(Collectors.toList());

		List<Dish> fatDishes = this.menuService.getMenu()
				.filter(d -> d.getCalories() >= 700)
				.collect(Collectors.toList());

		// When
		Map<Dish.CALORIC_LEVEL, List<Dish>> dishesByCaloricLevel =
				this.menuService.getDishedByCaloricLevel();

		// Then
//		System.out.println(dishesByCaloricLevel);
		assertThat(dishesByCaloricLevel, IsMapContaining.hasEntry(
				Dish.CALORIC_LEVEL.DIET, dietDishes));
		assertThat(dishesByCaloricLevel, IsMapContaining.hasEntry(
				Dish.CALORIC_LEVEL.NORMAL, normalDishes));
		assertThat(dishesByCaloricLevel, IsMapContaining.hasEntry(
				Dish.CALORIC_LEVEL.FAT, fatDishes));
	}

	@Test
	public void able_To_Group_Dishes_By_Type_Then_By_Caloric_Level() throws Exception {

		// Given
		Map<Dish.CALORIC_LEVEL, List<Dish>> dietMeatDishes = new HashMap<>();
		List<Dish> dietMeat = this.menuService.getMenu()
				.filter(d -> d.getName().equals("chicken"))
				.collect(Collectors.toList());
		dietMeatDishes.put(Dish.CALORIC_LEVEL.DIET, dietMeat);

		// When
		Map<Dish.Type, Map<Dish.CALORIC_LEVEL, List<Dish>>> dashesGroups =
				this.menuService.getDishByTypeThenCaloricLevel();

		// Then
	}

	@Test
	public void able_To_Group_Dish_Counts_By_Dish_Type() throws Exception {

		// When
		Map<Dish.Type, Long> dishCounts = this.menuService.getDishCountForType();

		// Then
		assertThat(dishCounts.get(Dish.Type.MEAT), is(3L));
		assertThat(dishCounts.get(Dish.Type.FISH), is(2L));
		assertThat(dishCounts.get(Dish.Type.OTHER), is(4L));
	}

	@Test
	public void able_To_Get_Highest_Calories_By_Type() throws Exception {

		// When
		Map<Dish.Type, Optional<Dish>> highestCaloriesByType =
				this.menuService.getHighestCaloriesByType();

		Dish fatFish = highestCaloriesByType.get(Dish.Type.FISH)
				.get();

		// Then
		assertThat(fatFish.getName(), is("salmon"));
	}

	@Test
	public void able_To_Get_One_K_Calories_Dish_By_Type() throws Exception {

		// When
		Map<Dish.Type, Dish> oneKDishes =
				this.menuService.getOneKCaloriesByType();

		// Then
		System.out.println(oneKDishes);
	}

	@Test
	public void able_To_Know_Which_Caloric_Levels_In_Each_Type() throws Exception {

		Set<Dish.CALORIC_LEVEL> caloricLevelsForMeat =
				this.menuService.getMenu()
				        .filter(Dish::isMeat)
				        .map(Dish::getCaloricLevel)
				        .collect(Collectors.toSet());

		Set<Dish.CALORIC_LEVEL> caloricLevelsForFish =
				this.menuService.getMenu()
						.filter(Dish::isFish)
						.map(Dish::getCaloricLevel)
						.collect(Collectors.toSet());

		Set<Dish.CALORIC_LEVEL> caloricLevelsForOther =
				this.menuService.getMenu()
						.filter(Dish::isOtherType)
						.map(Dish::getCaloricLevel)
						.collect(Collectors.toSet());

		// When
		Map<Dish.Type, Set<Dish.CALORIC_LEVEL>> levelsForTypes =
				this.menuService.getCaloricLevelsForEachType();

		Map<Dish.Type, Set<Dish.CALORIC_LEVEL>> levelsForTypesInHashSet =
				this.menuService.getCaloricLevelsForEachTypeInHashSet();

		// Then
		assertThat(levelsForTypes, IsMapContaining.hasEntry(Dish.Type.MEAT,
				caloricLevelsForMeat));
		assertThat(levelsForTypes, IsMapContaining.hasEntry(Dish.Type.FISH,
				caloricLevelsForFish));
		assertThat(levelsForTypes, IsMapContaining.hasEntry(Dish.Type.OTHER,
				caloricLevelsForOther));

		assertThat(levelsForTypesInHashSet.get(Dish.Type.MEAT),
				instanceOf(HashSet.class));
		assertThat(levelsForTypesInHashSet.get(Dish.Type.FISH),
				instanceOf(HashSet.class));
		assertThat(levelsForTypesInHashSet.get(Dish.Type.OTHER),
				instanceOf(HashSet.class));

	}//:End of able_To_Know_Which_Caloric_Levels_In_Each_Type()

	@Test
	public void able_To_Get_All_Vegetarian_Dishes_By_Partitioning() throws Exception {

		// Givne
		List<Dish> expectedVegetarianDishes = this.menuService.getMenu()
				.filter(Dish::isVegetarian)
				.collect(Collectors.toList());


		// When
		List<Dish> allVegetarianDishes =
				this.menuService.getAllVegetarianDishesByPartitioning();

		// Then
		assertThat(allVegetarianDishes, equalTo(expectedVegetarianDishes));
	}

	@Test
	public void able_To_Get_All_Vegetarian_Dishes_By_Type() throws Exception {

		// Given

		// When
		Map<Boolean, Map<Dish.Type, List<Dish>>> dishesByType =
				this.menuService.getVegetarianDishesByType();

		// Then
		assertThat(dishesByType.get(true).get(Dish.Type.MEAT), nullValue());
	}

	@Test
	public void able_To_Know_The_Most_Caloric_Dish_Among_Vegens_And_Non_Vegens() throws Exception {

		// When
		Map<Boolean, Dish> mostCaloricDishes = this.menuService
				.getTheMostCaloricDishAmongBothVegenAndNonvegen();

		// Then
		assertThat(mostCaloricDishes.get(true).getName(), is("pizza"));
		assertThat(mostCaloricDishes.get(false).getName(), is("pork"));
	}

	@Test
	public void able_To_Get_Dishes_Having_More_Than_500_Calories_Among_Vegen_And_Non_Vegen() throws Exception {

	}

}///:~