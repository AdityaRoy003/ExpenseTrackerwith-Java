// Expense.java
import java.io.Serializable;
import java.util.Date;

public record Expense(double amount, String category, Date date, String notes) implements Serializable {
}