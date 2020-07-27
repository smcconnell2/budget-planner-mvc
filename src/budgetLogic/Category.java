/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetLogic;

import java.io.Serializable;

/**
 *
 * @author steve
 */
public class Category implements Serializable, Comparable<Category>{
    
    private static final long serialVerisonUID = 3L;
    private int priority;
    private String name;
    private 
    
    public Category(int p){
        this.priority = p;
    }
    
    public int getPriority(){
        return this.priority;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setPriority(int newP){
        this.priority = newP;
    }
    
    @Override
    public int compareTo(Category m){
        return this.priority - m.getPriority();
    }
    
    @Override
    public String toString(){
        return "Category: " + this.name + "\n" + "Priority: " + this.priority;
    }
}
