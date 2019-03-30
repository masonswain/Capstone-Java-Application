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

    /**
     * @return the ticketID
     */
    public String getTicketID() {
        return ticketID;
    }

    /**
     * @param ticketID the ticketID to set
     */
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    /**
     * @return the ticketTitle
     */
    public String getTicketTitle() {
        return ticketTitle;
    }

    /**
     * @param ticketTitle the ticketTitle to set
     */
    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    /**
     * @return the techUN
     */
    public String getTechUN() {
        return techUN;
    }

    /**
     * @param techUN the techUN to set
     */
    public void setTechUN(String techUN) {
        this.techUN = techUN;
    }

    /**
     * @return the userUN
     */
    public String getUserUN() {
        return userUN;
    }

    /**
     * @param userUN the userUN to set
     */
    public void setUserUN(String userUN) {
        this.userUN = userUN;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * @param building the building to set
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * @return the room
     */
    public String getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
