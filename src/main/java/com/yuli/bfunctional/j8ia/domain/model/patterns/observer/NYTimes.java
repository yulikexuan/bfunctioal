//: com.yuli.bfunctional.j8ia.domain.model.patterns.observer.NYTimes.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.observer;


public class NYTimes implements IObserver<String> {

	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("money")) {
			System.out.println("Breaking news in NY! " + tweet);
		}
	}

}///:~