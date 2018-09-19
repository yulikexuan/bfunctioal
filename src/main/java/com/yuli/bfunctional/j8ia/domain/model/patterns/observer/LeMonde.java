//: com.yuli.bfunctional.j8ia.domain.model.patterns.observer.LeMonde.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.observer;


public class LeMonde implements IObserver<String> {

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("wine")) {
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }

}///:~