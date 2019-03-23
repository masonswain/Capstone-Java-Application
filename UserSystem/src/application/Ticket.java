/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author Joel
 */
public class Ticket {
    
    private String ticketID, ticketTitle, techUN,userUN, status, building, room, phone;
    
    public Ticket(){
    
    }
    
    public Ticket(String ticketID,String ticketTitle,String techUN,String userUN,String status,String building,String room,String phone){
    
        this.ticketID = ticketID;
        this.ticketTitle = ticketTitle;
        this.techUN = techUN;
        this.userUN = userUN;
        this.status = status;
        this.building = building;
        this.room = room;
        this.phone = phone;
        
        
        
    }
}
