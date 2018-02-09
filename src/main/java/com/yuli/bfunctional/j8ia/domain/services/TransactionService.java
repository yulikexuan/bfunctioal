//: com.yuli.bfunctional.j8ia.domain.services.TransactionService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;
import com.yuli.bfunctional.j8ia.domain.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionService implements ITransactionService {

	private final ITransactionRepository transactionRepository;

	@Autowired
	public TransactionService(ITransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return this.transactionRepository.getAllTransactions();
	}

}///:~