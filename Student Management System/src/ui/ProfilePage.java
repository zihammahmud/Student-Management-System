package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ProfilePage extends JPanel {
    private JTextField tfName, tfEmail, tfContact;
    private JButton btnSave, btnLoad;
    private final String profileFile = "data/profile.txt";

    public ProfilePage() {
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Name:"));
        tfName = new JTextField();
        add(tfName);

        add(new JLabel("Email:"));
        tfEmail = new JTextField();
        add(tfEmail);

        add(new JLabel("Contact Number:"));
        tfContact = new JTextField();
        add(tfContact);

        btnSave = new JButton("Save Profile");
        btnLoad = new JButton("Load Profile");

        add(btnSave);
        add(btnLoad);

        btnSave.addActionListener(e -> saveProfile());
        btnLoad.addActionListener(e -> loadProfile());
    }

    private void saveProfile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(profileFile))) {
            bw.write(tfName.getText() + "\n");
            bw.write(tfEmail.getText() + "\n");
            bw.write(tfContact.getText() + "\n");
            JOptionPane.showMessageDialog(this, "Profile saved!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving profile: " + e.getMessage());
        }
    }

    private void loadProfile() {
        try (BufferedReader br = new BufferedReader(new FileReader(profileFile))) {
            tfName.setText(br.readLine());
            tfEmail.setText(br.readLine());
            tfContact.setText(br.readLine());
            JOptionPane.showMessageDialog(this, "Profile loaded!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading profile: " + e.getMessage());
        }
    }
}
