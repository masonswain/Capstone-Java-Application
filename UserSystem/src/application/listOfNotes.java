package application;

import java.util.List; // Importation of the List Java Class


//Created By: Mohamed Mohamed

// listOfNotes class.
public class listOfNotes {

  // Variables for first, second and third note
	public String firstNote=null;
	public String secondNote=null;
	public String thirdNote=null;
	
	// Default Constructor for listOfNotes
	public listOfNotes() {
		
	}

	public listOfNotes(String firstNote, String secondNote, String thirdNote) {
		this.firstNote = firstNote;
		this.secondNote = secondNote;
		this.thirdNote = thirdNote;
	}
	
	//Setters

	public void setFirstNote(String firsNote) {
		this.firstNote = firsNote;
	}
	
	public void setSecondNote(String secondNote) {
		this.secondNote = secondNote;
	}
	
	public void setThirdNote(String thirdNote) {
		this.thirdNote = thirdNote;
	}

	//Getters
	public String getFirsNote() {
		return firstNote;
	}
	public String getSecondNote() {
		return secondNote;
	}

	public String getThirdNote() {
		return thirdNote;
	}

	// listOfNotes method that inherits from communicate class.
	public List<listOfNotes> getAllActiveNotes() {
		return getAllActiveNotes();
	}

}
