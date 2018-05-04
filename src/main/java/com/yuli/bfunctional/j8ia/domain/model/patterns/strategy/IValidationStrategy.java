//: com.yuli.bfunctional.j8ia.domain.model.patterns.strategy.IValidationStrategy.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.strategy;


public interface IValidationStrategy<T> {

	boolean execute(T t);

}///:~