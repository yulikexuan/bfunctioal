//: com.yuli.bfunctional.j8ia.domain.services.TransactionServiceTest.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.sun.security.sasl.util.AbstractSaslImpl;
import com.yuli.bfunctional.j8ia.domain.model.streams.Trader;
import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;
import com.yuli.bfunctional.j8ia.domain.repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class TransactionServiceTest {

	private TransactionService transactionService;

	@Before
	public void setUp() throws Exception {
		this.transactionService = new TransactionService(
				new TransactionRepository());
	}

	@Test
	public void able_To_Get_All_In_2011_And_Sorted_By_Value() throws Exception {

		// When
		List<Transaction> all2011 = this.transactionService
				.getAllSortedTransactionsIn2011();

		boolean areAllIn2011 = all2011.stream()
				.allMatch(t -> t.getYear() == 2011);

		// Then
		assertThat(all2011.size(), is(2));
		assertThat(all2011.get(0).getValue(), is(300));
		assertThat(all2011.get(0).getYear(), is(2011));
		assertThat(all2011.get(1).getValue(), is(400));
		assertThat(all2011.get(1).getYear(), is(2011));

		assertThat(areAllIn2011, is(true));
	}

	@Test
	public void able_To_Know_All_Trader_Cities() throws Exception {

		// When
		List<String> allCities = this.transactionService.getAllUniqueCities();

		// Then
		assertThat(allCities.size(), is(3));
		assertThat(allCities, containsInAnyOrder(
				"Roma", "Milan", "Cambridge"));
	}

	@Test
	public void able_To_Know_All_Traders_From_Cambridge() throws Exception {

		// When
		List<Trader> allTraders = this.transactionService
				.getAllSortedTradersFromCambridge();

		boolean allFromCambridge = allTraders.stream()
				.allMatch(t -> t.getCity().equals("Cambridge"));

		// Then
		assertThat(allTraders.size(), is(2));
		assertThat(allTraders.get(0).getName(), is("Alan"));
		assertThat(allTraders.get(1).getName(), is("Brian"));
		assertThat(allFromCambridge, is(true));
	}

	@Test
	public void able_To_Generate_A_String_Containing_All_Sorted_Trader_Names() throws Exception {

		// When
		String names = this.transactionService.getAllTradersNames();

		// Then
		assertThat(names, is("AlanBrianMarioMarioRaoulRaoul"));
	}

	@Test
	public void able_To_Find_Any_Trader_Who_Is_Based_In_Milan() throws Exception {

		// When
		Trader trader = this.transactionService.getAnyTraderBasedInMilan();

		// Then
		assertThat(trader.getCity(), is("Milan"));
	}

	@Test
	public void able_To_List_All_Values_From_Cambridge() throws Exception {

		// When
		List<Integer> values = this.transactionService
				.getAllTransactionValuesFromCambridge();

		// Then
		assertThat(values.size(), is(2));
		assertThat(values, containsInAnyOrder(300, 950));
	}

	@Test
	public void able_To_Know_The_Heightest_Value() throws Exception {

		// When
		int heightest = this.transactionService.getTheHeightestValue();

		// Then
		assertThat(heightest, is(1000));
	}

	@Test
	public void able_To_Know_The_Transaction_With_Smallest_Value() throws Exception {

		// When
		Transaction transaction = this.transactionService
				.getTransactionWithSmallestValue();

		// Then
		assertThat(transaction.getValue(), is(300));
	}


}///:~