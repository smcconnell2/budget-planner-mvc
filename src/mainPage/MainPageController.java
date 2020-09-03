package mainPage;

import dialogWindows.Alerts;
import enums.TextColor;
import interfaces.Controller;
import utils.GlobalButtonInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

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
import javafx.stage.FileChooser;
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
        boolean quit = new Alerts().warning2Choice(
            "WARNING", 
            "All unsaved data will be lost.",
            "Are you sure you want to quit?"
        );
        if(quit){
            Platform.exit();
        }  
    }
        
    @FXML
    private void handleOnAbout(ActionEvent event) throws IOException{
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
        boolean runShortcut = new Alerts().warning2Choice(
                "WARNING", 
                "Test: ShortCut to My Budget Page", 
                "Proceed?"
        );
        
        if(runShortcut){
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
    }
    
    @FXML
    private void handleLoadButton(ActionEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load Budget");
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
        chooser.showOpenDialog(stage);
    }  

    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
    public void clearMessageToUser(){
        this.label.setText("");
    }
    
}
