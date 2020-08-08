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
public class Expense implements Serializable, Comparable<Expense>, Observable{
    
    private static final long serialVerisonUID = 3L;
    private final static Logger LOGGER = Logger.getLogger(Expense.class.getName());
    
    private int priority;
    private String name;
    private BigDecimal amount = new BigDecimal(0);
    private Color color;
    
    
    
    public Expense(int p, String n, BigDecimal a, Color c){
        this.name = n;
        this.priority = p;
        this.amount = a;
        this.color = c;
    }
    
    public Expense(int p, String n){
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
    public int compareTo(Expense m){
        return this.priority - m.getPriority();
    }
    /*
    @Override
    public String toString(){
        return this.name + "\n" + "Priority: " + this.priority + "Amount " + getAmount().toString();
    }*/

    @Override
    public void addListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
