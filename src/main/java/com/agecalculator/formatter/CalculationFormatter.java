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
     * Formats a double value as a string, converting whole numbers to their
     * integer representation for cleaner display.
     *
     * <p>If the number has no fractional component (e.g., {@code 10.0}), it is
     * displayed as a long integer (e.g., {@code "10"}). If it has a fractional
     * component (e.g., {@code 3.75}), the full decimal representation is preserved.</p>
     *
     * <p>Special cases handled:</p>
     * <ul>
     *   <li>Values outside the range of {@code long} retain their double representation</li>
     *   <li>{@code NaN} and {@code Infinity} are rendered using their default string forms</li>
     * </ul>
     *
     * @param number the number to format
     * @return formatted number string
     */
    private String formatNumber(double number) {
        if (Double.isNaN(number) || Double.isInfinite(number)) {
            return String.valueOf(number);
        }
        if (number == (long) number) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }
}
