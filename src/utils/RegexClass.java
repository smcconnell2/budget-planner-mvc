package utils;

import java.util.regex.Pattern;

/**
 *
 * @author steve
 */
public class RegexClass {
    
    private String validNumber = "[0-9]+";
    private String validName = "[A-Z]+";
    private String password = "";
    
    private String tenCharLimit = "";
    private String twentyCharLimit = "";
    private String thirtyCharLimit = "";
    private String fortyCharLimit = "";
    
    private Pattern pattern;
    
    public static boolean charLimit(int amount){
        
        return false;
    }
    
    public static boolean onlyNumericalValues(){
        
        
        return false;
    } 
    
    public static boolean onlyAlphabeticalChars(){
        
        return false;
    }
    
    public static boolean password(){
        return false;
    }
    
    public static boolean userName(){
        return false;
    }
    
   
    
    
}
