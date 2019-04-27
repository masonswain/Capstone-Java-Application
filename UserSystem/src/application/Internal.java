/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.widget.WidgetExpandedController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

/**
 *
 * @author Joel
 */
public class Internal {

    /////    /////
    //Returns the appropriate color of status light
    public static Color updateStatusLight() {
        int ticketCount = 0;
        if (Main.currentUser.isAdmin) {
            ticketCount = Integer.parseInt(Main.unassignedTicketCount);
        } else {
            ticketCount = Integer.parseInt(Main.activeTicketCount);
        }
        if (Integer.parseInt(Main.unreadMessageCount) > 0) {
            return Color.RED;
        }
        if (ticketCount > 0) {
            return Color.YELLOW;
        }
        else{
            return Color.GREEN;
        }
    }
    
    public static void intializeWidgetStatus(){
    if(Main.currentUser!=null) {
            //Update Ticket List
            try {
                Main.ticketList = communicate.updateAllActiveUserTickets(Main.currentUser.uName);
                communicate.updateUnreadMessages(Main.currentUser.getuName());
            } catch (IOException ex) {
                Logger.getLogger(WidgetExpandedController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    Main.statusLightColor=updateStatusLight();
    }
    
    public static ObservableList<String> ticketArrayListToObservableList(ArrayList<Ticket> arrayList){
    
        ObservableList<String> listItems = FXCollections.observableArrayList();
        
        for(int i=0; i<arrayList.size();i++){
            listItems.add("Subject: "+arrayList.get(i).getTicketTitle()+"\nCreated: "+arrayList.get(i).getDateTimeCreated()+"\nUser: "+arrayList.get(i).getUserUN()+"\nStatus: "+arrayList.get(i).getStatus());
        }
       
        return listItems;
    }
    
     public static String noteArrayListToTextAreaString(ArrayList<Note> arrayList){
    
        String taNotes="";
        
        for(int i=0; i<arrayList.size();i++){
            taNotes+=(arrayList.get(i).getNote_ID()+" - "+arrayList.get(i).getOwner_UN()+"\n"+arrayList.get(i).getNote()+"\n\n");
        }
       
        return taNotes;
    }
    
}
