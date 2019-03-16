package application; // Application Package

import java.util.List; // Importationg of the List Java Class

// Created By: Mohamed Mohamed

 // Our listofTickets class
public class listOfTickets {

  //Variables for First, Second and Third ticket
	public String firstTicket=null;
	public String secondTicket=null;
	public String thirdTicket=null;
	

	// Default Constructor for the listOfTickets
	public listOfTickets(){
    
	}
	
	 //listOfTickets constructor that takes in the first, second and third ticket
	public listOfTickets(String firstTicket, String secondTicket, String thirdTicket) {
		this.firstTicket = firstTicket;
		this.secondTicket = secondTicket;
		this.thirdTicket = thirdTicket;
	}
	
	// Setters
	public void setFirstTicket(String firsTicket) {
		this.firstTicket = firsTicket;
	}

	public void setSecondTicket(String secondTicket) {
		this.secondTicket = secondTicket;
	}
	

	public void setThirdTicket(String thirdTicket) {
		this.thirdTicket = thirdTicket;
	}


	// Getters
	public String getFirstTicket() {
		return firstTicket;
	}

	public String getSecondTicket() {
		return secondTicket;
	}
	
	public String getThirdTicket() {
		return thirdTicket;
	}
	
	// listOfTickets that inherits from the communicate class.

	public List<listOfTickets> getAllActiveTickets() {
		return getAllActiveTickets();
	}
	
}
