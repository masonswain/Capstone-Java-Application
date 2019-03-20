/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        System.out.println("New ticket created");
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
