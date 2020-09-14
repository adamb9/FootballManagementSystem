package FootballApp;

/* @author: Adam Baldwin R00176025
 * @version 1.0
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javafx.geometry.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

import FootballApp.service.*;

public class ApplicationMain extends Application {
	private Stage window;
	private Button buttonListTeams1, buttonListTeams2, buttonCreateManager, buttonStarRatingSort,
			buttonAlphabeticalSort, buttonEditPlayer, buttonSearchPlayer, buttonCreateNewPlayer, buttonListPlayers,
			buttonAddNewTeam, buttonAddManagerToTeam, buttonAddPlayerToTeam, buttonEditTeam, buttonCrash, 
			buttonUpdatePlayer, buttonRemoveManager, buttonRemovePlayer;
	private static ApplicationController controller = new ApplicationController();
	private List<Team> teams = new ArrayList<Team>();
	private List<Manager> managers = new ArrayList<Manager>();
	private List<Player> players = new ArrayList<Player>();
	private List<Player> teamPlayers = new ArrayList<Player>();

	public static void main(String[] args) {
		
		//controller.test();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Football League Management System");

		//Gets all teams from the DB and adds them to the team list
		GetTeam getTeam1 = new GetTeam();
		teams = controller.populateTeamList(teams);
		//Gets all managers from the DB and adds them to the manager list
		GetManager getManager1 = new GetManager();
		managers = controller.populateManagerList(managers);
		//Gets all playerss from the DB and adds them to the player list
		GetPlayer getPlayer1 = new GetPlayer();
		players = controller.populatePlayerList(players);
		
		
		////////////////// INTRO///////////////////////////

		// INTRO TAB
		//
		//
		FileInputStream input = new FileInputStream("football.png");
		Image image = new Image(input);
		ImageView imageView = new ImageView(image);
		HBox imageHBox = new HBox(imageView);
		imageHBox.setAlignment(Pos.BASELINE_CENTER);

		Label introLabel = new Label("League Management System");
		introLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

		VBox introVBox = new VBox(imageHBox, introLabel);
		introVBox.setAlignment(Pos.BASELINE_CENTER);

		////////////////// LEAGUE////////////////////////

		// LEAGUE TAB
		//
		//
		Label allTeamsLabel = new Label("All Teams in League");
		allTeamsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		HBox leagueLabelHBox = new HBox(allTeamsLabel);
		leagueLabelHBox.setMargin(allTeamsLabel, new Insets(0, 0, 0, 165));

		buttonListTeams1 = new Button("List Teams");
		HBox listButtonLeagueHBox = new HBox(buttonListTeams1);
		listButtonLeagueHBox.setMargin(buttonListTeams1, new Insets(0, 0, 0, 240));

		ListView<Team> leagueListView = new ListView();
		leagueListView.setPrefWidth(530);
		leagueListView.setPrefHeight(450);
		HBox leagueHBoxTop = new HBox(20, leagueListView);
		leagueHBoxTop.setMargin(leagueListView, new Insets(5, 5, 5, 5));
		leagueHBoxTop.setStyle("-fx-border-color: gray;");

		Label memManagementLabel = new Label("Show Memory Management");
		memManagementLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		buttonCrash = new Button("Crash Program");
		VBox leagueVBoxBottom = new VBox(memManagementLabel,buttonCrash);
		leagueVBoxBottom.setMargin(buttonCrash, new Insets(5, 5, 5, 230));
		leagueVBoxBottom.setMargin(memManagementLabel, new Insets(5, 5, 5, 130));

		// LEAGUE GRIDPANE
		//
		//
		GridPane leagueGridpane = new GridPane();
		leagueGridpane.add(leagueLabelHBox, 0, 0);
		leagueGridpane.add(listButtonLeagueHBox, 0, 1);
		leagueGridpane.add(leagueHBoxTop, 0, 2);
		leagueGridpane.add(leagueVBoxBottom, 0, 3);

		///////////////////// TEAMS///////////////////////////////

		// TEAM TAB
		//
		//
		buttonListTeams2 = new Button("List Teams");
		HBox listButtonTeamHBox = new HBox(buttonListTeams2);
		listButtonTeamHBox.setMargin(buttonListTeams2, new Insets(0, 0, 0, 230));

		ListView<Team> teamListView = new ListView();
		teamListView.setPrefWidth(530);
		HBox teamHBoxTop = new HBox(20, teamListView);
		teamHBoxTop.setMargin(teamListView, new Insets(5, 5, 5, 5));
		teamHBoxTop.setStyle("-fx-border-color: gray;");

		Label newTeamLabel = new Label("Enter New Team Name");
		newTeamLabel.setFont(Font.font("Verdana", 15));
		Label addManagerLabel = new Label("Select New Manager");
		addManagerLabel.setFont(Font.font("Verdana", 15));
		Label addPlayerLabel = new Label("Add Player to Team");
		addPlayerLabel.setFont(Font.font("Verdana", 15));
		Label editTeamLabel = new Label("Remove Player/Manager");
		editTeamLabel.setFont(Font.font("Verdana", 14));

		TextField newTeamTextField = new TextField();
		ComboBox managerComboBox = new ComboBox<String>();
		ComboBox playerComboBox = new ComboBox<Player>();
		
		//populates the comboboxes with the list of managers and list of players
		controller.populateManagerCombobox(managers, managerComboBox);
		controller.populatePlayerCombobox(players, playerComboBox);

		buttonAddNewTeam = new Button("Add Team");
		buttonAddManagerToTeam = new Button("Add Manager");
		buttonAddPlayerToTeam = new Button("Add Player");
		buttonEditTeam = new Button("Edit Team");

		VBox teamLabelVBox = new VBox(15, newTeamLabel, addManagerLabel, addPlayerLabel, editTeamLabel);
		teamLabelVBox.setMargin(editTeamLabel, new Insets(20, 0, 0, 0));
		VBox teamManagementVBox = new VBox(13, newTeamTextField, managerComboBox, playerComboBox, buttonEditTeam);
		teamManagementVBox.setMargin(buttonEditTeam, new Insets(10, 0, 0, 0));
		VBox teamButtonVBox = new VBox(13, buttonAddNewTeam, buttonAddManagerToTeam, buttonAddPlayerToTeam);
		HBox teamCombinationHBox = new HBox(teamLabelVBox, teamManagementVBox, teamButtonVBox);
		teamCombinationHBox.setMargin(teamLabelVBox, new Insets(20, 5, 5, 5));
		teamCombinationHBox.setMargin(teamManagementVBox, new Insets(20, 5, 5, 5));
		teamCombinationHBox.setMargin(teamButtonVBox, new Insets(20, 5, 5, 5));

		// TEAM GRIDPANE
		//
		//

		GridPane teamGridpane = new GridPane();
		teamGridpane.add(listButtonTeamHBox, 0, 0);
		teamGridpane.add(teamHBoxTop, 0, 1);
		teamGridpane.add(teamCombinationHBox, 0, 2);

		////////////////////// PLAYERS////////////////////////////

		// PLAYER TAB
		//
		//
		Label selectTeamLabel = new Label("Select a Team to List Their Players");
		selectTeamLabel.setFont(Font.font("Verdana", 15));
		ComboBox teamComboBox = new ComboBox<Team>();
		buttonListPlayers = new Button("Show Selected Team");
		HBox teamSelectHBox = new HBox(10, teamComboBox, buttonListPlayers);

		//populates the team combobox with the list of every player
		controller.populateTeamCombobox(teams, teamComboBox);
		
		Label playerSearchLabel = new Label("Name");
		playerSearchLabel.setFont(Font.font("Verdana", 15));
		TextField playerSearchFirstNameText = new TextField();
		TextField playerSearchMiddleNameText = new TextField();
		TextField playerSearchLastNameText = new TextField();
		HBox playerSearchHBox = new HBox(5, playerSearchLabel, playerSearchFirstNameText, playerSearchMiddleNameText,
				playerSearchLastNameText);
		buttonSearchPlayer = new Button("Search Player");

		VBox selectTeamVBox = new VBox(10, selectTeamLabel, teamSelectHBox, playerSearchHBox, buttonSearchPlayer);
		selectTeamVBox.setMargin(selectTeamLabel, new Insets(3, 0, 0, 130));
		selectTeamVBox.setMargin(teamSelectHBox, new Insets(3, 0, 0, 170));
		selectTeamVBox.setMargin(playerSearchHBox, new Insets(3, 0, 5, 0));
		selectTeamVBox.setMargin(buttonSearchPlayer, new Insets(0, 0, 5, 225));

		// ListView
		ListView<Player> playerListView = new ListView();
		playerListView.setPrefWidth(530);
		playerListView.setPrefHeight(260);
		HBox playerHBoxTop = new HBox(20, playerListView);
		playerHBoxTop.setMargin(playerListView, new Insets(5, 5, 5, 5));
		playerHBoxTop.setStyle("-fx-border-color: gray;");

		buttonEditPlayer = new Button("Edit Selected Player");
		HBox editPlayerHBox = new HBox(buttonEditPlayer);
		editPlayerHBox.setMargin(buttonEditPlayer, new Insets(5, 5, 5, 215));

		// Create New Player Section
		Label newPlayerLabel = new Label("       			Add New Player");
		newPlayerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

		Label playerNameLabel = new Label("Full Name");
		playerNameLabel.setFont(Font.font("Verdana", 15));
		TextField playerFirstNameText = new TextField();
		TextField playerMiddleNameText = new TextField();
		TextField playerLastNameText = new TextField();
		HBox playerNameHBox = new HBox(5, playerNameLabel, playerFirstNameText, playerMiddleNameText,
				playerLastNameText);
		playerNameHBox.setStyle("-fx-border-color: white;");

		Label playerPhoneLabel = new Label("Phone Number");
		playerPhoneLabel.setFont(Font.font("Verdana", 15));
		TextField playerPhoneNumberText = new TextField();

		Label playerEmailLabel = new Label("Email");
		playerEmailLabel.setFont(Font.font("Verdana", 15));
		TextField playerEmailText = new TextField();

		Label playerGoalieLabel = new Label("Are they a goalie?");
		playerGoalieLabel.setFont(Font.font("Verdana", 15));
		RadioButton playerGoalieButton = new RadioButton("Yes");
		playerGoalieButton.setFont(Font.font("Verdana", 12));

		buttonCreateNewPlayer = new Button("Add Player");

		VBox playerLabelVBox = new VBox(16, playerPhoneLabel, playerEmailLabel, playerGoalieLabel);
		VBox playerManagementVBox = new VBox(10, playerPhoneNumberText, playerEmailText, playerGoalieButton,
				buttonCreateNewPlayer);
		playerManagementVBox.setMargin(playerGoalieButton, new Insets(0, 0, 0, 45));
		playerManagementVBox.setMargin(buttonCreateNewPlayer, new Insets(0, 0, 0, 35));
		HBox playerCombinationHBox = new HBox(5, playerLabelVBox, playerManagementVBox);
		playerCombinationHBox.setMargin(playerLabelVBox, new Insets(5, 5, 5, 5));
		playerCombinationHBox.setMargin(playerManagementVBox, new Insets(5, 5, 5, 5));

		// PLAYER GRIDPANE
		//
		//
		GridPane playerGridpane = new GridPane();
		playerGridpane.add(selectTeamVBox, 0, 0);
		playerGridpane.add(playerHBoxTop, 0, 1);
		playerGridpane.add(editPlayerHBox, 0, 2);
		playerGridpane.add(newPlayerLabel, 0, 3);
		playerGridpane.add(playerNameHBox, 0, 4);
		playerGridpane.add(playerCombinationHBox, 0, 5);

		////////////////////// MANAGER////////////////////////////

		// MANAGER TAB
		//
		//

		buttonStarRatingSort = new Button("Star Rating");
		buttonAlphabeticalSort = new Button("Alphabetical");
		HBox managerButtonHBox = new HBox(15, buttonStarRatingSort, buttonAlphabeticalSort);
		managerButtonHBox.setMargin(buttonStarRatingSort, new Insets(5, 0, 5, 185));
		managerButtonHBox.setMargin(buttonAlphabeticalSort, new Insets(5, 0, 5, 0));

		// ListView
		ListView<Manager> managerListView = new ListView();
			
		managerListView.setPrefWidth(530);
		managerListView.setPrefHeight(330);
		HBox managerHBoxTop = new HBox(20, managerListView);
		managerHBoxTop.setMargin(managerListView, new Insets(5, 5, 5, 5));
		managerHBoxTop.setStyle("-fx-border-color: gray;");

		Label managerNameLabel = new Label("Full Name");
		managerNameLabel.setFont(Font.font("Verdana", 15));
		TextField managerFirstNameText = new TextField();
		TextField managerMiddleNameText = new TextField();
		TextField managerLastNameText = new TextField();
		HBox managerNameHBox = new HBox(5, managerNameLabel, managerFirstNameText, managerMiddleNameText,
				managerLastNameText);
		managerNameHBox.setStyle("-fx-border-color: white;");

		Label managerPhoneLabel = new Label("Phone Number");
		managerPhoneLabel.setFont(Font.font("Verdana", 15));
		TextField managerPhoneNumberText = new TextField();

		Label managerEmailLabel = new Label("Email");
		managerEmailLabel.setFont(Font.font("Verdana", 15));
		TextField managerEmailText = new TextField();

		Label managerDOBLabel = new Label("DOB");
		managerDOBLabel.setFont(Font.font("Verdana", 15));
		DatePicker managerDOBDatePicker = new DatePicker();

		Label managerRatingLabel = new Label("Rating (1-10)");
		managerRatingLabel.setFont(Font.font("Verdana", 15));
		TextField managerRatingText = new TextField();

		buttonCreateManager = new Button("Add Manager");

		VBox managerLabelVBox = new VBox(18, managerPhoneLabel, managerEmailLabel, managerDOBLabel, managerRatingLabel);
		VBox managerManagementVBox = new VBox(10, managerPhoneNumberText, managerEmailText, managerDOBDatePicker,
				managerRatingText, buttonCreateManager);

		HBox managerCombinationHBox = new HBox(10, managerLabelVBox, managerManagementVBox);
		managerCombinationHBox.setMargin(managerLabelVBox, new Insets(5, 5, 5, 5));
		managerCombinationHBox.setMargin(managerManagementVBox, new Insets(5, 5, 5, 5));

		// MANAGER GRIDPANE
		//
		//
		GridPane managerGridpane = new GridPane();
		managerGridpane.add(managerButtonHBox, 0, 0);
		managerGridpane.add(managerHBoxTop, 0, 1);
		managerGridpane.add(managerNameHBox, 0, 2);
		managerGridpane.add(managerCombinationHBox, 0, 3);

		////////////////////// TABS///////////////////////////////

		// TAB PANE SETUP
		//
		//
		TabPane tabpane = new TabPane();

		Tab introTab 	= new Tab("Intro", introVBox);
		Tab leagueTab 	= new Tab("League", leagueGridpane);
		Tab teamTab 	= new Tab("Teams", teamGridpane);
		Tab playerTab 	= new Tab("Players", playerGridpane);
		Tab managerTab 	= new Tab("Managers", managerGridpane);
		tabpane.getTabs().add(introTab);
		tabpane.getTabs().add(leagueTab);
		tabpane.getTabs().add(teamTab);
		tabpane.getTabs().add(playerTab);
		tabpane.getTabs().add(managerTab);

		
		
		
		
		///////////////////////////////////////////////////EVENT HANDLERS ///////////////////////////////////////////////////////
		
		//creates a new player object. Adds new player to the player combobox. Adds new player to the database
		buttonCreateNewPlayer.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
				CreatePlayer createPlayer1 = new CreatePlayer(); 
				Player newPlayer = createPlayer1.CreateNewPlayer(playerFirstNameText.getText(), playerMiddleNameText.getText(), playerLastNameText.getText(), playerPhoneNumberText.getText(), playerEmailText.getText(), playerGoalieButton.isSelected());
				players.add(newPlayer);
				playerComboBox.getItems().add(newPlayer);
			} 
		});
		
		//creates a new team object. Adds new team to the team combobox. Adds new team to the database
		buttonAddNewTeam.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
				CreateTeam createTeam1 = new CreateTeam(); 
				Team newTeam = createTeam1.CreateNewTeam(newTeamTextField.getText());
				teams.add(newTeam);
				teamComboBox.getItems().add(newTeam);
			} 
		});
		
		
		//creates a new manager object. Adds new manager to the manager combobox. Adds new manager to the database
		buttonCreateManager.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
				String date = (managerDOBDatePicker.getValue()).toString();
				int rating = Integer.parseInt(managerRatingText.getText());
				CreateManager createManager1 = new CreateManager(); 
				Manager newManager = createManager1.CreateNewManager(managerFirstNameText.getText(), managerMiddleNameText.getText(), managerLastNameText.getText(), managerPhoneNumberText.getText(), managerEmailText.getText(), date, rating);
				managers.add(newManager);
				managerComboBox.getItems().add(newManager);
			} 
		});
		
		
		//Displays all teams on a listview on the league tab
		buttonListTeams1.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
				
			teams = getTeam1.GetAllTeams();
			leagueListView.getItems().clear();
		    for(int i=0;i<teams.size();i++) {
		    	leagueListView.getItems().add(teams.get(i));
			}
		    
		   }
		});
		
		//Displays all teams on a listview on the team tab
		buttonListTeams2.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
				
			teams = getTeam1.GetAllTeams();
			teamListView.getItems().clear();
		    for(int i=0;i<teams.size();i++) {
		    	teamListView.getItems().add(teams.get(i));
			} 
		   }
		});
		
		//Sorts the managers by their rating and displays the results on a listview
		buttonStarRatingSort.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			controller.orderByRating(managers, managerListView);
		   }
		});
		
		//Sorts the managers alphabetically and displays the results on a listview
		buttonAlphabeticalSort.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			controller.orderAlphabetically(managers, managerListView);
		   }
		});
		
		//Crashes the program by endlessly adding objects to a list
		buttonCrash.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			controller.programCrash(teams);
		   }
		});
		
		//Adds a player selected from a combobox to a team selected from a listview
		buttonAddPlayerToTeam.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			
			int selectedIndex = teamListView.getSelectionModel().getSelectedIndex();
			Team selectedTeam = teams.get(selectedIndex);
			Player selectedPlayer = (Player) playerComboBox.getValue();
			controller.AddPlayerToTeam(selectedTeam, selectedPlayer);
		   }
		});
		
		//Adds a manager selected from a combobox to a team selected from a listview
		buttonAddManagerToTeam.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			
			int selectedIndex = teamListView.getSelectionModel().getSelectedIndex();
			Team selectedTeam = teams.get(selectedIndex);
			Manager selectedManager = (Manager) managerComboBox.getValue();
			controller.AddManagerToTeam(selectedTeam, selectedManager);
		   }
		});
		
		//Lists all players from a chosen team and displays them on a listview
		buttonListPlayers.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			Team selectedTeam = (Team) teamComboBox.getValue();
			teamPlayers = selectedTeam.getPlayers();
	
			playerListView.getItems().clear();
		    for(int i=0;i<teamPlayers.size();i++) {
		    	playerListView.getItems().add(teamPlayers.get(i));
			}
			
		   }
		});
		
		//Takes a name input and searches for a player in the database.
		//A popup with the players details appears when the player is found.
		buttonSearchPlayer.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			String firstName = playerSearchFirstNameText.getText();
			String middleName = playerSearchMiddleNameText.getText();
			String lastName = playerSearchLastNameText.getText();
			
			String inputName = firstName + " " + middleName + " " + lastName;
			
			
			////////////////////////////////
			final Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(primaryStage);
            
            String infoString = controller.playerSearch(inputName);
            TextArea textArea = new TextArea();
            textArea.setText(infoString);
            
            Label popupLabel = new Label("Player Found!");
            popupLabel.setFont(Font.font("Verdana", 15));
            VBox popupLabelVbox = new VBox(popupLabel, textArea);
            popupLabelVbox.setMargin(popupLabel, new Insets(5,5,5,90));
            popupLabelVbox.setMargin(textArea, new Insets(5,5,5,5));
            
            GridPane popupGridpane = new GridPane();
            popupGridpane.add(popupLabelVbox, 0, 0);
            
            Scene popupScene = new Scene(popupGridpane, 275, 200);
            popup.setScene(popupScene);
            popup.show();
            
			
		   } 
		});
		
		//The user selects a player from a team in the listview.
		//A popup is opened to allow the user to edit the player's goals and email address.
		buttonEditPlayer.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			Team selectedTeam = (Team) teamComboBox.getValue();
			teamPlayers = selectedTeam.getPlayers();
			
			int selectedIndex = playerListView.getSelectionModel().getSelectedIndex();
			Player selectedPlayer = teamPlayers.get(selectedIndex);
			
			////////////////////////////////
			final Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.initOwner(primaryStage);
			
			Label updateEmailLabel = new Label("Email");
			TextField updateEmailText = new TextField();
			Label updateGoalLabel = new Label("Set Goals");
			TextField updateGoalText = new TextField();
			buttonUpdatePlayer = new Button("Confirm Update");
			
			//Saves the changes to the player to the database
			buttonUpdatePlayer.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
				UpdatePlayer updatePlayer1 = new UpdatePlayer();
				String email = updateEmailText.getText();
				int goals = Integer.parseInt(updateGoalText.getText());
				updatePlayer1.ChangeAttributes(selectedPlayer, email, goals);
				
			   }
			});
			
			updateEmailLabel.setFont(Font.font("Verdana", 15));
			updateGoalLabel.setFont(Font.font("Verdana", 15));
			
			VBox updateLabelVBox = new VBox(10, updateEmailLabel, updateGoalLabel);
			VBox updatePlayerVBox = new VBox(10, updateEmailText, updateGoalText, buttonUpdatePlayer);
			updateLabelVBox.setMargin(updateEmailLabel, new Insets(5, 5, 5, 5));
			updateLabelVBox.setMargin(updateGoalLabel, new Insets(5, 5, 5, 5));
			updatePlayerVBox.setMargin(updateEmailText, new Insets(5, 5, 5, 0));
			
			GridPane popupGridpane = new GridPane();
			popupGridpane.add(updateLabelVBox, 0, 0);
			popupGridpane.add(updatePlayerVBox, 1, 0);
			
			Scene popupScene = new Scene(popupGridpane, 270, 125);
			popup.setScene(popupScene);
			popup.show();
		   }
		});
		
		//Creates a popup menu to allow the user to remove the manager or remove players from a team
		buttonEditTeam.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
			int selectedIndex = teamListView.getSelectionModel().getSelectedIndex();
			String selectedTeamColour = teams.get(selectedIndex).getColour();
			GetTeam GetTeam1 = new GetTeam();
			Team selectedTeam = GetTeam1.GetSpecificTeam(selectedTeamColour);
			
			Manager manager = selectedTeam.getManager();
			teamPlayers = selectedTeam.getPlayers();
			
			////////////////////////////////
			final Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.initOwner(primaryStage);
			
			Label currentManager = new Label("Current Manager");
			currentManager.setFont(Font.font("Verdana", 15));
			
			TextArea textArea = new TextArea();
			textArea.setText(manager.simpleString());
			buttonRemoveManager = new Button("Remove Manager");
			
			//Commits the change to the team and removes it's manager
			buttonRemoveManager.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
				UpdateTeam updateTeam1 = new UpdateTeam();
				textArea.setText("");
				updateTeam1.RemoveManager(selectedTeam, manager);
				
			   }
			});
			
			
			VBox editTeamTopVBox = new VBox(10, currentManager, textArea, buttonRemoveManager);
			editTeamTopVBox.setMargin(currentManager, new Insets(5, 5, 5, 70));
			editTeamTopVBox.setMargin(buttonRemoveManager, new Insets(5, 5, 5, 80));
			
			
			Label popupLabel = new Label("Players");
			popupLabel.setFont(Font.font("Verdana", 15));
			
			ListView<Player> editPlayerListView = new ListView();
			editPlayerListView.setPrefWidth(530);
			editPlayerListView.setPrefHeight(260);
			HBox editTeamListViewHBox = new HBox(editPlayerListView);
			editTeamListViewHBox.setMargin(editPlayerListView, new Insets(5, 5, 5, 5));
			editTeamListViewHBox.setStyle("-fx-border-color: gray;");
			
			editPlayerListView.getItems().clear();
		    for(int i=0;i<teamPlayers.size();i++) {
		    	editPlayerListView.getItems().add(teamPlayers.get(i));
			}
			
			buttonRemovePlayer = new Button("Remove Player");
			
			//Removes the selected player from the team
			buttonRemovePlayer.setOnAction(new EventHandler<ActionEvent>(){ public void handle(ActionEvent event) {
				UpdateTeam updateTeam1 = new UpdateTeam();
	
				int selectedIndex = editPlayerListView.getSelectionModel().getSelectedIndex();
				Player selectedPlayer = teamPlayers.get(selectedIndex);
				
				updateTeam1.RemovePlayer(selectedTeam, selectedPlayer);
				
				editPlayerListView.getItems().clear();
			    for(int i=0;i<teamPlayers.size();i++) {
			    	editPlayerListView.getItems().add(teamPlayers.get(i));
				}
				
			   }
			});
			
			
			VBox  editTeamBottomVBox = new VBox(10, popupLabel, editTeamListViewHBox, buttonRemovePlayer);
			editTeamBottomVBox.setMargin(popupLabel, new Insets(5, 5, 5, 105));
			editTeamBottomVBox.setMargin(buttonRemovePlayer, new Insets(5, 5, 5, 90));

			
			GridPane popupGridpane = new GridPane();
			popupGridpane.add(editTeamTopVBox, 0, 0);
			popupGridpane.add(editTeamBottomVBox, 0, 1);

			
			Scene popupScene = new Scene(popupGridpane, 275, 450);
			popup.setScene(popupScene);
			popup.show();
			
		   }
		});
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// Initializes the scene
		Scene scene = new Scene(tabpane, 540, 620);
		window.setScene(scene);
		window.show();
	}
}
