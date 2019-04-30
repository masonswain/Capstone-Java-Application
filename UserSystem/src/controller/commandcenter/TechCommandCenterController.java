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
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    private Label lblTicketsAvailable;
    @FXML
    private Label lblMessagesWaiting;
    @FXML
    private Circle statusLight;
    @FXML
    private ListView lvUnassignedTicketList;
    @FXML
    private ListView lvAssignedTicketList;
    
    final ObservableList<String> unassignedListItems = FXCollections.observableArrayList();
    final ObservableList<String> assignedListItems = FXCollections.observableArrayList();
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
            timerStop=true;
            window.setScene(scene);
            window.show();
    }
    
    @FXML
    private void closeCommandCenter(){
        //Exit Program
        timerStop=true;
        System.exit(0);
    }
    
    @FXML
    private void openUnassignedTicketDetails(MouseEvent event) throws IOException{
        
        int i = lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
        TechCommandCenterDetailsController.selectedTicket=Main.unassignedTicketList.get(i);
        TechCommandCenterDetailsController.selectedIndex=i;
        TechCommandCenterDetailsController.isAssigned=false;
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterDetails.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
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
        
        
        //Set Scene and Show
        timerStop=true;
        window.setScene(scene);
        window.show();
    
    }
    
    @FXML
    private void openAssignedTicketDetails(MouseEvent event) throws IOException{
        
        int i = lvAssignedTicketList.getSelectionModel().getSelectedIndex();
        TechCommandCenterDetailsController.selectedTicket=Main.ticketList.get(i);
        TechCommandCenterDetailsController.selectedIndex=i;
        TechCommandCenterDetailsController.isAssigned=true;
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterDetails.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
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
        
        
        //Set Scene and Show
        timerStop=true;
        window.setScene(scene);
        window.show();
    
    }
    //assign button under unassigned ticket list
    @FXML
    private void assignTicketForm(MouseEvent event) throws IOException{
        
        int i = lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
        TechCommandCenterDetailsController.selectedTicket=Main.unassignedTicketList.get(i);
        TechCommandCenterDetailsController.selectedIndex=i;
        TechCommandCenterDetailsController.isAssigned=false;
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterAssignTicketForm.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    
    }
    //reassign button under assigned ticket list
    @FXML
    private void reassignTicketForm(MouseEvent event) throws IOException{
        
        int i = lvAssignedTicketList.getSelectionModel().getSelectedIndex();
        TechCommandCenterDetailsController.selectedTicket=Main.ticketList.get(i);
        TechCommandCenterDetailsController.selectedIndex=i;
        TechCommandCenterDetailsController.isAssigned=false;
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterAssignTicketForm.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    
    }
    
    @FXML
    private void unassignTicket(MouseEvent event) throws IOException{

        int i = lvAssignedTicketList.getSelectionModel().getSelectedIndex();
        TechCommandCenterDetailsController.selectedTicket=Main.ticketList.get(i);
        TechCommandCenterDetailsController.selectedIndex=i;
        TechCommandCenterDetailsController.isAssigned=false;
        
        communicate.assignTicket("unassigned", TechCommandCenterDetailsController.selectedTicket.getTicketID());
        communicate.updateMainUnassignedTicketsList();
        
        //refreshTechCommandCenterDetails(event);
        //refreshTechCommandCenter(event);
        updateStatus();
        
    }
    
    @FXML
    private void assignToMe(MouseEvent event) throws IOException{

        int i = lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
        TechCommandCenterDetailsController.selectedTicket=Main.unassignedTicketList.get(i);
        TechCommandCenterDetailsController.selectedIndex=i;
        TechCommandCenterDetailsController.isAssigned=true;
        
        communicate.assignTicket(Main.currentUser.getuName(), TechCommandCenterDetailsController.selectedTicket.getTicketID());
        
        //refreshTechCommandCenter(event);
        //refreshTechCommandCenter(event);
        updateStatus();
        
    }
    
    //Administrator Tools
    @FXML
    private void newOpenTicketForm(MouseEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterOpenTicketForm.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    
    }
    
    @FXML
    private void newCreateUserForm(MouseEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterCreateUserForm.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    
    }
    
    @FXML
    private void newModifyUserForm(MouseEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterModifyUserForm.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    
    }
    
    @FXML
    private void ticketSearchForm(MouseEvent event) throws IOException{
    
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterTicketSearchForm.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
        
    }
    
    @FXML
    private void remoteConnect(MouseEvent event) throws IOException, InterruptedException{
         
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterRemoteConnectionUtility.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }
    
    private void updateStatus(){
     
        int assignedSelected=lvAssignedTicketList.getSelectionModel().getSelectedIndex();
        int unassignedSelected=lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
        
        assignedListItems.clear();
        unassignedListItems.clear();
        
        //Update Ticket List and unread message counter
        if(Main.currentUser!=null) {
            try {
                Main.ticketList = communicate.updateAllActiveTechTickets(Main.currentUser.uName);
                communicate.updateMainUnassignedTicketsList();
                communicate.updateUnreadMessages(Main.currentUser.getuName());
            } catch (IOException ex) {
                Logger.getLogger(TechCommandCenterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Update Ticket & Note Counters
        lblTicketsAvailable.setText(Main.unassignedTicketCount);        
        lblMessagesWaiting.setText(Main.unreadMessageCount);
        
        //Update status light
        statusLight.setFill(Internal.updateStatusLight());
        
        /////////  UNASSIGNED TICKETS LIST  ///////////
        //Add strings to observable list
        for(int i=0; i<Integer.parseInt(Main.unassignedTicketCount);i++){
            unassignedListItems.add("Subject: "+Main.unassignedTicketList.get(i).getTicketTitle()+"\nCreated: "+Main.unassignedTicketList.get(i).getDateTimeCreated()+"\nUser: "+Main.unassignedTicketList.get(i).getUserUN());
        }
        
        //Add observable list to listview
        lvUnassignedTicketList.setItems(unassignedListItems);
        lvUnassignedTicketList.getSelectionModel().select(unassignedSelected);
        
        
        /////////  ASSIGNED TICKETS LIST   ////////////
        //Add strings to observable list
        String notification="";
        String endNotification="";
        for(int i=0; i<Integer.parseInt(Main.activeTicketCount);i++){
            //System.out.println("Unread Ticket List index 0: "+Main.unreadTicketIDList.get(0));
            //System.out.println(Main.ticketList.get(i).getTicketID());
            if(Main.unreadTicketIDList.contains(Main.ticketList.get(i).getTicketID())){
                notification =    "===== UNREAD MESSAGE(S) WAITING =====\n";
                endNotification="\n===============================";
            }else{
                notification = "";
                endNotification="";
            }
            assignedListItems.add(notification+"Subject: "+Main.ticketList.get(i).getTicketTitle()+"\nCreated: "+Main.ticketList.get(i).getDateTimeCreated()+"\nUser: "+Main.ticketList.get(i).getUserUN()+endNotification);
        }
        
        //Add observable list to listview
        lvAssignedTicketList.setItems(assignedListItems);
        lvAssignedTicketList.getSelectionModel().select(assignedSelected);
    }
    
}
