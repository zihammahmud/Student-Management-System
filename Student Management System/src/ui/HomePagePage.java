package ui;

import javax.swing.*;
import java.awt.*;

public class HomePagePage extends JPanel {
    public HomePagePage() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome to the Student Management System", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }
}
