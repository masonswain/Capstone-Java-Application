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
import application.user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    boolean timerStop=false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Update all dynamic elements in scene
        updateStatus();
        //Set the text area for the notes to scroll from the top
        taTicketHistory.home();
        
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
    private void closeCommandCenter(){
        //Exit Program
        timerStop=true;
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
            timerStop=true;
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
        
        timerStop=true;
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
        updateStatus();
        taTicketHistory.home();
            
    }
    
    @FXML
    private void openUnassignedTicketDetails(MouseEvent event) throws IOException{
        
        int i = lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
        selectedTicket=Main.unassignedTicketList.get(i);
        selectedIndex=i;
        isAssigned=false;
        updateStatus();
        taTicketHistory.home();    
    }
    
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
    
    @FXML
    private void assignToMe(MouseEvent event) throws IOException{

        int i = lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
        selectedTicket=Main.unassignedTicketList.get(i);
        selectedIndex=i;
        isAssigned=true;
        
        communicate.assignTicket(Main.currentUser.getuName(), selectedTicket.getTicketID());
        updateStatus();
        taTicketHistory.home();
        
    }
    
    @FXML
    private void unassignTicket(MouseEvent event) throws IOException{

        int i = lvAssignedTicketList.getSelectionModel().getSelectedIndex();
        selectedTicket=Main.ticketList.get(i);
        selectedIndex=i;
        isAssigned=false;
        
        communicate.assignTicket("unassigned", selectedTicket.getTicketID());
        communicate.updateMainUnassignedTicketsList();
        
        //refreshTechCommandCenterDetails(event);
        //gotoTechCommandCenter(event);
        updateStatus();
        taTicketHistory.home();
        
    }
    
    @FXML
    private void closeTicket(MouseEvent event) throws IOException{
    
        if(selectedTicket.getTechUN().equalsIgnoreCase("unassigned")){
        selectedTicket.setTechUN(Main.currentUser.getuName());
        }
        if(!taTicketReply.getText().isEmpty()){
        replyToTicket(event);
        }
        selectedIndex--;
        communicate.closeTicket(selectedTicket);
        gotoTechCommandCenter(event);
        
    }
    
    @FXML
    private void replyToTicket(MouseEvent event) throws IOException{
        System.out.println(selectedTicket.getTicketID());
        System.out.println(Main.currentUser.getuName());
        System.out.println(taTicketReply.getText());
        
        communicate.createTicketNote(selectedTicket.getTicketID(), Main.currentUser.getuName(), taTicketReply.getText());
        updateStatus();
        taTicketHistory.home();
        taTicketReply.clear();
    }
        
    private void updateStatus(){
        
        int assignedSelected=lvAssignedTicketList.getSelectionModel().getSelectedIndex();
        int unassignedSelected=lvUnassignedTicketList.getSelectionModel().getSelectedIndex();
                
        assignedListItems.clear();
        unassignedListItems.clear();
        taTicketHistory.clear();
    
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
        
        //Build and populate the text area that holds the notes history
        try {
            
            //Get the user object for the username associated with the ticket
            endUser=communicate.getUserByUN(selectedTicket.getUserUN());
            
            //Update Ticket Details labels and comments
            lblName.setText("Name:  "+endUser.getfName()+" "+endUser.getlName());
            lblPhone.setText("Phone:   "+selectedTicket.getPhone());
            lblBuilding.setText("Building: "+selectedTicket.getBuilding());
            lblRoom.setText("Room:    "+selectedTicket.getRoom());
            lblSubject.setText("Subject: "+selectedTicket.getTicketTitle());
            listOfNotes=communicate.getAllCurrentTicketNotesNewestToOldest(selectedTicket.getTicketID());
            
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
        lvUnassignedTicketList.getSelectionModel().select(unassignedSelected);
                
        /////////  ASSIGNED TICKETS LIST   ////////////
        //Add strings to observable list
        String notification="";
        String endNotification="";
        for(int i=0; i<Integer.parseInt(Main.activeTicketCount);i++){
            System.out.println("Unread Ticket List index 0: "+Main.unreadTicketIDList.get(0));
            System.out.println(Main.ticketList.get(i).getTicketID());
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
