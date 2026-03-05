package com.agecalculator;

import com.agecalculator.model.CalculationResult;
import com.agecalculator.service.CalculatorService;
import com.agecalculator.validator.CalculatorInputValidator;
import com.agecalculator.formatter.CalculationFormatter;
import com.agecalculator.ui.CalculatorConsoleUI;
import java.util.Scanner;

/**
 * Main application entry point that provides a menu-driven interface
 * for the Age Calculator and Arithmetic Calculator features.
 *
 * <p>This class serves as the orchestration layer: it presents a main menu
 * to the user, reads menu selections, and routes execution to the
 * appropriate feature flow. All user-facing I/O for individual features
 * is delegated to dedicated UI classes; only the top-level menu display
 * and routing logic uses {@code System.out} directly.</p>
 *
 * <p>A single shared {@link Scanner} instance is created and passed to
 * feature UI components via constructor injection to avoid multiple
 * {@code System.in} readers.</p>
 *
 * <p>Exception handling is performed at this orchestration layer so that
 * services and validators never print directly to the console.</p>
 */
public class AgeCalculatorApp {

    /** Shared Scanner instance for reading all user input from System.in. */
    private static final Scanner scanner = new Scanner(System.in);

    /** Menu option constant for the Age Calculator feature. */
    private static final String MENU_OPTION_AGE_CALCULATOR = "1";

    /** Menu option constant for the Arithmetic Calculator feature. */
    private static final String MENU_OPTION_ARITHMETIC_CALCULATOR = "2";

    /** Menu option constant for exiting the application. */
    private static final String MENU_OPTION_EXIT = "3";

    /**
     * Application entry point. Displays a continuously looping main menu
     * that allows the user to choose between the Age Calculator, the
     * Arithmetic Calculator, or exiting the application.
     *
     * <p>The menu loop runs until the user selects the exit option. Invalid
     * selections produce an error message and re-display the menu. The
     * shared {@link Scanner} instance is used for all input and is never
     * closed explicitly to avoid closing {@code System.in}.</p>
     *
     * @param args command-line arguments (currently unused)
     */
    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case MENU_OPTION_AGE_CALCULATOR:
                    runAgeCalculator();
                    break;
                case MENU_OPTION_ARITHMETIC_CALCULATOR:
                    runCalculator();
                    break;
                case MENU_OPTION_EXIT:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    break;
            }
        }
    }

    /**
     * Displays the main application menu to standard output.
     *
     * <p>Presents three numbered options: Age Calculator, Arithmetic
     * Calculator, and Exit. The trailing prompt is printed without a
     * newline so that the user's input appears on the same line.</p>
     */
    private static void displayMainMenu() {
        System.out.println("=== Application Menu ===");
        System.out.println("1. Age Calculator");
        System.out.println("2. Arithmetic Calculator");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Orchestrates the Arithmetic Calculator feature flow.
     *
     * <p>This method creates instances of all Calculator-related components
     * using the shared {@link Scanner}, then executes the prompt-validate-
     * calculate-format-display pipeline. The full flow is:</p>
     * <ol>
     *   <li>Prompt for the first number via {@link CalculatorConsoleUI}</li>
     *   <li>Parse and validate the first number via {@link CalculatorInputValidator}</li>
     *   <li>Prompt for the second number</li>
     *   <li>Parse and validate the second number</li>
     *   <li>Prompt for the arithmetic operator</li>
     *   <li>Parse and validate the operator</li>
     *   <li>Pre-check for division by zero</li>
     *   <li>Perform the calculation via {@link CalculatorService}</li>
     *   <li>Format the result via {@link CalculationFormatter}</li>
     *   <li>Display the formatted result to the user</li>
     * </ol>
     *
     * <p>All exceptions are caught at this level and displayed to the user
     * through the UI's error display channel. No raw stack traces are shown.</p>
     */
    private static void runCalculator() {
        CalculatorConsoleUI calcUI = new CalculatorConsoleUI(scanner);
        CalculatorInputValidator calcValidator = new CalculatorInputValidator();
        CalculatorService calcService = new CalculatorService();
        CalculationFormatter calcFormatter = new CalculationFormatter();

        try {
            // Step 1: Prompt and validate first number
            String num1Input = calcUI.promptFirstNumber();
            double num1 = calcValidator.parseNumber(num1Input);

            // Step 2: Prompt and validate second number
            String num2Input = calcUI.promptSecondNumber();
            double num2 = calcValidator.parseNumber(num2Input);

            // Step 3: Prompt and validate operator
            String opInput = calcUI.promptOperator();
            char operator = calcValidator.parseOperator(opInput);

            // Step 4: Pre-check division by zero before computation
            calcValidator.validateNotDivisionByZero(num2, operator);

            // Step 5: Perform calculation
            CalculationResult result = calcService.calculate(num1, num2, operator);

            // Step 6: Format and display result
            String formatted = calcFormatter.formatResult(result);
            calcUI.displayResult(formatted);

        } catch (IllegalArgumentException e) {
            // Handles invalid numeric input and unsupported operator errors
            calcUI.displayError(calcFormatter.formatError(e.getMessage()));
        } catch (ArithmeticException e) {
            // Handles division by zero errors
            calcUI.displayError(calcFormatter.formatError(e.getMessage()));
        }
    }

    /**
     * Orchestrates the Age Calculator feature flow.
     *
     * <p>This method is a placeholder for the Age Calculator feature, which
     * is planned but whose supporting classes (AgeResult, AgeCalculationService,
     * DateInputValidator, AgeFormatter, ConsoleUI) have not yet been implemented.
     * Once those classes are available, this method will be updated to follow the
     * same prompt-validate-calculate-format-display pattern as
     * {@link #runCalculator()}.</p>
     */
    private static void runAgeCalculator() {
        System.out.println("Age Calculator feature - coming soon!");
    }
}
