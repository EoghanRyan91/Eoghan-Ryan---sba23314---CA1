import java.io.*;
import java.util.Scanner;


public class studentValidator {

      // GitHub repo for the assignment
      // https://github.com/EoghanRyan91/Eoghan-Ryan---sba23314---CA1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Starts a scanner to take user input on which mode they would like to use with the program, as per the criteria for a
        // distinction

        System.out.println("Welcome to the Student Details Validator!");

        // Defines a while loop to take the user input and then run a switch/case with the input to determine the mode of use.
        // Foe each of the respective modes I have broken the functionality into two methods; validateStudentDetails, which
        // uses a file locally, and enterStudentDetailsManually, which allows users to enter manual details to be checked.

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Read student details from a file");
            System.out.println("2. Enter student details manually");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    validateStudentDetailsFromFile("students.txt");
                    break;
                case 2:
                    enterStudentDetailsManually();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // Method code for the manual usage of the program

    public static void enterStudentDetailsManually() {

        // Taking user input and saving the details to relevant data

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student details manually:");

        System.out.print("First Name: ");
        String firstName = scanner.next();

        System.out.print("Second Name: ");
        String secondName = scanner.next();

        System.out.print("Number of Classes: ");
        int numberOfClasses = scanner.nextInt();

        System.out.print("Student Number: ");
        String studentNumber = scanner.next();

        // Validate and process the entered student details using a method called isValidDetails which contains many error checks and returns true if all are passed.
        // If all details are correct, if value is TRUE and the console outputs a success message.

        if (isValidDetails(firstName, secondName, numberOfClasses, studentNumber)) {
            System.out.println("Student details are valid:");
            System.out.println("First Name: " + firstName);
            System.out.println("Second Name: " + secondName);
            System.out.println("Number of Classes: " + numberOfClasses);
            System.out.println("Student Number: " + studentNumber);

            // Write valid student details to "status.txt" if they pass the RegEx checks inside isValidDetails. writeValidStudentDetails is defined later in the code

            writeValidStudentDetails(firstName, secondName, numberOfClasses, studentNumber);

            // Do not write the details if isValidDetails is FALSE and show the user the error

        } else {
            System.out.println("Invalid student details. Not written to the file.");
        }
    }


    // Method to validate student details from a file called students.txt

    public static void validateStudentDetailsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line into first name and second name
                String[] names = line.split(" ");
                if (names.length < 2) {
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


                // Run Checks

                if (isValidDetails(firstName, secondName, numberOfClasses, studentNumber)) {
                    System.out.println("Student details are valid and have been exported to status.txt!");
                    System.out.println("First Name: " + firstName);
                    System.out.println("Second Name: " + secondName);
                    System.out.println("Number of Classes: " + numberOfClasses);
                    System.out.println("Student Number: " + studentNumber);
                    System.out.println();
                    writeValidStudentDetails(firstName, secondName, numberOfClasses, studentNumber); // this calls the write method to write only the valid student details to status.txt
                } else {
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


    // Method to check valid details w/ Regular Expressions used in earlier methods in code

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
        if (!studentNumber.matches("(20\\d{3}[A-Za-z]{3}[1-9]\\d{0,2}|\\d{2}[A-Za-z]{3}[1-9]\\d{0,2})")) {
            System.out.println("Invalid student number: " + studentNumber + " (Student number must begin with 20 or higher, with 3 letters after this, then a number between 1-200!)");
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

    // Method to write valid student details and workload status to "status.txt"
    public static void writeValidStudentDetails(String firstName, String secondName, int numberOfClasses, String studentNumber) {
        // Define workload status based on the specified rules
        String workloadStatus;
        if (numberOfClasses == 1) {
            workloadStatus = "Very Light";
        } else if (numberOfClasses == 2) {
            workloadStatus = "Light";
        } else if (numberOfClasses >= 3 && numberOfClasses <= 5) {
            workloadStatus = "Part Time";
        } else {
            workloadStatus = "Full Time";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("status.txt", true))) {
            // Write first name and second name on one line
            writer.write(firstName + " " + secondName);
            writer.newLine();

            // Write workload status on the line underneath
            writer.write(workloadStatus);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
