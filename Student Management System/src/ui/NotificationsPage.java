package ui;

import javax.swing.*;
import java.awt.*;

public class NotificationsPage extends JPanel {
    private DefaultListModel<String> notificationsModel;
    private JList<String> notificationsList;
    private JButton btnAddNotification, btnClearNotifications;

    public NotificationsPage() {
        setLayout(new BorderLayout(10, 10));

        notificationsModel = new DefaultListModel<>();
        notificationsList = new JList<>(notificationsModel);
        JScrollPane scrollPane = new JScrollPane(notificationsList);

        JPanel buttonPanel = new JPanel();
        btnAddNotification = new JButton("Add Notification");
        btnClearNotifications = new JButton("Clear All");

        buttonPanel.add(btnAddNotification);
        buttonPanel.add(btnClearNotifications);

        add(new JLabel("Notifications"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnAddNotification.addActionListener(e -> addNotification());
        btnClearNotifications.addActionListener(e -> clearNotifications());
    }

    private void addNotification() {
        String message = JOptionPane.showInputDialog(this, "Enter notification message:");
        if (message != null && !message.trim().isEmpty()) {
            notificationsModel.addElement(message.trim());
        }
    }

    private void clearNotifications() {
        notificationsModel.clear();
    }
}
