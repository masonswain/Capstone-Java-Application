/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.communicate;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterRemoteConnectionUtility implements Initializable {

    @FXML
    private AnchorPane apScene;
    @FXML
    private Label lblStatus;
    @FXML
    private TextField tfEndUser;
    @FXML
    private Button btnConnect;
    @FXML
    private Button btnCancel;

    String userName=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connect(MouseEvent event) throws IOException, InterruptedException {
                
        userName=tfEndUser.getText();
        
        Random r = new Random();
        String sessionID = Integer.toString(r.nextInt(999999999));
        
        //Add Entry to Remote Session Table
        if(communicate.createRemoteSession(userName, sessionID)){
            
            //Delete REMOTESESSION
            communicate.deleteRemoteSession(userName, sessionID);
            updateStatus("Status: Attempting Connection");
            //Start the uvnc viewer with session id
            System.out.println("Session ID: "+sessionID);
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec("src/uvnc/vncviewer.exe -proxy joelknutson.asuscomm.com:5901 ID:"+sessionID);
            //while(process.isAlive()){}
            cancel(event);
        }else{
            lblStatus.setText("Status: Connection Failed");
            System.out.println("Remote connection failed");
        
        }
        
    }

    @FXML
    private void cancel(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    private void updateStatus(String msg){
    lblStatus.setText(msg);
    }
    
}
