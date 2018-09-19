//: com.yuli.bfunctional.j8ia.domain.model.patterns.strategy.NumericStrategy.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.strategy;


public class NumericStrategy implements IValidationStrategy<String> {

    @Override
    public boolean execute(String input) {
        return input.matches("\\d+");
    }

}///:~