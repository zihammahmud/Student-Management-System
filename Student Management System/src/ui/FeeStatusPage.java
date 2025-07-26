package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FeeStatusPage extends JPanel {
    JTextField nameField = new JTextField(10);
    JButton checkBtn = new JButton("Check Status");
    JLabel result = new JLabel("");

    public FeeStatusPage() {
        setLayout(new FlowLayout());
        add(new JLabel("Enter Student Name:"));
        add(nameField);
        add(checkBtn);
        add(result);

        checkBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                checkFee(name);
            } else {
                result.setText("Please enter a name.");
            }
        });
    }

    private void checkFee(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader("fees.txt"))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().startsWith(name.toLowerCase())) {
                    result.setText("Status: " + line.split(":")[1].trim());
                    found = true;
                    break;
                }
            }
            if (!found) result.setText("Student not found.");
        } catch (IOException e) {
            result.setText("Error loading fee status.");
        }
    }
}
