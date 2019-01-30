package application; // our application package

import javafx.event.ActionEvent; // This is our ActionEvent class.

import javafx.fxml.FXML; // This is our FXML class.

import javafx.fxml.FXMLLoader; // This is our FXMLLoader class.

import javafx.scene.Parent; // This is our Parent class.

import javafx.scene.Scene; // This is our Scene class.

import javafx.scene.control.Label; // This is our Label class.

import javafx.scene.control.TextField; // This is our TextField class.

import javafx.stage.Stage; // This is our Stage class.

public class MainController { // Our MainController class.

	@FXML
	private Label lblAuthentication; // Our label Authentication Field
	
	@FXML
	private TextField UserNameTextField; // Our UserNameTextField
	
	@FXML
	private TextField PasswordTextField; // Our PasswordTextField
	
	public void Login(ActionEvent e) throws Exception {
		
		if(UserNameTextField.getText().equals("username") && PasswordTextField.getText().equals("password")) { // I just used username and password as and example of how a user can login to our system
			
			lblAuthentication.setText("Successful"); // Displays to user that login was successful
			
			Stage primaryStage = new Stage();
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml")); // loads main fxml class
			
		    Scene scene = new Scene(root,400,400);
		    
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    
			primaryStage.setScene(scene);
			
			primaryStage.show();
		
		} else {
			
			lblAuthentication.setText("Failed"); // Displays to user that login has failed

		}
		
	}
}
