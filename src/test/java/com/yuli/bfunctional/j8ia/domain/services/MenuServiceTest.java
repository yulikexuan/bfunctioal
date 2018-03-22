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

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
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

}///:~