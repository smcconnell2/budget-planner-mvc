package budgetLogic;

import enums.MonthName;
import utils.AssignMonthEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 *
 * @author steve
 */
public class MonthBudget implements Serializable {
    
    private static final long serialVerisonUID = 2L;
    private final static Logger LOGGER = Logger.getLogger(MonthBudget.class.getName());
    
    private MonthName name;  
    private BigDecimal monthlyExpenses = new BigDecimal("0.00");
    private BigDecimal monthlyIncome = new BigDecimal("0.00");
    
    private Map<Integer, Expense> expensesMap = new HashMap<Integer, Expense>(); 
    
    /**
     * This may or may not be needed when assigning each month while using the
     * default month budget
     */
    public MonthBudget(int month){
        this.name = AssignMonthEnum.getMonthName(month);
    }
    
    public MonthBudget(int month, Map expMap, BigDecimal income){
        this.name = AssignMonthEnum.getMonthName(month);
        this.monthlyIncome = income;
        this.expensesMap = expMap;
    }
    
        public boolean addCategory(Expense newCat){
        if(prioritiesConflict(newCat.getPriority())){
            return false;
        }
        this.expensesMap.put(newCat.getPriority(), newCat);
        return true;
    }
    
            public void remove(int key){
        this.expensesMap.remove(key);
    }
            
    public String getName(){
        return this.name.getReadable();
    }
    
    public int getMonthValue(){
        return this.name.getValue();
    }
    
    public BigDecimal getMonthlyIncome(){
        return this.monthlyIncome;
    }
    
    public BigDecimal getMonthlyExpenses(){
        updateMonthlyExpenses();
        return this.monthlyExpenses;
    }
    
    public void setMonthIncome(BigDecimal value){
        this.monthlyIncome = value;
    }
    
    private void updateMonthlyExpenses(){
        BigDecimal tempBD = new BigDecimal("0.00");

        for(Map.Entry<Integer, Expense> pair: this.expensesMap.entrySet()){
            tempBD = tempBD.add(pair.getValue().getAmount());
        }
        this.monthlyExpenses = tempBD;
        
    }
    
    public void switchPriorities(int key1, int key2){

        Expense tempCat1 = expensesMap.get(key1);
        tempCat1.setPriority(key2);
        Expense tempCat2 = expensesMap.get(key2);
        tempCat2.setPriority(key1);
        
        expensesMap.replace(key1, tempCat1, tempCat2);
        expensesMap.replace(key2, tempCat2, tempCat1);

    }
    
    private boolean prioritiesConflict(int priority){
        return this.expensesMap.get(priority) != null; 
    }
    
    @Override
    public String toString(){
        return this.name.getReadable();
    }
}
