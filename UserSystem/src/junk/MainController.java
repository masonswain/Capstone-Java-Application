package junk; // our application package

import javafx.event.ActionEvent; // This is our ActionEvent class.
import javafx.fxml.FXML; // This is our FXML class.
import javafx.fxml.FXMLLoader; // This is our FXMLLoader class.
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent; // This is our Parent class.
import javafx.scene.Scene; // This is our Scene class.
import javafx.scene.control.Label; // This is our Label class.
import javafx.scene.control.TextField; // This is our TextField class.
import javafx.stage.Screen;
import javafx.stage.Stage;


public class MainController{        
        
        
        //@FXML 
        //private ListView ticketListView;
         /*
	
        public void Login(ActionEvent e) throws Exception {
                        
        communicate auth = new communicate();
        
    //////////////////////     Credential Entry Bypass     //////////////////////////    

        //comment this line out to bypass credential entry
        //user user1 = auth.authenticate(UserNameTextField.getText(),PasswordTextField.getText());
        
        //uncomment this line to bypass credential entry
        user user1 = communicate.authenticate("jknutson","12345");
        
    //////////////////////   End Credential Entry Bypass   ////////////////////////// 
        
        if(user1.authenticated) { 
		
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetExpanded.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //Source for screen position
            //https://coderanch.com/t/620036/java/Stage-corner-screen
            //Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            //window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - (125));
            //window.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - (350));
            window.setScene(scene);
            window.show();
		
            } else {
			
            lblAuthentication.setText("Failed"); // Displays to user that login has failed

            }
        }*/
        
///////////////////////////         Screen Transitions      /////////////////////////////
        
        /*
        public void CollapseWidget() throws Exception{
        
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetCollapsed.fxml")); // loads main fxml class
            Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Main.setScene(scene, 300, 40);
            
        }
        
        public void ExpandWidget() throws Exception{
        
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/WidgetExpanded.fxml")); // loads main fxml class
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    Main.setScene(scene,300, 100);
            
        }
        
        
        public void OpenTicket() throws Exception{
        
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/OpenTicket.fxml")); // loads main fxml class
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    Main.setScene(scene, 300, 400);
            
        }
                
        public void ViewTickets(ActionEvent e) throws Exception{
                                       
                    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/ViewTickets.fxml")); // loads main fxml class
                    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                 
                    window.setScene(scene);
                    window.show();
                    
                    System.out.println("before");
                    //viewTickets.setText("Hello");
                    System.out.println("after");
            
        }
        
        public void ViewTicketComment() throws Exception{
                                       
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/ViewTicketComment.fxml")); // loads main fxml class
                    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                 
                    Main.setScene(scene, 300, 400);
            
        }
        
        public void ReplyToTicket() throws Exception{
                                       
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/ReplyToTicket.fxml")); // loads main fxml class
                    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                 
                    Main.setScene(scene, 300, 400);
            
        }
        
        public void ViewTicketHistory() throws Exception{
                                       
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/ViewTicketHistory.fxml")); // loads main fxml class
                    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());                 
                    Main.setScene(scene, 300, 400);
            
        }
*/
///////////////////////////     End Screen Transitions      /////////////////////////////
        

	
}
