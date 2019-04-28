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
import application.Note;
import application.communicate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class ViewTicketCommentController implements Initializable {

    @FXML
    private Label lblTicketsOpen;
    @FXML
    private Label lblMessagesWaiting;
    @FXML
    private TextArea txtNote;
    @FXML
    private Label lblCommentDateTime;
    @FXML
    private Label lblOwner;
    @FXML
    private Circle statusLight;
    
    public static String ticketID;
    Note currentNote;
    boolean timerStop=false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        updateStatus();
        
        try {
            currentNote=communicate.getCurrentTicketNote(ticketID);
            
            txtNote.setText(currentNote.getNote());
            lblOwner.setText("Ticket Comment: "+Main.currentUser.getfName()+" "+Main.currentUser.getlName());
            lblCommentDateTime.setText("Comment Date: "+currentNote.getNote_ID());
            System.out.println(currentNote.getNote());
        } catch (IOException ex) {
            txtNote.setText("** error getting note **");
            Logger.getLogger(ViewTicketCommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    private void gotoViewTickets(ActionEvent event) throws IOException {
     
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/ViewTickets.fxml")); // loads main fxml class
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
            timerStop=true;
            window.setScene(scene);
            window.show();
    
    }
    
    @FXML
    private void gotoWidgetExpanded(ActionEvent event) throws IOException {
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

    @FXML
    private void gotoWidgetCollapsed(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/WidgetCollapsed.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();             
            double nextStageHeight = 40.0;
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

    @FXML
    private void ReplyToTicket(MouseEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/ReplyToTicket.fxml")); // loads main fxml class
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
            timerStop=true;
            window.setScene(scene);
            window.show();
    }

    @FXML
    private void gotoViewTicketHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/widget/ViewTicketHistory.fxml")); // loads main fxml class
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
        timerStop=true;
        window.setScene(scene);
        window.show();
    }
    
    private void updateStatus(){
        
        Internal.intializeWidgetStatus();
        lblTicketsOpen.setText(Main.activeTicketCount);
        lblMessagesWaiting.setText(Main.unreadMessageCount);
        //Update status light
        statusLight.setFill(Main.statusLightColor);
        
    }
}
