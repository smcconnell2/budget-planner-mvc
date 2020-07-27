/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetLogic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author steve
 */
public class YearBudget implements Serializable{
   
    private static final long serialVersionUID = 1L;
    private DefaultMonthBudget defaultMonthBudget = new DefaultMonthBudget();
    private Map<Integer, MonthBudget> monthMap = new HashMap<Integer, MonthBudget>();
    
    public YearBudget(){
        
    }
    
    public void setDefaultMonth(DefaultMonthBudget month){
        this.defaultMonthBudget = month;
    }
}
