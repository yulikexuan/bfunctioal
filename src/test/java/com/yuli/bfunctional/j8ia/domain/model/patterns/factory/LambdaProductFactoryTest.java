//: com.yuli.bfunctional.j8ia.domain.model.patterns.factory.LambdaProductFactoryTest.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.factory;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class LambdaProductFactoryTest {

    private IProductFactory pf;

    @Before
    public void setUp() throws Exception {
        this.pf = new LambdaProductFactory();
    }

    @Test
    public void able_To_Create_Finance_Products() throws Exception {

        // Given

        // When
        IFinanceProduct loan = this.pf.createProduct("loan");
        IFinanceProduct stock = this.pf.createProduct("stock");
        IFinanceProduct bond = this.pf.createProduct("bond");
        // Then
        assertThat(loan, instanceOf(Loan.class));
        assertThat(stock, instanceOf(Stock.class));
        assertThat(bond, instanceOf(Bond.class));
    }

}///:~