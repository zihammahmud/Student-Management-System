package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class AttendancePage extends JPanel {
    JTextArea attendanceArea = new JTextArea(10, 30);
    JTextField nameField = new JTextField(10);
    JButton markPresentBtn = new JButton("Mark Present");

    public AttendancePage() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Student Name:"));
        topPanel.add(nameField);
        topPanel.add(markPresentBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(attendanceArea), BorderLayout.CENTER);

        markPresentBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                String entry = name + " - Present\n";
                attendanceArea.append(entry);
                saveToFile(entry);
                nameField.setText("");
            }
        });

        loadFromFile();
    }

    private void saveToFile(String entry) {
        try (FileWriter fw = new FileWriter("attendance.txt", true)) {
            fw.write(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("attendance.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                attendanceArea.append(line + "\n");
            }
        } catch (IOException e) {
            attendanceArea.setText("No records yet.");
        }
    }
}
