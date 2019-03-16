package application;

import java.util.List;

public class openTickets {

	// Variables for first, second and third open tickets.
	public String firstOpenTicket=null;
	public String secondOpenTicket=null;
	public String thirdOpenTicket=null;
	
	// Default Constructor for openTickets
	public openTickets() {
		
	}
	
	// OpenTickets Constructor with parameters of first, second and third open tickets
	public openTickets(String firstOpenTicket, String secondOpenTicket, String thirdOpenTicket) {
		this.firstOpenTicket = firstOpenTicket;
		this.secondOpenTicket = secondOpenTicket;
		this.thirdOpenTicket = thirdOpenTicket;
	}

	//Setters
	public void setFirstOpenTicket(String firstOpenTicket) {
		this.firstOpenTicket = firstOpenTicket;
	}
	
	public void setSecondOpenTicket(String secondOpenTicket) {
		this.secondOpenTicket = secondOpenTicket;
	}
	
	public void setThirdOpenTicket(String thirdOpenTicket) {
		this.thirdOpenTicket = thirdOpenTicket;
	}


	// Getters
	public String getFirstOpenTicket() {
		return firstOpenTicket;
	}

	public String getSecondOpenTicket() {
		return secondOpenTicket;
	}

	public String getThirdOpenTicket() {
		return thirdOpenTicket;
	}

      // OpenTicket Method that inherits from the communicate class.
	public List<openTickets> getAllActiveOpenTickets() {
		return getAllActiveOpenTickets();
	}
	
	

}
