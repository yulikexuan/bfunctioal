//: com.yuli.bfunctional.j8ia.domain.services.TransactionServiceIT.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceIT {

	@Autowired
	private TransactionService transactionService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void able_To_Get_All_Transactions() {

		// When
		List<Transaction> transactions = this.transactionService
				.getAllTransactions();

		// Then
		assertThat(transactions.size(), greaterThan(0));
	}

}///:~