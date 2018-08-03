//: com.yuli.bfunctional.j8ia.domain.model.async.Quote.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import lombok.Getter;


@Getter
public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    private Quote(String shopName, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String priceInfo) {

        String[] split = priceInfo.split(":");

        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);

        Quote quote = new Quote(shopName, price, discountCode);

        return quote;
    }

} ///:~
