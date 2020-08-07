package Structs;

import java.math.BigDecimal;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.WHITE;

/**
 *
 * @author steve
 */
public class ExpenseStruct {
    public static int priority = 0;
    public static String name = "";
    public static BigDecimal amount = new BigDecimal("0");
    public static Color color = WHITE;
    public static boolean submitClicked = false;
}
