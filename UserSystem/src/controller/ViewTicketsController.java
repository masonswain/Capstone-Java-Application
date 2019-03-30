/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.Main;
import application.Ticket;
import application.communicate;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class ViewTicketsController implements Initializable {

    @FXML
    private Label viewTickets;
    @FXML
    private Button btnOpenTicket;
    @FXML
    private Button btnCancel;
    @FXML
    private Polygon btnCollapse;
    @FXML
    private Label lblTicketsOpen;
    @FXML
    private ListView tvTicketList;
    
    final ObservableList<String> listItems = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
           communicate.updateAllActiveUserTickets(Main.currentUser.uName);
        } catch (IOException ex) {
            Logger.getLogger(ViewTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblTicketsOpen.setText(Main.activeTicketCount);

        //Add strings to observable list
        for(int i=0; i<Integer.parseInt(Main.activeTicketCount);i++){
        listItems.add(Main.ticketList.get(i).getTicketTitle()+"  |  "+Main.ticketList.get(i).getUserUN());
        }
        
        //Add observable list to listview
        tvTicketList.setItems(listItems);
        
    }

    private void viewTicket() throws IOException{
            
    }

    @FXML
    private void ExpandWidget(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetExpanded.fxml")); 
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
    private void gotoOpenTicket(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/OpenTicket.fxml")); 
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
    private void gotoWidgetExpanded(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetExpanded.fxml")); 
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
    private void gotoWidgetCollapsed(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetCollapsed.fxml")); 
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
    private void gotoViewTicketComment(MouseEvent event) throws IOException {
            System.out.println(tvTicketList.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ViewTicketComment.fxml")); 
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
