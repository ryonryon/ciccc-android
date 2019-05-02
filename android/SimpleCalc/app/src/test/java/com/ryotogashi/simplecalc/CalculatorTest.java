package com.ryotogashi.simplecalc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double result = mCalculator.add(1d, 2d);
        assertThat(result, is(equalTo(3d)));
    }

    @Test
    public void addTwoNumbersNegative() {
        double result = mCalculator.add(-1d, 2d);
        assertThat(result, is(equalTo(1d)));
    }

//    @Test
//    public void addTwoNumbersFloats() {
//        double result = mCalculator.add(1.111f, 1.111d);
//        assertThat(result, is(closeTo(2.222d)));
//    }

    @Test
    public void subTwoNumbers() {
        double result = mCalculator.sub(1d, 2d);
        assertThat(result, is(equalTo(-1d)));
    }

    @Test
    public void subWorksWithNegativeResults() {
        double result = mCalculator.sub(1d, -2d);
        assertThat(result, is(equalTo(3d)));
    }

    @Test
    public void mulTwoNumbers(){
        double result = mCalculator.mul(1d, 2d);
        assertThat(result, is(equalTo(2d)));
    }

    @Test
    public void mulTwoNumbersZero(){
        double result = mCalculator.mul(1d, 0);
        assertThat(result, is(equalTo(0d)));
    }

    @Test
    public void divTwoNumbers(){
        double result = mCalculator.div(9d, 3d);
        assertThat(result, is(equalTo(3d)));
    }

//    @Test
//    public void divTwoNumbersZero(){
//        double result = mCalculator.div(9d, 0d);
//        assertThat(result, is(equalTo(0d)));
//    }

    @Test (expected = IllegalArgumentException.class)
    public void divByZeroThrows(){
        double result = mCalculator.div(9d, 0d);
    }
}