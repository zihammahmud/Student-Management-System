package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CertificateRequestPage extends JPanel {
    JTextField nameField = new JTextField(15);
    JComboBox<String> typeBox = new JComboBox<>(new String[]{"Transcript", "Bonafide", "Character", "Provisional"});
    JButton requestBtn = new JButton("Submit Request");
    JTextArea resultArea = new JTextArea(10, 30);

    public CertificateRequestPage() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Name:"));
        top.add(nameField);
        top.add(new JLabel("Certificate Type:"));
        top.add(typeBox);
        top.add(requestBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        requestBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String cert = (String) typeBox.getSelectedItem();
            if (!name.isEmpty()) {
                String request = name + " requested " + cert + " certificate.\n";
                resultArea.append(request);
                saveRequest(request);
                nameField.setText("");
            }
        });
    }

    private void saveRequest(String entry) {
        try (FileWriter fw = new FileWriter("certificate_requests.txt", true)) {
            fw.write(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
