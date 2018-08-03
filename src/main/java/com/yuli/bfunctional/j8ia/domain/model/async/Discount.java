//: com.yuli.bfunctional.j8ia.domain.model.async.Discount.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Discount {

    public enum Code {

        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    private static final DecimalFormat PRICE_FORMAT =
            new DecimalFormat("#.##",
                    new DecimalFormatSymbols(Locale.US));

    public static String applyDiscount(Quote quote) {

        delay();

        synchronized (PRICE_FORMAT) {

            double finalPrice = new Double(PRICE_FORMAT.format(
                    quote.getPrice() * (100 -
                            quote.getDiscountCode().percentage) / 100));

            return String.format("%s price is %.2f", quote.getShopName(),
                    finalPrice);
        }
    }

    private static void delay() {

        int delay = 500 + IShop.TLR.nextInt(2000);

        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

} ///:~
