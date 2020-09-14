package FootballApp.service;
/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
import java.util.List;

import javax.persistence.*;

import FootballApp.Manager;
import FootballApp.Player;
import FootballApp.Team;

public class UpdateTeam {

	public void AddManager(String colour, Manager manager) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );
	    
	    //We find the team object using its colour.
	    //This team is located at index 0 of the result list
	    Query query = em.createQuery("Select t from Team t where t.colour = ?1");
	    query.setParameter(1, colour);
	    List<Team> resultList = query.getResultList();
	    Team team = resultList.get(0);
	    //We add the new manager to the team
	    team.addManager(manager);
	    
	    //We begin a transaction to the database with the entity manager which we created
	  	//We persist the updated  team to the database.
	    em.getTransaction().begin();
	    em.persist(team);
	    em.getTransaction().commit();
	    em.close();
	}
	
	public void AddPlayer(String colour, Player player) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );
	    
	    //We find the team object using its colour.
	    //This team is located at index 0 of the result list
	    Query query = em.createQuery("Select t from Team t where t.colour = ?1");
	    query.setParameter(1, colour);
	    List<Team> resultList = query.getResultList();
	    Team team = resultList.get(0);
	    //We add the new player to the team
	    team.addPlayer(player);
	    
	    //We begin a transaction to the database with the entity manager which we created
	  	//We persist the updated  team to the database.
	    em.getTransaction().begin();
	    em.persist(team);
	    em.getTransaction().commit();
	    em.close();
	}
	
	public void RemoveManager(Team team, Manager manager) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );
	    
	    //We set the manager attribute in the team to null
	    //We change the team attribute in the manager to null
	    //This effectively removes the link between the manager and team
	    team.setManager(null);
	    manager.setTeam(null);
	    
	    //We begin a new transaction and merge the updated team and manager objects to the database
	    //This merge function means that there is no duplication of objects and that the old manager and team objects are overwritten.
	    em.getTransaction().begin();
	    em.merge(team);
	    em.merge(manager);
	    em.getTransaction().commit();
	    em.close();
	}
	
	public void RemovePlayer(Team team, Player player) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );
	    
	    //We remove the player from the selected team
	    team.removePlayer(player);
	    
	 	//We begin a new transaction and merge this updated team object to the database
	    //This merge function means that there is no duplication of objects and that the old team object is overwritten.
	    em.getTransaction().begin();
	    em.merge(team);
	    em.getTransaction().commit();
	    em.close();
	}
	
}
