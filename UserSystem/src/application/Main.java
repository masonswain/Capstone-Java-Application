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
	
    public static Stage window;
    Scene scene1, scene2;

    
    @Override
    public void start(Stage primaryStage) { // Start method
	
        window=primaryStage;
	
        // Using try-catch block to check login system for Javafx
	try {
			
	    Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml")); // loads login fxml file
            
            Scene scene = new Scene(root);
		    
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
            window.initStyle(StageStyle.UNDECORATED);
	    window.setScene(scene);
			
	    window.show();
		    
            // End of try-catch block
		    
            } catch(Exception e) {
		
            e.printStackTrace();
            }
	}
	
	public static void main(String[] args) { // Launches the User login system Application
		launch(args);
	}
        
    //This method is called to change the current scene
    public static void setScene(Scene newScene, int width, int height){
        //Source for screen position
        //https://coderanch.com/t/620036/java/Stage-corner-screen
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - (width+25));
        window.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - (height+50));
        window.setScene(newScene);
        window.show();
    }
}
