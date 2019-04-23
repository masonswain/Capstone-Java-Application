/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.Main;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterTicketSearchFormController implements Initializable {

    @FXML
    private ComboBox cbTech;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox cbEndUser;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private ListView lvTicketList;
    
    ArrayList<user> listOfAdmins= new ArrayList();
    ArrayList<user> listOfUsers= new ArrayList();
    ArrayList<Ticket> listOfTickets = new ArrayList();
    final ObservableList<String> ticketListItems = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    ////////       Populate Admin List      ////////
    listOfAdmins.add(new user());
    listOfAdmins.get(0).setuName("Unassigned");
    listOfAdmins.get(0).setfName("Unassigned");
    listOfAdmins.get(0).setlName("");
    
    try {
        listOfAdmins.addAll(communicate.getListOfAdmins());
        for(int i=0; i<listOfAdmins.size();i++){
            cbTech.getItems().add(listOfAdmins.get(i).getfName()+" "+listOfAdmins.get(i).getlName());
            cbTech.getSelectionModel().select(0);
        }
    }
    catch (IOException ex) {
            Logger.getLogger(TechCommandCenterAssignTicketFormController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    ////////     Populate End User List     ////////
        try {
            listOfUsers.addAll(communicate.getListOfAllUsers());
            for(int i=0; i<listOfUsers.size();i++){
            
                cbEndUser.getItems().add(listOfUsers.get(i).getfName()+" "+listOfUsers.get(i).getlName());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TechCommandCenterAssignTicketFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void search(MouseEvent event) throws IOException {
        ticketListItems.clear();
        String techUN="%";
        String userUN="%";
        String fromDate="0";
        String toDate="3019-12-31 23:59:59";
        
        if(!cbTech.getSelectionModel().isEmpty()){
            System.out.println("cbTech not empty");
            techUN=listOfAdmins.get(cbTech.getSelectionModel().getSelectedIndex()).getuName();
        }
        if(!cbEndUser.getSelectionModel().isEmpty()){
            System.out.println("cbEndUser not empty");
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
        
        
        
        /////////  UNASSIGNED TICKETS LIST  ///////////
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
    
}
