/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.widget;

import application.Internal;
import application.Main;
import application.setWidgetPosition;
import static application.Main.xOffset;
import static application.Main.yOffset;
import application.communicate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class WidgetCollapsedController implements Initializable {

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
    private void gotoWidgetExpanded(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/WidgetExpanded.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            double nextStageHeight = 100.0;
            window.setY(setWidgetPosition.setWidgetY(window.getY(), primaryScreenBounds, nextStageHeight));
                        
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
        Internal.intializeWidgetStatus();
        //Update status light
        statusLight.setFill(Main.statusLightColor);
    }
    
}
