/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

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
        if (ticketCount > 0) {
            return Color.YELLOW;
        }
        if (Integer.parseInt(Main.unreadMessageCount) > 0) {
            return Color.RED;
        } else {
            return Color.GREEN;
        }
    }
    
}
