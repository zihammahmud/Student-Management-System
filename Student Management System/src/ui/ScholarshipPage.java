package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScholarshipPage extends JPanel {
    public ScholarshipPage() {
        setLayout(new BorderLayout());

        JLabel lblGPA = new JLabel("Enter your GPA: ");
        JTextField txtGPA = new JTextField(10);
        JButton btnCheck = new JButton("Check Scholarship");
        JLabel resultLabel = new JLabel("");

        JPanel inputPanel = new JPanel();
        inputPanel.add(lblGPA);
        inputPanel.add(txtGPA);
        inputPanel.add(btnCheck);

        add(inputPanel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);

        btnCheck.addActionListener(e -> {
            try {
                double gpa = Double.parseDouble(txtGPA.getText());
                if (gpa >= 3.75) {
                    resultLabel.setText("✅ Eligible for Scholarship");
                } else {
                    resultLabel.setText("❌ Not Eligible");
                }
            } catch (Exception ex) {
                resultLabel.setText("Invalid GPA");
            }
        });
    }
}
