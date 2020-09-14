package FootballApp.service;
/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
import java.util.List;

import javax.persistence.*;

import FootballApp.Player;
import FootballApp.Team;

public class GetPlayer {
	public List<Player> GetAllPlayers() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );

	    //We create a new query to get all players from the Person table in the database
	    //We execute this query and store the results in a list of players
	    Query query = em.createQuery("Select p from Person p where Type(p) = Player");
	   	List<Player> resultList = query.getResultList();
	   
	    em.close();
	    //We return the list of players
	    return resultList;
	}
	
}
