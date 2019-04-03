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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class communicate {
    

    //authenticate takes a username and password as a string and sends it 
    //via POST to a PHP script that determines whether the credentials are correct
    //Uses server file "authenticate.php"
    public static user authenticate(String uName, String pw) throws IOException{
    
            String inputFromURL = null;
            String fName;
            String lName;
            String isAdmin="U";
            
            boolean authenticated = false;
            
            int startSub=0;
            int endSub=0;
            
            user loggedInUser = new user();
            
            //Reference to Sending GET Request and returning data
            //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
            //Example GET/POST Request URL
            String url = "http://csc450.joelknutson.net/java/authenticate.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
           
            String urlParameters= "un="+uName+"&authpw="+pw;
            
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
            
            String serverResponse=response.toString();

            //Debugging Line - output the location of the comma or -1 if not found
            //System.out.println("Comma Point: "+commaPt);

            if(!serverResponse.contains("Authentication Failed")){
    
            
            //Find fName information
            startSub = serverResponse.indexOf("fName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            fName = serverResponse.substring(startSub,endSub);
            //Debug Line
            System.out.println("First Name: "+fName);
            
            //Find lName information
            startSub = serverResponse.indexOf("lName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            lName = serverResponse.substring(startSub,endSub);
            //Debug Line
            System.out.println("Last Name: "+lName);
            
            //Find isAdmin information
            startSub = serverResponse.indexOf("isAdmin: ",startSub)+9;
            endSub = serverResponse.indexOf("<br/>",startSub);
            isAdmin = serverResponse.substring(startSub,endSub);
            //Debug Line
            System.out.println("Admin: "+isAdmin);
            
            //update user object
            loggedInUser.fName = fName;
            loggedInUser.lName = lName;
            loggedInUser.uName = uName;
            loggedInUser.isAdmin = isAdmin.equalsIgnoreCase("Y");
            loggedInUser.authenticated = (fName!=null);
            
            //Debugging Line - output object variables to prove object creation and variable assignment
            //System.out.println("Hello "+loggedInUser.getfName()+" "+loggedInUser.getlName());
            
            }
            else{
                System.out.println("Authentication Failed");
            }
            
            return loggedInUser;
        
    }
    
    // Returns all the active list of Tickets associated with a tech username
    //Uses server file "return-user-tickets.php"
    public static ArrayList<Ticket> updateAllActiveUserTickets(String uName) throws IOException {	
        
        /////////////    VARIABLES    /////////////////
        ArrayList<Ticket> ticketList = new ArrayList();
        String inputFromURL = null;
        String serverResponse;
        int ticketCount=0;
        int fromIndex=0;
        
        
        
        //////////////    HTTP COMMUNICATION    ////////////////////
            String url = "http://csc450.joelknutson.net/java/return-user-tickets.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            String urlParameters= "uName="+uName+"&status=active";
            
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = conn.getResponseCode();
            
            //Debugging Log Info
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            System.out.println("Server Response: "+serverResponse);
            
            ///////////////////  PARSE SERVER RESPONSE    ///////////////////////
            
            //Count the number of tickets
            while((fromIndex = serverResponse.indexOf("Ticket ID: ", fromIndex))!= -1){
            ticketCount++;
            fromIndex++;
            }
            
            //Create and add Tickets list            
            for(int i=0, startSub=0, endSub=0; i < ticketCount; i++){
                //Find ticketID information
                startSub = serverResponse.indexOf("Ticket ID: ",startSub)+11;
                endSub = serverResponse.indexOf("<br/>",startSub);
                String ticketID = serverResponse.substring(startSub,endSub);
                //Debug Line
                //System.out.println("Ticket ID: "+ticketID);
                
                //Find ticketTitle information
                startSub = serverResponse.indexOf("Title: ",startSub)+7;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String ticketTitle = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Ticket Title: "+ticketTitle);
                
                 //Find endUser information
                startSub = serverResponse.indexOf("End User: ",startSub)+10;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String userUN = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("End User: "+userUN);
                
                //Find technicianUser information
                startSub = serverResponse.indexOf("Tech: ",startSub)+6;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String technicianUN = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Tech User: "+technicianUN);
                
                //Find building information
                startSub = serverResponse.indexOf("Building: ",startSub)+10;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String building = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Building: "+building);
                
                //Find room information
                startSub = serverResponse.indexOf("Room: ",startSub)+6;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String room = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Room: "+room);
                
                //Find phone information
                startSub = serverResponse.indexOf("Phone: ",startSub)+7;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String phone = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Phone: "+phone);
                
                //Find DATETIME_CREATED information
                startSub = serverResponse.indexOf("DATETIME_CREATED: ",startSub)+18;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String dateTimeCreated = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Created: "+dateTimeCreated);
                
                //Find DATETIME_SOLVED information
                startSub = serverResponse.indexOf("DATETIME_SOLVED: ",startSub)+17;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String dateTimeSolved = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Solved: "+dateTimeSolved);
                
                
                ticketList.add(new Ticket(ticketID,ticketTitle, technicianUN, userUN, "Active", building, room, phone, dateTimeCreated));
                
            }
            //Debug Line
            //System.out.println("Ticket Count: "+ticketCount);
            Main.activeTicketCount=Integer.toString(ticketCount);
            Main.ticketList=ticketList;
            
        
        return ticketList;
    }
    
    //Uses server file "create-ticket.php"
    public static void createTicket(String building, String room, String phone, String description, String subject) throws IOException{
    
            String inputFromURL = null;
            String userUN = Main.currentUser.getuName();
            
            
            //Reference to Sending GET Request and returning data
            //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
            //Example GET/POST Request URL
            String url = "http://csc450.joelknutson.net/java/create-ticket.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
           
            String urlParameters= "ticketTitle="+subject+"&techUN=unassigned"+"&userUN="+userUN+"&status=Active"+"&building="+building+"&room="+room+"&phone="+phone+"&description="+description;
            
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
            
            int success=response.toString().indexOf("New record created successfully");
            
            //Debugging Line - output the location of the comma or -1 if not found
            //System.out.println("Comma Point: "+commaPt);
            if(success>-1){
                System.out.println("Ticket Created"); 
            }
            else{
                System.out.println("Request Failed");
            }
                    
    }
    
    // Returns all the active list of Tickets associated with a tech username
    public static Note getCurrentTicketNote(String ticket_ID) throws IOException {	
        
        /////////////    VARIABLES    /////////////////
        ArrayList<Ticket> ticketList = new ArrayList();
        String inputFromURL = null;
        String serverResponse;
        int ticketCount=0;
        int fromIndex=0;
        String owner_UN=null;
        String note_ID=null;
        String note=null;
        
        
        
        
        //////////////    HTTP COMMUNICATION    ////////////////////
            String url = "http://csc450.joelknutson.net/java/return-ticket-notes.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            String urlParameters= "ticketID="+ticket_ID;
            
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = conn.getResponseCode();
            
            //Debugging Log Info
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            System.out.println("Server Response: "+serverResponse);
            
            ///////////////////  PARSE SERVER RESPONSE    ///////////////////////
                        
            //Create and add Tickets list            
            for(int i=0, startSub=0, endSub=0; i < ticketCount; i++){
                //Find ticketID information
                startSub = serverResponse.indexOf("OWNER: ",startSub)+11;
                endSub = serverResponse.indexOf("<br/>",startSub);
                owner_UN = serverResponse.substring(startSub,endSub);
                //Debug Line
                System.out.println("OWNER: "+owner_UN);
                
                //Find ticketTitle information
                startSub = serverResponse.indexOf("NOTE_ID: ",startSub)+7;
                endSub = serverResponse.indexOf("<br/>", startSub);
                note_ID = serverResponse.substring(startSub, endSub);
                //Debug Line
                System.out.println("Ticket Title: "+note_ID);
                
                 //Find endUser information
                startSub = serverResponse.indexOf("NOTE: ",startSub)+10;
                endSub = serverResponse.indexOf("<br/>", startSub);
                note = serverResponse.substring(startSub, endSub);
                //Debug Line
                System.out.println("End User: "+note);
                
                
            }
                 
        
        return new Note(note_ID, ticket_ID, owner_UN, note);
    }
}


