package ui;

import javax.swing.*;
import java.awt.*;

public class AcademicCalendarPage extends JFrame {
    public AcademicCalendarPage() {
        setTitle("Academic Calendar");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea calendarArea = new JTextArea("📅 Academic Calendar will be shown here.");
        calendarArea.setEditable(false);
        calendarArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(calendarArea);

        add(scrollPane);
        setVisible(true);
    }
}
