package model.data;

public class Player {
	private String FirstName;
	private String LastName;
	
	public Player() {
	
	}
	
	public Player(String FirstName, String LastName) {
		this.FirstName = new String(FirstName);
		this.LastName = new String(LastName);
	}
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
}
