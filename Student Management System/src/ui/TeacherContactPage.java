package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TeacherContactPage extends JPanel {
    JTextArea contactArea = new JTextArea(15, 35);

    public TeacherContactPage() {
        setLayout(new BorderLayout());
        contactArea.setEditable(false);
        add(new JScrollPane(contactArea), BorderLayout.CENTER);
        loadContacts();
    }

    private void loadContacts() {
        try (BufferedReader br = new BufferedReader(new FileReader("teachers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                contactArea.append(line + "\n");
            }
        } catch (IOException e) {
            contactArea.setText("No teacher contact info available.");
        }
    }
}
