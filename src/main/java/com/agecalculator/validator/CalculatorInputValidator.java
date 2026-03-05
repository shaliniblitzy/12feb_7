package com.agecalculator.validator;

/**
 * Input validation class for the Arithmetic Calculator feature.
 * Validates and parses raw string input into typed numeric and operator values.
 * Provides pre-computation checks for division-by-zero conditions.
 *
 * <p>This class follows the Single Responsibility Principle — it handles
 * only input validation and parsing, with no I/O or computation logic.</p>
 */
public class CalculatorInputValidator {

    /** Supported arithmetic operator for addition. */
    private static final char ADDITION = '+';

    /** Supported arithmetic operator for subtraction. */
    private static final char SUBTRACTION = '-';

    /** Supported arithmetic operator for multiplication. */
    private static final char MULTIPLICATION = '*';

    /** Supported arithmetic operator for division. */
    private static final char DIVISION = '/';

    /** Supported arithmetic operator for modulus. */
    private static final char MODULUS = '%';

    /**
     * Parses the given string input into a double value.
     *
     * <p>Uses {@link Double#parseDouble(String)} to convert the raw string input
     * into a double-precision floating-point number. Supports both integer values
     * (e.g., "5") and decimal values (e.g., "3.14", "-7.5").</p>
     *
     * @param input the string to parse as a number
     * @return the parsed double value
     * @throws IllegalArgumentException if the input is not a valid numeric value
     */
    public double parseNumber(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Invalid number input. Please enter a valid numeric value.", e);
        }
    }

    /**
     * Parses and validates the given string input as an arithmetic operator.
     *
     * <p>The input must be exactly one character matching one of the five
     * supported operators: {@code +}, {@code -}, {@code *}, {@code /}, or
     * {@code %}. Null input and multi-character strings are rejected.</p>
     *
     * @param input the string to validate as an operator
     * @return the validated operator character
     * @throws IllegalArgumentException if the input is not a supported operator (+, -, *, /, %)
     */
    public char parseOperator(String input) {
        if (input == null || input.length() != 1) {
            throw new IllegalArgumentException(
                "Unsupported operation '" + input + "'. Please use +, -, *, /, or %.");
        }
        char operator = input.charAt(0);
        if (operator != ADDITION && operator != SUBTRACTION && operator != MULTIPLICATION
                && operator != DIVISION && operator != MODULUS) {
            throw new IllegalArgumentException(
                "Unsupported operation '" + operator + "'. Please use +, -, *, /, or %.");
        }
        return operator;
    }

    /**
     * Pre-checks whether a division or modulus operation would result in division by zero.
     *
     * <p>This is a guard method intended to be called before
     * {@code CalculatorService.calculate()} to catch division-by-zero errors
     * early at the validation layer. It checks both the division ({@code /})
     * and modulus ({@code %}) operators since both are undefined for a zero divisor.</p>
     *
     * @param num2     the second operand (divisor)
     * @param operator the arithmetic operator to be applied
     * @throws ArithmeticException if the operator is '/' or '%' and num2 is zero
     */
    public void validateNotDivisionByZero(double num2, char operator) {
        if ((operator == DIVISION || operator == MODULUS) && num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
    }
}
