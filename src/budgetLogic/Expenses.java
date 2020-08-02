/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetLogic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;

/**
 *
 * @author steve
 */
public class Expenses implements Serializable, Comparable<Expenses>, Observable{
    
    private static final long serialVerisonUID = 3L;
    private final static Logger LOGGER = Logger.getLogger(Expenses.class.getName());
    
    private int priority;
    private String name;
    private Color color;
    private BigDecimal amount = new BigDecimal(0);
    
    
    public Expenses(int p, String n, BigDecimal a){
        this.name = n;
        this.priority = p;
        this.amount = a;
    }
    
    public Expenses(int p, String n){
        this.name = n;
        this.priority = p;
    }
        
    public int getPriority(){
        return this.priority;
    }
    
    public String getName(){
        return this.name;
    }
    
    public BigDecimal getAmount(){
        return this.amount;
    }
    
    public void setPriority(int newP){
        this.priority = newP;
    }
    
    @Override
    public int compareTo(Expenses m){
        return this.priority - m.getPriority();
    }
    
    @Override
    public String toString(){
        return this.name + "\n" + "Priority: " + this.priority;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
