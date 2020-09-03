package budgetLogic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author steve
 */
public class YearBudget implements Serializable{
   
    private static final long serialVersionUID = 1L;
    private final static Logger LOGGER = Logger.getLogger(YearBudget.class.getName());
    
    private BigDecimal yearlyExpenses = new BigDecimal("0.00");
    private BigDecimal yearlyIncome = new BigDecimal("0.00");
    private BigDecimal totalSpent = new BigDecimal("0.00");
    
    private DefaultMonthBudget defaultMonthBudget = new DefaultMonthBudget();
    private Map<Integer, MonthBudget> monthMap = new HashMap<Integer, MonthBudget>();
    
    public YearBudget(){
        
    }
    
    public void setDefaultMonth(DefaultMonthBudget month){
        this.defaultMonthBudget = month;
    }
    
    public void setYearlyExpenses(BigDecimal yE){
        this.yearlyExpenses = yE;
    }
    
    public void setlYearlyIncome(BigDecimal yI){
        this.yearlyIncome = yI;
    }
    
    /*private void setAllToDefault(){
        for(int curMonth = 1; curMonth < 13; curMonth ++){
            monthMap.put(curMonth, new MonthBudget(curMonth, defaultMonthBudget.getExpenseMap()));
        }
    }*/
}
