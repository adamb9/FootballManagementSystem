package FootballApp.service;
/* @author: Adam Baldwin R00176025
 * @version 1.0
 */
import java.util.List;

import javax.persistence.*;

import FootballApp.Team;

public class GetTeam {

	public List<Team> GetAllTeams() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );

	    //We create a new query to get all teams from the Team table in the database
	    //We execute this query and store the results in a list of teams
	    Query query = em.createQuery("Select t from Team as t");
	   	List<Team> resultList = query.getResultList();
	   
	    em.close();
	    //we return the list of teams
	    return resultList;
	}
	
	public Team GetSpecificTeam(String colour){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		//A new entity manager is created and called em
	    EntityManager em = emfactory.createEntityManager( );

	    //We create a new query to get the team with the specified colur from the database
	    //We execute this query and store the results in a list of teams.
	    //We know that there is only one team with each colour so this team is located at index 0 of the list
	    Query query = em.createQuery("Select t from Team t where t.colour = ?1");
	    query.setParameter(1, colour);
	    List<Team> resultList = query.getResultList();
	    Team team = resultList.get(0);
	   
	    em.close();
	    //We return the team
	    return team;
	}
	
}
