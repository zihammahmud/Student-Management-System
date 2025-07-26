package ui;

import javax.swing.*;
import java.awt.*;

public class StudentIDPage extends JPanel {
    public StudentIDPage() {
        setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        infoPanel.add(new JLabel("Name:"));
        infoPanel.add(new JLabel("John Doe"));  // Hardcoded for demo
        infoPanel.add(new JLabel("Student ID:"));
        infoPanel.add(new JLabel("2023001234"));
        infoPanel.add(new JLabel("Program:"));
        infoPanel.add(new JLabel("Computer Science"));

        JLabel idCardLabel = new JLabel("Digital Student ID Card", SwingConstants.CENTER);
        idCardLabel.setFont(new Font("Arial", Font.BOLD, 20));

        add(infoPanel, BorderLayout.NORTH);
        add(idCardLabel, BorderLayout.CENTER);
    }
}
