package com.ryotogashi.simplecalc;

/**
 * Utility class for SimpleCalc to perform the actual calculations.
 */
public class Calculator {

    /**
     * Addition operation
     */
    public double add(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    /**
     * Subtract operation
     */
    public double sub(double firstOperand, double secondOperand) {

        return firstOperand - secondOperand;
    }

    /**
     * Divide operation
     */
    public double div(double firstOperand, double secondOperand) {

        if(secondOperand == 0) {
            throw new IllegalArgumentException("You can't divide by 0.");
        }

        return firstOperand / secondOperand;
    }

    /**
     * Multiply operation
     */
    public double mul(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }
}