import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterCount {
    // Initialize the letter count arrays
    private final static int[] upperCaseCount = new int[26];
    private final static int[] lowerCaseCount = new int[26];

    /**
     * counts the number of each letter in the file, including uppercase and lowercase letters
     */
    public static void count() {
        // Ask the user for the name of the input file
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Enter the name of the input file:");
        String fileName = null;
        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
            System.exit(1);
        }

        // Check if the input file exists
        java.io.File inputFile = new java.io.File(fileName);
        if (!inputFile.exists()) {
            System.out.println("Error: input file does not exist");
            System.exit(1);
        }


        // Read the input file and count the letters
        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isUpperCase(c)) {
                        upperCaseCount[c - 'A']++;
                    } else if (Character.isLowerCase(c)) {
                        lowerCaseCount[c - 'a']++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void write() {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));

        // Ask the user for the name of the output file
        System.out.println("Enter the name of the output file:");
        String outputFileName = null;
        try {
            outputFileName = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
            System.exit(1);
        }

        // Write the letter count results to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (int i = 0; i < 26; i++) {
                writer.write((char) ('A' + i) + ": " + upperCaseCount[i] + "\n");
                writer.write((char) ('a' + i) + ": " + lowerCaseCount[i] + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
            System.exit(1);
        }

        // Print a success message
        System.out.println("Letter count successfully written to " + outputFileName);
    }
}