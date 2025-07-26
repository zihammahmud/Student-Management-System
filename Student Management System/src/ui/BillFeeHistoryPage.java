package ui;

import javax.swing.*;
import java.awt.*;

public class BillFeeHistoryPage extends JPanel {
    private DefaultListModel<String> billListModel;
    private JList<String> billList;
    private JButton btnAddBill;

    public BillFeeHistoryPage() {
        setLayout(new BorderLayout(10, 10));

        billListModel = new DefaultListModel<>();
        billList = new JList<>(billListModel);
        JScrollPane scrollPane = new JScrollPane(billList);

        btnAddBill = new JButton("Add Bill/Fee");

        add(new JLabel("Bill & Fee History"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnAddBill, BorderLayout.SOUTH);

        btnAddBill.addActionListener(e -> addBill());
    }

    private void addBill() {
        JTextField typeField = new JTextField();
        JTextField amountField = new JTextField();

        Object[] inputs = {
                "Bill Type (Tuition, Library, etc.):", typeField,
                "Amount:", amountField
        };

        int result = JOptionPane.showConfirmDialog(this, inputs, "Add New Bill/Fee", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String type = typeField.getText().trim();
                double amount = Double.parseDouble(amountField.getText().trim());
                if (!type.isEmpty() && amount > 0) {
                    billListModel.addElement(type + " - $" + amount);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter valid type and amount.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount entered.");
            }
        }
    }
}
