/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.util.List;

/**
 *
 * @author steve
 */
public abstract class CellObserver {
    public Object subject;
    public abstract void update(List list);
}
//List<Expense> itemsCopy = new ArrayList<>();