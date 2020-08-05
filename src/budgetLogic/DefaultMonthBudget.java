/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetLogic;

import utils.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Initially fills all months with this month
 * Can be changed
 * @author steve
 */
public class DefaultMonthBudget implements Serializable{
    
    private static final long serialVerisonUID = 4L;
    private final static Logger LOGGER = Logger.getLogger(DefaultMonthBudget.class.getName());
    
    private BigDecimal income = new BigDecimal(0);
    private BigDecimal totalExpenses = new BigDecimal(0);
    Map<Integer, Expense> expenseMap = new HashMap<>();
    
    
    public DefaultMonthBudget(){
        
    }
    
    public boolean addExpense(Expense newExpense){
        if(prioritiesConflict(newExpense.getPriority())){
            return false;
        }
        this.expenseMap.put(newExpense.getPriority(), newExpense);
        addTotalExpenses(newExpense.getAmount().toString());
        return true;
    }
    
    public boolean removeExpense(Integer key){
        if(this.expenseMap.containsKey(key)){
            this.expenseMap.remove(key);
            return true;
        }
        return false;
    }
    
    public void setIncome(String value){ // FIX THIS
        this.income = ScrubUserData.parseToBigDecimal(value);
    }
    
    public void addTotalExpenses(String value){
        this.totalExpenses = this.totalExpenses.add(ScrubUserData.parseToBigDecimal(value));
    }
    
    public void switchPriorities(Integer key1, Integer key2){

        Expense tempCat1 = expenseMap.get(key1);
        tempCat1.setPriority(key2);
        Expense tempCat2 = expenseMap.get(key2);
        tempCat2.setPriority(key1);
        
        expenseMap.replace(key1, tempCat1, tempCat2);
        expenseMap.replace(key2, tempCat2, tempCat1);

    }
    
    public Map getCategoryMap(){
        return this.expenseMap;
    }
    
    public BigDecimal getIncome(){
        return this.income;
    }
    
    public BigDecimal getTotalExpenses(){
        return this.totalExpenses;
    }
    
    private boolean prioritiesConflict(Integer priority){
        return this.expenseMap.get(priority) != null; 
    }
    
    private void addExpenseBetween(int key, Expense expense){
        ArrayList<Expense> list = (ArrayList)this.expenseMap.values();
        list.add(key, expense); // add might not be supported or may not reflect
                                // in the original Map
    }
  
    /**
     * If no two categories priority variables conflict than each category is 
     * placed in a new HashMap with its priority variable set as its key. The 
     * new HashMap replaces this.generalCategoryMap, true is returned. If there
     * is a conflict, the process is halted and false is returned.
     * @return 
     */
    //private boolean resetPriorities(){
        
    //}
}
