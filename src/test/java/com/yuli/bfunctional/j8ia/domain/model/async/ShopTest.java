//: com.yuli.bfunctional.j8ia.domain.model.async.ShopTest.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ShopTest {

    private String availableProduct;
    private String unavailableProduct;

	@Before
	public void setUp() throws Exception {
	    this.availableProduct = "iPhone X";
	    this.unavailableProduct = IShop.UNAVAILABLE_PRODUCT;
	}

    @Test
    public void able_To_Get_Normal_Product_Price() throws Exception {
        this.able_To_Get_Price_Asynchronously(this.availableProduct);
    }

    @Test(expected = RuntimeException.class)
    public void able_To_Get_The_Error_Inside_Price_Service() throws Exception {
        this.able_To_Get_Price_Asynchronously(this.unavailableProduct);
    }

    private void able_To_Get_Price_Asynchronously(String product) throws Exception {

	    // Given
        String shopName = "BestBuy";
        Shop shop = new Shop(shopName);
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync(product);
        long invocationTime = (System.nanoTime() - start) / 1_000_000;

        // %n is portable accross platforms \n is not.
        System.out.printf("Invocation returned after %1$d msecs. %n",
                invocationTime);

        this.doSomethingElse();

        // When
        double price = 0;
        try {
            price = futurePrice.get();
        } catch (InterruptedException | ExecutionException e) {
            assertThat(e instanceof ExecutionException, is(true));
            assertThat(e.getCause().getMessage(),
                    equalTo("The product is unavailable."));
            throw new RuntimeException(e);
        }
        System.out.printf("The price of %s is %10.2f%n", product, price);

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;

        // Then
        System.out.printf("Price returned after %d msecs. %n", retrievalTime);
    }

    void doSomethingElse() {
        System.out.println("Do somethings else here.");
    }

}///:~