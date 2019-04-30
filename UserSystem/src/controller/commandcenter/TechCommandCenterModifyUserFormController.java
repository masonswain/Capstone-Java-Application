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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterModifyUserFormController implements Initializable {

    @FXML
    private ComboBox cbSelectUser;
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtUName;
    @FXML
    private TextField txtAuthPW;
    @FXML
    private ComboBox cbAdmin;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private CheckBox chkbxShowPW;
    @FXML
    private CheckBox chkbxDeleteUser;
    
    ArrayList<user> listOfUsers= new ArrayList();
    user selectedUser = new user();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int select=0;
        cbAdmin.getItems().addAll("N","Y");
        try {
            listOfUsers.addAll(communicate.getListOfAllUsers());
            for(int i=0; i<listOfUsers.size();i++){
                
                if(listOfUsers.get(i).uName.equals(Main.currentUser.uName)){
                   select=i;
                   System.out.println("Selct Index: "+select);
                }
                cbSelectUser.getItems().add(listOfUsers.get(i).getfName()+" "+listOfUsers.get(i).getlName());
                
            }
        } catch (IOException ex) {
            Logger.getLogger(TechCommandCenterAssignTicketFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void userSelected(ActionEvent event) throws IOException{
    
        selectedUser=listOfUsers.get(cbSelectUser.getSelectionModel().getSelectedIndex());
        System.out.println("You selected "+selectedUser.getfName()+" "+selectedUser.getlName());
        txtFName.setText(selectedUser.getfName());
        txtLName.setText(selectedUser.getlName());
        txtUName.setText(selectedUser.getuName());
        
        System.out.println(selectedUser.isAdmin);
        if(selectedUser.isAdmin){
        cbAdmin.getSelectionModel().select(1);
        }
        else{
        cbAdmin.getSelectionModel().select(0);
        }
        
        togglePW();
        /*
        if(chkbxShowPW.isSelected()){
            //System.out.println(communicate.getUserPWByUN("jknutson"));
            txtAuthPW.setText(communicate.getUserPWByUN(selectedUser.getuName()));
        }else{
            txtAuthPW.setText("*******");
        }
*/
    }
    @FXML
    private void submit(MouseEvent event) throws IOException {
        if(chkbxDeleteUser.isSelected()){
            deleteUser(event);
        }else{
            updateUser(event);
        }
    }

    @FXML
    private void cancel(MouseEvent event) {
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    @FXML
    private void chkbxPW(ActionEvent event) throws IOException{
    
        if(cbSelectUser.getSelectionModel().getSelectedIndex()>=0){
        togglePW();
        }
        
    }
    
    private void updateUser(MouseEvent event) throws IOException{
    
        //System.out.println(cbAdmin.getSelectionModel().getSelectedItem().toString());
        String pw=null;
        if(chkbxShowPW.isSelected()){
            pw=txtAuthPW.getText();
        }
        System.out.println("Admin: "+cbAdmin.getSelectionModel().getSelectedItem().toString());
        if(communicate.updateUser(txtFName.getText(), txtLName.getText(), txtUName.getText(), pw, cbAdmin.getSelectionModel().getSelectedItem().toString())){
        System.out.println("Account Updated Successfully");
        TechCommandCenterModifyUserConfirmationController.msg=selectedUser.getfName()+" "+selectedUser.getlName()+" has been updated";
        }else{
        System.out.println("There was an error creating the account");
        TechCommandCenterModifyUserConfirmationController.msg="There was an error deleting "+selectedUser.getfName()+" "+selectedUser.getlName();
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterModifyUserConfirmation.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
    }
    
    private void deleteUser(MouseEvent event) throws IOException{
    
        System.out.println("Admin: "+cbAdmin.getSelectionModel().getSelectedItem().toString());
        if(communicate.deleteUser(txtUName.getText(), Main.key)){
        System.out.println("Account Deleted Successfully");
        TechCommandCenterModifyUserConfirmationController.msg=selectedUser.getfName()+" "+selectedUser.getlName()+" has been deleted";
        }else{
        System.out.println("There was an error deleting the account");
        TechCommandCenterModifyUserConfirmationController.msg="There was an error deleting "+selectedUser.getfName()+" "+selectedUser.getlName();
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterModifyUserConfirmation.fxml")); 
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
    }
    
    private void togglePW() throws IOException{
    
        if(chkbxShowPW.isSelected()){
            //System.out.println(communicate.getUserPWByUN("jknutson"));
            txtAuthPW.setText(communicate.getUserPWByUN(selectedUser.getuName()));
        }else{
            if(cbSelectUser.getSelectionModel().getSelectedIndex()>=0){
                txtAuthPW.setText("*******");
            }
        }
        
    }
    
}
