package FootballApp.service;
/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
import javax.persistence.*;

import FootballApp.Player;

public class UpdatePlayer {
	
	public void ChangeAttributes(Player player, String email, int goals) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );
	    
	    //We set the goals and email of the selected player to the new input values
	    player.setGoals(goals);
	    player.setEmail(email);
	    
	    //We begin a new transaction and merge this updated player object to the database
	    //This merge function means that there is no duplication of objects and that the old player object is overwritten.
	    em.getTransaction().begin();
	    em.merge(player);
	    em.getTransaction().commit();
	    em.close();
	}
}
