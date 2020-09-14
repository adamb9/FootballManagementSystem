package FootballApp;

import javax.persistence.*;

/* @author: Adam Baldwin R00176025
 * @version 1.0
 */

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "PERSON_TYPE")

public class Person {
	@Embedded
	private Name name;
	@Id
	private String phone;
	private String email;

	public Person(Name name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public Person() {

	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return name + "\tPhone:" + phone + "\tEmail:" + email;
	}

	public void print() {
		System.out.println(toString());
	}

}
