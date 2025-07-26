package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PasswordChangePage extends JPanel {
    private JPasswordField pfOldPassword, pfNewPassword, pfConfirmPassword;
    private JButton btnChange;
    private final String passwordFile = "data/password.txt";

    public PasswordChangePage() {
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Old Password:"));
        pfOldPassword = new JPasswordField();
        add(pfOldPassword);

        add(new JLabel("New Password:"));
        pfNewPassword = new JPasswordField();
        add(pfNewPassword);

        add(new JLabel("Confirm New Password:"));
        pfConfirmPassword = new JPasswordField();
        add(pfConfirmPassword);

        btnChange = new JButton("Change Password");
        add(new JLabel());
        add(btnChange);

        btnChange.addActionListener(e -> changePassword());
    }

    private void changePassword() {
        String oldPass = new String(pfOldPassword.getPassword());
        String newPass = new String(pfNewPassword.getPassword());
        String confirmPass = new String(pfConfirmPassword.getPassword());

        String storedPass = readStoredPassword();

        if (!oldPass.equals(storedPass)) {
            JOptionPane.showMessageDialog(this, "Old password is incorrect.");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "New password and confirm password do not match.");
            return;
        }

        if (newPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "New password cannot be empty.");
            return;
        }

        if (writeNewPassword(newPass)) {
            JOptionPane.showMessageDialog(this, "Password changed successfully.");
            pfOldPassword.setText("");
            pfNewPassword.setText("");
            pfConfirmPassword.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to change password.");
        }
    }

    private String readStoredPassword() {
        try (BufferedReader br = new BufferedReader(new FileReader(passwordFile))) {
            return br.readLine();
        } catch (IOException e) {
            // If no password file, assume default "admin"
            return "admin";
        }
    }

    private boolean writeNewPassword(String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(passwordFile))) {
            bw.write(password);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
