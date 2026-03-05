package com.agecalculator.service;

import com.agecalculator.model.CalculationResult;

/**
 * Core arithmetic computation service that performs basic arithmetic operations
 * on two numeric operands. Supports addition, subtraction, multiplication,
 * division, and modulus operations.
 *
 * <p>This service follows the Single Responsibility Principle — it handles
 * only computation logic, with no I/O or input validation.</p>
 */
public class CalculatorService {

    /**
     * Performs an arithmetic operation on two numbers based on the specified operator.
     *
     * @param num1     the first operand
     * @param num2     the second operand
     * @param operator the arithmetic operator: '+', '-', '*', '/', or '%'
     * @return a {@link CalculationResult} containing the operands, operator, and computed result
     * @throws ArithmeticException      if division or modulus by zero is attempted
     * @throws IllegalArgumentException if the operator is not one of +, -, *, /, %
     */
    public CalculationResult calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return new CalculationResult(num1, num2, operator, num1 + num2);
            case '-':
                return new CalculationResult(num1, num2, operator, num1 - num2);
            case '*':
                return new CalculationResult(num1, num2, operator, num1 * num2);
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return new CalculationResult(num1, num2, operator, num1 / num2);
            case '%':
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return new CalculationResult(num1, num2, operator, num1 % num2);
            default:
                throw new IllegalArgumentException(
                    "Unsupported operation '" + operator + "'. Please use +, -, *, /, or %.");
        }
    }
}
