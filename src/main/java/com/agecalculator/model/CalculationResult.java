package com.agecalculator.model;

/**
 * Immutable data holder representing the result of an arithmetic calculation.
 * Stores the two operands, the operator used, and the computed result.
 * This class has no setter methods — once constructed, its state cannot change.
 *
 * <p>This is the foundational data transfer object for the Arithmetic Calculator
 * feature. It is produced by {@code CalculatorService} after performing a
 * calculation and consumed by {@code CalculationFormatter} for display formatting.</p>
 *
 * <p>Supported operators include: {@code +} (addition), {@code -} (subtraction),
 * {@code *} (multiplication), {@code /} (division), and {@code %} (modulus).</p>
 */
public class CalculationResult {

    /** The first operand in the arithmetic calculation. */
    private final double num1;

    /** The second operand in the arithmetic calculation. */
    private final double num2;

    /** The arithmetic operator used in the calculation (+, -, *, /, %). */
    private final char operator;

    /** The computed result of applying the operator to the two operands. */
    private final double result;

    /**
     * Constructs a new CalculationResult with the given operands, operator, and result.
     *
     * @param num1     the first operand
     * @param num2     the second operand
     * @param operator the arithmetic operator used (+, -, *, /, %)
     * @param result   the computed result of the operation
     */
    public CalculationResult(double num1, double num2, char operator, double result) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
        this.result = result;
    }

    /**
     * Returns the first operand.
     *
     * @return the first operand as a double
     */
    public double getNum1() {
        return num1;
    }

    /**
     * Returns the second operand.
     *
     * @return the second operand as a double
     */
    public double getNum2() {
        return num2;
    }

    /**
     * Returns the arithmetic operator used in the calculation.
     *
     * @return the operator character (+, -, *, /, %)
     */
    public char getOperator() {
        return operator;
    }

    /**
     * Returns the computed result of the calculation.
     *
     * @return the result as a double
     */
    public double getResult() {
        return result;
    }

    /**
     * Returns a string representation of this calculation result for debugging purposes.
     *
     * @return a string in the format "CalculationResult{num1=X, num2=Y, operator=Z, result=W}"
     */
    @Override
    public String toString() {
        return "CalculationResult{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", operator=" + operator +
                ", result=" + result +
                '}';
    }
}
