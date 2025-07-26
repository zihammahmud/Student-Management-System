package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SpecialExamApplicationPage extends JPanel {
    private JTextArea reasonArea;
    private JButton btnSubmit;

    public SpecialExamApplicationPage() {
        setLayout(new BorderLayout(10, 10));

        reasonArea = new JTextArea(8, 40);
        JScrollPane scrollPane = new JScrollPane(reasonArea);

        btnSubmit = new JButton("Submit Application");

        add(new JLabel("Special Exam Application - Provide reason"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnSubmit, BorderLayout.SOUTH);

        btnSubmit.addActionListener(e -> submitApplication());
    }

    private void submitApplication() {
        String reason = reasonArea.getText().trim();
        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a reason.");
            return;
        }
        // Here you can add file save or send logic
        JOptionPane.showMessageDialog(this, "Application submitted:\n" + reason);
        reasonArea.setText("");
    }
}
