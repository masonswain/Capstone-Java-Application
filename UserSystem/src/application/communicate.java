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
import javafx.scene.paint.Color;

public class communicate {
    

    /////  USER  /////
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
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+response.toString());
            
            String serverResponse=response.toString();

            //Debugging Line - output the location of the comma or -1 if not found
            //System.out.println("Comma Point: "+commaPt);

            if(!serverResponse.contains("Authentication Failed")){
    
            
            //Find fName information
            startSub = serverResponse.indexOf("fName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            fName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("First Name: "+fName);
            
            //Find lName information
            startSub = serverResponse.indexOf("lName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            lName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("Last Name: "+lName);
            
            //Find isAdmin information
            startSub = serverResponse.indexOf("isAdmin: ",startSub)+9;
            endSub = serverResponse.indexOf("<br/>",startSub);
            isAdmin = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("Admin: "+isAdmin);
            
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
    
    public static user getUserByUN(String uName) throws IOException{
    
        String inputFromURL = null;
        String fName;
        String lName;
        String isAdmin="U";
            
        int startSub=0;
        int endSub=0;
            
        user user = new user();
            
        //Reference to Sending GET Request and returning data
        //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
        //Example GET/POST Request URL
        String url = "http://csc450.joelknutson.net/java/return-user-by-username.php";
            
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
           
        String urlParameters= "un="+uName;
            
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
            
        int responseCode = conn.getResponseCode();
	//System.out.println("\nSending 'POST' request to URL : " + url);
	//System.out.println("Post parameters : " + urlParameters);
	//System.out.println("Response Code : " + responseCode);
            
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
        }
        
        in.close();
            
        //Debugging Line - server response text (this is the string that is parsed
        //System.out.println("Server Response: "+response.toString());
            
        String serverResponse=response.toString();

        //Debugging Line - output the location of the comma or -1 if not found
        //System.out.println("Comma Point: "+commaPt);

        if(!serverResponse.contains("No User Found")){
    
            
            //Find fName information
            startSub = serverResponse.indexOf("fName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            fName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("First Name: "+fName);

            //Find lName information
            startSub = serverResponse.indexOf("lName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            lName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("Last Name: "+lName);

            //Find isAdmin information
            startSub = serverResponse.indexOf("isAdmin: ",startSub)+9;
            endSub = serverResponse.indexOf("<br/>",startSub);
            isAdmin = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("Admin: "+isAdmin);

            //update user object
            user.fName = fName;
            user.lName = lName;
            user.uName = uName;
            user.isAdmin = isAdmin.equalsIgnoreCase("Y");
            user.authenticated = (fName!=null);

            //Debugging Line - output object variables to prove object creation and variable assignment
            //System.out.println("Hello "+loggedInUser.getfName()+" "+loggedInUser.getlName());

        }else{
           System.out.println("No User Found");
        }
            
        return user;
    }
    
    public static ArrayList<user> getListOfAdmins() throws IOException{
    
        ArrayList<user> adminList = new ArrayList();
        
        String inputFromURL = null;
        String fName;
        String lName;
        String uName;
        int adminCount=0;
        int fromIndex=0;
            
        int startSub=0;
        int endSub=0;
            
        user user = new user();
            
        String url = "http://csc450.joelknutson.net/java/return-all-admins.php";
            
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
            
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        //wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
            
        int responseCode = conn.getResponseCode();
	//System.out.println("\nSending 'POST' request to URL : " + url);
	//System.out.println("Post parameters : " + urlParameters);
	//System.out.println("Response Code : " + responseCode);
            
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
        }
        
        in.close();
            
        //Debugging Line - server response text (this is the string that is parsed
        //System.out.println("Server Response: "+response.toString());
            
        String serverResponse=response.toString();

        //Debugging Line - output the location of the comma or -1 if not found
        //System.out.println("Comma Point: "+commaPt);

        if(!serverResponse.contains("No User Found")){
    
            while((fromIndex = serverResponse.indexOf("fName: ", fromIndex))!= -1){
            adminCount++;
            fromIndex++;
            }
            
            //Create and add Tickets list            
            for(int i=0; i < adminCount; i++){
            //Find fName information
            startSub = serverResponse.indexOf("fName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            fName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("First Name: "+fName);

            //Find lName information
            startSub = serverResponse.indexOf("lName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            lName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("Last Name: "+lName);

            //Find isAdmin information
            startSub = serverResponse.indexOf("uName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            uName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("Username: "+uName);

            //update user object
            user.fName = fName;
            user.lName = lName;
            user.uName = uName;
            user.isAdmin = uName.equalsIgnoreCase("Y");
            user.authenticated = (fName!=null);

            adminList.add(new user(fName, lName, uName, true, false));
            //adminList.add(user);
            //Debugging Line - output object variables to prove object creation and variable assignment
            //System.out.println("Hello "+loggedInUser.getfName()+" "+loggedInUser.getlName());
            }
        }else{
           System.out.println("No User Found");
        }
        
        return adminList;
    }
    
    public static ArrayList<user> getListOfAllUsers() throws IOException{
    
        ArrayList<user> userList = new ArrayList();
        
        String inputFromURL = null;
        String fName;
        String lName;
        String uName;
        int adminCount=0;
        int fromIndex=0;
            
        int startSub=0;
        int endSub=0;
            
        user user = new user();
            
        String url = "http://csc450.joelknutson.net/java/return-all-users.php";
            
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
            
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        //wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
            
        int responseCode = conn.getResponseCode();
	//System.out.println("\nSending 'POST' request to URL : " + url);
	//System.out.println("Post parameters : " + urlParameters);
	//System.out.println("Response Code : " + responseCode);
            
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
        }
        
        in.close();
            
        //Debugging Line - server response text (this is the string that is parsed
        //System.out.println("Server Response: "+response.toString());
            
        String serverResponse=response.toString();

        //Debugging Line - output the location of the comma or -1 if not found
        //System.out.println("Comma Point: "+commaPt);

        if(!serverResponse.contains("No User Found")){
    
            while((fromIndex = serverResponse.indexOf("fName: ", fromIndex))!= -1){
            adminCount++;
            fromIndex++;
            }
            
            //Create and add Tickets list            
            for(int i=0; i < adminCount; i++){
            //Find fName information
            startSub = serverResponse.indexOf("fName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            fName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("First Name: "+fName);

            //Find lName information
            startSub = serverResponse.indexOf("lName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            lName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("Last Name: "+lName);

            //Find isAdmin information
            startSub = serverResponse.indexOf("uName: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            uName = serverResponse.substring(startSub,endSub);
            //Debug Line
            //System.out.println("Username: "+uName);

            //update user object
            user.fName = fName;
            user.lName = lName;
            user.uName = uName;
            user.isAdmin = uName.equalsIgnoreCase("Y");
            user.authenticated = (fName!=null);

            userList.add(new user(fName, lName, uName, true, false));
            //adminList.add(user);
            //Debugging Line - output object variables to prove object creation and variable assignment
            //System.out.println("Hello "+loggedInUser.getfName()+" "+loggedInUser.getlName());
            }
        }else{
           System.out.println("No User Found");
        }
        
        return userList;
    }
    
    public static boolean createNewUser(String fName, String lName, String uName,String authPW, String isAdmin) throws IOException{
    
        boolean result = false;
        
        String inputFromURL = null;            
            
            //Reference to Sending GET Request and returning data
            //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
            //Example GET/POST Request URL
            String url = "http://csc450.joelknutson.net/java/create-user.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
           
            String urlParameters= "fname="+fName+"&lname="+lName+"&uname="+uName+"&authpw="+authPW+"&isAdmin="+isAdmin;
            
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = conn.getResponseCode();
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+response.toString());
            
            int success=response.toString().indexOf("New user: "+fName+" "+lName+" created successfully");
            
            //Debugging Line - output the location of the comma or -1 if not found
            //System.out.println("Comma Point: "+commaPt);
            if(success>-1){
                result=true; 
            }
        
        return result;
    }
    
    /////  TICKET  /////
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
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+serverResponse);
            
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
    
    //
    public static ArrayList<Ticket> updateAllActiveTechTickets(String uName) throws IOException {	
        
        /////////////    VARIABLES    /////////////////
        ArrayList<Ticket> ticketList = new ArrayList();
        String inputFromURL = null;
        String serverResponse;
        int ticketCount=0;
        int fromIndex=0;
        
        
        
        //////////////    HTTP COMMUNICATION    ////////////////////
            String url = "http://csc450.joelknutson.net/java/return-tech-tickets.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            String urlParameters= "techUN="+uName+"&status=active";
            
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = conn.getResponseCode();
            
            //Debugging Log Info
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+serverResponse);
            
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
    
    //
    public static void updateMainUnassignedTicketsList() throws IOException {	
        
        String uName="unassigned";
        /////////////    VARIABLES    /////////////////
        ArrayList<Ticket> unassignedTicketList = new ArrayList();
        String inputFromURL = null;
        String serverResponse;
        int unassignedTicketCount=0;
        int fromIndex=0;
        
        
        
        //////////////    HTTP COMMUNICATION    ////////////////////
            String url = "http://csc450.joelknutson.net/java/return-tech-tickets.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            String urlParameters= "techUN=unassigned"+"&status=active";
            
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = conn.getResponseCode();
            
            //Debugging Log Info
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+serverResponse);
            
            ///////////////////  PARSE SERVER RESPONSE    ///////////////////////
            
            //Count the number of tickets
            while((fromIndex = serverResponse.indexOf("Ticket ID: ", fromIndex))!= -1){
            unassignedTicketCount++;
            fromIndex++;
            }
            
            //Create and add Tickets list            
            for(int i=0, startSub=0, endSub=0; i < unassignedTicketCount; i++){
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
                
                
                unassignedTicketList.add(new Ticket(ticketID,ticketTitle, technicianUN, userUN, "Active", building, room, phone, dateTimeCreated));
                
            }
            //Debug Line
            //System.out.println("Ticket Count: "+ticketCount);
            Main.unassignedTicketCount=Integer.toString(unassignedTicketCount);
            Main.unassignedTicketList=unassignedTicketList;
            
    }
    
    //Uses server file "create-ticket.php"
    public static boolean createTicket(String building, String room, String phone, String description, String subject) throws IOException{
    
        boolean result = false;
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
	//System.out.println("\nSending 'POST' request to URL : " + url);
	//System.out.println("Post parameters : " + urlParameters);
	//System.out.println("Response Code : " + responseCode);
            
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
        }
        
        in.close();   
            
        //Debugging Line - server response text (this is the string that is parsed
        //System.out.println("Server Response: "+response.toString());
          
        int success=response.toString().indexOf("New record created successfully");
          
        //Debugging Line - output the location of the comma or -1 if not found
        //System.out.println("Comma Point: "+commaPt);
        if(success>-1){
            //System.out.println("Ticket Created");
            result=true;
        }
        else{
            //System.out.println("Request Failed");
        }
        
        return result;
    }
    
    //Uses server file "create-ticket.php"
    public static boolean createTicketForUser(String building, String room, String phone, String description, String subject, String endUser) throws IOException{
    
        boolean result = false;
        String inputFromURL = null;
        String userUN = endUser;
            
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
	//System.out.println("\nSending 'POST' request to URL : " + url);
	//System.out.println("Post parameters : " + urlParameters);
	//System.out.println("Response Code : " + responseCode);
            
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
        }
        
        in.close();   
            
        //Debugging Line - server response text (this is the string that is parsed
        //System.out.println("Server Response: "+response.toString());
          
        int success=response.toString().indexOf("New record created successfully");
          
        //Debugging Line - output the location of the comma or -1 if not found
        //System.out.println("Comma Point: "+commaPt);
        if(success>-1){
            //System.out.println("Ticket Created");
            result=true;
        }
        else{
            //System.out.println("Request Failed");
        }
        
        return result;
    }
    
    //Assigns ticket to given user
    public static boolean assignTicket(String techUN, String ticketID) throws IOException{

        String inputFromURL = null;
        String serverResponse;
        //UPDATE `TICKET` SET `TECH_UN`="jknutson" WHERE TICKET_ID=49
        //System.out.println("techUN: "+techUN);
        //System.out.println("ticketID: "+ticketID);
        //Reference to Sending GET Request and returning data
        //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
        //Example GET/POST Request URL
        String url = "http://csc450.joelknutson.net/java/assign-ticket.php";
           
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
           
        String urlParameters= "techUN="+techUN+"&ticketID="+ticketID;
            
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
          
        int responseCode = conn.getResponseCode();
	//System.out.println("\nSending 'POST' request to URL : " + url);
	//System.out.println("Post parameters : " + urlParameters);
	//System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+serverResponse);
    
        return true;
    }
 
    public static boolean closeTicket(Ticket ticket) throws IOException{
    
        String inputFromURL = null;
        String serverResponse;
        //UPDATE TICKET SET TECH_UN='jknutson', STATUS='closed' WHERE TICKET_ID=57
        //System.out.println("techUN: "+ticket.getTechUN());
        //System.out.println("ticketID: "+ticket.getTicketID());
        //Reference to Sending GET Request and returning data
        //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
        //Example GET/POST Request URL
        String url = "http://csc450.joelknutson.net/java/close-ticket.php";
           
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
           
        String urlParameters= "techUN="+ticket.getTechUN()+"&ticketID="+ticket.getTicketID();
            
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
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            System.out.println("Server Response: "+serverResponse);
    
        
        return true;
    }
    
    public static boolean openCloseTicketByID(Ticket ticket, Boolean active) throws IOException{
    
        
        String inputFromURL = null;
        String serverResponse;
        
        String status="Closed";
        if(active){status="Active";}
        
        //UPDATE TICKET SET TECH_UN='jknutson', STATUS='closed' WHERE TICKET_ID=57
        //System.out.println("techUN: "+ticket.getTechUN());
        //System.out.println("ticketID: "+ticket.getTicketID());
        //Reference to Sending GET Request and returning data
        //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
        //Example GET/POST Request URL
        String url = "http://csc450.joelknutson.net/java/open-close-ticket-by-id.php";
           
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
           
        String urlParameters= "status="+status+"&ticketID="+ticket.getTicketID();
            
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
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            System.out.println("Server Response: "+serverResponse);
    
        
        return true;
    }
    
    public static ArrayList<Ticket> searchTickets(String techUN, String userUN, String fromDate, String toDate)throws IOException{
    
        /////////////    VARIABLES    /////////////////
        ArrayList<Ticket> ticketList = new ArrayList();
        String inputFromURL = null;
        String serverResponse;
        int ticketCount=0;
        int fromIndex=0;
        
        
        
        //////////////    HTTP COMMUNICATION    ////////////////////
            String url = "http://csc450.joelknutson.net/java/return-search-tickets.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            String urlParameters= "techUN="+techUN+"&userUN="+userUN+"&fromDate="+fromDate+"&toDate="+toDate;
            
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
                
                //Find ticketTitle information
                startSub = serverResponse.indexOf("Status: ",startSub)+8;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String status = serverResponse.substring(startSub, endSub);
                
                 //Find endUser information
                startSub = serverResponse.indexOf("End User: ",startSub)+10;
                endSub = serverResponse.indexOf("<br/>", startSub);
                String ticketUserUN = serverResponse.substring(startSub, endSub);
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
                
                
                ticketList.add(new Ticket(ticketID,ticketTitle, technicianUN, ticketUserUN, status, building, room, phone, dateTimeCreated));
                
            }
            //Debug Line
            //System.out.println("Ticket Count: "+ticketCount);
            Main.activeTicketCount=Integer.toString(ticketCount);
            Main.ticketList=ticketList;
            
        
        return ticketList;
        
    }
    
    
    //////  NOTE  //////
    // Returns all the active list of Tickets associated with a tech username
    public static Note getCurrentTicketNote(String ticket_ID) throws IOException {	
        
        /////////////    VARIABLES    /////////////////
        ArrayList<Ticket> ticketList = new ArrayList();
        String inputFromURL = null;
        String serverResponse;
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
                        
            {
            int startSub=0, endSub=0;
            //Find ticketID information
            startSub = serverResponse.indexOf("OWNER: ",startSub)+7;
            endSub = serverResponse.indexOf("<br/>",startSub);
            owner_UN = serverResponse.substring(startSub,endSub);
            //Debug Line
            System.out.println("OWNER: "+owner_UN);
            
            //Find ticketTitle information
            startSub = serverResponse.indexOf("NOTE_ID: ",startSub)+9;
            endSub = serverResponse.indexOf("<br/>", startSub);
            note_ID = serverResponse.substring(startSub, endSub);
            //Debug Line
            System.out.println("Ticket Title: "+note_ID);
              
            //Find endUser information
            startSub = serverResponse.indexOf("NOTE: ",startSub)+6;
            endSub = serverResponse.indexOf("<br/>", startSub);
            note = serverResponse.substring(startSub, endSub);
            //Debug Line
            System.out.println("End User: "+note);
            }   
                
            
                 
        
        return new Note(note_ID, ticket_ID, owner_UN, note);
    }
 
    //Returns an array of notes ** OLD VERSION AT BOTTOM **
    public static ArrayList<Note> getAllCurrentTicketNotesNewestToOldest(String ticket_ID) throws IOException {	
        
        /////////////    VARIABLES    /////////////////
        ArrayList<Note> ticketNoteList = new ArrayList();
        String inputFromURL = null;
        String serverResponse;
        int ticketCount=0;
        int noteCount=0;
        int fromIndex=0;
        String owner_UN=null;
        String note_ID=null;
        String note=null;
        
        
        
        
        //////////////    HTTP COMMUNICATION    ////////////////////
            String url = "http://csc450.joelknutson.net/java/return-all-ticket-notes.php";
            
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
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+serverResponse);
            
            ///////////////////  PARSE SERVER RESPONSE    ///////////////////////
            
            //Count the number of notes            
            while((fromIndex = serverResponse.indexOf("OWNER: ", fromIndex))!= -1){
            noteCount++;
            fromIndex=fromIndex+7;
            }
            
            //System.out.println("Note Count: "+noteCount);
            
            //Create and add notes to list            
            for(int i=0, startSub=0, endSub=0; i < noteCount; i++){
                //Find ticketID information
                startSub = serverResponse.indexOf("OWNER: ",startSub)+7;
                endSub = serverResponse.indexOf("<br/>",startSub);
                owner_UN = serverResponse.substring(startSub,endSub);
                //Debug Line
                //System.out.println("OWNER: "+owner_UN);
                
                //Find ticketTitle information
                startSub = serverResponse.indexOf("NOTE_ID: ",startSub)+9;
                endSub = serverResponse.indexOf("<br/>", startSub);
                note_ID = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Ticket Title: "+note_ID);
                
                 //Find endUser information
                startSub = serverResponse.indexOf("NOTE: ",startSub)+6;
                endSub = serverResponse.indexOf("<br/>", startSub);
                note = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("End User: "+note);
                
                ticketNoteList.add(new Note(note_ID,ticket_ID,owner_UN,note));
                               
            }
                 
        
        return ticketNoteList;
    }

    public static ArrayList<Note> getAllCurrentTicketNotesOldestToNewest(String ticket_ID) throws IOException {	
        
        /////////////    VARIABLES    /////////////////
        ArrayList<Note> ticketNoteList = new ArrayList();
        String inputFromURL = null;
        String serverResponse;
        int ticketCount=0;
        int noteCount=0;
        int fromIndex=0;
        String owner_UN=null;
        String note_ID=null;
        String note=null;
        
        
        
        
        //////////////    HTTP COMMUNICATION    ////////////////////
            String url = "http://csc450.joelknutson.net/java/return-all-ticket-notes-asc.php";
            
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
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+serverResponse);
            
            ///////////////////  PARSE SERVER RESPONSE    ///////////////////////
            
            //Count the number of notes            
            while((fromIndex = serverResponse.indexOf("OWNER: ", fromIndex))!= -1){
            noteCount++;
            fromIndex=fromIndex+7;
            }
            
            //System.out.println("Note Count: "+noteCount);
            
            //Create and add notes to list            
            for(int i=0, startSub=0, endSub=0; i < noteCount; i++){
                //Find ticketID information
                startSub = serverResponse.indexOf("OWNER: ",startSub)+7;
                endSub = serverResponse.indexOf("<br/>",startSub);
                owner_UN = serverResponse.substring(startSub,endSub);
                //Debug Line
                //System.out.println("OWNER: "+owner_UN);
                
                //Find ticketTitle information
                startSub = serverResponse.indexOf("NOTE_ID: ",startSub)+9;
                endSub = serverResponse.indexOf("<br/>", startSub);
                note_ID = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("Ticket Title: "+note_ID);
                
                 //Find endUser information
                startSub = serverResponse.indexOf("NOTE: ",startSub)+6;
                endSub = serverResponse.indexOf("<br/>", startSub);
                note = serverResponse.substring(startSub, endSub);
                //Debug Line
                //System.out.println("End User: "+note);
                
                ticketNoteList.add(new Note(note_ID,ticket_ID,owner_UN,note));
                               
            }
                 
        
        return ticketNoteList;
    }

    
    //Creates a Ticket Note in DB
    public static void createTicketNote(String ticketID, String ownerUN, String ticketNote) throws MalformedURLException, ProtocolException, IOException{
        
        //create-note.php will create a note for the current user and clear the unread flag for other user notes
        //associated with the ticket ID
        String url = "http://csc450.joelknutson.net/java/create-note.php";
            
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
           
        String urlParameters= "ticketID="+ticketID+"&ownerUN="+ownerUN+"&ticketNote="+ticketNote;
            
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
          
        int responseCode = conn.getResponseCode();
	//System.out.println("\nSending 'POST' request to URL : " + url);
	//System.out.println("Post parameters : " + urlParameters);
	//System.out.println("Response Code : " + responseCode);
                    
    }
    
    public static void updateUnreadMessages(String uName) throws IOException{
    
        ArrayList<String> ticketIDList = new ArrayList();
        String authenticationKey="VZg9h08WW2LIweUpJIwBPFcp12JvUa0z";
        String listOfTicketIDs="";
        int unreadNoteCount = 0;
        String inputFromURL = null;
        String serverResponse;
        int fromIndex=0;
        
        
        for(int i=0; i<Main.ticketList.size();i++){
            listOfTicketIDs=listOfTicketIDs+"TICKET_ID="+Main.ticketList.get(i).getTicketID()+" ";
            if((i+1)<Main.ticketList.size()){
            listOfTicketIDs=listOfTicketIDs+"OR ";
            }
        }
        
        //Debug Line for String sent to PHP script
        //System.out.println(listOfTicketIDs);
        
        //////////////    HTTP COMMUNICATION    ////////////////////
            String url = "http://csc450.joelknutson.net/java/return-unread-ticket-notes.php";
            
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            String urlParameters= "ticketIDString="+listOfTicketIDs+"&uName="+Main.currentUser.getuName()+"&authKey="+authenticationKey;
            
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = conn.getResponseCode();
            
            //Debugging Log Info
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            while((inputFromURL = in.readLine())!=null){
            response.append(inputFromURL);
            }
            in.close();
            serverResponse=response.toString();
            //Debugging Line - server response text (this is the string that is parsed
            //System.out.println("Server Response: "+serverResponse);
            
            ///////////////////  PARSE SERVER RESPONSE    ///////////////////////
            
            //Count the number of tickets
            while((fromIndex = serverResponse.indexOf("NOTE_ID: ", fromIndex))!= -1){
            unreadNoteCount++;
            fromIndex++;
            }
            
            if(unreadNoteCount>0){
                //Create and add Tickets list            
                for(int i=0, startSub=0, endSub=0; i < unreadNoteCount; i++){
                    //Find ticketID information
                    startSub = serverResponse.indexOf("TICKET_ID: ",startSub)+11;
                    endSub = serverResponse.indexOf("<br/>",startSub);
                    String ticketID = serverResponse.substring(startSub,endSub);
                    //Debug Line
                    //System.out.println("Ticket ID: "+ticketID);

                    ticketIDList.add(ticketID);

                }
                Main.unreadTicketIDList=ticketIDList;
            } else{
                ticketIDList.add("0");
            }
            
            System.out.println("Unread Note Count: "+unreadNoteCount);
            Main.unreadMessageCount=Integer.toString(unreadNoteCount);
            Main.unreadTicketIDList=ticketIDList;
        
    }
       
}

