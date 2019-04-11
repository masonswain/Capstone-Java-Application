/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.communicate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterCreateUserFormController implements Initializable {

    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtUName;
    @FXML
    private TextField txtAuthPW;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox cbAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbAdmin.getItems().addAll("N","Y");
        cbAdmin.getSelectionModel().select(0);
    }    
    
    @FXML
    private void submit(MouseEvent event) throws IOException{
        
        //System.out.println(cbAdmin.getSelectionModel().getSelectedItem().toString());
        if(communicate.createNewUser(txtFName.getText(), txtLName.getText(), txtUName.getText(), txtAuthPW.getText(), cbAdmin.toString())){
        System.out.println("Account Created Successfully");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        }else{
        System.out.println("There was an error creating the account");
        }
    }
    
    @FXML
    private void cancel(MouseEvent event) throws IOException{
    
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    
    }
    
}
