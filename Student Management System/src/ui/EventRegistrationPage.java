package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class EventRegistrationPage extends JPanel {
    JTextField nameField = new JTextField(10);
    JComboBox<String> eventList = new JComboBox<>(new String[]{"Tech Fest", "Sports Day", "Cultural Night"});
    JButton registerBtn = new JButton("Register");
    JTextArea registeredArea = new JTextArea(10, 30);

    public EventRegistrationPage() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Name:"));
        top.add(nameField);
        top.add(new JLabel("Event:"));
        top.add(eventList);
        top.add(registerBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(registeredArea), BorderLayout.CENTER);

        registerBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String event = (String) eventList.getSelectedItem();
            if (!name.isEmpty()) {
                String entry = name + " registered for " + event + "\n";
                registeredArea.append(entry);
                saveEntry(entry);
                nameField.setText("");
            }
        });

        loadRegistered();
    }

    private void saveEntry(String entry) {
        try (FileWriter fw = new FileWriter("event_registrations.txt", true)) {
            fw.write(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRegistered() {
        try (BufferedReader br = new BufferedReader(new FileReader("event_registrations.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                registeredArea.append(line + "\n");
            }
        } catch (IOException e) {
            registeredArea.setText("No registrations yet.");
        }
    }
}
