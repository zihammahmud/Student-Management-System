package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AttendanceTrackingPage extends JPanel {
    private DefaultListModel<String> courseListModel;
    private JList<String> courseList;
    private JButton btnMarkPresent, btnMarkAbsent;
    private JLabel lblAttendance;
    private HashMap<String, Integer> totalClasses;
    private HashMap<String, Integer> attendedClasses;

    public AttendanceTrackingPage() {
        setLayout(new BorderLayout(10, 10));

        courseListModel = new DefaultListModel<>();
        // Example courses
        courseListModel.addElement("CSE101");
        courseListModel.addElement("MAT201");
        courseListModel.addElement("PHY101");

        courseList = new JList<>(courseListModel);
        JScrollPane scrollPane = new JScrollPane(courseList);

        JPanel buttonPanel = new JPanel();
        btnMarkPresent = new JButton("Mark Present");
        btnMarkAbsent = new JButton("Mark Absent");

        buttonPanel.add(btnMarkPresent);
        buttonPanel.add(btnMarkAbsent);

        lblAttendance = new JLabel("Select a course and mark attendance", SwingConstants.CENTER);

        add(new JLabel("Courses"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.CENTER);
        add(lblAttendance, BorderLayout.SOUTH);

        totalClasses = new HashMap<>();
        attendedClasses = new HashMap<>();

        btnMarkPresent.addActionListener(e -> markAttendance(true));
        btnMarkAbsent.addActionListener(e -> markAttendance(false));
        courseList.addListSelectionListener(e -> updateAttendanceLabel());
    }

    private void markAttendance(boolean present) {
        String course = courseList.getSelectedValue();
        if (course == null) {
            JOptionPane.showMessageDialog(this, "Select a course first.");
            return;
        }
        totalClasses.put(course, totalClasses.getOrDefault(course, 0) + 1);
        if (present) {
            attendedClasses.put(course, attendedClasses.getOrDefault(course, 0) + 1);
        }
        updateAttendanceLabel();
    }

    private void updateAttendanceLabel() {
        String course = courseList.getSelectedValue();
        if (course == null) {
            lblAttendance.setText("Select a course and mark attendance");
            return;
        }
        int total = totalClasses.getOrDefault(course, 0);
        int attended = attendedClasses.getOrDefault(course, 0);
        if (total == 0) {
            lblAttendance.setText(course + ": No attendance records");
        } else {
            double percent = (attended * 100.0) / total;
            lblAttendance.setText(String.format("%s: Attendance %.2f%% (%d/%d classes)", course, percent, attended, total));
        }
    }
}
