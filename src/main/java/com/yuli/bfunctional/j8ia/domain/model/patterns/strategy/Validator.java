//: com.yuli.bfunctional.j8ia.domain.model.patterns.strategy.Validator.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.strategy;


public class Validator {

    private IValidationStrategy<String> validationStrategy;

    public Validator(IValidationStrategy<String> validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public void setStrategy(IValidationStrategy<String> validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String input) {
        return this.validationStrategy.execute(input);
    }

}///:~