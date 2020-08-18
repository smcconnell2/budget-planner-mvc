package about;

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
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import static javafx.scene.input.Clipboard.getSystemClipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import utils.GlobalButtonInfo;

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
    @FXML private Button copyBtn;
    @FXML private Button doneBtn;
    @FXML private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //messageToUser("Initialized", TextColor.TEST.getColor());
        initGitHubLink();
        initLinkedInLink();
        initCopyClipBoardBtn();
        
    }
    
    private void initCopyClipBoardBtn(){
        this.copyBtn.setTooltip(new Tooltip("Copy to Clipboard"));
        ImageView copyBtnView = new ImageView("/images/clipboard.png");
        
        copyBtnView.setFitWidth(GlobalButtonInfo.standardBtnHeight);
        copyBtnView.setPreserveRatio(true);
        copyBtnView.setSmooth(true);
        copyBtnView.setCache(true);
        
        this.copyBtn.setGraphic(copyBtnView); 
    }
    private void initGitHubLink(){
        ImageView gitHubLogoView = new ImageView("/images/github2.png");
                
        gitHubLogoView.setFitHeight(GlobalButtonInfo.standardMidIconHeight);
        gitHubLogoView.setPreserveRatio(true);
        gitHubLogoView.setSmooth(true);
        gitHubLogoView.setCache(true);
        
        this.gitHubLink.setGraphic(gitHubLogoView);
    }
    
    private void initLinkedInLink(){
        ImageView linkedInLogoView = new ImageView("/images/linkedin.png");
        
        linkedInLogoView.setFitHeight(GlobalButtonInfo.standardMidIconHeight);
        linkedInLogoView.setPreserveRatio(true);
        linkedInLogoView.setSmooth(true);
        linkedInLogoView.setCache(true);
        
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
    
    @FXML
    public void handleCopyClipBoard(ActionEvent action){
        ClipboardContent content = new ClipboardContent();
        content.putString("stevendanielmcconnell@gmail.com");
        getSystemClipboard().setContent(content);        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      
    }
    
     
}
