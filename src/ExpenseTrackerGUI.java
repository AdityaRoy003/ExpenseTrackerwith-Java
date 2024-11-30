// ExpenseTrackerGUI.java

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseTrackerGUI {
    private final ExpenseManager expenseManager;
    private DefaultTableModel tableModel;

    public ExpenseTrackerGUI() {
        expenseManager = new ExpenseManager();
        initialize();
    }

    private void initialize() {
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Amount", "Category", "Date", "Notes"}, 0);
        JTable table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel();
        JTextField amountField = new JTextField(10);
        JTextField categoryField = new JTextField(10);
        JTextField notesField = new JTextField(10);
        JButton addButton = new JButton("Add Expense");

        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Notes:"));
        inputPanel.add(notesField);
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // Add button action
        addButton.addActionListener(_ -> {
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            String notes = notesField.getText();
            Date date = new Date();
            Expense expense = new Expense(amount, category, date, notes);
            expenseManager.addExpense(expense);
            updateTable();
            amountField.setText("");
            categoryField.setText("");
            notesField.setText("");
        });

        frame.setVisible(true);
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Expense expense : expenseManager.getExpenses()) {
            tableModel.addRow(new Object[]{
                    expense.amount(),
                    expense.category(),
                    new SimpleDateFormat("yyyy-MM-dd").format(expense.date()),
                    expense.notes()
            });
        }
    }
}
