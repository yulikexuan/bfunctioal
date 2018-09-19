//: com.yuli.bfunctional.j8ia.domain.model.patterns.strategy.ValidatorTest.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.strategy;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class ValidatorTest {

    private Validator validator;

    @Before
    public void setup() {
        this.validator = new Validator(new NumericStrategy());
    }

    @Test
    public void using_Classic_Strategy() throws Exception {

        // Given
        String input_1 = "aaaaaaa";
        String input_2 = "bbbbbbb";

        // When
        boolean isInput_1Numeric = this.validator.validate(input_1);
        boolean isInput_2Numeric = this.validator.validate(input_2);

        this.validator.setStrategy(new AllLowerCaseStrategy());
        boolean isInput_1LowerCase = this.validator.validate(input_1);
        boolean isInput_2LowerCase = this.validator.validate(input_2);

        // Given
        assertThat(isInput_1Numeric, is(false));
        assertThat(isInput_2Numeric, is(false));

        assertThat(isInput_1LowerCase, is(true));
        assertThat(isInput_2LowerCase, is(true));
    }

    @Test
    public void using_Strategy_With_Lambda() throws Exception {

        // Given

        // When
        this.validator.setStrategy(s -> s.matches("[a-z]+"));
        boolean isAllLowerCase = this.validator.validate("aaaaa");

        this.validator.setStrategy(s -> s.matches("\\d+"));
        boolean isAllDigits = this.validator.validate("123456789");

        // Then
        assertThat(isAllLowerCase, is(true));
        assertThat(isAllDigits, is(true));
    }

}///:~