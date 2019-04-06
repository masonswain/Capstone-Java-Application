package application; // Application package
	
import java.util.ArrayList; // ArrayList class.
import javafx.application.Application; // Application class
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader; // FXMLLoader class
import javafx.stage.Stage; // Stage class
import javafx.scene.Parent; // Parent class
import javafx.scene.Scene; // Scene class.
import javafx.stage.StageStyle;//StageStyle class.
import javafx.scene.input.MouseEvent;// MouseEvent class.
import javafx.event.EventHandler;// EventHandler class.

// Edited By: Mohamed Mohamed

public class Main extends Application { // Main class extends Application
    // Variables
    public static user currentUser;
    public static ArrayList<Ticket> ticketList;
    public static ArrayList<Ticket> unassignedTicketList;
    public static String activeTicketCount="0";
    public static String unassignedTicketCount="0";
    public static String unreadMessageCount="0";
    public static String ticketID=null;
    public static Ticket ticket=null;
        
    public static double xOffset;
    public static double yOffset;

     //start method
    
    @Override
       public void start(Stage primaryStage) throws Exception {
    	 
 	Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")); // loads login fxml file
 
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
        
          // User Screen is able to be grabbed by Mouse.
           
        	// Handle Method
        	
           @Override
           public void handle(MouseEvent event) {
               xOffset = event.getSceneX();
               yOffset = event.getSceneY();
           } 
        });
        
        // User Screen is able to moved by Mouse.
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               primaryStage.setX(event.getScreenX() - xOffset);
               primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        
        Scene scene = new Scene(root); // Creates a scene object.
       
        primaryStage.setScene(scene); // calls scene.
        
        primaryStage.show(); // calls show.
        
     }
      // Our Main Method in Java
       public static void main(String[] args) { // Launches the User login system Application
		launch(args);
	}
        
}
