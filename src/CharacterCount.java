import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * asks the user for the absolute name of the file, checks if the file exists, opens it for reading, counts the number
 * of capital and small letters of the English alphabet in it and writes the results of the count to a user-defined file,
 * creating it (or opening it for writing if such a file already exists)
 */
public class CharacterCount extends JFrame {
    private JTextField fileNameField;
    private JTextArea resultArea;
    /**
     * structuring a GUI and get file path
     */

    public CharacterCount() {
        //GUI
        super("File Analyzer");

        JLabel fileNameLabel = new JLabel("Enter absolute file path:");
        fileNameField = new JTextField(20);
        JButton analyzeButton = new JButton("Analyze");
        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analyzeFile();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(fileNameLabel);
        inputPanel.add(fileNameField);
        inputPanel.add(analyzeButton);

        resultArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * counts the number of each letter in the file, including uppercase and lowercase letters and write them into
     * existing file or write them into new file
     */
    private void analyzeFile() {
        String fileName = fileNameField.getText();
        File inputFile = new File(fileName);
        // Check if the input file exists
        if (!inputFile.exists()) {
            JOptionPane.showMessageDialog(this, "File does not exist.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Read the input file and count the letters
        try (FileReader reader = new FileReader(inputFile)) {
            // Initialize the letter count array
            int[] letterCounts = new int[26 * 2];

            int c;
            while ((c = reader.read()) != -1) {
                if (c >= 'A' && c <= 'Z') {
                    letterCounts[c - 'A']++;
                } else if (c >= 'a' && c <= 'z') {
                    letterCounts[c - 'a' + 26]++;
                }
            }

            // Ask the user for the name of the output file
            String outputFileName = JOptionPane.showInputDialog(this, "Enter output file name:");
            if (outputFileName == null) {
                return;
            }

            File outputFile = new File(outputFileName);
            boolean fileExists = outputFile.exists();

            // Write the letter count results to the output file
            try (FileWriter writer = new FileWriter(outputFile, true)) {
                if (!fileExists) {
                    writer.write("Letter Counts:\n");
                }
                for (int i = 0; i < 26; i++) {
                    writer.write((char) ('A' + i) + ": " + letterCounts[i] + "\n");
                }
                for (int i = 26; i < 26 * 2; i++) {
                    writer.write((char) ('a' + i - 26) + ": " + letterCounts[i] + "\n");
                }
                writer.write("\n");
            }
            JOptionPane.showMessageDialog(this, "File analysis complete.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
