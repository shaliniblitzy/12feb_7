package com.agecalculator.service;

import com.agecalculator.model.CalculationResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Comprehensive JUnit 5 test class for {@link CalculatorService}.
 *
 * <p>Validates all arithmetic operations (addition, subtraction, multiplication,
 * division, modulus), error handling (division by zero, unsupported operators),
 * and edge cases (large numbers, decimal precision, field population) through
 * 17 targeted test cases.</p>
 *
 * <p>Each test invokes {@code CalculatorService.calculate(double, double, char)}
 * and asserts the returned {@link CalculationResult} using JUnit 5 assertions
 * with appropriate delta tolerances for floating-point comparisons.</p>
 */
public class CalculatorServiceTest {

    /** The service under test — stateless, so a single shared instance suffices. */
    private final CalculatorService service = new CalculatorService();

    // ========================================================================
    // Addition Tests (+)
    // ========================================================================

    /**
     * Verifies addition of two positive numbers produces the correct sum.
     * Input: 10.0 + 5.0 → Expected: 15.0
     */
    @Test
    void testAdditionPositiveNumbers() {
        CalculationResult result = service.calculate(10.0, 5.0, '+');
        assertEquals(15.0, result.getResult(), 0.0001);
    }

    /**
     * Verifies addition of two negative numbers produces the correct negative sum.
     * Input: -3.0 + (-7.0) → Expected: -10.0
     */
    @Test
    void testAdditionNegativeNumbers() {
        CalculationResult result = service.calculate(-3.0, -7.0, '+');
        assertEquals(-10.0, result.getResult(), 0.0001);
    }

    /**
     * Verifies addition of two decimal (floating-point) numbers produces the correct sum.
     * Input: 1.5 + 2.3 → Expected: 3.8
     */
    @Test
    void testAdditionDecimalNumbers() {
        CalculationResult result = service.calculate(1.5, 2.3, '+');
        assertEquals(3.8, result.getResult(), 0.0001);
    }

    /**
     * Verifies addition of a negative and a positive number produces the correct sum.
     * Input: -5.0 + 3.0 → Expected: -2.0
     */
    @Test
    void testAdditionMixedPositiveNegative() {
        CalculationResult result = service.calculate(-5.0, 3.0, '+');
        assertEquals(-2.0, result.getResult(), 0.0001);
    }

    // ========================================================================
    // Subtraction Tests (-)
    // ========================================================================

    /**
     * Verifies subtraction of a smaller number from a larger number produces a positive result.
     * Input: 10.0 - 3.0 → Expected: 7.0
     */
    @Test
    void testSubtractionNormal() {
        CalculationResult result = service.calculate(10.0, 3.0, '-');
        assertEquals(7.0, result.getResult(), 0.0001);
    }

    /**
     * Verifies subtraction where the result is negative.
     * Input: 3.0 - 10.0 → Expected: -7.0
     */
    @Test
    void testSubtractionNegativeResult() {
        CalculationResult result = service.calculate(3.0, 10.0, '-');
        assertEquals(-7.0, result.getResult(), 0.0001);
    }

    // ========================================================================
    // Multiplication Tests (*)
    // ========================================================================

    /**
     * Verifies multiplication of two non-zero numbers produces the correct product.
     * Input: 4.0 * 5.0 → Expected: 20.0
     */
    @Test
    void testMultiplicationNormal() {
        CalculationResult result = service.calculate(4.0, 5.0, '*');
        assertEquals(20.0, result.getResult(), 0.0001);
    }

    /**
     * Verifies multiplication by zero produces zero.
     * Input: 5.0 * 0.0 → Expected: 0.0
     */
    @Test
    void testMultiplicationByZero() {
        CalculationResult result = service.calculate(5.0, 0.0, '*');
        assertEquals(0.0, result.getResult(), 0.0001);
    }

    // ========================================================================
    // Division Tests (/)
    // ========================================================================

    /**
     * Verifies division of two numbers producing a whole-number result.
     * Input: 10.0 / 2.0 → Expected: 5.0
     */
    @Test
    void testDivisionNormal() {
        CalculationResult result = service.calculate(10.0, 2.0, '/');
        assertEquals(5.0, result.getResult(), 0.0001);
    }

    /**
     * Verifies division producing a decimal (non-integer) result.
     * Input: 7.0 / 2.0 → Expected: 3.5
     */
    @Test
    void testDivisionDecimalResult() {
        CalculationResult result = service.calculate(7.0, 2.0, '/');
        assertEquals(3.5, result.getResult(), 0.0001);
    }

    /**
     * Verifies that division by zero throws an ArithmeticException with the
     * exact message "Cannot divide by zero".
     * Input: 10.0 / 0.0 → Expected: ArithmeticException
     */
    @Test
    void testDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class,
            () -> service.calculate(10.0, 0.0, '/'));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    // ========================================================================
    // Modulus Tests (%)
    // ========================================================================

    /**
     * Verifies modulus operation produces the correct remainder.
     * Input: 10.0 % 3.0 → Expected: 1.0
     */
    @Test
    void testModulusNormal() {
        CalculationResult result = service.calculate(10.0, 3.0, '%');
        assertEquals(1.0, result.getResult(), 0.0001);
    }

    /**
     * Verifies that modulus by zero throws an ArithmeticException with the
     * exact message "Cannot divide by zero".
     * Input: 10.0 % 0.0 → Expected: ArithmeticException
     */
    @Test
    void testModulusByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class,
            () -> service.calculate(10.0, 0.0, '%'));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    // ========================================================================
    // Unsupported Operator Test
    // ========================================================================

    /**
     * Verifies that an unsupported operator throws an IllegalArgumentException
     * whose message contains "Unsupported operation".
     * Input: 10.0 ^ 5.0 → Expected: IllegalArgumentException
     */
    @Test
    void testUnsupportedOperator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> service.calculate(10.0, 5.0, '^'));
        assertTrue(exception.getMessage().contains("Unsupported operation"));
    }

    // ========================================================================
    // Edge Case Tests
    // ========================================================================

    /**
     * Verifies arithmetic correctness with large numbers.
     * Input: 1,000,000.0 * 1,000,000.0 → Expected: 1,000,000,000,000.0
     */
    @Test
    void testLargeNumbers() {
        CalculationResult result = service.calculate(1_000_000.0, 1_000_000.0, '*');
        assertEquals(1_000_000_000_000.0, result.getResult(), 0.0001);
    }

    /**
     * Verifies decimal precision for a repeating-decimal division result.
     * Input: 1.0 / 3.0 → Expected: approximately 0.3333
     */
    @Test
    void testDecimalPrecision() {
        CalculationResult result = service.calculate(1.0, 3.0, '/');
        assertEquals(0.3333, result.getResult(), 0.001);
    }

    // ========================================================================
    // Result Field Population Test
    // ========================================================================

    /**
     * Verifies that the returned {@link CalculationResult} has all four fields
     * correctly populated: num1, num2, operator, and result.
     * Input: 10.0 + 5.0 → Verify all fields match.
     */
    @Test
    void testResultFieldsPopulated() {
        CalculationResult result = service.calculate(10.0, 5.0, '+');
        assertEquals(10.0, result.getNum1(), 0.0001);
        assertEquals(5.0, result.getNum2(), 0.0001);
        assertEquals('+', result.getOperator());
        assertEquals(15.0, result.getResult(), 0.0001);
    }
}
