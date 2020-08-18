/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalBudgetPage;

import interfaces.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.GlobalButtonInfo;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class FinalBudgetPageController extends Application implements Initializable, Controller {
    
    @FXML private Label budgetTabLabel;
    @FXML private Button aboutBtn;
    @FXML private Button menuBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initAboutBtn();
        initMenuBtn();
    }  
    
    private void initAboutBtn(){
        this.aboutBtn.setTooltip(new Tooltip("About Creator"));
        ImageView view = new ImageView("/images/about.png");
        
        view.setFitHeight(GlobalButtonInfo.standardMidIconHeight);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        
        this.aboutBtn.setGraphic(view);
    }
    
    private void initMenuBtn(){
        this.menuBtn.setTooltip(new Tooltip("Menu"));
        ImageView view = new ImageView("/images/menu2.png");
        
        view.setFitHeight(GlobalButtonInfo.standardMidIconHeight);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        
        this.menuBtn.setGraphic(view);
    }

    @FXML
    public void handleAboutBtnClick() throws IOException{
        loadWindow("/about/about.fxml");  
    }
    
    @FXML
    public void handleMenuBtnClick(ActionEvent e) throws IOException{
        loadWindow("/finalBudgetPage/menu/menu.fxml");
    }
    
    private void loadWindow(String filePath) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @Override
    public void messageToUser(String message, Paint color) {
        this.budgetTabLabel.setTextFill(color);
        this.budgetTabLabel.setText(message);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
