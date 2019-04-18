/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.Internal;
import application.Main;
import static application.Main.xOffset;
import static application.Main.yOffset;
import application.communicate;
import application.setWidgetPosition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterMinimizedController implements Initializable {

    @FXML
    private Circle statusLight;
    
    boolean timerStop=false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       //Update all dynamic elements in scene
       updateStatus();
       
       //Create a runnable that is capable of updating JavaFX elements
        Runnable updater = new Runnable(){
            @Override
            public void run() {
                updateStatus();
            }
        
        };
    
        //Create a thread to loop until the timerStop is set to true
        new Thread(()->{
        int i=0;
                while(!timerStop){
                try{
                    Thread.sleep(Main.refreshTimer);
                    
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                i++;
                System.out.println("Updating Status...");
                Platform.runLater(updater);
                }
        }).start();
    
    } 
    
    @FXML
    private void gotoTechCommandCenter(MouseEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenter.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            //Set screen in middle of primary screen
            window.setX((primaryScreenBounds.getWidth()/2) - 500);
            window.setY((primaryScreenBounds.getHeight()/2) - 300);
            
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
            timerStop=true;
            window.setScene(scene);
            window.show();
    }
    
    private void updateStatus(){
    
         //Update Ticket Lists
        if(Main.currentUser!=null) {
            try {
                Main.ticketList = communicate.updateAllActiveTechTickets(Main.currentUser.uName);
                communicate.updateMainUnassignedTicketsList();
                communicate.updateUnreadMessages(Main.currentUser.getuName());
            } catch (IOException ex) {
                Logger.getLogger(TechCommandCenterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Update status light
        statusLight.setFill(Internal.updateStatusLight());
        
    }
    
}
