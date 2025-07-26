package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class StudentLeaderboardPage extends JPanel {
    JTextArea leaderboardArea = new JTextArea(15, 30);

    public StudentLeaderboardPage() {
        setLayout(new BorderLayout());
        leaderboardArea.setEditable(false);
        add(new JScrollPane(leaderboardArea), BorderLayout.CENTER);
        showLeaderboard();
    }

    private void showLeaderboard() {
        Map<String, Integer> scores = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("leaderboard.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                scores.put(parts[0], Integer.parseInt(parts[1].trim()));
            }
        } catch (IOException e) {
            leaderboardArea.setText("Leaderboard not available.");
            return;
        }

        leaderboardArea.append("Top Students:\n----------------------\n");
        scores.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(5)
                .forEach(e -> leaderboardArea.append(e.getKey() + " - " + e.getValue() + " pts\n"));
    }
}
