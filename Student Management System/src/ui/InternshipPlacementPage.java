package ui;

import javax.swing.*;
import java.awt.*;

public class InternshipPlacementPage extends JFrame {
    public InternshipPlacementPage() {
        setTitle("Internship & Placement");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea internshipArea = new JTextArea("💼 Internship & Placement details will appear here.");
        internshipArea.setEditable(false);
        internshipArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(internshipArea);

        add(scrollPane);
        setVisible(true);
    }
}
