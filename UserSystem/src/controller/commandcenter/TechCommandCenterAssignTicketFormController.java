/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.Main;
import application.communicate;
import application.user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterAssignTicketFormController implements Initializable {

    @FXML
    private ComboBox cbAssignTo;
    @FXML
    private TextField txtRoomNum;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextArea txtProblemSubject;
    @FXML
    private TextArea txtProblemDescription;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private TextArea txtAssignTicketNote;
    
    ArrayList<user> listOfAdmins= new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listOfAdmins.add(new user());
        listOfAdmins.get(0).setuName("Unassigned");
        listOfAdmins.get(0).setfName("Unassigned");
        listOfAdmins.get(0).setlName("");
        
        try {
            listOfAdmins.addAll(communicate.getListOfAdmins());
            for(int i=0; i<listOfAdmins.size();i++){
                cbAssignTo.getItems().add(listOfAdmins.get(i).getfName()+" "+listOfAdmins.get(i).getlName());
                cbAssignTo.getSelectionModel().select(0);
            }
        } catch (IOException ex) {
            Logger.getLogger(TechCommandCenterAssignTicketFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void submit(MouseEvent event) throws IOException {
        
        if(!txtAssignTicketNote.getText().isEmpty()){
        communicate.createTicketNote(TechCommandCenterDetailsController.selectedTicket.getTicketID(), Main.currentUser.getuName(), txtAssignTicketNote.getText());
        }
        //System.out.println(cbAdmin.getSelectionModel().getSelectedItem().toString());
        if(communicate.assignTicket(listOfAdmins.get(cbAssignTo.getSelectionModel().getSelectedIndex()).getuName(), TechCommandCenterDetailsController.selectedTicket.getTicketID())){
            System.out.println("Ticket assigned successfully");
            communicate.updateMainUnassignedTicketsList();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
            
        }else{
            System.out.println("There was an error assigning the ticket");
        }
    }
    
    @FXML
    private void cancel(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
}
