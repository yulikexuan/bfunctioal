//: com.yuli.bfunctional.j8ia.domain.model.async.IShop.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;


public interface IShop {

	Random TLR = new Random(System.currentTimeMillis());

	String UNAVAILABLE_PRODUCT = "iPhone XI";

	String getName();
	double calculatePrice(String product);
	double getPrice(String product);

	String getPriceQuote(String product);

	Future<Double> getPriceAsync(String product);
    Future<Double> getPriceAsync(String product, Executor executor);

	static void delay() {

        int delay = 500 + TLR.nextInt(2000);

		try {
			Thread.sleep(delay);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}///:~