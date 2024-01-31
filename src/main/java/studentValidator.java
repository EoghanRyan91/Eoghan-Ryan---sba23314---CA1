import java.io.*;

public class studentValidator {

    public static void main(String[] args) {
        validateStudentDetails("students.txt");
    }


    // Method to validate student details
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


                // Run Checks

                if (isValidDetails(firstName, secondName, numberOfClasses, studentNumber)) {
                    System.out.println("Student details are valid:");
                    System.out.println("First Name: " + firstName);
                    System.out.println("Second Name: " + secondName);
                    System.out.println("Number of Classes: " + numberOfClasses);
                    System.out.println("Student Number: " + studentNumber);
                    System.out.println();
                    writeValidStudentDetails(firstName, secondName, numberOfClasses, studentNumber); // this calls the write method to write only the valid student details to status.txt
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


    // Method to check valid details w/ Regular Expressions
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
