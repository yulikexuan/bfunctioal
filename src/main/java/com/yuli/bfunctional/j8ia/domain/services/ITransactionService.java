//: com.yuli.bfunctional.j8ia.domain.services.ITransactionService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;

import java.util.List;


/*
P110
Find all transactions in the year 2011 and sort them by value (small to high)
What are all the unique cities where the traders work?
Find all traders from Cambridge and sort them by name
Return a string of all traders’ names sorted alphabetically
Are any traders based in Milan?
Print all transactions’ values from the traders living in Cambridge

Collectors and collect P124
Group a list of transactions by currency to obtain the sum of the values of all transactions with that currency (returning a Map)
Partition a list of transactions into two groups: expensive and not expensive (returning a Map>)
Create multilevel groupings such as grouping transactions by cities and then further categorizing by whether they’re expensive or not (returning a Map>>)
*/
public interface ITransactionService {

	List<Transaction> getAllTransactions();

}///:~