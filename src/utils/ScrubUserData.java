/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author steve
 */
public class ScrubUserData {
    
    public static Scanner fin;
    private final static Logger LOGGER = Logger.getLogger(ScrubUserData.class.getName());
    private static Level lvl;
    
    private static void initScanner(){
        if(fin == null){
            fin = new Scanner(System.in);
        }
    }
    
    public static BigDecimal parseToBigDecimal(String value){
        try{
            
        }catch(NumberFormatException e){
            LOGGER.log(Level.SEVERE, "Error when parsing String to BigDecimal. " + value + " is incompatible.", e);
        }
        finally{
            return new BigDecimal(0.00);
        }
    }
    
    public static int parseInt(String value){
            int newValue = 0;
        try{
            newValue = Integer.parseInt(value);
        }catch(NumberFormatException e){
            newValue = -1;
        }
        return newValue;
    }
    
}
