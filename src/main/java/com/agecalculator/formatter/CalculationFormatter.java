package com.agecalculator.formatter;

import com.agecalculator.model.CalculationResult;

/**
 * Formats arithmetic calculation results and error messages into human-readable strings.
 * This class handles output formatting only — it does not perform any computation or I/O.
 *
 * <p>Used by the application orchestration layer to convert {@link CalculationResult} objects
 * into display-ready strings and to wrap error messages in a consistent format.</p>
 *
 * <p>This class is stateless and can be safely shared across threads.</p>
 */
public class CalculationFormatter {

    /**
     * Formats a calculation result into a human-readable string.
     *
     * <p>The output follows the pattern {@code "num1 operator num2 = result"}.
     * For example: {@code "10.0 + 5.0 = 15.0"}, {@code "7.5 / 2.0 = 3.75"},
     * or {@code "10.0 % 3.0 = 1.0"}.</p>
     *
     * <p>This method supports all five arithmetic operators: {@code +}, {@code -},
     * {@code *}, {@code /}, and {@code %}.</p>
     *
     * @param result the CalculationResult containing operands, operator, and computed result
     * @return a formatted string in the pattern "num1 operator num2 = result"
     */
    public String formatResult(CalculationResult result) {
        return String.format("%s %s %s = %s",
                formatNumber(result.getNum1()),
                result.getOperator(),
                formatNumber(result.getNum2()),
                formatNumber(result.getResult()));
    }

    /**
     * Formats an error message for display to the user.
     *
     * <p>Prepends the standard "Error: " prefix to the provided message for
     * consistent error output across the application. For example, an input of
     * {@code "Cannot divide by zero"} produces {@code "Error: Cannot divide by zero"}.</p>
     *
     * @param errorMessage the error message to format
     * @return a formatted error string in the pattern "Error: errorMessage"
     */
    public String formatError(String errorMessage) {
        return "Error: " + errorMessage;
    }

    /**
     * Formats a double value as its standard string representation.
     *
     * <p>Uses {@link String#valueOf(double)} to produce the default Java string form
     * of the number, preserving the decimal point for whole numbers (e.g., {@code 10.0}
     * remains {@code "10.0"}) and retaining full precision for fractional values
     * (e.g., {@code 3.75} becomes {@code "3.75"}).</p>
     *
     * <p>Special values {@code NaN} and {@code Infinity} are rendered using their
     * default string forms.</p>
     *
     * @param number the number to format
     * @return formatted number string
     */
    private String formatNumber(double number) {
        return String.valueOf(number);
    }
}
