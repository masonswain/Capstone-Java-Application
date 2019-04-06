/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.Main;
import static application.Main.xOffset;
import static application.Main.yOffset;
import application.communicate;
import application.user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class LoginController implements Initializable {

    @FXML
    private TextField UserNameTextField;
    @FXML
    private PasswordField PasswordTextField;
    @FXML
    private Label lblAuthentication;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

public void Login(ActionEvent event) throws Exception {
                        
        communicate auth = new communicate();
        
    //////////////////////     Credential Entry Bypass     //////////////////////////    

        //comment this line out to bypass credential entry
        //Main.currentUser = communicate.authenticate(UserNameTextField.getText(),PasswordTextField.getText());
        
        //uncomment this line to bypass credential entry
        Main.currentUser = communicate.authenticate("jknutson","12345");
        
    //////////////////////   End Credential Entry Bypass   ////////////////////////// 
        
        if(Main.currentUser.authenticated) { 
            
            //Update Ticket List
            Main.ticketList = communicate.updateAllActiveUserTickets(Main.currentUser.uName);
            //Load New Scene
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetExpanded.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - (325));
            window.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - (125));
            
    /////////  Allow undecorated window be dragged     ////////////// 
    
            root.setOnMousePressed(new EventHandler<MouseEvent>() {       	
            @Override
            public void handle(MouseEvent event) {
               xOffset = event.getSceneX();
               yOffset = event.getSceneY();
           } 
        });
        
        // User Screen is able to moved by Mouse.
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               window.setX(event.getScreenX() - xOffset);
               window.setY(event.getScreenY() - yOffset);
            }
        });
        
    ////////////////////        END      //////////////////////////// 
            
            window.setScene(scene);
            window.show();
		
            } else {
			
            lblAuthentication.setText("Failed"); // Displays to user that login has failed

            }
        }    
    
}
