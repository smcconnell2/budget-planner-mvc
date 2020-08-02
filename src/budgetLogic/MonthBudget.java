/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetLogic;

import enums.MonthName;
import utils.AssignMonthEnum;

import java.io.Serializable;
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
    
    private long monthlyExpenses; // look into which type to use for money
    private long monthlyIncome;
    
    private Map<Integer, Expenses> expensesMap = new HashMap<Integer, Expenses>(); // integer will be priority number
    
    /**
     * This may or may not be needed when assigning each month while using the
     * default month budget
     */
    public MonthBudget(int month){
        this.name = AssignMonthEnum.getMonthName(month);
    }
    
    public MonthBudget(int month, Map catMap){
        this.name = AssignMonthEnum.getMonthName(month);
        this.expensesMap = catMap;
    }
    
        public boolean addCategory(Expenses newCat){
        if(prioritiesConflict(newCat.getPriority())){
            return false;
        }
        this.expensesMap.put(newCat.getPriority(), newCat);
        return true;
    }
    
            public void remove(int key){
        this.expensesMap.remove(key);
    }
    
    public void switchPriorities(int key1, int key2){

        Expenses tempCat1 = expensesMap.get(key1);
        tempCat1.setPriority(key2);
        Expenses tempCat2 = expensesMap.get(key2);
        tempCat2.setPriority(key1);
        
        expensesMap.replace(key1, tempCat1, tempCat2);
        expensesMap.replace(key2, tempCat2, tempCat1);

    }
    
    private boolean prioritiesConflict(int priority){
        return this.expensesMap.get(priority) != null; 
    }
}
