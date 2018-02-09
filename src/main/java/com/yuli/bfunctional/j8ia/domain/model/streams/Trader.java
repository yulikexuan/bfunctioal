//: com.yuli.bfunctional.j8ia.domain.model.streams.Trader.java


package com.yuli.bfunctional.j8ia.domain.model.streams;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@EqualsAndHashCode(of = {"name"})
@ToString
public class Trader {

    private final String name;
    private final String city;

    Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public static final class Builder {

        private String name;
        private String city;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Trader createTrader() {
            return new Trader(name, city);
        }

        public Builder clear() {
            this.name = null;
            this.city = null;
            return this;
        }
    }

}///:~