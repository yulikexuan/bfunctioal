//: com.yuli.bfunctional.j8ia.domain.services.MenuServiceTest.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;
import com.yuli.bfunctional.j8ia.domain.repositories.IMenuRepository;
import com.yuli.bfunctional.j8ia.domain.repositories.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
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

	}

}///:~