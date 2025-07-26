package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LibraryBorrowPage extends JPanel {
    JTextField bookField = new JTextField(20);
    JTextField studentField = new JTextField(10);
    JButton borrowBtn = new JButton("Borrow Book");
    JTextArea statusArea = new JTextArea(10, 30);

    public LibraryBorrowPage() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel();
        form.add(new JLabel("Book Title:"));
        form.add(bookField);
        form.add(new JLabel("Student Name:"));
        form.add(studentField);
        form.add(borrowBtn);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(statusArea), BorderLayout.CENTER);

        borrowBtn.addActionListener(e -> {
            String book = bookField.getText();
            String student = studentField.getText();
            if (!book.isEmpty() && !student.isEmpty()) {
                String entry = student + " borrowed \"" + book + "\"\n";
                statusArea.append(entry);
                saveBorrow(entry);
                bookField.setText("");
                studentField.setText("");
            }
        });

        loadHistory();
    }

    private void saveBorrow(String entry) {
        try (FileWriter fw = new FileWriter("library_borrow.txt", true)) {
            fw.write(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader("library_borrow.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                statusArea.append(line + "\n");
            }
        } catch (IOException e) {
            statusArea.setText("No history yet.");
        }
    }
}
