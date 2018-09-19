//: com.yuli.bfunctional.j8ia.client.ch05.PuttingIntoPracticeIT.java


package com.yuli.bfunctional.j8ia.client.ch05;


import com.yuli.bfunctional.j8ia.domain.model.streams.Trader;
import com.yuli.bfunctional.j8ia.domain.model.streams.Transaction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class PuttingIntoPracticeIT {

    @Autowired
    PuttingIntoPractice practice;

    @Before
    public void setUp() throws Exception {
    }

    /*
     * Query 1: Find all transactions from year 2011 and sort them by value
     * (small to high).
     */
    @Test
    public void all_Transactions_From_Year_2011_Sort_By_Value() {

        // When
        List<Transaction> t2011 = this.practice.query_1();

        // Then
        assertThat(t2011.size(), is(2));
        assertThat(t2011.get(0).getTraderName(), is("Brian"));
        assertThat(t2011.get(1).getTraderName(), is("Raoul"));
    }

    @Test
    public void all_The_Unique_Cities_Where_The_Traders_Work() {

        // When
        List<String> cities = this.practice.query_2();

        // Then
        assertThat(cities.size(), is(3));
        assertThat(cities, containsInAnyOrder("Roma", "Milan", "Cambridge"));
    }

    @Test
    public void all_Traders_From_Cambridge_And_Sorted_By_Name() {

        // When
        List<Trader> traders = this.practice.query_3();

        // Then
        assertThat(traders.size(), is(2));
        assertThat(traders.get(0).getName(), is("Alan"));
        assertThat(traders.get(1).getName(), is("Brian"));
    }

    @Test
    public void a_String_Of_All_Traders_Names_Sorted_By_Alphabetically() {

        // When
        String nameString = this.practice.query_4();

        // Then
        assertThat(nameString, is("AlanBrianMarioRaoul"));
    }

    @Test
    public void any_Traders_Based_In_Milan() {

        // When
        boolean exist = this.practice.query_5();

        // Then
        assertThat(exist, is(true));
    }

    @Test
    public void all_Transactions_Values_From_Traders_Living_In_Cambridge() {

        // When
        List<Integer> values = this.practice.query_6();

        // Then
        assertThat(values.size(), is(2));
        assertThat(values.get(0), is(300));
        assertThat(values.get(1), is(950));
    }

    @Test
    public void the_Highest_Value_Of_All_The_Transactions() {

        // When
        int highestValue = this.practice.query_7();

        // Then
        assertThat(highestValue, is(1000));
    }

    @Test
    public void transaction_With_The_Smallest_Value() {

        // When
        Transaction transaction = this.practice.query_8();

        // Then
        assertThat(transaction.getTraderName(), is("Brian"));
        assertThat(transaction.getYear(), is(2011));
    }

}///:~