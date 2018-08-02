//: com.yuli.bfunctional.j8ia.domain.model.async.ShopTest.java


package com.yuli.bfunctional.j8ia.domain.model.async;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ShopTest {

    private String availableProduct;
    private String unavailableProduct;

    private List<IShop> shops;

    @Before
	public void setUp() throws Exception {

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.printf("There are %1$d cores available.", cores);

	    this.availableProduct = "iPhone X";
	    this.unavailableProduct = IShop.UNAVAILABLE_PRODUCT;
	    this.shops = Arrays.asList(
	            new Shop("BestBuy"),
                new Shop("Amazon"),
                new Shop("Costco"),
                new Shop("BuyItAll"),
                new Shop("Walmart"),
                new Shop("CoolShop"),
                new Shop("Bay"),
                new Shop("Payless"),
                new Shop("Aldo"),
                new Shop("Dell")
        );
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
        long start = System.nanoTime();
        Future<Double> futurePrice = shops.get(0).getPriceAsync(product);
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

    @Test
    public void able_To_Get_Prices_From_Multi_Shops_In_A_Bad_Way() {
        this.able_To_Get_Prices_From_Multi_Shops_In_A_Specified_Way(
                this.findPricesOfAProductInABadWay);
    }

    @Test
    public void able_To_Get_Prices_From_Multi_Shops_In_A_Parallel_Way() {
        this.able_To_Get_Prices_From_Multi_Shops_In_A_Specified_Way(
                this.findPricesOfAProductInAParallelWay);
    }

    void able_To_Get_Prices_From_Multi_Shops_In_A_Specified_Way(
            Function<String, List<String>> func) {

        // Given
        long start = System.nanoTime();

        // When
        List<String> prices = func.apply(this.availableProduct);

        long duration = (System.nanoTime() - start) / 1_000_000;

        // Then
        System.out.println(prices);
        assertThat(prices.size(), is(this.shops.size()));
        System.out.printf("Done in %d msecs.", duration);
    }

    Function<String, List<String>> findPricesOfAProductInABadWay = p ->
        this.shops.stream()
                .map(s -> String.format("%s price is %.2f",
                        s.getName(), s.getPrice(p)))
                .collect(Collectors.toList());

    Function<String, List<String>> findPricesOfAProductInAParallelWay = p ->
        this.shops.parallelStream()
                .map(s -> String.format("%s price is %.2f",
                        s.getName(), s.getPrice(p)))
                .collect(Collectors.toList());

    @Test
    public void able_To_Get_Prices_From_Multi_Shops_In_Async_Way() throws Exception {

        // Given
        long start = System.nanoTime();

        List<CompletableFuture<String>> priceFutures = this.shops.stream()
                .map(s -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", s.getName(),
                                s.getPrice(this.availableProduct))))
                .collect(Collectors.toList());

        // When
        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        long duration = (System.nanoTime() - start) / 1_000_000;

        // Then
        System.out.println(prices);
        assertThat(prices.size(), is(this.shops.size()));
        System.out.printf("Done in %d msecs.", duration);
    }

    private Executor provideExecutor() {
        int shopSize = this.shops.size();
        int threadPoolSize = Math.min(shopSize, 100);
        return Executors.newFixedThreadPool(threadPoolSize, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
    }

}///:~