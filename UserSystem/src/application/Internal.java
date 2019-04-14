/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.widget.WidgetExpandedController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
}
