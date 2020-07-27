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

/**
 *
 * @author steve
 */
public class MonthBudget implements Serializable {
    
    private static final long serialVerisonUID = 2L;
    private MonthName name;
    private Map<Integer, Category> categoryMap = new HashMap<Integer, Category>(); // integer will be priority number
    
    /**
     * This may or may not be needed when assigning each month while using the
     * default month budget
     */
    public MonthBudget(){
        
    }
    
    public MonthBudget(int month){
        this.name = AssignMonthEnum.getMonthName(month);
    }
    
        public boolean addCategory(Category newCat){
        if(prioritiesConflict(newCat.getPriority())){
            return false;
        }
        this.categoryMap.put(newCat.getPriority(), newCat);
        return true;
    }
    
            public void remove(int key){
        this.categoryMap.remove(key);
    }
    
    public void switchPriorities(int key1, int key2){

        Category tempCat1 = categoryMap.get(key1);
        tempCat1.setPriority(key2);
        Category tempCat2 = categoryMap.get(key2);
        tempCat2.setPriority(key1);
        
        categoryMap.replace(key1, tempCat1, tempCat2);
        categoryMap.replace(key2, tempCat2, tempCat1);

    }
    
    private boolean prioritiesConflict(int priority){
        return this.categoryMap.get(priority) != null; 
    }
    
    /**
     * This functionality has been moved to the utils folder. Remove from here
     * or utils folder on next refactor.
     * @param val 
     */
    public void getMonthName(int val){
        switch(val){
            case 1:
                this.name = MonthName.JANUARY;
            case 2:
                this.name = MonthName.FEBUARY;
            case 3:
                this.name = MonthName.MARCH;
            case 4:
                this.name = MonthName.APRIL;
            case 5:
                this.name = MonthName.MAY;
            case 6:
                this.name = MonthName.JUNE;
            case 7:
                this.name = MonthName.JULY;
            case 8:
                this.name = MonthName.AUGUST;
            case 9:
                this.name = MonthName.SEPTEMBER;
            case 10:
                this.name = MonthName.OCTOBER;
            case 11:
                this.name = MonthName.NOVEMBER;
            case 12:
                this.name = MonthName.DECEMBER;
            default:
                // do some error checking with Logger
        }
    }
}
