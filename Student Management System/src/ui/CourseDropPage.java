package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CourseDropPage extends JPanel {
    private DefaultListModel<String> enrolledCoursesModel;
    private JList<String> enrolledCoursesList;
    private JButton btnDrop;

    public CourseDropPage() {
        setLayout(new BorderLayout(10, 10));

        enrolledCoursesModel = new DefaultListModel<>();
        // Sample enrolled courses; in real app, load dynamically
        enrolledCoursesModel.addElement("CSE101 - Introduction to Programming");
        enrolledCoursesModel.addElement("MAT201 - Calculus II");

        enrolledCoursesList = new JList<>(enrolledCoursesModel);
        JScrollPane scrollPane = new JScrollPane(enrolledCoursesList);

        btnDrop = new JButton("Drop Selected Course");

        add(new JLabel("Enrolled Courses"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnDrop, BorderLayout.SOUTH);

        btnDrop.addActionListener(e -> dropCourse());
    }

    private void dropCourse() {
        int selectedIndex = enrolledCoursesList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Select a course to drop.");
            return;
        }
        String course = enrolledCoursesModel.getElementAt(selectedIndex);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to drop:\n" + course,
                "Confirm Drop", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            enrolledCoursesModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, course + " dropped.");
        }
    }
}
