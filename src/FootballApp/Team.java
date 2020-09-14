package FootballApp;

/* @author: Adam Baldwin R00176025
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity
public class Team {
	
	private List<Player> players = new ArrayList<Player>();
	
	private Manager manager;
	@Id
	private String colour;

	public Team(String colour) {
		this.colour = colour;
		players = new ArrayList<Player>();
	}
	
	public Team() {
		
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public boolean addPlayer(Player player) {
		players.add(player);
		return true;
	}

	public void removePlayer(Player player) {
		players.remove(player);
	}

	public boolean addManager(Manager newManager) {
		if (manager == null) {
			manager = newManager;
			if (newManager.getTeam() != this) {
				newManager.setTeam(this);
			}
			return true;
		} else {
			System.out.println("Error: You cannot have more than 1 manager per team!");
			return false;
		}
	}

	public void removeManager() {
		manager = null;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String toString() {
		return "Colour: " + colour ;
	}

	public void print() {
		System.out.println(toString());
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i));
		}
		;
		System.out.println("");
	}
}
