/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author jknutson
 */
public class Note {
    
    private String note_ID;
    private String ticket_ID;
    private String owner_UN;
    private String note;

    
    public Note(String note_ID,String ticket_ID,String owner_UN, String note){
    
        this.note_ID=note_ID;
        this.ticket_ID=ticket_ID;
        this.owner_UN=owner_UN;
        this.note=note;
        
    }
    /**
     * @return the note_ID
     */
    public String getNote_ID() {
        return note_ID;
    }

    /**
     * @param note_ID the note_ID to set
     */
    public void setNote_ID(String note_ID) {
        this.note_ID = note_ID;
    }

    /**
     * @return the ticket_ID
     */
    public String getTicket_ID() {
        return ticket_ID;
    }

    /**
     * @param ticket_ID the ticket_ID to set
     */
    public void setTicket_ID(String ticket_ID) {
        this.ticket_ID = ticket_ID;
    }

    /**
     * @return the owner_UN
     */
    public String getOwner_UN() {
        return owner_UN;
    }

    /**
     * @param owner_UN the owner_UN to set
     */
    public void setOwner_UN(String owner_UN) {
        this.owner_UN = owner_UN;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
    
}
