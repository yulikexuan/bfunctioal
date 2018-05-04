//: com.yuli.bfunctional.j8ia.domain.model.patterns.observer.Feed.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.observer;


import java.util.ArrayList;
import java.util.List;


public class Feed implements ISubject<String> {

	private final List<IObserver<String>> observers = new ArrayList<>();

	@Override
	public void registerObserver(IObserver<String> o) {
		this.observers.add(o);
	}

	@Override
	public void notifyObservers(String tweets) {
		this.observers.forEach(o -> o.notify(tweets));
	}

}///:~