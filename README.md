# 12feb_7

A Java 21 console application featuring two tools: an **Age Calculator** that computes a person's age from their birth date, and an **Arithmetic Calculator** that performs basic arithmetic operations on two numbers. Built with Maven Standard Directory Layout, following OOP best practices and a layered architecture.

---

## Arithmetic Calculator

### Feature Description

The Arithmetic Calculator accepts two numeric values and an arithmetic operation as input, performs the specified operation on the two numbers, and displays the result to the user. It supports both integer and floating-point numbers using the `double` data type, ensuring seamless handling of whole numbers (e.g., `5`) and decimals (e.g., `3.14`).

### Supported Operations

| Symbol | Operation      |
|--------|----------------|
| `+`    | Addition       |
| `-`    | Subtraction    |
| `*`    | Multiplication |
| `/`    | Division       |
| `%`    | Modulus        |

### Usage Instructions

1. **Build the project:**

   ```bash
   mvn clean compile
   ```

2. **Run the application:**

   ```bash
   mvn exec:java
   ```

   Or run directly with Java:

   ```bash
   java -cp target/classes com.agecalculator.AgeCalculatorApp
   ```

3. **Select "2. Arithmetic Calculator"** from the main menu.
4. **Enter two numbers** and an **operation** when prompted.

### Sample Input/Output

```
=== Application Menu ===
1. Age Calculator
2. Arithmetic Calculator
3. Exit
Choose an option: 2
Enter first number: 10
Enter second number: 5
Enter operation (+, -, *, /, %): +
10.0 + 5.0 = 15.0
```

### Error Handling

The calculator gracefully handles the following error scenarios with descriptive messages:

- **Invalid number input:**
  ```
  Error: Invalid number input. Please enter a valid numeric value.
  ```
- **Invalid operator:**
  ```
  Error: Unsupported operation 'x'. Please use +, -, *, /, or %.
  ```
- **Division by zero:**
  ```
  Error: Cannot divide by zero
  ```

---

## Project Structure

The project follows the Maven Standard Directory Layout with a layered package structure under `com.agecalculator`:

```
src/
├── main/java/com/agecalculator/
│   ├── model/             — Data models (AgeResult, CalculationResult)
│   ├── service/           — Business logic services (AgeCalculationService, CalculatorService)
│   ├── validator/         — Input validation (DateInputValidator, CalculatorInputValidator)
│   ├── formatter/         — Output formatting (AgeFormatter, CalculationFormatter)
│   ├── ui/                — Console user interface (ConsoleUI, CalculatorConsoleUI)
│   └── AgeCalculatorApp.java  — Application entry point
└── test/java/com/agecalculator/
    ├── service/           — Service unit tests
    ├── validator/         — Validator unit tests
    └── formatter/         — Formatter unit tests
```

| Package                        | Responsibility            |
|--------------------------------|---------------------------|
| `com.agecalculator.model`      | Data models               |
| `com.agecalculator.service`    | Business logic services   |
| `com.agecalculator.validator`  | Input validation          |
| `com.agecalculator.formatter`  | Output formatting         |
| `com.agecalculator.ui`         | Console user interface    |

---

## Build and Run

**Prerequisites:**

- Java Development Kit (JDK) 21 or later
- Apache Maven 3.8+

**Build the project:**

```bash
mvn clean compile
```

**Run the application:**

```bash
mvn exec:java
```

Or run the compiled class directly:

```bash
java -cp target/classes com.agecalculator.AgeCalculatorApp
```

**Package as a JAR:**

```bash
mvn clean package
```

---

## Testing

All unit tests are written using **JUnit 5** (Jupiter 5.10.2). To run the full test suite:

```bash
mvn test
```

Test coverage includes:

- **CalculatorServiceTest** — Validates all arithmetic operations (addition, subtraction, multiplication, division, modulus), division-by-zero handling, unsupported operator rejection, large number handling, and decimal precision
- **CalculatorInputValidatorTest** — Validates numeric input parsing (integers and decimals), invalid input rejection (letters, symbols, empty strings), operator parsing for all five supported operators, invalid operator rejection, and division-by-zero pre-checks
- **CalculationFormatterTest** — Validates correct output format (`num1 operator num2 = result`) for each operation and error message formatting
