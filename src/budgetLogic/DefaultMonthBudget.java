/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetLogic;

import utils.*;

import java.io.Serializable;
import java.math.BigDecimal;
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
    Map<Integer, Expenses> generalCategoryMap = new HashMap<Integer, Expenses>();
    
    
    public DefaultMonthBudget(){
        
    }
    
    public boolean addCategory(Expenses newCat){
        if(prioritiesConflict(newCat.getPriority())){
            return false;
        }
        this.generalCategoryMap.put(newCat.getPriority(), newCat);
        return true;
    }
    
    public void remove(int key){
        this.generalCategoryMap.remove(key);
    }
    
    public void setIncome(String value){
        this.income = ScrubUserData.parseToBigDecimal(value);
    }
    
    public void addTotalExpenses(String value){
        this.totalExpenses = this.totalExpenses.add(ScrubUserData.parseToBigDecimal(value));
    }
    
    public void switchPriorities(int key1, int key2){

        Expenses tempCat1 = generalCategoryMap.get(key1);
        tempCat1.setPriority(key2);
        Expenses tempCat2 = generalCategoryMap.get(key2);
        tempCat2.setPriority(key1);
        
        generalCategoryMap.replace(key1, tempCat1, tempCat2);
        generalCategoryMap.replace(key2, tempCat2, tempCat1);

    }
    
    public Map getCategoryMap(){
        return this.generalCategoryMap;
    }
    
    private boolean prioritiesConflict(int priority){
        return this.generalCategoryMap.get(priority) != null; 
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
