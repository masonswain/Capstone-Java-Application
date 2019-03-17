/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class communicate {
    
    private listOfTickets tickets = new listOfTickets();
   
    private listOfNotes notes = new listOfNotes();
    
    private openTickets ot = new openTickets();
    
/*
authenticate takes a username and password as a string and sends it 
via POST to a PHP script that determines whether the credentials are correct
*/
    
    public static user authenticate(String uname, String pw) throws IOException{
    
    String inputFromURL = null;
            String username = uname;
            String password = pw;
            String fName="";
            String lName="";
            boolean authenticated = false;
            int startPt=0;
            int endPt=0;
            int commaPt=0;
            user loggedInUser = new user(fName, lName);
            
            //Reference to Sending GET Request and returning data
            //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
            //Example GET/POST Request URL
            String url = "http://csc450.joelknutson.net/authenticate.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
           
            String urlParameters= "un="+username+"&authpw="+password;
            
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            
            //Debugging Line - server response text (this is the string that is parsed
            System.out.println("Server Response: "+response.toString());
            
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
            
            //update user object
            loggedInUser.fName = fName;
            loggedInUser.lName = lName;
            loggedInUser.authenticated = true;
            
            //Debugging Line - output object variables to prove object creation and variable assignment
            System.out.println("Hello "+loggedInUser.getfName()+" "+loggedInUser.getlName());
            
            return loggedInUser;
            
            }
            else{
                System.out.println("Authentication Failed");
            }
            
            return loggedInUser;
		
        
    }
    
    // listOfTickets method(Returns all the active list of Ticket) Created By : Mohamed Mohamed     
    public List<listOfTickets> getAllActiveTickets() {	
        return tickets.getAllActiveTickets();
    }
    
    // listOfNotes method(Returns all the active Notes) Created By : Mohamed Mohamed
    public List<listOfNotes> getAllActiveNotes() { 	
        return notes.getAllActiveNotes();
    }


    // openTicket method(Returns all the active Open Tickets) Created By : Mohamed Mohamed
    public List<openTickets> getAllActiveOpenTickets() {	
        return ot.getAllActiveOpenTickets();
    }
}


