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
import application.Note;
import application.Ticket;
import application.communicate;
import application.setWidgetPosition;
import application.user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class TechCommandCenterDetailsController implements Initializable {

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
    @FXML
    private Label lblName;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblBuilding;
    @FXML
    private Label lblRoom;
    @FXML
    private Label lblSubject;
    @FXML
    private TextArea taTicketHistory;
    @FXML
    private TextArea taTicketReply;
    
    public static boolean isAssigned;
    final ObservableList<String> unassignedListItems = FXCollections.observableArrayList();
    final ObservableList<String> assignedListItems = FXCollections.observableArrayList();
    
    private user endUser;
    ArrayList<Note> listOfNotes;
    public static int selectedIndex;
    public static Ticket selectedTicket;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         //Update Ticket Lists
        if(Main.currentUser!=null) {
            try {
                Main.ticketList = communicate.updateAllActiveTechTickets(Main.currentUser.uName);
                communicate.updateMainUnassignedTicketsList();
            } catch (IOException ex) {
                Logger.getLogger(TechCommandCenterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Update "Ticket(s) Available" counter
        lblTicketsAvailable.setText(Main.unassignedTicketCount);        
        
        //Update status light
        statusLight.setFill(Internal.updateStatusLight());
        
        try {
            //Debugging Line
            //System.out.println("Checking for UN:"+Main.unassignedTicketList.get(selectedIndex).getUserUN());
            
            //Get the user object for the username associated with the ticket
            endUser=communicate.getUserByUN(selectedTicket.getUserUN());
            
            //Update Ticket Details labels and comments
            lblName.setText("Name:  "+endUser.getfName()+" "+endUser.getlName());
            lblPhone.setText("Phone:   "+selectedTicket.getPhone());
            lblBuilding.setText("Building: "+selectedTicket.getBuilding());
            lblRoom.setText("Room:    "+selectedTicket.getRoom());
            lblSubject.setText("Subject: "+selectedTicket.getTicketTitle());
            listOfNotes=communicate.getAllCurrentTicketNotes(selectedTicket.getTicketID());
            
            if(listOfNotes.size()>0){
                for(int i=0; i<listOfNotes.size();i++){
                    taTicketHistory.appendText(listOfNotes.get(i).getNote_ID()+" - "+listOfNotes.get(i).getOwner_UN()+"\n"+listOfNotes.get(i).getNote()+"\n\n");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TechCommandCenterDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /////////  UNASSIGNED TICKETS LIST  ///////////
        //Add strings to observable list
        for(int i=0; i<Integer.parseInt(Main.unassignedTicketCount);i++){
            unassignedListItems.add("Subject: "+Main.unassignedTicketList.get(i).getTicketTitle()+"\nCreated: "+Main.unassignedTicketList.get(i).getDateTimeCreated()+"\nUser: "+Main.unassignedTicketList.get(i).getUserUN());
        }
        
        //Add observable list to listview
        lvUnassignedTicketList.setItems(unassignedListItems);
        if(!isAssigned){
            lvUnassignedTicketList.getSelectionModel().select(selectedIndex);
        }
        
        /////////  ASSIGNED TICKETS LIST   ////////////
        //Add strings to observable list
        for(int i=0; i<Integer.parseInt(Main.activeTicketCount);i++){
            assignedListItems.add("Subject: "+Main.ticketList.get(i).getTicketTitle()+"\nCreated: "+Main.ticketList.get(i).getDateTimeCreated()+"\nUser: "+Main.ticketList.get(i).getUserUN());
        }
        
        //Add observable list to listview
        lvAssignedTicketList.setItems(assignedListItems);
        
        //Determine which list is being used and update selected list item
        if(isAssigned){
            lvAssignedTicketList.getSelectionModel().select(selectedIndex);
        }else{
            lvUnassignedTicketList.getSelectionModel().select(selectedIndex);
        }
    }

    @FXML
    private void closeCommandCenter(){
        //Exit Program
        System.exit(0);
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
    private void gotoTechCommandCenter (MouseEvent event) throws IOException{
    
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenter.fxml")); 
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
        window.setScene(scene);
        window.show();
        
    }
        
    @FXML
    private void openAssignedTicketDetails(MouseEvent event) throws IOException{
        
        int i = lvAssignedTicketList.getSelectionModel().getSelectedIndex();
        selectedTicket=Main.ticketList.get(i);
        selectedIndex=i;
        isAssigned=true;
        
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
        window.setScene(scene);
        window.show();
    
    }
    
    @FXML
    private void openUnassignedTicketDetails(MouseEvent event) throws IOException{
        
        int i = lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
        selectedTicket=Main.unassignedTicketList.get(i);
        selectedIndex=i;
        isAssigned=false;
        
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
        window.setScene(scene);
        window.show();
    
    }
    
    @FXML
    private void replyToTicket(MouseEvent event) throws IOException{
        System.out.println(selectedTicket.getTicketID());
        System.out.println(Main.currentUser.getuName());
        System.out.println(taTicketReply.getText());
        
        communicate.createTicketNote(selectedTicket.getTicketID(), Main.currentUser.getuName(), taTicketReply.getText());
        refreshTechCommandCenterDetails(event);
    }
    
    @FXML
    private void refreshTechCommandCenterDetails(MouseEvent event) throws IOException{
               
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
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void assignToMe(MouseEvent event) throws IOException{

        int i = lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
        selectedTicket=Main.unassignedTicketList.get(i);
        selectedIndex=i;
        isAssigned=true;
        
        communicate.assignTicket(Main.currentUser.getuName(), selectedTicket.getTicketID());
        
        //refreshTechCommandCenterDetails(event);
        gotoTechCommandCenter(event);
        
    }
    
    @FXML
    private void reassignTicket(MouseEvent event) throws IOException{

        int i = lvAssignedTicketList.getSelectionModel().getSelectedIndex();
        selectedTicket=Main.ticketList.get(i);
        selectedIndex=i;
        isAssigned=false;
        
        communicate.assignTicket("unassigned", selectedTicket.getTicketID());
        communicate.updateMainUnassignedTicketsList();
        
        //refreshTechCommandCenterDetails(event);
        gotoTechCommandCenter(event);
        
    }
    
    @FXML
    private void closeTicket(MouseEvent event) throws IOException{
    
        if(selectedTicket.getTechUN().equalsIgnoreCase("unassigned")){
        selectedTicket.setTechUN(Main.currentUser.getuName());
        }
        selectedIndex--;
        communicate.closeTicket(selectedTicket);
        gotoTechCommandCenter(event);
        
    }
    
}
