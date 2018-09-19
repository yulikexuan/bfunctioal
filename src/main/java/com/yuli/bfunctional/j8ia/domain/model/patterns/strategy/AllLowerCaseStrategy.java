//: com.yuli.bfunctional.j8ia.domain.model.patterns.strategy.AllLowerCaseStrategy.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.strategy;


public class AllLowerCaseStrategy implements IValidationStrategy<String> {

    @Override
    public boolean execute(String input) {
        return input.matches("[a-z]+");
    }

}///:~