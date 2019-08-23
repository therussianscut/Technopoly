/**
 * 
 */
package technopoly_group_28;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to run a game of Technopoly
 * @author David McElhill
 *
 */
public class Technopoly {
	
	public static Scanner input; 
	public static GameVariables gameVars;
	public static GameManager gameManager;
	public static Dice d1;
	public static Timer timer;
	
	
	/**
	 * Main method runs through a game of Technolopy
	 * @param args
	 */
	public static void main(String[] args) {
		
		//initialise game objects and import variables
		initialiseVars();
		
		System.out.println("******************************");
		System.out.println("**  Welcome to Technopoly!  **");
		System.out.println("******************************\n");
		
		// setup players
		gameManager.setNumPlayers(input, gameVars);
		gameManager.confirmPlayers(input, gameVars);
		
		// begin playing game
		System.out.println("Lets get Technopolising!\n");
		System.out.println("The aim is simple; as ruthless CEOs of major Tech firms, it is up to you to lead your respective "
				+ "\nteams to superiority! Gaining control of the tiles and trying to create a Technopoly on particular"
				+ "\nfields is the way to achieve this, for your competitors will need to pay for the privelige of "
				+ "\nlanding on your tiles! Be sure to manage your liquidity however, for if you run out of funds, all "
				+ "\nthe tiles in the world won't prevent bankrupcy and one of your competitors claiming superiority!\n");
		
		System.out.println("__________________________________________________________________________________________________\n");
		System.out.println("Press <Enter> to proceed and get started!");
		System.out.println("__________________________________________________________________________________________________");
		input.nextLine();
		
		// take turn of the game
		while(!gameManager.isGameOver()) {
			gameManager.nextTurn();
			System.out.println("\n**************");
			System.out.println("**  Turn " + gameManager.getCurrentTurn() + "  **");
			System.out.println("**************\n");
			// each player gets turn
			for (Player p : gameManager.players) {
				if(!gameManager.isGameOver()) {
					System.out.println(p.getPlayerName() + " is up!\n");
					takeTurn(p);
				} else {
					break;
				}
			}			
		} 
		input.close();
		gameManager.gameOver(gameManager.players, gameVars);
	}

	/**
	 * Method Initialises the game's variables and objects
	 */
	public static void initialiseVars() {	
		
		input = new Scanner(System.in);
		gameVars = new GameVariables();
		gameManager = new GameManager();
		gameManager.gameBoard = new ArrayList<GameTile>();
		ImportData importData = new ImportData();
		timer = new Timer();
		
		try {
			importData.importGameData(gameVars, gameManager.gameBoard);
		} catch (IOException e) {
			e.printStackTrace();
		}
		d1 = new Dice(gameVars);
		
	}
	
	/**
	 * Method runs through one turn of the game
	 */
	@SuppressWarnings("deprecation")
	public static void takeTurn(Player player) {
		
		boolean endPlayerTurn = false;
		
		do {
			Thread thread = new Thread(timer);
			thread.start(); // a bit of experimentation with threading..
			int choice = gameManager.choiceMenu(input);
			thread.stop(); // stop timer if player makes choice, TODO find un-deprecated approach..
			
			switch (choice) {
			case 1: // player chose to view their resources
				gameManager.showHoldings(player, gameVars, gameManager.gameBoard);
				endPlayerTurn = false;
				break;
			case 2: // player chose to manage the tiles they already own
				int manageChoice;
				do {
					manageChoice = gameManager.manageTilesMenu(input, gameManager, player);
					switch (manageChoice) {
					// player chose to upgrade a tile
					case 1:
						gameManager.upgradeTileSelection(input, player, gameVars, gameManager.gameBoard);
						break;
					// player chose to downgrade a tile
					case 2:
						gameManager.downgradeTileSelection(input, player, gameVars, gameManager.gameBoard);
						break;
					// player chose to auction a tile
					case 3:
						gameManager.auctionOwnTileSelection(input, player, gameVars, gameManager);
						break;
					// player chose to return to top menu
					case 4:
						gameManager.gameBoardSummary(gameManager.gameBoard);
						break;
					// player chose to return to top menu
					case 5:
						System.out.println();
						break;
					}
				} while(manageChoice != 5);
				endPlayerTurn = false;
				break;
			case 3: // player chose to proceed with rolling dice
				int newPosition = player.getCurrentPosition() + gameManager.roll2Dice(d1);
				if (newPosition >= gameManager.gameBoard.size()) {
					// recycle board position back through zero if applicable
					player.setCurrentPosition(newPosition - gameManager.gameBoard.size());
					if (player.getCurrentPosition() == 0) { //activate start tile function if player lands on start tile
						gameManager.gameBoard.get(0).tileFunction(player, gameVars, input, gameManager);
					} else { //activate start tile function when player passes it AND function of the tile they land on
						gameManager.gameBoard.get(0).tileFunction(player, gameVars, input, gameManager);
						gameManager.gameBoard.get(player.getCurrentPosition()).tileFunction(player, gameVars, input, gameManager);
					}
				} else {
					player.setCurrentPosition(newPosition);
					gameManager.gameBoard.get(player.getCurrentPosition()).tileFunction(player, gameVars, input, gameManager);
				}
				endPlayerTurn = true;
				System.out.println("__________________________________________________________________________________________________\n");
				System.out.println("Press <Enter> to proceed to the next player.");
				System.out.println("__________________________________________________________________________________________________");
				input.nextLine();
				break;
			case 4: // player chose to end the game	
				System.out.println("Are you sure you wish to end the game and declare the winner? [Y/N]");
				String proceedFlag = gameManager.confirmChoice(input);
				if (proceedFlag.equalsIgnoreCase("Y")) {
					gameManager.gameOver(gameManager.players, gameVars);
					gameManager.setGameOver(true);
					endPlayerTurn = true;
				} else {
					continue;
				}
				
				break;			
			}
		} while (!endPlayerTurn);
	}
}