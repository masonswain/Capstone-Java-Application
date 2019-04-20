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
public class TechCommandCenterOpenTicketFormController implements Initializable {

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
    private ComboBox cbBuilding;
    @FXML
    private ComboBox cbEndUser;
    
    ArrayList<user> listOfUsers= new ArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //listOfUsers.add(Main.currentUser);
       //listOfUsers.get(0).setuName();
        //listOfUsers.get(0).setfName("Unassigned");
        //listOfUsers.get(0).setlName("");
        int select=0;
        try {
            listOfUsers.addAll(communicate.getListOfAllUsers());
            for(int i=0; i<listOfUsers.size();i++){
                
                if(listOfUsers.get(i).uName.equals(Main.currentUser.uName)){
                   select=i;
                   System.out.println("Selct Index: "+select);
                }
                cbEndUser.getItems().add(listOfUsers.get(i).getfName()+" "+listOfUsers.get(i).getlName());
                
            }
        } catch (IOException ex) {
            Logger.getLogger(TechCommandCenterAssignTicketFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cbEndUser.getSelectionModel().select(select);
        //System.out.println("Selct Index: "+select);
        cbBuilding.getItems().addAll("CLC","District Office","Kaposia","Lincoln Center","Secondary");
        cbBuilding.getSelectionModel().select(0);
    }    
    
    @FXML
    private void submit(MouseEvent event) throws IOException{
    
        if(communicate.createTicketForUser(cbBuilding.getSelectionModel().getSelectedItem().toString(), txtRoomNum.getText(), txtPhone.getText(), txtProblemDescription.getText(), txtProblemSubject.getText(), listOfUsers.get(cbEndUser.getSelectionModel().getSelectedIndex()).uName)){
        System.out.println("Ticket Created Successfully");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        }else{
        System.out.println("There was an error creating the ticket");
        }
    }
    
    @FXML
    private void cancel(MouseEvent event) throws IOException{
    
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    
    }
    
}
