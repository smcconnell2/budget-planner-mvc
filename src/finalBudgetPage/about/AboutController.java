package finalBudgetPage.about;

import enums.TextColor;
import interfaces.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class AboutController extends Application implements Initializable, Controller{
    
    //Extending Application is to allow the use of 
    //getHostServices().showDocument();
    
    @FXML private Hyperlink gitHubLink;
    @FXML private Hyperlink linkedInLink;
    @FXML private Button doneBtn;
    @FXML private Label label;
    
    private final double widthHeight = 60;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //messageToUser("Initialized", TextColor.TEST.getColor());
        setGitHubImage();
        setLinkedInImage();
    }
    
    private void setGitHubImage(){
        ImageView gitHubLogoView = new ImageView();
        gitHubLogoView.setFitHeight(this.widthHeight);
        gitHubLogoView.setFitWidth(this.widthHeight);
        Image gitHubLogo = new Image("/images/github.png");
        gitHubLogoView.setImage(gitHubLogo);
        
        this.gitHubLink.setGraphic(gitHubLogoView);
    }
    
    private void setLinkedInImage(){
        ImageView linkedInLogoView = new ImageView();
        linkedInLogoView.setFitHeight(this.widthHeight);
        linkedInLogoView.setFitWidth(this.widthHeight);
        Image linkedInLogo = new Image("/images/linkedin.png");
        linkedInLogoView.setImage(linkedInLogo);
        
        this.linkedInLink.setGraphic(linkedInLogoView);
    }
    
    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
    @FXML
    public void handleGitHubLinkClick(ActionEvent action){
        messageToUser("Loading GitHub", TextColor.TEST.getColor());
        getHostServices().showDocument("https://github.com/smcconnell2/Portfolio");
    }
    
    @FXML
    public void handlLinkedInLinkClick(ActionEvent action){
        messageToUser("Loading LinkedIn", TextColor.TEST.getColor());
        getHostServices().showDocument("www.linkedin.com/in/steven-mcconnell-8a2506195");
    }  
    
    @FXML
    public void handleDoneBtnClick(ActionEvent action){
        Stage stage = (Stage) this.doneBtn.getScene().getWindow();
        stage.close(); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      
    }
    
     
}
