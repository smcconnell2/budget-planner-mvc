
package enums;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GRAY;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.GREEN;
import javafx.scene.paint.Paint;

public enum TextColor {
    ERROR(RED),
    DISABLED(GRAY),
    STANDARD(BLACK),
    TEST(GREEN);
    
    private final Paint color;
    
    TextColor(Paint c){
        this.color = c;
    }
    
    public Paint getColor(){
        return this.color;
    }
    
}
