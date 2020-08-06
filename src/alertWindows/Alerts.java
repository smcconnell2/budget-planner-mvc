package alertWindows;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author steve
 */
public class Alerts {
    
    private Alert alert;
    
    public boolean warning2Choice(String title, String header, String message){
        
        this.alert = new Alert(AlertType.WARNING);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        this.alert.contentTextProperty().set(message);
        Optional<ButtonType> result = this.alert.showAndWait();
        
        return false;
    }
}
