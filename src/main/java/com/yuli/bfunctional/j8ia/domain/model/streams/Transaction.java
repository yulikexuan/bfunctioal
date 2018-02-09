//: com.yuli.bfunctional.j8ia.domain.model.streams.Transaction.java


package com.yuli.bfunctional.j8ia.domain.model.streams;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


@Getter
@EqualsAndHashCode
@ToString
public class Transaction {

    private final Trader trader;
    private final String traderName;
    private final String traderCity;
    private final int year;
    private final int value;

    Transaction(@NonNull Trader trader, int year, int value) {
        this.trader = trader;
        this.traderName = trader.getName();
        this.traderCity = trader.getCity();
        this.year = year;
        this.value = value;
    }

    public static final class Builder {

        private Trader trader;
        private int year;
        private int value;

        public Builder setTrader(Trader trader) {
            this.trader = trader;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setValue(int value) {
            this.value = value;
            return this;
        }

        public Transaction createTransaction() {
            return new Transaction(trader, year, value);
        }

        public Builder clear() {

            this.trader = null;
            this.value = 0;
            this.year = 0;

            return this;
        }
    }

}///:~