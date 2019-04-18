/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.widget;

import application.Internal;
import application.setWidgetPosition;
import application.Main;
import static application.Main.xOffset;
import static application.Main.yOffset;
import application.communicate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class ViewTicketsController implements Initializable {

    @FXML
    private Label viewTickets;
    @FXML
    private Button btnOpenTicket;
    @FXML
    private Button btnCancel;
    @FXML
    private Polygon btnCollapse;
    @FXML
    private Label lblTicketsOpen;
    @FXML
    private Label lblMessagesWaiting;
    @FXML
    private ListView tvTicketList;
    @FXML
    private Circle statusLight;
    
    final ObservableList<String> listItems = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        Internal.intializeWidgetStatus();

        lblTicketsOpen.setText(Main.activeTicketCount);
        lblMessagesWaiting.setText(Main.unreadMessageCount);
        
        //Update status light
        statusLight.setFill(Main.statusLightColor);

        //Add strings to observable list
        String notification="";
        String endNotification="";
        
        for(int i=0; i<Integer.parseInt(Main.activeTicketCount);i++){
        if(Main.unreadTicketIDList.contains(Main.ticketList.get(i).getTicketID())){
                notification =    "===== UNREAD MESSAGE(S) WAITING =====\n";
                endNotification="\n===============================";
            }else{
                notification = "";
                endNotification="";
            }    
        listItems.add(notification+"Issue: "+Main.ticketList.get(i).getTicketTitle()+"  \nCreated: "+Main.ticketList.get(i).getDateTimeCreated()+"  \nTechnician: "+Main.ticketList.get(i).getTechUN()+endNotification);
        }
        
        //Add observable list to listview
        tvTicketList.setItems(listItems);
        
    }

    @FXML
    private void gotoOpenTicket(MouseEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/OpenTicket.fxml")); 
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();   
            double nextStageHeight = 400.0;
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
            
            window.setScene(scene);
            window.show();
    }
    @FXML
    private void gotoWidgetExpanded(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/WidgetExpanded.fxml")); 
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
            
            window.setScene(scene);
            window.show();
    }

    @FXML
    private void gotoWidgetCollapsed(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/WidgetExpanded.fxml")); 
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
            
            window.setScene(scene);
            window.show();
    }
    
     @FXML
    private void gotoViewTicketComment(MouseEvent event) throws IOException {
            System.out.println(tvTicketList.getSelectionModel().getSelectedItem());
            int i=tvTicketList.getSelectionModel().getSelectedIndex();
            //Debug
            //System.out.println(Main.ticketList.get(i).getTicketID());
            //Assign ticket id of selected item to ticketID in ViewTicketComment.java
            ViewTicketCommentController.ticketID=Main.ticketList.get(i).getTicketID();
            Main.ticket=Main.ticketList.get(i);
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/ViewTicketComment.fxml")); 
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();             
            double nextStageHeight = 400.0;
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
            
            window.setScene(scene);
            window.show();
    }
    
}
