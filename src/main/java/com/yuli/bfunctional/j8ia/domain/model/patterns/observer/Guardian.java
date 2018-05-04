//: com.yuli.bfunctional.j8ia.domain.model.patterns.observer.Guardian.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.observer;


public class Guardian implements IObserver<String> {

	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("queen")) {
			System.out.println("Yet another news in London... " + tweet);
		}
	}

}///:~