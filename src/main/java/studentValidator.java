import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class studentValidator {

    public static void main(String[] args) {
        validateStudentDetails("students.txt");
    }

    public static void validateStudentDetails(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line into first name and second name
                String[] names = line.split(" ");
                if (names.length < 2) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }

                String firstName = names[0];
                String secondName = names[1];

                // Read the remaining information
                String classesString = reader.readLine();
                String studentNumber = reader.readLine();

                // Validate if classesString is a valid integer
                if (!isValidInteger(classesString)) {
                    System.out.println("Invalid number of classes: " + classesString + " (Should be an integer between 1 and 8 inclusive)");
                    continue; // Skip to the next iteration
                }

                int numberOfClasses = Integer.parseInt(classesString);

                if (isValidDetails(firstName, secondName, numberOfClasses, studentNumber)) {
                    System.out.println("Student details are valid:");
                    System.out.println("First Name: " + firstName);
                    System.out.println("Second Name: " + secondName);
                    System.out.println("Number of Classes: " + numberOfClasses);
                    System.out.println("Student Number: " + studentNumber);
                    System.out.println();
                } else {
                    System.out.println("Invalid student details:");
                    System.out.println("First Name: " + firstName);
                    System.out.println("Second Name: " + secondName);
                    System.out.println("Number of Classes: " + numberOfClasses);
                    System.out.println("Student Number: " + studentNumber);
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidDetails(String firstName, String secondName, int numberOfClasses, String studentNumber) {
        // Validation criteria based on specified rules

        // Check if the first name contains only letters
        if (!firstName.matches("[A-Za-z]+")) {
            System.out.println("Invalid first name: " + firstName + " (Should contain only letters)");
            return false;
        }

        // Check if the second name contains letters and/or numbers
        if (!secondName.matches("[A-Za-z0-9 ]+")) {
            System.out.println("Invalid second name: " + secondName + " (Should contain letters and/or numbers)");
            return false;
        }

        // Check if the number of classes is between 1 and 8 inclusive
        if (numberOfClasses < 1 || numberOfClasses > 8) {
            System.out.println("Invalid number of classes: " + numberOfClasses + " (Should be between 1 and 8 inclusive)");
            return false;
        }

        // Check if the student number follows the specified pattern
        if (!studentNumber.matches("\\d{2}[A-Za-z]{2}\\d+")) {
            System.out.println("Invalid student number: " + studentNumber + " (Should follow the specified pattern)");
            return false;
        }

        // If all checks pass, the details are considered valid
        return true;
    }

    // Additional method to check if a string is a valid integer
    public static boolean isValidInteger(String str) {
        try {
            int value = Integer.parseInt(str);
            return value >= 1 && value <= 8;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
