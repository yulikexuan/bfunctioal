//: com.yuli.bfunctional.j8ia.domain.model.async.IShop.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;


public interface IShop {

	ThreadLocalRandom TLR = ThreadLocalRandom.current();

	String UNAVAILABLE_PRODUCT = "iPhone XI";

	String getName();
	double calculatePrice(String product);
	double getPrice(String product);

	String getPriceQuote(String product);

	Future<Double> getPriceAsync(String product);
    Future<Double> getPriceAsync(String product, Executor executor);

	static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}///:~