package ui;

import javax.swing.*;
import java.awt.*;

public class EventManagementPage extends JPanel {
    private DefaultListModel<String> eventListModel;
    private JList<String> eventList;
    private JButton btnAddEvent, btnRemoveEvent;

    public EventManagementPage() {
        setLayout(new BorderLayout(10, 10));

        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        JScrollPane scrollPane = new JScrollPane(eventList);

        JPanel buttonPanel = new JPanel();
        btnAddEvent = new JButton("Add Event");
        btnRemoveEvent = new JButton("Remove Selected");

        buttonPanel.add(btnAddEvent);
        buttonPanel.add(btnRemoveEvent);

        add(new JLabel("Campus Events"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnAddEvent.addActionListener(e -> addEvent());
        btnRemoveEvent.addActionListener(e -> removeSelectedEvent());
    }

    private void addEvent() {
        String event = JOptionPane.showInputDialog(this, "Enter event name:");
        if (event != null && !event.trim().isEmpty()) {
            eventListModel.addElement(event.trim());
        }
    }

    private void removeSelectedEvent() {
        int selectedIndex = eventList.getSelectedIndex();
        if (selectedIndex != -1) {
            eventListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select an event to remove.");
        }
    }
}
