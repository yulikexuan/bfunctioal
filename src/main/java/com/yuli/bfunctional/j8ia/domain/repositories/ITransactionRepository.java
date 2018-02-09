//: com.yuli.bfunctional.j8ia.domain.repositories.ITransactionRepository.java


package com.yuli.bfunctional.j8ia.domain.repositories;


import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;

import java.util.List;


public interface ITransactionRepository {

    List<Transaction> getAllTransactions();

}///:~