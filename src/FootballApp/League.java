package FootballApp;

/* @author: Adam Baldwin R00176025
 * @version 1.0
 */

import java.util.ArrayList;

public class League {
	private ArrayList<Team> teams;

	public League() {
		teams = new ArrayList<Team>();
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public boolean addTeam(Team team) {
		teams.add(team);
		return true;
	}

	public void removeTeam(Team team) {
		teams.remove(team);
	}

	public String toString() {
		return "Teams: ";
	}

	public void print() {
		System.out.println(toString());
		for (int i = 0; i < teams.size(); i++) {
			Team currentTeam = teams.get(i);
			currentTeam.print();
		}
		;
		System.out.println("");
	}
}
