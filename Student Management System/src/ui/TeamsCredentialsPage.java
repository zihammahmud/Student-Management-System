package ui;

import javax.swing.*;
import java.awt.*;

public class TeamsCredentialsPage extends JFrame {
    public TeamsCredentialsPage() {
        setTitle("Teams Credentials");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel infoLabel = new JLabel("🧑‍💻 Your Microsoft Teams credentials will be shown here.");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        add(infoLabel);
        setVisible(true);
    }
}
