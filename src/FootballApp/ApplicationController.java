package FootballApp;

/* @author: Adam Baldwin R00176025
 * @version 1.0
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import FootballApp.service.GetManager;
import FootballApp.service.GetPlayer;
import FootballApp.service.GetTeam;
import FootballApp.service.UpdateTeam;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ApplicationController {

	
	//This method is never called. It is used to test the classes
	//If you want to test it, uncomment in the main
	public static void  test(){
		//League
		League league1 = new League();
	
		//Teams
		Team team1 = new Team("Red");
		Team team2 = new Team("Blue");
		
		//Managers
		Name name1 = new Name("Alex", "Chapman", "Ferguson");
		Name name2 = new Name("Frank", "James", "Lampard");
		Manager manager1 = new Manager(name1, "0871234567", "aferguson@gmail.com", "31/12/1941", 9);
		Manager manager2 = new Manager(name2, "0869876543", "flampard@gmail.com", "01/01/1980", 7);
		
		//Players
		Name name3 = new Name("John", "Michael", "Smith");
		Name name4 = new Name("Kevin", "Shaun", "Radley");
		Name name5 = new Name("Aaron", "Chris", "Murphy");
		Name name6 = new Name("Steve", "John", "Manning");
		Player player1 = new Player(name3, "0211234567", "jsmith@gmail.com", true);
		Player player2 = new Player(name4, "0897678987", "kradley@gmail.com", false);
		Player player3 = new Player(name5, "0214567895", "ac1murphy@gmail.com", false);
		Player player4 = new Player(name6, "0834567432", "stevemanning@gmail.com", true);
		
		//Adding Players & Goals
		System.out.println(team1.addPlayer(player1));
		System.out.println(team1.addPlayer(player2));
		System.out.println(team2.addPlayer(player3));
		System.out.println(team2.addPlayer(player4));
		player1.setGoals(6);
		player2.setGoals(11);
		player3.setGoals(3);
		player4.setGoals(9);
		
		//Adding Managers
		System.out.println(team1.addManager(manager1));
		System.out.println(team1.addManager(manager2)); //Will fail....Used to test 1 manager limit
		System.out.println(team2.addManager(manager2));
		
		//Adding Teams to League
		System.out.println(league1.addTeam(team1));
		System.out.println(league1.addTeam(team2));
		
		league1.print();
		
	}
	
	//Add the teams from the database to the team list
	public List<Team> populateTeamList(List<Team> team) {
		GetTeam getTeam1 = new GetTeam();
		team = getTeam1.GetAllTeams();
		return team;
	}
	
	//Add the teams from the database to the team combobox
	public void populateTeamCombobox(List<Team> team, ComboBox teamComboBox) {
		GetTeam getTeam1 = new GetTeam();
		team = getTeam1.GetAllTeams();
		for(int i=0;i<team.size();i++) {
	    	teamComboBox.getItems().add(team.get(i));
	    }
	}
	
	//Add the managers from the database to the managers list
	public List<Manager> populateManagerList(List<Manager> managers) {
		GetManager getManager1 = new GetManager();
		managers = getManager1.GetAllManagers();
		return managers;
	}
	
	//Add the managerss from the database to the manager combobox
	public void populateManagerCombobox(List<Manager> manager, ComboBox managerComboBox) {
		GetManager getManager1 = new GetManager();
		manager = getManager1.GetAllManagers();
		for(int i=0;i<manager.size();i++) {
	    	managerComboBox.getItems().add(manager.get(i));
	    }
	}
	
	//Add the players from the database to the player list
	public List<Player> populatePlayerList(List<Player> players) {
		GetPlayer getPlayer1 = new GetPlayer();
		players= getPlayer1.GetAllPlayers();
		return players;
	}
	
	//Add the players from the database to the player combobox
	public void populatePlayerCombobox(List<Player> player, ComboBox playerComboBox) {
		GetPlayer getPlayer1 = new GetPlayer();
		player = getPlayer1.GetAllPlayers();
		for(int i=0;i<player.size();i++) {
	    	playerComboBox.getItems().add(player.get(i));
	    }
	}
	
	
	//Sort the managers according to their rating and displays this on the managerListView
	public void orderByRating(List<Manager> managers, ListView<Manager> managerListView) {
		managerListView.getItems().clear();
		managers.sort(Comparator.comparing(Manager::getRating));
		for(int i=0;i<managers.size();i++) {
	    	managerListView.getItems().add(managers.get(i));
	    }
	 }
	
	//Sort the managers alphabetically and displays this on the managerListView
	public void orderAlphabetically(List<Manager> managers, ListView<Manager> managerListView) {
		managerListView.getItems().clear();
		managers.sort(Comparator.comparing(Manager::getNameString));
	    for(int i=0;i<managers.size();i++) {
	    	managerListView.getItems().add(managers.get(i));
	    }
	 }
	
	//Endlessly adds teams to list of teams in order to show increased memory usage and crash the program
	public void programCrash(List<Team> teams) {
		while(true) {
 			Team team = new Team("white");
 			teams.add(team);
 		}
	}
	
	//Takes a players name and returns their name, goals and their manager's details.
	public String playerSearch(String name) {
		//gets all teams in a list
		GetTeam getTeam1 = new GetTeam();
		List<Team> teams = getTeam1.GetAllTeams();
		Player foundPlayer = null;
		Team playersTeam = null;
		Manager playersManager = null;
		
		//Searches through every player in every team in order to try and find a player object 
		//with a name that equals the previously input player name.
		for(int i=0;i<teams.size();i++) {
			List<Player> players = teams.get(i).getPlayers();
			for(int j=0;j<players.size();j++) {
				String playerName = players.get(j).getNameString();
				if(playerName.equals(name)) {
					foundPlayer = players.get(j);
					playersTeam = teams.get(i);
					playersManager = playersTeam.getManager();
				}
			}
		}
		String returnString = "Player: \n\t" + foundPlayer + "\nManager: \n\t" + playersManager.simpleString();
		return returnString;
	}
	
	//Takes a team and a player and calls the UpdateTeam class to add the player to the team
	public void AddPlayerToTeam(Team selectedTeam, Player selectedPlayer) {
		UpdateTeam updateTeam1 = new UpdateTeam();
		
		String selectedColour = selectedTeam.getColour();
		updateTeam1.AddPlayer(selectedColour, selectedPlayer);
	}
	
	//Takes a team and a manager and calls the UpdateTeam class to add the manager to the team
	public void AddManagerToTeam(Team selectedTeam, Manager selectedManager) {
		UpdateTeam updateTeam1 = new UpdateTeam();
		
		String selectedColour = selectedTeam.getColour();
		updateTeam1.AddManager(selectedColour, selectedManager);
	}
}
