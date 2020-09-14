package FootballApp.service;
/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
import java.util.List;

import javax.persistence.*;

import FootballApp.Manager;


public class GetManager {
	
	public List<Manager> GetAllManagers() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );

	    //We create a new query to get all managers from the Person table in the database
	    //We execute this query and store the results in a list of managers
	    Query query = em.createQuery("Select p from Person p where Type(p) = Manager");
	   	List<Manager> resultList = query.getResultList();
	   
	    em.close();
	    //We return the list of managers
	    return resultList;
	}
	
	
}
