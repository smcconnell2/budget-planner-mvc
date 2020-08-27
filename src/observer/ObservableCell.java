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
public interface ObservableCell {
    public abstract void notifyObserver(List list);
}
