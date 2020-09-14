package FootballApp;

import javax.persistence.*;

/* @author: Adam Baldwin R00176025
 * @version 1.0
 */

@Embeddable

public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Name() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		return firstName + " " + middleName + " " + lastName;
	}

	public void print() {
		System.out.println(toString());
	}

}
