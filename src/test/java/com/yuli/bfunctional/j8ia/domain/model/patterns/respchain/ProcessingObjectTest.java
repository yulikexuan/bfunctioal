//: com.yuli.bfunctional.j8ia.domain.model.patterns.respchain.ProcessingObjectTest.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.respchain;


import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class ProcessingObjectTest {

    private UnaryOperator<String> headerProcessing;
    private UnaryOperator<String> spellCheckerProcessing;

    @Before
    public void setUp() throws Exception {
        this.headerProcessing = text -> "From Raoul, Mario and Alan: " + text;
        this.spellCheckerProcessing = text -> text.replaceAll("labda", "lambda");
    }

    @Test
    public void test_Classic_Chain_Of_Responsibility() throws Exception {

        // Given
        ProcessingObject<String> p1 = new ProcessingObject(this.headerProcessing);
        ProcessingObject<String> p2 = new ProcessingObject(this.spellCheckerProcessing);

        p1.setSuccessor(p2);

        // When
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        // Then
        assertThat(result, is("From Raoul, Mario and Alan: Aren't " + "lambdas really sexy?!!"));
    }

    @Test
    public void test_Using_Lambdas() throws Exception {

        // Given
        Function<String, String> pipeline = this.headerProcessing.andThen(this.spellCheckerProcessing);

        // When
        String result = pipeline.apply("Aren't labdas really sexy?!!");

        // Then
        assertThat(result, is("From Raoul, Mario and Alan: Aren't " + "lambdas really sexy?!!"));
    }

}///:~