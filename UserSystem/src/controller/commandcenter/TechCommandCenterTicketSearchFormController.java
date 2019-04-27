/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commandcenter;

import application.Internal;
import application.Main;
import static application.Main.xOffset;
import static application.Main.yOffset;
import application.Ticket;
import application.communicate;
import application.setWidgetPosition;
import application.user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel
 */
public class TechCommandCenterTicketSearchFormController implements Initializable {

    @FXML
    private ComboBox cbTech;
    @FXML
    private ComboBox cbEndUser;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private ListView lvTicketList;
    @FXML
    private AnchorPane apScene;
    
    private static ArrayList<user> listOfAdmins= new ArrayList();
    private static ArrayList<user> listOfUsers= new ArrayList();
    private static ArrayList<Ticket> listOfTickets = new ArrayList();
    final ObservableList<String> ticketListItems = FXCollections.observableArrayList();
    
    private static Integer cbTechSelected = 0;
    private static Integer cbEndUserSelected = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        //cbTech.getItems().clear();
        //cbEndUser.getItems().clear();
        listOfAdmins.clear();
        listOfUsers.clear();
        listOfTickets.clear();
        
        //cbTech.getSelectionModel().selectLast();
        //System.out.println("cbTech index 1 is: "+cbTech.getSelectionModel().getSelectedIndex());
    
        ////////       Populate Admin List      ////////
        listOfAdmins.add(new user());
        listOfAdmins.get(0).setuName("Unassigned");
        listOfAdmins.get(0).setfName("Unassigned");
        listOfAdmins.get(0).setlName("");
    
        try {
            listOfAdmins.addAll(communicate.getListOfAdmins());
            for(int i=0; i<listOfAdmins.size();i++){
                cbTech.getItems().add(listOfAdmins.get(i).getfName()+" "+listOfAdmins.get(i).getlName());
                cbTech.getSelectionModel().select(0);
            }
        }
        catch (IOException ex) {
            Logger.getLogger(TechCommandCenterAssignTicketFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        ////////     Populate End User List     ////////
        try {
            listOfUsers.addAll(communicate.getListOfAllUsers());
            for(int i=0; i<listOfUsers.size();i++){
            
                cbEndUser.getItems().add(listOfUsers.get(i).getfName()+" "+listOfUsers.get(i).getlName());
            }    
        } catch (IOException ex) {
            Logger.getLogger(TechCommandCenterAssignTicketFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void search(MouseEvent event) throws IOException {
        ticketListItems.clear();
        String techUN="%";
        String userUN="%";
        String fromDate="0";
        String toDate="3019-12-31 23:59:59";
        
        if(!cbTech.getSelectionModel().isEmpty()){
            //System.out.println("cbTech not empty");
            techUN=listOfAdmins.get(cbTech.getSelectionModel().getSelectedIndex()).getuName();
            cbTechSelected=cbTech.getSelectionModel().getSelectedIndex();
        }
        if(!cbEndUser.getSelectionModel().isEmpty()&& cbEndUser.getSelectionModel().getSelectedIndex()!=0){
            //System.out.println("cbEndUser not empty");
            //System.out.println("123"+cbEndUser.getSelectionModel().getSelectedItem().toString()+"456");
            userUN=listOfUsers.get(cbEndUser.getSelectionModel().getSelectedIndex()).getuName();
            cbEndUserSelected=cbEndUser.getSelectionModel().getSelectedIndex();
        }
        System.out.println("Date box: "+dateFrom.getValue());
        if(dateFrom.getValue()!=null){
            fromDate=dateFrom.getValue().toString()+" 00:00:00";
            System.out.println("From date: "+fromDate);
        }
        if(dateTo.getValue()!=null){
        toDate=dateTo.getValue().toString()+" 00:00:00";
        }
        
        listOfTickets=communicate.searchTickets(techUN,userUN,fromDate,toDate);
        
        lvTicketList.setItems(Internal.ticketArrayListToObservableList(listOfTickets));
        
    }
    @FXML
    private void gotoTicketDetails(MouseEvent event) throws IOException {
        
        TechCommandCenterTicketSearchFormDetailsController.fromSearchForm=true;
        TechCommandCenterTicketSearchFormDetailsController.cbTechSelection=cbTechSelected;
        TechCommandCenterTicketSearchFormDetailsController.cbEndUserSelection=cbEndUserSelected;
        TechCommandCenterTicketSearchFormDetailsController.lvTicketListSelection=lvTicketList.getSelectionModel().getSelectedIndex();
    
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/commandcenter/TechCommandCenterTicketSearchFormDetails.fxml")); // loads main fxml class
        Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();             
        double nextStageHeight = 400.0;
        window.setY(setWidgetPosition.setWidgetY(window.getY(), primaryScreenBounds, nextStageHeight));
        
        window.setScene(scene);
        window.show();
        
    }
    @FXML
    private void cancel(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    public static ArrayList<Object> getItems(){
    
        ArrayList<Object> array = new ArrayList();
        array.add(listOfAdmins);
        array.add(listOfUsers);
        array.add(listOfTickets);
        
        return array;
        
    }
    
}
