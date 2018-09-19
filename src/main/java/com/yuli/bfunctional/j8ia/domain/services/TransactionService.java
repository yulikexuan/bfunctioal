//: com.yuli.bfunctional.j8ia.domain.services.TransactionService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Trader;
import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;
import com.yuli.bfunctional.j8ia.domain.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;

    @Autowired
    public TransactionService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Stream<Transaction> getAllTransactions() {
        return this.transactionRepository.getAllTransactions().stream();
    }

    @Override
    public List<Transaction> getAllSortedTransactionsIn2011() {
        return this.getAllTransactions().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(toList());
    }

    @Override
    public List<String> getAllUniqueCities() {
        return this.getAllTransactions().map(Transaction::getTraderCity).distinct().collect(toList());
    }

    @Override
    public List<Trader> getAllSortedTradersFromCambridge() {
        return this.getAllTransactions().filter(t -> t.getTraderCity().equals("Cambridge")).map(Transaction::getTrader).sorted(Comparator.comparing(Trader::getName)).collect(toList());
    }

    @Override
    public String getAllTradersNames() {
        return this.getAllTransactions().map(Transaction::getTraderName).sorted().reduce("", String::concat);
    }

    @Override
    public Trader getAnyTraderBasedInMilan() {
        return this.getAllTransactions().filter(t -> t.getTraderCity() == "Milan").map(Transaction::getTrader).findAny().get();
    }

    @Override
    public List<Integer> getAllTransactionValuesFromCambridge() {
        return this.getAllTransactions().filter(t -> t.getTraderCity().equals("Cambridge")).map(Transaction::getValue).collect(toList());
    }

    @Override
    public int getTheHeightestValue() {
        return this.getAllTransactions().mapToInt(Transaction::getValue).max().getAsInt();
    }

    @Override
    public Transaction getTransactionWithSmallestValue() {
        //		return this.getAllTransactions()
        //				.reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
        //				.get();
        return this.getAllTransactions().min(Comparator.comparing(Transaction::getValue)).get();
    }

}///:~