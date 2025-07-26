package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentForumPage extends JPanel {
    private JTextArea forumArea;
    private JTextField inputField;
    private JButton btnPost;
    private java.util.List<String> messages;

    public StudentForumPage() {
        setLayout(new BorderLayout(10, 10));

        forumArea = new JTextArea();
        forumArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(forumArea);

        inputField = new JTextField();
        btnPost = new JButton("Post");

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(btnPost, BorderLayout.EAST);

        add(new JLabel("Student Forum"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        messages = new ArrayList<>();

        btnPost.addActionListener(e -> postMessage());
    }

    private void postMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            messages.add(message);
            updateForum();
            inputField.setText("");
        }
    }

    private void updateForum() {
        StringBuilder sb = new StringBuilder();
        for (String msg : messages) {
            sb.append(msg).append("\n\n");
        }
        forumArea.setText(sb.toString());
    }
}
