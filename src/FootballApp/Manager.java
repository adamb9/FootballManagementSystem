package FootballApp;

import javax.persistence.*;

/* @author: Adam Baldwin R00176025
 * @version 1.0
 */

@Entity
@DiscriminatorValue("Manager")

public class Manager extends Person {
	private String DOB;
	private int rating;
	
	
	private Team team;

	public Manager(Name name, String phone, String email, String DOB, int rating) {
		super(name, phone, email);
		this.DOB = DOB;
		this.rating = rating;
	}
	
	public Manager() {
		
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getNameString() {
		return getName().getFirstName() + " " + getName().getMiddleName() + " " + getName().getLastName();
	}

	public String toString() {
		return getName() + "\t\tRating:" + rating;
	}

	public String simpleString() {
		return getName().getFirstName() + " " + getName().getMiddleName() + " " + getName().getLastName() + "\n\tPhone:"
				+ getPhone() + "\n\tEmail:" + getEmail() + "\n\tDOB:" + DOB + "\n\tRating:" + rating;
	}

	public void print() {
		System.out.println(toString());
	}

}