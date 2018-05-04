//: com.yuli.bfunctional.j8ia.client.ch05.PuttingIntoPractice.java


package com.yuli.bfunctional.j8ia.client.ch05;


import com.yuli.bfunctional.j8ia.domain.model.streams.Trader;
import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;
import com.yuli.bfunctional.j8ia.domain.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class PuttingIntoPractice {

    private final List<Transaction> transactions;

    @Autowired
    public PuttingIntoPractice(TransactionRepository repository) {
        this.transactions = repository.getAllTransactions();
    }

    /*
     * Query 1: Find all transactions from year 2011 and sort them by value
     * (small to high).
     */
    public List<Transaction> query_1() {

        List<Transaction> t2011 = this.transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        log.debug(">>>>>>> Query 1: " + t2011);

        return t2011;
    }

    // all_The_Unique_Cities_Where_The_Traders_Work
    public List<String> query_2() {
        return this.transactions.stream()
                .map(Transaction::getTraderCity)
                .distinct()
                .collect(Collectors.toList());
    }

    // all_Traders_From_Cambridge_And_Sorted_By_Name
    public List<Trader> query_3() {
        return this.transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTraderCity()))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    // a_String_Of_All_Traders_Names_Sorted_By_Alphabetically
    public String query_4() {
        return this.transactions.stream()
                .map(Transaction::getTraderName)
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
    }

    // any_Traders_Based_In_Milan
    public boolean query_5() {
        return this.transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTraderCity()));
    }

    // all_Transactions_Values_From_Traders_Living_In_Cambridge
    public List<Integer> query_6() {
        return this.transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTraderCity()))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
    }

    // the_Highest_Value_Of_All_The_Transactions
    public Integer query_7() {
        return this.transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .get();
    }

    // transaction_With_The_Smallest_Value
    public Transaction query_8() {
        return this.transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .get();
    }

}///:~