package FootballApp.service;
/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
import javax.persistence.*;

import FootballApp.Name;
import FootballApp.Player;

public class CreatePlayer {
	
	public Player CreateNewPlayer(String firstName, String middleName, String lastName, String phoneNo, String email, boolean isGoalie) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );
	
	    //The Name object of the player is created and a Player object is created
		Name name1 = new Name(firstName, middleName, lastName);
		Player player1 = new Player(name1, phoneNo, email, isGoalie);
		
		//We begin a transaction to the database with the entity manager which we created
		//We persist the new  player to the database and return the new player.
	    em.getTransaction().begin();
	    em.persist(player1);
	    em.getTransaction().commit();
	    em.close();
	    
	    return player1;
    
	}
}
