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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class WidgetExpandedController implements Initializable {

    @FXML
    private Button btnViewTickets;
    @FXML
    private Button btnOpenTicket;
    @FXML
    private Polygon btnCollapse;
    @FXML
    private Label lblMessagesWaiting;
    @FXML
    private Label lblTicketsOpen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Update Ticket List
        try {
            Main.ticketList = communicate.updateAllActiveUserTickets(Main.currentUser.uName);
        } catch (IOException ex) {
            Logger.getLogger(WidgetExpandedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Update GUI count
        lblTicketsOpen.setText(Main.activeTicketCount);
        
    }    

    @FXML
    private void gotoViewTickets(ActionEvent event) throws IOException {
        
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
    private void gotoOpenTicket(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/OpenTicket.fxml")); // loads main fxml class
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
