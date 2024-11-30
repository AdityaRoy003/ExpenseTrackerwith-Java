// ExpenseManager.java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private final List<Expense> expenses;
    private final String filePath = "expenses.dat";

    public ExpenseManager() {
        expenses = loadExpenses();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveExpenses();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    private void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Expense> loadExpenses() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}