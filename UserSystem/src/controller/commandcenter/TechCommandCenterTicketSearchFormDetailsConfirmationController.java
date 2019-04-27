/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.Main;
import application.Ticket;
import application.communicate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterTicketSearchFormDetailsConfirmationController implements Initializable {

    @FXML
    private Label lblConfirmationMsg;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    
    
    public static String ticketID;
    public static String request;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(request.equalsIgnoreCase("open")){
            lblConfirmationMsg.setText("Are you sure you want to reopen ticket id# "+ticketID+"?");
            System.out.println("Open/Close"); 
        }
        
        if(request.equalsIgnoreCase("close")){
            lblConfirmationMsg.setText("Are you sure you want to close ticket id# "+ticketID+"?");
            System.out.println("Open/Close"); 
        }
        
        if(request.equalsIgnoreCase("assignToMe")){
            lblConfirmationMsg.setText("Are you sure you want to assign ticket id# "+ticketID+" to yourself?");
            System.out.println("Assign to me");
        }
}
    @FXML
    private void confirm(MouseEvent event) throws IOException{
        if(request.equalsIgnoreCase("open")){
            Ticket ticket = new Ticket();
            ticket.setTicketID(ticketID);
            communicate.openCloseTicketByID(ticket,true);
        }
        
        if(request.equalsIgnoreCase("close")){
            Ticket ticket = new Ticket();
            ticket.setTicketID(ticketID);
            communicate.openCloseTicketByID(ticket,false);
        }
        
        if(request.equalsIgnoreCase("assignToMe")){
            communicate.assignTicket(Main.currentUser.getuName(), ticketID);
        }
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    @FXML
    private void cancel(MouseEvent event){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
}

