package FootballApp;

import javax.persistence.*;

/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
@Entity
@DiscriminatorValue("Player")

public class Player extends Person {
	private boolean isGoalie;
	private int goals;
	private String email;

	public Player(Name name, String phone, String email, boolean isGoalie) {
		super(name, phone, email);
		this.isGoalie = isGoalie;
	}

	public Player() {

	}

	public boolean isGoalie() {
		return isGoalie;
	}

	public void setGoalie(boolean isGoalie) {
		this.isGoalie = isGoalie;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public String getNameString() {
		return getName().getFirstName() + " " + getName().getMiddleName() + " " + getName().getLastName();
	}
	
	public String toString() {
		return getName()  + "\tGoals: " + goals;
	}

	public String simpleString() {
		return getName().getFirstName() + "," + getName().getMiddleName() + "," + getName().getLastName() + ","
				+ getPhone() + "," + getEmail() + "," + isGoalie() + "," + goals;
	}

	public void print() {
		System.out.println(toString());
	}

}
