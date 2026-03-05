package com.agecalculator.ui;

import java.util.Scanner;

/**
 * Console-based user interface for the Arithmetic Calculator feature.
 * Handles all user prompts and result/error display for calculator operations.
 *
 * <p>This class is the only class in the project allowed to directly use
 * {@code System.out} and {@code System.err} for user-facing I/O.
 * It follows the Single Responsibility Principle — I/O handling only,
 * with no computation, validation, or formatting logic.</p>
 */
public class CalculatorConsoleUI {

    private final Scanner scanner;

    /**
     * Constructs a new CalculatorConsoleUI with the given Scanner for reading user input.
     *
     * @param scanner the shared Scanner instance for reading from System.in
     */
    public CalculatorConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prompts the user to enter the first number and reads the input.
     *
     * @return the raw string input entered by the user
     */
    public String promptFirstNumber() {
        System.out.print("Enter first number: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to enter the second number and reads the input.
     *
     * @return the raw string input entered by the user
     */
    public String promptSecondNumber() {
        System.out.print("Enter second number: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to enter an arithmetic operation and reads the input.
     *
     * @return the raw string input entered by the user
     */
    public String promptOperator() {
        System.out.print("Enter operation (+, -, *, /, %): ");
        return scanner.nextLine();
    }

    /**
     * Displays a result message to the user via standard output.
     *
     * @param message the formatted result message to display
     */
    public void displayResult(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user via standard error output.
     *
     * @param message the formatted error message to display
     */
    public void displayError(String message) {
        System.err.println(message);
    }
}
