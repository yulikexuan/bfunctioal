//: com.yuli.bfunctional.j8ia.domain.model.async.Shop.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;


public class Shop implements IShop {

    private final String name;

    public Shop(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
	public double calculatePrice(String product) {
		IShop.delay();
        if (UNAVAILABLE_PRODUCT.equals(product)) {
            throw new RuntimeException("The product is unavailable.");
        }
		double price = TLR.nextDouble() * product.charAt(0) + product.charAt(1);
		return price;
	}

	@Override
	public double getPrice(String product) {
		return calculatePrice(product);
	}

	@Override
	public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> this.calculatePrice(product));
	}

    @Override
    public Future<Double> getPriceAsync(String product, Executor executor) {
        return CompletableFuture.supplyAsync(() -> this.calculatePrice(product),
                executor);
    }

}///:~