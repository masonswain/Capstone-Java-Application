/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.Main;
import application.communicate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class OpenTicketController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Polygon btnCollapse;
    @FXML
    private Button btnSubmit;
    @FXML
    private Label lblMessagesWaiting;
    @FXML
    private Label lblTicketsOpen;
    @FXML
    private ComboBox cbBuilding;
    @FXML
    private TextField txtRoomNum;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextArea txtProblemDescription;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblTicketsOpen.setText(Main.activeTicketCount);
        
        cbBuilding.getItems().addAll("CLC","District Office","Kaposia","Lincoln Center","Secondary");
        
    }    

    

    @FXML
    private void CollapseWidget(MouseEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetCollapsed.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - (325));
            window.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - (65));
            
            window.setScene(scene);
            window.show();
    }

    @FXML
    private void gotoWidgetExpanded(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetExpanded.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - (325));
            window.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - (125));
            window.setScene(scene);
            window.show();
    }

    @FXML
    private void openNewTicket(ActionEvent event) throws IOException {
        //System.out.println("New ticket created");
        System.out.println(cbBuilding.getValue().toString());
        
        String building = cbBuilding.getValue().toString();
        
        String room=txtRoomNum.getText();
        System.out.println(room);
        String phone=txtPhone.getText();
        System.out.println(phone);
        String description=txtProblemDescription.getText();
        communicate.createTicket(building, room, phone, description);
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ViewTickets.fxml")); // loads main fxml class
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - (325));
        window.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - (425));
        window.setScene(scene);
        window.show();
    }
    
}
