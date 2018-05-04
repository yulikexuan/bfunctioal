//: com.yuli.bfunctional.j8ia.domain.model.patterns.observer.FeedTest.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.observer;


import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.*;


public class FeedTest {

	private Feed feed;

	@Before
	public void setUp() throws Exception {
		this.feed = new Feed();
	}

	@Test
	public void able_To_Notify_Observers() throws Exception {

		// Given
		this.feed.registerObserver(new NYTimes());
		this.feed.registerObserver(new Guardian());
		this.feed.registerObserver(new LeMonde());

		// When
		this.feed.notifyObservers("The queen said her favourite " +
				"book is Java 8 in Action!");
	}

	private void lambdaObserverNYTime(String tweet) {
		if ((tweet != null) && tweet.contains("money")) {
			System.out.println("Lambda ---> Breaking news in NY! " +
					tweet);
		}
	}

	private void lambdaObserverGuardian(String tweet) {
		if ((tweet != null) && tweet.contains("queen")) {
			System.out.println("Lambda ---> Yet another news in London... " +
					tweet);
		}
	}

	private void lambdaObserverLeMonde(String tweet) {
		if ((tweet != null) && tweet.contains("wine")) {
			System.out.println("Lambda ---> Today cheese, wine and news! " +
					tweet);
		}
	}

	@Test
	public void able_To_Register_Lambdas_As_Observers() throws Exception {

		// Given
		this.feed.registerObserver(this::lambdaObserverNYTime);
		this.feed.registerObserver(this::lambdaObserverGuardian);
		this.feed.registerObserver(this::lambdaObserverLeMonde);

		// When
		this.feed.notifyObservers("The queen said her favourite " +
				"book is Java 8 in Action!");
	}

}///:~