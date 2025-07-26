package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CourseSelectionPage extends JPanel {
    JCheckBox[] courses = {
            new JCheckBox("Java"),
            new JCheckBox("Python"),
            new JCheckBox("Data Structures"),
            new JCheckBox("Operating Systems")
    };
    JTextField nameField = new JTextField(10);
    JButton saveBtn = new JButton("Save Selection");
    JTextArea result = new JTextArea(10, 30);

    public CourseSelectionPage() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Student Name:"));
        top.add(nameField);

        JPanel coursePanel = new JPanel();
        for (JCheckBox cb : courses) coursePanel.add(cb);

        JPanel actionPanel = new JPanel();
        actionPanel.add(saveBtn);

        add(top, BorderLayout.NORTH);
        add(coursePanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
        add(new JScrollPane(result), BorderLayout.EAST);

        saveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                StringBuilder sb = new StringBuilder(name + " selected: ");
                for (JCheckBox cb : courses) {
                    if (cb.isSelected()) sb.append(cb.getText()).append(", ");
                }
                String entry = sb.toString().replaceAll(", $", "") + "\n";
                result.append(entry);
                saveToFile(entry);
                nameField.setText("");
                for (JCheckBox cb : courses) cb.setSelected(false);
            }
        });
    }

    private void saveToFile(String entry) {
        try (FileWriter fw = new FileWriter("course_selections.txt", true)) {
            fw.write(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
