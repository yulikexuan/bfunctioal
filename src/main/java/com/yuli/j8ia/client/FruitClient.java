//: com.yuli.j8ia.client.FruitClient.java


package com.yuli.j8ia.client;


import com.yuli.j8ia.domain.model.*;
import com.yuli.j8ia.services.FruitService;
import com.yuli.j8ia.services.IFruitService;

import java.util.*;


public class FruitClient {

    static final int SIZE = 10;

    static Random random;
    static List<Apple> appleInventory;
    static Apple.Builder appleBuilder;

    static {
        random = new Random(System.currentTimeMillis());
        appleInventory = new ArrayList<>();
        appleBuilder = new Apple.Builder();
        for (int i = 0; i < SIZE; i++) {
            appleInventory.add(appleBuilder
                    .setBarcode(UUID.randomUUID().toString())
                    .setColor(Color.values()[random.nextInt(Color.values().length)])
                    .setWeight(random.nextInt(400))
                    .create());
            appleBuilder.clear();
        }
    }

    public static void main(String[] args) {
        IFruitService fruitService = new FruitService();
        System.out.println(fruitService.sort(appleInventory));
    }

}///:~