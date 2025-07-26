package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class HostelPage extends JPanel {
    JTextField nameField = new JTextField(10);
    JTextField roomField = new JTextField(5);
    JButton btnAssign = new JButton("Assign Room");
    JTextArea roomStatus = new JTextArea(10, 30);

    public HostelPage() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Student Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Room No:"));
        panel.add(roomField);
        panel.add(btnAssign);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(roomStatus), BorderLayout.CENTER);

        btnAssign.addActionListener(e -> {
            String name = nameField.getText();
            String room = roomField.getText();
            if (!name.isEmpty() && !room.isEmpty()) {
                String entry = name + " assigned to Room " + room + "\n";
                roomStatus.append(entry);
                saveToFile(entry);
                nameField.setText("");
                roomField.setText("");
            }
        });

        loadHistory();
    }

    private void saveToFile(String entry) {
        try (FileWriter fw = new FileWriter("hostel_data.txt", true)) {
            fw.write(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader("hostel_data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                roomStatus.append(line + "\n");
            }
        } catch (IOException e) {
            roomStatus.setText("No assignments yet.");
        }
    }
}
