package application; // our application package

import application.user;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
       
	
        // Login Method
	public void Login(ActionEvent e) throws Exception {
            
            String inputFromURL = null;
            String username = UserNameTextField.getText();
            String password = PasswordTextField.getText();
            String fName="";
            String lName="";
            boolean authenticated = false;
            int startPt=0;
            int endPt=0;
            int commaPt=0;
            
            //Reference to Sending GET Request and returning data
            //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
            //Example GET Request URL
            //http://csc450.joelknutson.net/authenticate.php?un=jknutson&authpw=12345
            String url = "http://csc450.joelknutson.net/authenticate.php?un="+username+"&authpw="+password;
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+response.toString());
            
            inputFromURL=response.toString();
            startPt=inputFromURL.indexOf("<body>")+6;
            endPt=inputFromURL.indexOf("</body>");
            commaPt=inputFromURL.indexOf(",", startPt);
            
            //Debugging Line - output the location of the comma or -1 if not found
            //System.out.println("Comma Point: "+commaPt);
            
            //Parse the string returned by the server as long as a comma it found
            if(commaPt != -1){
            authenticated=true;
            fName=inputFromURL.substring(startPt, commaPt);
            lName=inputFromURL.substring(commaPt+1, endPt);
            
            //Create user object
            user loggedInUser = new user(fName,lName);
            
            //Debugging Line - output object variables to prove object creation and variable assignment
            System.out.println("Welcome " + loggedInUser.getfName()+ " " + loggedInUser.getlName());
            }
            else{
                System.out.println("Authentication Failed");
            }
            
            
		if(authenticated) { 
			
			lblAuthentication.setText("Welcome " + fName + lName); // Displays to user that login was successful
			
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
