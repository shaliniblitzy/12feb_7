package com.agecalculator.formatter;

import com.agecalculator.model.CalculationResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test class for {@link CalculationFormatter}.
 *
 * <p>Validates the output formatting logic for both calculation results and
 * error messages. Tests cover all five arithmetic operators ({@code +},
 * {@code -}, {@code *}, {@code /}, {@code %}) and multiple error scenarios
 * including division by zero, invalid number input, and unsupported operations.</p>
 *
 * <p>Each test method creates a {@link CalculationResult} (for result formatting tests)
 * or passes an error string (for error formatting tests), invokes the appropriate
 * formatter method, and asserts the output matches the expected format.</p>
 */
public class CalculationFormatterTest {

    /** Shared stateless formatter instance used across all test methods. */
    private final CalculationFormatter formatter = new CalculationFormatter();

    // ========================================================================
    // formatResult() Tests — All Five Operators
    // ========================================================================

    /**
     * Verifies that addition results are formatted as "num1 + num2 = result".
     * Uses operands 10.0 and 5.0 with expected result 15.0.
     */
    @Test
    void testFormatResultAddition() {
        CalculationResult result = new CalculationResult(10.0, 5.0, '+', 15.0);
        assertEquals("10.0 + 5.0 = 15.0", formatter.formatResult(result));
    }

    /**
     * Verifies that subtraction results are formatted as "num1 - num2 = result".
     * Uses operands 10.0 and 5.0 with expected result 5.0.
     */
    @Test
    void testFormatResultSubtraction() {
        CalculationResult result = new CalculationResult(10.0, 5.0, '-', 5.0);
        assertEquals("10.0 - 5.0 = 5.0", formatter.formatResult(result));
    }

    /**
     * Verifies that multiplication results are formatted as "num1 * num2 = result".
     * Uses operands 4.0 and 3.0 with expected result 12.0.
     */
    @Test
    void testFormatResultMultiplication() {
        CalculationResult result = new CalculationResult(4.0, 3.0, '*', 12.0);
        assertEquals("4.0 * 3.0 = 12.0", formatter.formatResult(result));
    }

    /**
     * Verifies that division results are formatted as "num1 / num2 = result".
     * Uses operands 7.5 and 2.0 with expected result 3.75 to also validate
     * that decimal precision is preserved correctly in the output.
     */
    @Test
    void testFormatResultDivision() {
        CalculationResult result = new CalculationResult(7.5, 2.0, '/', 3.75);
        assertEquals("7.5 / 2.0 = 3.75", formatter.formatResult(result));
    }

    /**
     * Verifies that modulus results are formatted as "num1 % num2 = result".
     * Uses operands 10.0 and 3.0 with expected result 1.0.
     */
    @Test
    void testFormatResultModulus() {
        CalculationResult result = new CalculationResult(10.0, 3.0, '%', 1.0);
        assertEquals("10.0 % 3.0 = 1.0", formatter.formatResult(result));
    }

    /**
     * Verifies that decimal values with multiple fractional digits are rendered
     * correctly in the formatted output. Uses operands 3.14 and 2.0 with
     * expected result 6.28 to confirm that Java's default double-to-string
     * conversion via {@link String#valueOf(double)} preserves precision.
     */
    @Test
    void testFormatResultDecimalPrecision() {
        CalculationResult result = new CalculationResult(3.14, 2.0, '*', 6.28);
        assertEquals("3.14 * 2.0 = 6.28", formatter.formatResult(result));
    }

    // ========================================================================
    // formatError() Tests — Error Message Formatting
    // ========================================================================

    /**
     * Verifies that the division-by-zero error message is formatted correctly
     * with the "Error: " prefix prepended to the message.
     */
    @Test
    void testFormatErrorDivisionByZero() {
        assertEquals("Error: Cannot divide by zero",
                formatter.formatError("Cannot divide by zero"));
    }

    /**
     * Verifies that the invalid number input error message is formatted correctly.
     * Tests a longer error string to confirm the full message is preserved.
     */
    @Test
    void testFormatErrorInvalidNumber() {
        assertEquals("Error: Invalid number input. Please enter a valid numeric value.",
                formatter.formatError("Invalid number input. Please enter a valid numeric value."));
    }

    /**
     * Verifies that the unsupported operation error message is formatted correctly.
     * Tests an error string containing special characters (single quotes, operators)
     * to confirm they are passed through without modification.
     */
    @Test
    void testFormatErrorUnsupportedOperation() {
        assertEquals("Error: Unsupported operation 'x'. Please use +, -, *, /, or %.",
                formatter.formatError("Unsupported operation 'x'. Please use +, -, *, /, or %."));
    }
}
