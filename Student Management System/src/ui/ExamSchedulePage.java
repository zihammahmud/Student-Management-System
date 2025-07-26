package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ExamSchedulePage extends JPanel {
    JTextArea scheduleArea = new JTextArea(10, 30);

    public ExamSchedulePage() {
        setLayout(new BorderLayout());
        scheduleArea.setEditable(false);
        add(new JScrollPane(scheduleArea), BorderLayout.CENTER);
        loadSchedule();
    }

    private void loadSchedule() {
        try (BufferedReader br = new BufferedReader(new FileReader("exam_schedule.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                scheduleArea.append(line + "\n");
            }
        } catch (IOException e) {
            scheduleArea.setText("No schedule available.");
        }
    }
}
