//: com.yuli.bfunctional.j8ia.domain.model.async.Shop.java


package com.yuli.bfunctional.j8ia.domain.model.async;


public class Shop implements IShop {

	@Override
	public double calculatePrice(String product) {
		IShop.delay();
		return TLR.nextDouble() * product.charAt(0) + product.charAt(1);
	}

	@Override
	public double getPrice(String product) {
		return 0;
	}

}///:~