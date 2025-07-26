package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class AttendanceGraphPage extends JPanel {
    Map<String, Integer> attendanceMap = new LinkedHashMap<>();

    public AttendanceGraphPage() {
        setPreferredSize(new Dimension(400, 300));
        loadAttendance();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int yBase = 250;

        g.drawString("Attendance Chart", 140, 20);

        for (Map.Entry<String, Integer> entry : attendanceMap.entrySet()) {
            int height = entry.getValue() * 2;
            g.setColor(Color.BLUE);
            g.fillRect(x, yBase - height, 40, height);
            g.setColor(Color.BLACK);
            g.drawString(entry.getKey(), x, yBase + 15);
            g.drawString(entry.getValue() + "%", x, yBase - height - 5);
            x += 70;
        }
    }

    private void loadAttendance() {
        try (BufferedReader br = new BufferedReader(new FileReader("attendance_graph.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                attendanceMap.put(parts[0], Integer.parseInt(parts[1].trim()));
            }
        } catch (IOException e) {
            attendanceMap.put("No Data", 0);
        }
    }
}
