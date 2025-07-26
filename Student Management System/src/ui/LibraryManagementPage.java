package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class LibraryManagementPage extends JPanel {
    private DefaultListModel<String> bookListModel;
    private JList<String> bookList;
    private JButton btnAddBook, btnBorrow, btnReturn, btnSave, btnLoad;
    private java.util.List<String> borrowedBooks;
    private final String bookFile = "data/books.txt";

    public LibraryManagementPage() {
        setLayout(new BorderLayout(10,10));

        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);
        JScrollPane scrollPane = new JScrollPane(bookList);

        borrowedBooks = new ArrayList<>();

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        btnAddBook = new JButton("Add Book");
        btnBorrow = new JButton("Borrow");
        btnReturn = new JButton("Return");
        btnSave = new JButton("Save");
        btnLoad = new JButton("Load");

        buttonPanel.add(btnAddBook);
        buttonPanel.add(btnBorrow);
        buttonPanel.add(btnReturn);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnLoad);

        add(new JLabel("Library Book List"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnAddBook.addActionListener(e -> addBook());
        btnBorrow.addActionListener(e -> borrowBook());
        btnReturn.addActionListener(e -> returnBook());
        btnSave.addActionListener(e -> saveBooks());
        btnLoad.addActionListener(e -> loadBooks());
    }

    private void addBook() {
        String bookName = JOptionPane.showInputDialog(this, "Enter book name:");
        if (bookName != null && !bookName.trim().isEmpty()) {
            bookListModel.addElement(bookName.trim());
        }
    }

    private void borrowBook() {
        String selected = bookList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Select a book to borrow.");
            return;
        }
        if (borrowedBooks.contains(selected)) {
            JOptionPane.showMessageDialog(this, "Book already borrowed.");
            return;
        }
        borrowedBooks.add(selected);
        JOptionPane.showMessageDialog(this, "You borrowed: " + selected);
    }

    private void returnBook() {
        String selected = bookList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Select a book to return.");
            return;
        }
        if (!borrowedBooks.contains(selected)) {
            JOptionPane.showMessageDialog(this, "Book is not borrowed.");
            return;
        }
        borrowedBooks.remove(selected);
        JOptionPane.showMessageDialog(this, "You returned: " + selected);
    }

    private void saveBooks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(bookFile))) {
            for (int i = 0; i < bookListModel.size(); i++) {
                String book = bookListModel.getElementAt(i);
                bw.write(book);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Books saved!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving books: " + e.getMessage());
        }
    }

    private void loadBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader(bookFile))) {
            bookListModel.clear();
            String line;
            while ((line = br.readLine()) != null) {
                bookListModel.addElement(line);
            }
            JOptionPane.showMessageDialog(this, "Books loaded!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + e.getMessage());
        }
    }
}
