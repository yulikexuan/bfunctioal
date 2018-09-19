//: com.yuli.bfunctional.j8ia.domain.model.generics.ConsumerTest.java


package com.yuli.bfunctional.j8ia.domain.model.generics;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class ConsumerTest {

    private Consumer consumer;

    @Before
    public void setUp() throws Exception {
        this.consumer = new Consumer();
        List<C2> c2List = new ArrayList<>();
        c2List.add(new C2());
        c2List.add(new D1());
        c2List.add(new D2());
        c2List.add(new E1());
        c2List.add(new E2());
        c2List.add(new E3());
        c2List.add(new E4());
        this.consumer.setElements(c2List);
    }

    @Test
    public void any_Element_From_Consumer_Must_Be_Object() throws Exception {

        // Given
        List<? super C2> elements = this.consumer.getElements();

        // Ween

        /*
         * The only guarantee is that you will get an instance of an Object
         * Others are not guaranteed
         */
        Object object = elements.get(0);

        // C2 c2 = elements.get(0); // Compiler Err.
        // B2 b2 = elements.get(0); // Compiler Err.
        // B3 b3 = elements.get(0); // Compiler Err.
        // D1 d1 = elements.get(0); // Compiler Err.
        // D2 d2 = elements.get(0); // Compiler Err.

        boolean allC2 = elements.stream().anyMatch(e -> e instanceof C2);

        boolean anyD1 = elements.stream().anyMatch(e -> e instanceof D1);

        boolean anyE1 = elements.stream().anyMatch(e -> e instanceof E1);

        // Then
        assertThat(allC2, is(true));
        assertThat(anyD1, is(true));
        assertThat(anyE1, is(true));
    }

    @Test
    public void can_Only_Consume_Subclasses_Of_C2() throws Exception {

        // Given
        List<? super C2> elements = this.consumer.getElements();
        B3 b3 = mock(B3.class);

        // When
        // elements.add(new Object()); // Compiler Err
        // elements.add(mock(B2.class)); // Compiler Err
        // elements.add(b3); // Compiler Err

        elements.add(new C2());
        elements.add(new D1());
        elements.add(new D2());
        elements.add(new E1());
        elements.add(new E2());
        elements.add(new E3());
        elements.add(new E4());
    }

}///:~