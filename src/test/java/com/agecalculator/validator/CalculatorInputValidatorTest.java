package com.agecalculator.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link CalculatorInputValidator}.
 * Covers all three public validation methods and their edge cases:
 * <ul>
 *   <li>{@code parseNumber(String)} — valid integers, decimals, negatives; invalid letters, symbols, empty</li>
 *   <li>{@code parseOperator(String)} — all 5 valid operators; invalid single char, multi-char, null</li>
 *   <li>{@code validateNotDivisionByZero(double, char)} — valid non-zero divisors; division and modulus by zero</li>
 * </ul>
 *
 * <p>No {@code System.out.println()} calls — assertions only. Each test method is independent
 * and stateless, sharing a single immutable {@link CalculatorInputValidator} instance.</p>
 */
class CalculatorInputValidatorTest {

    /** Shared validator instance — stateless, safe to reuse across all tests. */
    private final CalculatorInputValidator validator = new CalculatorInputValidator();

    // ========================================================================
    // parseNumber(String) Tests — 6 methods (3 valid + 3 invalid)
    // ========================================================================

    /**
     * Verifies that a valid integer string is correctly parsed to its double equivalent.
     */
    @Test
    void testParseNumberValidInteger() {
        assertEquals(5.0, validator.parseNumber("5"), 0.0001);
    }

    /**
     * Verifies that a valid decimal string is correctly parsed to its double equivalent.
     */
    @Test
    void testParseNumberValidDecimal() {
        assertEquals(3.14, validator.parseNumber("3.14"), 0.0001);
    }

    /**
     * Verifies that a valid negative decimal string is correctly parsed to its double equivalent.
     */
    @Test
    void testParseNumberValidNegative() {
        assertEquals(-7.5, validator.parseNumber("-7.5"), 0.0001);
    }

    /**
     * Verifies that alphabetic input throws {@link IllegalArgumentException}
     * with the exact expected error message.
     */
    @Test
    void testParseNumberInvalidLetters() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> validator.parseNumber("abc"));
        assertEquals("Invalid number input. Please enter a valid numeric value.", ex.getMessage());
    }

    /**
     * Verifies that special symbol input throws {@link IllegalArgumentException}.
     */
    @Test
    void testParseNumberInvalidSymbols() {
        assertThrows(IllegalArgumentException.class, () -> validator.parseNumber("@#$"));
    }

    /**
     * Verifies that an empty string input throws {@link IllegalArgumentException}.
     */
    @Test
    void testParseNumberInvalidEmpty() {
        assertThrows(IllegalArgumentException.class, () -> validator.parseNumber(""));
    }

    // ========================================================================
    // parseOperator(String) Tests — 8 methods (5 valid + 3 invalid)
    // ========================================================================

    /**
     * Verifies that the addition operator string "+" is correctly parsed to '+'.
     */
    @Test
    void testParseOperatorAddition() {
        assertEquals('+', validator.parseOperator("+"));
    }

    /**
     * Verifies that the subtraction operator string "-" is correctly parsed to '-'.
     */
    @Test
    void testParseOperatorSubtraction() {
        assertEquals('-', validator.parseOperator("-"));
    }

    /**
     * Verifies that the multiplication operator string "*" is correctly parsed to '*'.
     */
    @Test
    void testParseOperatorMultiplication() {
        assertEquals('*', validator.parseOperator("*"));
    }

    /**
     * Verifies that the division operator string "/" is correctly parsed to '/'.
     */
    @Test
    void testParseOperatorDivision() {
        assertEquals('/', validator.parseOperator("/"));
    }

    /**
     * Verifies that the modulus operator string "%" is correctly parsed to '%'.
     */
    @Test
    void testParseOperatorModulus() {
        assertEquals('%', validator.parseOperator("%"));
    }

    /**
     * Verifies that an unsupported single character throws {@link IllegalArgumentException}
     * with a message containing "Unsupported operation".
     */
    @Test
    void testParseOperatorInvalidSingleChar() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> validator.parseOperator("^"));
        assertTrue(ex.getMessage().contains("Unsupported operation"));
    }

    /**
     * Verifies that a multi-character string throws {@link IllegalArgumentException}.
     */
    @Test
    void testParseOperatorInvalidMultiChar() {
        assertThrows(IllegalArgumentException.class, () -> validator.parseOperator("++"));
    }

    /**
     * Verifies that null input throws {@link IllegalArgumentException}.
     */
    @Test
    void testParseOperatorNull() {
        assertThrows(IllegalArgumentException.class, () -> validator.parseOperator(null));
    }

    // ========================================================================
    // validateNotDivisionByZero(double, char) Tests — 5 methods (3 valid + 2 invalid)
    // ========================================================================

    /**
     * Verifies that dividing by a non-zero divisor does not throw any exception.
     */
    @Test
    void testValidateNotDivisionByZeroValidDivision() {
        assertDoesNotThrow(() -> validator.validateNotDivisionByZero(5.0, '/'));
    }

    /**
     * Verifies that modulus with a non-zero divisor does not throw any exception.
     */
    @Test
    void testValidateNotDivisionByZeroValidModulus() {
        assertDoesNotThrow(() -> validator.validateNotDivisionByZero(3.0, '%'));
    }

    /**
     * Verifies that a zero second operand with a non-division operator (addition)
     * does not throw any exception — zero is only problematic for division and modulus.
     */
    @Test
    void testValidateNotDivisionByZeroZeroWithAddition() {
        assertDoesNotThrow(() -> validator.validateNotDivisionByZero(0.0, '+'));
    }

    /**
     * Verifies that division by zero throws {@link ArithmeticException}
     * with the exact expected message.
     */
    @Test
    void testValidateNotDivisionByZeroDivisionByZero() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
            () -> validator.validateNotDivisionByZero(0.0, '/'));
        assertEquals("Cannot divide by zero", ex.getMessage());
    }

    /**
     * Verifies that modulus by zero throws {@link ArithmeticException}
     * with the exact expected message.
     */
    @Test
    void testValidateNotDivisionByZeroModulusByZero() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
            () -> validator.validateNotDivisionByZero(0.0, '%'));
        assertEquals("Cannot divide by zero", ex.getMessage());
    }
}
