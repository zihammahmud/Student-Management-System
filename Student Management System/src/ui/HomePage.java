package ui;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    private final String[] features = {
            "Home",
            "Profile",
            "CourseEnrollment",
            "CourseRegistration",
            "ClassRoutine",
            "CourseDrop",
            "SemesterDrop",
            "ProgramChange",
            "StudentID",
            "ResultHistory",
            "BillFeeHistory",
            "PasswordChange",
            "AdmitCard",
            "SpecialExamApplication",
            "ExamRoutine",
            "CourseEvaluation",
            "FeedbackSystem",
            "CommunicationSystem",
            "HealthRecords",
            "Scholarship",
            "InternshipTracking",
            "StudentForum",
            "AcademicCalendar",
            "TeamsUser",
            "AdmissionStatus",
            "Notifications",
            "LibraryManagement",
            "EventManagement",
            "AttendanceTracking",
            "BehavioralRecords"
    };

    public HomePage() {
        setTitle("Student Management System - Dashboard");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        JScrollPane scrollPane = new JScrollPane(panel);

        for (String feature : features) {
            JButton btn = new JButton(feature);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> openFeatureWindow(feature));
            panel.add(btn);
        }

        add(scrollPane);
        setVisible(true);
    }

    private void openFeatureWindow(String featureName) {
        JFrame frame = new JFrame(featureName);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel featurePanel = getFeaturePanel(featureName);

        frame.add(featurePanel);
        frame.setVisible(true);
    }

    private JPanel getFeaturePanel(String featureName) {
        try {
            String className = "ui." + featureName + "Page";
            Class<?> clazz = Class.forName(className);
            Object obj = clazz.getDeclaredConstructor().newInstance();
            if (obj instanceof JPanel) {
                return (JPanel) obj;
            } else {
                return createPlaceholderPanel(featureName + " (Invalid JPanel)");
            }
        } catch (Exception e) {
            return createPlaceholderPanel(featureName + " - Under Construction");
        }
    }

    private JPanel createPlaceholderPanel(String message) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
}
