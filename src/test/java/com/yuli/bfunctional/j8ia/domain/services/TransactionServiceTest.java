//: com.yuli.bfunctional.j8ia.domain.services.TransactionServiceTest.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;
import com.yuli.bfunctional.j8ia.domain.repositories.ITransactionRepository;
import com.yuli.bfunctional.j8ia.domain.repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


public class TransactionServiceTest {

	private ITransactionRepository transactionRepository;
	private TransactionService transactionService;

	@Before
	public void setUp() throws Exception {
		this.transactionService = new TransactionService(
				new TransactionRepository());
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