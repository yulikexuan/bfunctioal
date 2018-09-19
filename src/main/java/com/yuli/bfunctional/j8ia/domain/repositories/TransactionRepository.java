//: com.yuli.bfunctional.j8ia.domain.repositories.TransactionRepository.java


package com.yuli.bfunctional.j8ia.domain.repositories;


import com.yuli.bfunctional.j8ia.domain.model.streams.Trader;
import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public class TransactionRepository implements ITransactionRepository {

    @Override
    public List<Transaction> getAllTransactions() {

        Trader.Builder traderBuilder = new Trader.Builder();

        traderBuilder.setName("Raoul");
        traderBuilder.setCity("Cambridge");

        Trader raoul = traderBuilder.clear().setName("Raoul").setCity("Roma").createTrader();
        Trader mario = traderBuilder.clear().setName("Mario").setCity("Milan").createTrader();
        Trader alan = traderBuilder.clear().setName("Alan").setCity("Cambridge").createTrader();
        Trader brian = traderBuilder.clear().setName("Brian").setCity("Cambridge").createTrader();

        Transaction.Builder transactionBuilder = new Transaction.Builder();

        Transaction[] transactionArr = {transactionBuilder.clear().setTrader(brian).setYear(2011).setValue(300).createTransaction(), transactionBuilder.clear().setTrader(raoul).setYear(2012).setValue(1000).createTransaction(), transactionBuilder.clear().setTrader(raoul).setYear(2011).setValue(400).createTransaction(), transactionBuilder.clear().setTrader(mario).setYear(2012).setValue(710).createTransaction(), transactionBuilder.clear().setTrader(mario).setYear(2012).setValue(700).createTransaction(), transactionBuilder.clear().setTrader(alan).setYear(2012).setValue(950).createTransaction(),};

        return Arrays.asList(transactionArr);

    }// End of getAllTransactions()

}///:~