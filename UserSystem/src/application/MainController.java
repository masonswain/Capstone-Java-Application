package application; // our application package

import javafx.event.ActionEvent; // This is our ActionEvent class.
import javafx.fxml.FXML; // This is our FXML class.
import javafx.fxml.FXMLLoader; // This is our FXMLLoader class.
import javafx.scene.Parent; // This is our Parent class.
import javafx.scene.Scene; // This is our Scene class.
import javafx.scene.control.Label; // This is our Label class.
import javafx.scene.control.TextField; // This is our TextField class.


public class MainController{ // Our MainController class.
	@FXML
	private Label lblAuthentication; // Our label Authentication Field
	
	@FXML
	private TextField UserNameTextField; // Our UserNameTextField
	
	@FXML
	private TextField PasswordTextField; // Our PasswordTextField
        
        @FXML
        public Label ticketList;
        
        @FXML 
        //private ListView ticketListView;
         
	
        public void Login(ActionEvent e) throws Exception {
                        
        communicate auth = new communicate();
        
    //////////////////////     Credential Entry Bypass     //////////////////////////    

        //comment this line out to bypass credential entry
        user user1 = auth.authenticate(UserNameTextField.getText(),PasswordTextField.getText());
        
        //uncomment this line to bypass credential entry
        //user user1 = communicate.authenticate("jknutson","12345");
        
    //////////////////////   End Credential Entry Bypass   ////////////////////////// 
        
        if(user1.authenticated) { 
			                                   
            ExpandWidget();
		
            } else {
			
            lblAuthentication.setText("Failed"); // Displays to user that login has failed

            }
        }
        
///////////////////////////         Screen Transitions      /////////////////////////////
        
        public void CollapseWidget() throws Exception{
        
            Parent root = FXMLLoader.load(getClass().getResource("/application/WidgetCollapsed.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Main.setScene(scene, 300, 40);
            
        }
        
        public void ExpandWidget() throws Exception{
        
                    Parent root = FXMLLoader.load(getClass().getResource("/application/WidgetExpanded.fxml")); // loads main fxml class
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    Main.setScene(scene,300, 100);
            
        }
        
        
        public void OpenTicket() throws Exception{
        
                    Parent root = FXMLLoader.load(getClass().getResource("/application/OpenTicket.fxml")); // loads main fxml class
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    Main.setScene(scene, 300, 400);
            
        }
                
        public void ViewTickets(ActionEvent e) throws Exception{
                                       
                    Parent root = FXMLLoader.load(getClass().getResource("/application/ViewTickets.fxml")); // loads main fxml class
                    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                 
                    Main.setScene(scene, 300, 400);
                    
                    System.out.println("before");
                    //ticketList.setText("Hello");
                    System.out.println("after");
            
        }
        
        public void ViewTicketComment() throws Exception{
                                       
                    Parent root = FXMLLoader.load(getClass().getResource("/application/ViewTicketComment.fxml")); // loads main fxml class
                    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                 
                    Main.setScene(scene, 300, 400);
            
        }
        
        public void ReplyToTicket() throws Exception{
                                       
                    Parent root = FXMLLoader.load(getClass().getResource("/application/ReplyToTicket.fxml")); // loads main fxml class
                    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                 
                    Main.setScene(scene, 300, 400);
            
        }
        
        public void ViewTicketHistory() throws Exception{
                                       
                    Parent root = FXMLLoader.load(getClass().getResource("/application/ViewTicketHistory.fxml")); // loads main fxml class
                    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                 
                    Main.setScene(scene, 300, 400);
            
        }
///////////////////////////     End Screen Transitions      /////////////////////////////
        

	
}
