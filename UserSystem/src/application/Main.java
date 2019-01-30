package application; // Application package
	
import javafx.application.Application; // Application class

import javafx.fxml.FXMLLoader; // FXMLLoader class

import javafx.stage.Stage; // Stage class

import javafx.scene.Parent; // Parent class

import javafx.scene.Scene; // Scene class.


public class Main extends Application { // Main class extends Application
	
	@Override
	public void start(Stage primaryStage) { // Start method
		
		// Using try-catch block to check login system for Javafx
		try {
			
		    Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml")); // loads login fxml file
		    
		    Scene scene = new Scene(root,400,400);
		    
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
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
