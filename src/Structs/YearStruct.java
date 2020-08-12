package Structs;

import budgetLogic.MonthBudget;
import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author steve
 */
public class YearStruct {
    public static Map<Integer, MonthBudget> monthMap = new HashMap<>();
    public static BigDecimal yearIncome = new BigDecimal("0.00");
    public static BigDecimal yearTotalExpenses = new BigDecimal("0.00");
}
