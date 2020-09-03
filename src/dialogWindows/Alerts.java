package dialogWindows;

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
    
    public void warning(String title, String header){
        this.alert = new Alert(AlertType.ERROR);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        //Optional<ButtonType> result = this.alert.showAndWait(); 
        this.alert.showAndWait();
    }
    
    public boolean warning2Choice(String title, String header, String message){      
        this.alert = new Alert(AlertType.CONFIRMATION);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        this.alert.contentTextProperty().set(message);
        Optional<ButtonType> result = this.alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }   
    
    public void information(String title, String header){
        this.alert = new Alert(AlertType.INFORMATION);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        this.alert.showAndWait();
    }

}
