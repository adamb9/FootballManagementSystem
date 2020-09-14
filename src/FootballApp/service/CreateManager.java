package FootballApp.service;
/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
import javax.persistence.*;

import FootballApp.Name;
import FootballApp.Manager;

public class CreateManager {

	public Manager CreateNewManager(String firstName, String middleName, String lastName, String phone, String email, String DOB, int rating) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );

	    //The Name object of the manager is created and a Manager object is created
		Name name1 = new Name(firstName, middleName, lastName);
		Manager manager1 = new Manager(name1, phone, email, DOB, rating);
		
		//We begin a transaction to the database with the entity manager which we created
		//We persist the new  manager to the database and return the new manager.
	    em.getTransaction().begin();
	    em.persist(manager1);
	    em.getTransaction().commit();
	    em.close();
	    
	    return manager1;
	}
}
