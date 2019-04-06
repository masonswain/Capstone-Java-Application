/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.Main;
import static application.Main.xOffset;
import static application.Main.yOffset;
import application.communicate;
import application.setWidgetPosition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterController implements Initializable {

    @FXML
    private Label lblTicketsOpen;
    @FXML
    private Label lblMessagesWaiting;
    @FXML
    private Circle statusLight;
    @FXML
    private ListView tvUnassignedTicketList;
    
    final ObservableList<String> listItems = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lblTicketsOpen.setText(Main.activeTicketCount);        
        //Update status light
        statusLight.setFill(communicate.updateStatusLight());
        
        //Add strings to observable list
        for(int i=0; i<Integer.parseInt(Main.activeTicketCount);i++){
        listItems.add("Subject: "+Main.ticketList.get(i).getTicketTitle()+"\nCreated: "+Main.ticketList.get(i).getDateTimeCreated()+"\nUser: "+Main.ticketList.get(i).getUserUN());
        }
        
        //Add observable list to listview
        tvUnassignedTicketList.setItems(listItems);
    
    }

    @FXML
    private void gotoTechCommandCenterMinimized(MouseEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterMinimized.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            //Set widget in lower right corner of primary screen
            window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - (50));
            window.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - (50));
            
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
    }
    
    @FXML
    private void closeCommandCenter(){
        //Exit Program
        System.exit(0);
    }
    
    @FXML
    private void openTicketDetails(){
    
    }
    
}
