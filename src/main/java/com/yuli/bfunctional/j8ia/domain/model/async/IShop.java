//: com.yuli.bfunctional.j8ia.domain.model.async.IShop.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import java.util.concurrent.ThreadLocalRandom;

public interface IShop {

	ThreadLocalRandom TLR = ThreadLocalRandom.current();

	double calculatePrice(String product);
	double getPrice(String product);

	static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}///:~