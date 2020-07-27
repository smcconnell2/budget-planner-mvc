/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import enums.MonthName;

/**
 *
 * @author steve
 */
public class AssignMonthEnum {
    
    public static MonthName getMonthName(int val){
        switch(val){
            case 1:
                return MonthName.JANUARY;
            case 2:
                return MonthName.FEBUARY;
            case 3:
                return MonthName.MARCH;
            case 4:
                return MonthName.APRIL;
            case 5:
                return MonthName.MAY;
            case 6:
                return MonthName.JUNE;
            case 7:
                return MonthName.JULY;
            case 8:
                return MonthName.AUGUST;
            case 9:
                return MonthName.SEPTEMBER;
            case 10:
                return MonthName.OCTOBER;
            case 11:
                return MonthName.NOVEMBER;
            case 12:
                return MonthName.DECEMBER;
            default:
                // do some error checking with Logger
                return null;
        }
    }
}
