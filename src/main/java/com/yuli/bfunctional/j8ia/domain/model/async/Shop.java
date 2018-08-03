//: com.yuli.bfunctional.j8ia.domain.model.async.Shop.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;


public class Shop implements IShop {

    static final Random RANDOM = new Random(System.currentTimeMillis());

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
		double price = TLR.nextDouble() * product.charAt(3) + product.charAt(1);
		return price;
	}

	@Override
	public double getPrice(String product) {
		return calculatePrice(product);
	}

    @Override
    public String getPriceQuote(String product) {

        double price = this.calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                RANDOM.nextInt(Discount.Code.values().length)];

        return String.format("%s:%.2f:%s", this.name, price, code);
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