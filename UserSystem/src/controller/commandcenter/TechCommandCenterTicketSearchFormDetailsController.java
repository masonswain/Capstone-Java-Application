/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.Internal;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterTicketSearchFormDetailsController implements Initializable {

    @FXML
    private ComboBox cbTech;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnOpen;
    @FXML
    private Button btnOpenAndAssign;
    @FXML
    private ComboBox cbEndUser;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private ListView lvTicketList;
    @FXML
    private Label lblStatus;
    @FXML
    private TextArea taNotesList;
    
    private ArrayList<Object> previousSceneLists;
    private ArrayList<user> listOfAdmins;
    private ArrayList<user> listOfUsers;
    private ArrayList<Ticket> listOfTickets;
    
    final ObservableList<String> ticketListItems = FXCollections.observableArrayList();
    
    public static Integer cbTechSelection;
    public static Integer cbEndUserSelection;
    public static Integer lvTicketListSelection;
    
    public static boolean fromSearchForm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(fromSearchForm){
            previousSceneLists = TechCommandCenterTicketSearchFormController.getItems();
            listOfAdmins= (ArrayList<user>) previousSceneLists.get(0);
            listOfUsers= (ArrayList<user>) previousSceneLists.get(1);
            listOfTickets = (ArrayList<Ticket>) previousSceneLists.get(2);
        }
        
        
        for(int i=0; i<listOfAdmins.size();i++){
            cbTech.getItems().add(listOfAdmins.get(i).getfName()+" "+listOfAdmins.get(i).getlName());
            cbTech.getSelectionModel().select((int) cbTechSelection);
        }
    
        for(int i=0; i<listOfUsers.size();i++){
            cbEndUser.getItems().add(listOfUsers.get(i).getfName()+" "+listOfUsers.get(i).getlName());
            cbEndUser.getSelectionModel().select((int) cbEndUserSelection);
        }
         
        /////////  UNASSIGNED TICKETS LIST  ///////////
        //Add strings to observable list
        for(int i=0; i<listOfTickets.size();i++){
            ticketListItems.add("Subject: "+listOfTickets.get(i).getTicketTitle()+"\nCreated: "+listOfTickets.get(i).getDateTimeCreated()+"\nUser: "+listOfTickets.get(i).getUserUN()+"\nStatus: "+listOfTickets.get(i).getStatus());
        }
        
        lvTicketList.setItems(ticketListItems);
        System.out.println("Selecting..."+lvTicketListSelection);
        //lvTicketList.getSelectionModel().select(lvTicketListSelection);
        lvTicketList.getSelectionModel().clearAndSelect(lvTicketListSelection);
        
        try {
            updateDetails();
        } catch (IOException ex) {
            Logger.getLogger(TechCommandCenterTicketSearchFormDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    

    @FXML
    private void gotoTicketDetails(MouseEvent event) throws IOException {
        updateDetails();
    }
    
    @FXML
    private void search(MouseEvent event) throws IOException {
        ticketListItems.clear();
        String techUN="%";
        String userUN="%";
        String fromDate="0";
        String toDate="3019-12-31 23:59:59";
        
        if(!cbTech.getSelectionModel().isEmpty()){
            //System.out.println("cbTech not empty");
            techUN=listOfAdmins.get(cbTech.getSelectionModel().getSelectedIndex()).getuName();
        }
        if(!cbEndUser.getSelectionModel().isEmpty()&& cbEndUser.getSelectionModel().getSelectedIndex()!=0){
            //System.out.println("cbEndUser not empty");
            System.out.println(cbEndUser.getSelectionModel().isEmpty());
            userUN=listOfUsers.get(cbEndUser.getSelectionModel().getSelectedIndex()).getuName();
        }
        System.out.println("Date box: "+dateFrom.getValue());
        if(dateFrom.getValue()!=null){
            fromDate=dateFrom.getValue().toString()+" 00:00:00";
            System.out.println("From date: "+fromDate);
        }
        if(dateTo.getValue()!=null){
        toDate=dateTo.getValue().toString()+" 00:00:00";
        }
        
        listOfTickets=communicate.searchTickets(techUN,userUN,fromDate,toDate);
        
        //Add strings to observable list
        for(int i=0; i<listOfTickets.size()-1;i++){
            ticketListItems.add("Subject: "+listOfTickets.get(i).getTicketTitle()+"\nCreated: "+listOfTickets.get(i).getDateTimeCreated()+"\nUser: "+listOfTickets.get(i).getUserUN()+"\nStatus: "+listOfTickets.get(i).getStatus());
        }
        
        lvTicketList.setItems(ticketListItems);
        
    }
    
    @FXML
    private void cancel(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    @FXML
    private void openOrClose(MouseEvent event) throws IOException{
        //System.out.println("Open or Close");
        if(listOfTickets.get(lvTicketList.getSelectionModel().getSelectedIndex()).getStatus().equalsIgnoreCase("Active")){
        TechCommandCenterTicketSearchFormDetailsConfirmationController.request="close";
        }
        if(listOfTickets.get(lvTicketList.getSelectionModel().getSelectedIndex()).getStatus().equalsIgnoreCase("Closed")){
        TechCommandCenterTicketSearchFormDetailsConfirmationController.request="open";
        }
        openConfirmation(event);
        
    }
    
    @FXML
    private void assignToMe(MouseEvent event) throws IOException{
        //System.out.println("Assign to me");
        TechCommandCenterTicketSearchFormDetailsConfirmationController.request="assignToMe";
        openConfirmation(event);
    }
    
   private void updateDetails() throws IOException{
        cbTechSelection=cbTech.getSelectionModel().getSelectedIndex();
        cbEndUserSelection=cbEndUser.getSelectionModel().getSelectedIndex();
        lblStatus.setText("Ticket ID: "+listOfTickets.get(lvTicketList.getSelectionModel().getSelectedIndex()).getTicketID()+"\nCreated: "+listOfTickets.get(lvTicketList.getSelectionModel().getSelectedIndex()).getDateTimeCreated());
        
        ArrayList<Note> listOfNotes = communicate.getAllCurrentTicketNotesOldestToNewest(listOfTickets.get(lvTicketList.getSelectionModel().getSelectedIndex()).getTicketID());
        
        taNotesList.clear();
        //taNotesList. =Internal.noteArrayListToTextAreaString(listOfNotes);
        taNotesList.appendText(Internal.noteArrayListToTextAreaString(listOfNotes));
        taNotesList.home();
    
   }

   private void openConfirmation(MouseEvent event) throws IOException{
        
        TechCommandCenterTicketSearchFormDetailsConfirmationController.ticketID=listOfTickets.get(lvTicketList.getSelectionModel().getSelectedIndex()).getTicketID();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterTicketSearchFormDetailsConfirmation.fxml")); 
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();        
        //Set Scene and Show
        window.setScene(scene);
        window.show();
    }
}

