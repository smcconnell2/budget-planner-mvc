package mainPage;

import enums.TextColor;
import interfaces.Controller;
import utils.GlobalButtonInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author steve
 */
public class MainPageController implements Initializable, Controller {
    
    @FXML private Label label;
    @FXML private ImageView mainImage;
    @FXML private Button testBtn;
    @FXML private Button aboutBtn;
    @FXML private Button quitBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTestButton();
        initMainImage();
        initAboutButton();
    }  
    
    private void initTestButton(){
        this.testBtn.setTooltip(new Tooltip("Test: Skip to Final Budget"));
        ImageView view = new ImageView("/images/test.png");
        
        view.setFitHeight(GlobalButtonInfo.standardLogoHeight);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        
        this.testBtn.setGraphic(view); 
    }
    
    private void initAboutButton(){
        this.aboutBtn.setTooltip(new Tooltip("About Creator"));
        ImageView view = new ImageView("/images/about.png");
        
        view.setFitHeight(GlobalButtonInfo.standardMidIconHeight);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        
        this.aboutBtn.setGraphic(view);
    }
    
    private void initMainImage(){
        Image image = new Image("/images/main.png");
        this.mainImage.setImage(image);
    }
    
    private void loadWindow(String filePath) throws IOException{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
        }catch(IOException e){
            messageToUser("Error Loading About Page", TextColor.ERROR.getColor());
        }
        catch(Exception e){
            messageToUser(e.getMessage(), TextColor.ERROR.getColor());
        }
        
    }
    
    @FXML
    private void handleQuitButton(ActionEvent event) throws IOException{
        messageToUser("Quit clicked", TextColor.TEST.getColor());
    }
        
    @FXML
    private void handleOnAbout(ActionEvent event) throws IOException{
        messageToUser("About clicked", TextColor.TEST.getColor());
        loadWindow("/about/about.fxml"); 
    }
    
    @FXML
    private void handleNewButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/newBudgetPage/newBudgetPage.fxml"));          
            Scene scene= new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("New Budget");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            messageToUser("Error on new button", TextColor.ERROR.getColor()); // Temp error checking
        }
    }
    
    /**
     * THIS IS FOR TESTING PURPOSES ONLY
     * @param event 
     */
    @FXML
    public void handleTestButton(ActionEvent event){
        
         try{
            Parent root = FXMLLoader.load(getClass().getResource("/finalBudgetPage/finalBudgetPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("MY Budget");
            stage.setScene(scene);
            stage.show();
        } catch(IOException ex){
            messageToUser("Error in test button.", TextColor.ERROR.getColor());
        }
        
    }
    
    @FXML
    private void handleLoadButton(ActionEvent event) {
        messageToUser("Load Clicked", TextColor.TEST.getColor());
    }  

    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
}
