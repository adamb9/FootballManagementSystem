package FootballApp.service;
/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
import javax.persistence.*;

import FootballApp.Team;

public class CreateTeam {
	

	public Team CreateNewTeam(String colour) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );

	    //We create a new Team object
	    Team team1 = new Team(colour);
		
	    //We begin a transaction to the database with the entity manager which we created
	  	//We persist the new  team to the database and return the new team.
	    em.getTransaction().begin();
	    em.persist(team1);
	    em.getTransaction().commit();
	    em.close();

	    //We return the new team
	    return team1;
	}
}
