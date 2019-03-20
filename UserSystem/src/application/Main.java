package application; // Application package
	

import javafx.application.Application; // Application class
import javafx.fxml.FXMLLoader; // FXMLLoader class
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage; // Stage class
import javafx.scene.Parent; // Parent class
import javafx.scene.Scene; // Scene class.
import javafx.stage.Screen;
import javafx.stage.StageStyle;


public class Main extends Application { // Main class extends Application

    
    @Override
    public void start(Stage primaryStage) { // Start method
	
        
	
        // Using try-catch block to check login system for Javafx
	try {
			
	    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")); // loads login fxml file
            
            Scene scene = new Scene(root);
		    
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
            primaryStage.initStyle(StageStyle.UNDECORATED);
	    primaryStage.setScene(scene);
			
	    primaryStage.show();
		    
            // End of try-catch block
		    
            } catch(Exception e) {
		
            e.printStackTrace();
            }
	}
	
	public static void main(String[] args) { // Launches the User login system Application
		launch(args);
	}
        
}
