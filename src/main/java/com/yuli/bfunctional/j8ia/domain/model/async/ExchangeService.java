//: com.yuli.bfunctional.j8ia.domain.model.async.ExchangeService.java


package com.yuli.bfunctional.j8ia.domain.model.async;


public class ExchangeService {

    public enum Money {
        USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);
        private final double rate;
        Money(double rate) {
            this.rate = rate;
        }
    }

    public static double getRate(Money source, Money target) {

        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        synchronized (ExchangeService.class) {
            return target.rate / source.rate;
        }
    }

} ///:~
