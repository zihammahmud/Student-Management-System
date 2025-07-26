package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class ResultHistoryPage extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton btnAdd, btnSave, btnLoad;
    private final String resultFile = "data/results.txt";

    public ResultHistoryPage() {
        setLayout(new BorderLayout(10, 10));

        // Columns: Subject, Grade, GPA
        tableModel = new DefaultTableModel(new Object[]{"Subject", "Grade", "GPA"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel btnPanel = new JPanel();
        btnAdd = new JButton("Add Result");
        btnSave = new JButton("Save Results");
        btnLoad = new JButton("Load Results");

        btnPanel.add(btnAdd);
        btnPanel.add(btnSave);
        btnPanel.add(btnLoad);

        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addResult());
        btnSave.addActionListener(e -> saveResults());
        btnLoad.addActionListener(e -> loadResults());
    }

    private void addResult() {
        JTextField subjectField = new JTextField();
        JTextField gradeField = new JTextField();
        JTextField gpaField = new JTextField();

        Object[] inputs = {
                "Subject:", subjectField,
                "Grade:", gradeField,
                "GPA:", gpaField
        };

        int result = JOptionPane.showConfirmDialog(this, inputs, "Add New Result", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String subject = subjectField.getText().trim();
                String grade = gradeField.getText().trim();
                double gpa = Double.parseDouble(gpaField.getText().trim());

                if (!subject.isEmpty() && !grade.isEmpty()) {
                    tableModel.addRow(new Object[]{subject, grade, gpa});
                } else {
                    JOptionPane.showMessageDialog(this, "Subject and Grade cannot be empty.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid GPA value.");
            }
        }
    }

    private void saveResults() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultFile))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String subject = tableModel.getValueAt(i, 0).toString();
                String grade = tableModel.getValueAt(i, 1).toString();
                String gpa = tableModel.getValueAt(i, 2).toString();
                bw.write(subject + "," + grade + "," + gpa);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Results saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving results: " + e.getMessage());
        }
    }

    private void loadResults() {
        try (BufferedReader br = new BufferedReader(new FileReader(resultFile))) {
            tableModel.setRowCount(0);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    tableModel.addRow(parts);
                }
            }
            JOptionPane.showMessageDialog(this, "Results loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading results: " + e.getMessage());
        }
    }
}
