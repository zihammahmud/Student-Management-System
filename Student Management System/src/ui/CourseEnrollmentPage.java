package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CourseEnrollmentPage extends JPanel {
    private DefaultListModel<String> courseListModel;
    private JList<String> courseList;
    private JButton btnEnroll;
    private JTextArea enrolledCoursesArea;
    private java.util.List<String> enrolledCourses;

    public CourseEnrollmentPage() {
        setLayout(new BorderLayout(10,10));

        courseListModel = new DefaultListModel<>();
        // Sample courses - in real app, load from file or database
        courseListModel.addElement("CSE101 - Introduction to Programming");
        courseListModel.addElement("MAT201 - Calculus II");
        courseListModel.addElement("PHY101 - Physics I");
        courseListModel.addElement("ENG101 - English Literature");

        courseList = new JList<>(courseListModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroll = new JScrollPane(courseList);

        btnEnroll = new JButton("Enroll");
        enrolledCoursesArea = new JTextArea(10, 30);
        enrolledCoursesArea.setEditable(false);
        JScrollPane enrolledScroll = new JScrollPane(enrolledCoursesArea);

        enrolledCourses = new ArrayList<>();

        btnEnroll.addActionListener(e -> {
            String selected = courseList.getSelectedValue();
            if (selected != null && !enrolledCourses.contains(selected)) {
                enrolledCourses.add(selected);
                updateEnrolledCourses();
                JOptionPane.showMessageDialog(this, "Enrolled in: " + selected);
            } else {
                JOptionPane.showMessageDialog(this, "Select a course to enroll or already enrolled.");
            }
        });

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Available Courses"), BorderLayout.NORTH);
        leftPanel.add(listScroll, BorderLayout.CENTER);
        leftPanel.add(btnEnroll, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Enrolled Courses"), BorderLayout.NORTH);
        rightPanel.add(enrolledScroll, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private void updateEnrolledCourses() {
        StringBuilder sb = new StringBuilder();
        for (String course : enrolledCourses) {
            sb.append(course).append("\n");
        }
        enrolledCoursesArea.setText(sb.toString());
    }
}
