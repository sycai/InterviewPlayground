package Moderate;

import java.util.Arrays;

/**
 * Created by sycai on 7/3/2017.
 * Given an algorithm equation consisting of positive integers, +, -, * and / (no parentheses), compute the result.
 *
 * EXAMPLE
 * Input: 2*3+5/6*3+15
 * Output: 23.5
 */
public class Calculator {
    public static double calculate(String equation) {
        if (equation == null || equation.length() == 0) {
            throw new IllegalArgumentException("Incorrect usage of operators.");
        }
        // Trim out whitespaces
        String sanitizedEq = equation.trim();
        // Equation is a pure number
        if (sanitizedEq.matches("[0-9]+")) {
            return Double.parseDouble(sanitizedEq);
        }

        // Parse plus and minus operators first
        int plusIdx = sanitizedEq.indexOf('+');
        if (plusIdx != -1)  {
            return calculate(sanitizedEq.substring(0, plusIdx)) + calculate(sanitizedEq.substring(plusIdx+1));
        }
        int minusIdx = sanitizedEq.indexOf('-');
        if (minusIdx != -1) {
            return calculate(sanitizedEq.substring(0, minusIdx)) - calculate(sanitizedEq.substring(minusIdx+1));
        }

        // Then parse the multiplication and division operators
        int multiIdx = sanitizedEq.indexOf('*');
        if (multiIdx != -1) {
            return calculate(sanitizedEq.substring(0, multiIdx)) * calculate(sanitizedEq.substring(multiIdx+1));
        }
        int divIdx = sanitizedEq.indexOf('/');
        if (divIdx != -1) {
            return calculate(sanitizedEq.substring(0, divIdx)) / calculate(sanitizedEq.substring(divIdx+1));
        }

        // If execution ever reaches here, then there is at least one invalid character appearing in the equation.
        // Thus, we fail fast.
        throw new IllegalArgumentException("Invalid characters.");
    }

    public static void main(String[] args) {
        String equation = "2 * 3 + 5 / 6 * 3 + 15";
        System.out.println(calculate(equation));
    }
}
