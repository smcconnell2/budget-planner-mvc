package Structs;

import budgetLogic.Expense;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author steve
 */
public class MonthStruct {
    
    public static BigDecimal income = new BigDecimal(0);
    public static BigDecimal totalExpenses = new BigDecimal(0);
    public static Map<Integer, Expense> expenseMap = new HashMap<>();
}
