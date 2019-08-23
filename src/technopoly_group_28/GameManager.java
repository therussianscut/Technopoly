/**
 * 
 */
package technopoly_group_28;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Class contains many of the game management methods
 * @author David McElhill
 *
 */
public class GameManager {
	
	private int numPlayers;
	private int currentTurn = 0;
	private boolean gameOver = false;
	ArrayList<Player> players;
	ArrayList<GameTile> gameBoard;
	String proceedFlag;
	
	// constant for controlling minimum players in a game
	private final int MIN_PLAYERS = 2;
	
	/**
	 * No args constructor
	 */
	public GameManager() {
		
	}
	
	/**
	 * Constructor with player and game tile array lists. Other vars have initial values. 
	 * @param players
	 * @param gameBoard
	 */
	public GameManager(ArrayList<Player> players, ArrayList<GameTile> gameBoard) {
		this.players = players;
		this.gameBoard = gameBoard;		
	}

	/**
	 * @return the numPlayers
	 */
	public int getNumPlayers() {
		return numPlayers;
	}

	/**
	 * @param numPlayers the numPlayers to set
	 */
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	/**
	 * 
	 * @param currentTurn
	 * @return
	 */
	public int getCurrentTurn() {
		return currentTurn;
	}
	
	/**Method to increment the current turn
	 * 
	 */
	public void nextTurn() {
		currentTurn++;
	}
	
	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * Method returns the Player object whose owns a tile based on the playerID passed in
	 * @param activePlayer
	 * @return Player 
	 */
	public Player getOwner(int playerID) {
		return players.get(playerID);
	}
	
	/**
	 * Method to setup the number of players in a game
	 * @param input
	 * @param gameVars
	 * @return int - number of players
	 */
	public void setNumPlayers(java.util.Scanner input, GameVariables gameVars) {
		
		do {
			System.out.println("This is a 2-" + gameVars.getMaxPlayers() + " player game, please enter how many will be playing:");
			try {
				numPlayers = input.nextInt();
				if (numPlayers >= MIN_PLAYERS && numPlayers <= gameVars.getMaxPlayers()) {
					System.out.println(numPlayers + " players selected");
					input.nextLine();// discard newline character
					System.out.println("Ready to proceed? [Y/N]");
					proceedFlag = confirmChoice(input);	
					if (proceedFlag.equalsIgnoreCase("Y")) {
						players = new ArrayList<Player>(numPlayers);
						break; // break loop when user confirms to proceed with current player number
					} else 
						continue; // restart loop to allow re-selection of player number
				} else {
					System.out.println("Invalid number of players.");
					continue; // invalid number of players, restart loop
				}
			} catch (InputMismatchException ie) {
				System.out.println("Invalid Input, please try again.");
				input.next(); // discard non-integer input
				continue; // invalid input type, restart loop
			}
		} while (true);
	}
	
	/**
	 * Method to setup and confirm players
	 */
	public void confirmPlayers(java.util.Scanner input, GameVariables gameVars) {
		
		do {
			setPlayerNames(players, numPlayers, input, gameVars); // allow user to select player names
			System.out.println("\nPlayers entered:");
			displayNames(players);
			System.out.println("\nOk to proceed? [Y/N]");
			proceedFlag = confirmChoice(input);
			
			if (proceedFlag.equalsIgnoreCase("Y")) {
				break; // break loop when user confirms to proceed with player names
			} else 
				players.clear(); // empty the previously entered names from array list
				continue; // restart loop to allow re-selection of player names
		} while (true);	
	}
	
	/**
	 * Method to create player objects and add to player list
	 * @param players
	 * @param numPlayers
	 * @param input
	 * @param gameVars
	 */
	public void setPlayerNames(ArrayList<Player> players, int numPlayers, java.util.Scanner input, GameVariables gameVars) {	
		
		String name;		
		for(int loop = 1; loop <= numPlayers; loop++) {
			do {
				System.out.println("Please enter the name of Player " + loop + ":");
				name = input.nextLine();
				if(name == null || name.trim().length() == 0) {
					System.out.println("Nothing entered!");
					continue; // restart loop if name is blank
				} else if (duplicateName(players, name)) {
					System.out.println("Name already in use! Please choose a different one.");
					continue; // restart loop if name is same as another player's
				} else {
					Player player = new Player(name, loop, gameVars);
					players.add(player);
					break; // break out of loop if valid name set
				}
			} while(true);
		}
	}
	
	/**
	 * Method to allow user to confirm their choices with 'Y' or 'N'
	 * @return String proceedFlag
	 */
	public String confirmChoice(java.util.Scanner input) {
		
		do {			
			proceedFlag = input.nextLine();
			if (proceedFlag.equalsIgnoreCase("N")) {
				break; //valid choice, exit loop
			} else if (proceedFlag.equalsIgnoreCase("Y")) {
				System.out.println("OK!\n");
				break; //valid choice, exit loop
			} else {
				System.out.println("Invalid input, please enter again! [Y/N]:");
				continue; //invalid input, restart loop
			}
		} while (true);
		return proceedFlag;
	}

	/**
	 * Method to display Player names from Array list of players
	 * @param players
	 */
	public void displayNames(ArrayList<Player> players) {
		for(int loop = 0; loop < players.size(); loop++) {
			System.out.println(players.get(loop).getPlayerName());
		}
	}
	
	/**
	 * Method to check for duplicate name in array and return true if there is.
	 * @param players
	 * @param name
	 * @return boolean
	 */
	public boolean duplicateName(ArrayList<Player> players, String name) {
		
		boolean dupeName = false;
		for(int loop = 0; loop < players.size(); loop++) {
			if(players.get(loop).getPlayerName().equalsIgnoreCase(name.trim())) {
				dupeName = true;
				break; // break out of loop if duplicate detected
			} else {
				dupeName = false;
			}
		}
		return dupeName;
		
	}
	
	/**
	 * Choice menu at beginning of each turn for each player - manage owned tiles, roll dice or end game
	 * @param input
	 * @return int
	 */
	public int choiceMenu(java.util.Scanner input) {
		
		int choice;
		System.out.println("Please choose from the following options: \n1. Display Holdings \n2. Manage your Tiles \n3. Roll Dice \n4. End Game");
		do {
			try {
				choice = input.nextInt();
				switch (choice) {
				case 1:
					input.nextLine(); // discard newline character
					return 1;
				case 2:
					input.nextLine(); // discard newline character
					return 2;
				case 3:
					input.nextLine(); // discard newline character
					return 3;
				case 4:
					input.nextLine(); // discard newline character
					return 4;
				default:
					System.out.println("Invalid Input, please try again:");
					continue;
				}
			} catch (InputMismatchException ie) {
				System.out.println("Invalid Input, please try again:");
				input.next(); // discard non-integer input
				continue; // invalid input type, restart loop
			}
		} while (true);
		
	}
	
	/**
	 * Method to give player an options menu to manage tiles they own
	 * @param input
	 * @return int 1 - 4 with players choice 
	 */
	public int manageTilesMenu(java.util.Scanner input, GameManager gameManager, Player player) {
		int choice = 0;
		do {
			System.out.println("\nPlease choose from the following tile management options: \n1.Invest Tile \n2.Divest Tile \n3.Auction Tile \n4.Gameboard Summary \n5.Return to top menu ");
			try {
				choice = input.nextInt();
				switch (choice) {
				case 1:
					input.nextLine(); // discard newline character
					return 1;
				case 2:
					input.nextLine(); // discard newline character
					return 2;
				case 3:
					input.nextLine(); // discard newline character
					return 3;
				case 4:
					input.nextLine(); // discard newline character
					return 4;
				case 5:
					input.nextLine(); // discard newline character
					return 5;
				default:
					System.out.println("Invalid Input, please try again:");
					continue;
				}
			} catch (InputMismatchException ie) {
				System.out.println("Invalid Input, please try again:");
				input.next(); // discard non-integer input
				continue; // invalid input type, restart loop
			}
		} while (true);

	}
	
	/**
	 * Method rolls 2 dice and returns the total of the rolls
	 * @param d1
	 * @return int
	 */
	public int roll2Dice(Dice d1) {
		
		int roll1, roll2, totalRoll;
		roll1 = d1.dieRoll();
		roll2 = d1.dieRoll();
		totalRoll = roll1 + roll2;
		System.out.println("\nYou have rolled " + roll1 + " and " + roll2 + ". Total: " + totalRoll + "\n");
		return totalRoll;
	}
	
	/**
	 * Method to print a summary of player's resource balance and tiles owned
	 * @param player
	 * @param gameVars
	 * @param gameBoard
	 */
	public void showHoldings(Player player, GameVariables gameVars, ArrayList<GameTile> gameBoard) {
		System.out.println("___________________________________________________________________________________");
		System.out.println("\nSummary of holdings for " + player.getPlayerName() + ":\n\nResource Balance: " + player.getResourceBalance() + " " + gameVars.getCurrency());
		showTilesOwned(player, gameBoard);
		System.out.println("___________________________________________________________________________________\n");
	}
	
	/**
	 * Method shows the tiles owned by the player passed in
	 * @param player
	 * @param gameBoard
	 */
	public void showTilesOwned(Player player, ArrayList<GameTile> gameBoard) {
		
		int ownedCount = 0;
		System.out.printf("\n%-20s %-20s %-20s %-20s\n", "Tile Name", "Field Name", "Investment Level", "Invest/Divest Eligible");
		for (GameTile g : gameBoard) {
			if(g.getClass().equals(OwnableTile.class)) {
				if(((OwnableTile)g).getOwnerID() == player.getPlayerID()) {
					System.out.printf("%-20s %-20s %-20s %-20s\n", ((OwnableTile)g).getTileName(), ((OwnableTile)g).getFieldName(), ((OwnableTile)g).upgradelevelAsString(), ((OwnableTile)g).isUpgradeableAsString());
					ownedCount++;
				}
			}
		}
		if(ownedCount == 0) {
			System.out.println("No tiles owned");
		}
		System.out.println();
	}
	
	/**
	 * Method to display all the game tile information for a game board
	 * @param gameBoard
	 */
	public void gameBoardSummary(ArrayList<GameTile> gameBoard) {
		System.out.println("\nGameboard summary:");
		for(GameTile g : gameBoard) {
			System.out.println(g.tileInfo());
		}
	}
	
	/**
	 * Method returns a boolean to indicate if player owns any tiles eligible for upgrade
	 * @param player
	 * @param gameBoard
	 * @return boolean
	 */
	public boolean canPlayerUpgrade(Player player, ArrayList<GameTile> gameBoard) {
		boolean canUpgrade = false;	
		for(GameTile g : gameBoard) {
			if(g.getClass().equals(OwnableTile.class)) {
				if(player.getPlayerID() == ((OwnableTile)g).getOwnerID() && ((OwnableTile)g).isUpgradable()) {					
					canUpgrade = true;
					break;
				} else {
					canUpgrade = false;
				}
			}			
		}
		return canUpgrade;
	}
	
	/**
	 * Method allows selection of which tile the player wants to upgrade 
	 * @param player
	 * @param gameVars
	 * @param gameBoard
	 */
	public void upgradeTileSelection(java.util.Scanner input,Player player, GameVariables gameVars, ArrayList<GameTile> gameBoard) {
		int upgradableCount = 0;
		String upgradeChoice;
		showTilesOwned(player, gameBoard);
		for (GameTile g : gameBoard) {
			if(g.getClass().equals(OwnableTile.class)) {
				// if tile is upgradable, not already at max level and player id matches tile owner id
				if(((OwnableTile)g).isUpgradable() && ((OwnableTile)g).getUpgradeLevel() < OwnableTile.MAX_LEVEL && player.getPlayerID() == ((OwnableTile)g).getOwnerID()) {
					System.out.println(((OwnableTile)g).getTileName() + "\nUpgrade Cost: " + ((OwnableTile)g).getUpgradeCost() + " " + gameVars.getCurrency());
					upgradableCount++;
					System.out.println("Would you like to invest in this tile? [Y/N]");
					upgradeChoice = confirmChoice(input);
					if (upgradeChoice.equalsIgnoreCase("Y")) {
						((OwnableTile)g).increaseUpgradeLevel(player);
						System.out.println(((OwnableTile)g).getTileName() + " is now at investment level '" + ((OwnableTile)g).upgradelevelAsString() + "'\n");
						break;
					} else {
						continue;
					}
				}
			}
		}
		if(upgradableCount == 0) {
			System.out.println("__________________________________________________");
			System.out.println("You do not have any tiles eligible for investment.");
			System.out.println("__________________________________________________\n");
		}
	}
	
	/**
	 * Method allows selection of which tile the player wants to downgrade 
	 * @param player
	 * @param gameVars
	 * @param gameBoard
	 */
	public void downgradeTileSelection(java.util.Scanner input,Player player, GameVariables gameVars, ArrayList<GameTile> gameBoard) {
		int downgradableCount = 0;
		String downgradeChoice;
		showTilesOwned(player, gameBoard);
		for (GameTile g : gameBoard) {
			if(g.getClass().equals(OwnableTile.class)) {
				// if tile level is greater than zero and player id matches tile owner id
				if(((OwnableTile)g).getUpgradeLevel() > 0 && player.getPlayerID() == ((OwnableTile)g).getOwnerID()) {
					System.out.println(((OwnableTile)g).getTileName() + "\nReimbursement value: " + (((OwnableTile)g).getUpgradeCost() / 2) + " " + gameVars.getCurrency());
					System.out.println("Would you like to divest from this tile? [Y/N]");
					downgradableCount++;
					downgradeChoice = confirmChoice(input);
					if (downgradeChoice.equalsIgnoreCase("Y")) {	
						((OwnableTile)g).decreaseUpgradeLevel(player);
						System.out.println(((OwnableTile)g).getTileName() + " is now at investment level '" + ((OwnableTile)g).upgradelevelAsString() + "'\n");
						break;
					} else {
						continue;
					}
				}
			}
		}
		if(downgradableCount == 0) {
			System.out.println("__________________________________________________");
			System.out.println("You do not have any tiles eligible for divestment.\n");
			System.out.println("__________________________________________________\n");
		}
	}
	
	/**
	 * Method determines if player has any tiles to auction and calls the auction tile method which excludes the initiating player
	 * @param input
	 * @param player
	 * @param gameVars
	 * @param gameBoard
	 * @return
	 */
	public void auctionOwnTileSelection(java.util.Scanner input,Player player, GameVariables gameVars, GameManager gameManager) {
		int auctionableCount = 0;
		String confirmChoice;
		showTilesOwned(player, gameBoard);
		for (GameTile g : gameBoard) {
			if(g.getClass().equals(OwnableTile.class)) {
				// if tile belongs to player
				if(player.getPlayerID() == ((OwnableTile)g).getOwnerID()) {
					// only allow sale of tiles without a Technopoly on the field
					if(((OwnableTile)g).isUpgradable() == false) {
						System.out.println(((OwnableTile)g).getTileName() + " " + "\nWould you like to auction this tile to the highest bidder? [Y/N]");
						auctionableCount++;
						confirmChoice = confirmChoice(input);
						if (confirmChoice.equalsIgnoreCase("Y")) {
							((OwnableTile)g).auctionTile(player, gameVars, input, gameManager);
							break;
						} else {
							continue;
						}
					} else {
						System.out.println(((OwnableTile)g).getTileName() + " cannot be auctioned as you have Technopoly on the field - " + ((OwnableTile)g).getFieldName() + "\n");
					}
				}
			}
		} if (auctionableCount == 0) {
			System.out.println("_______________________________________________");
			System.out.println("You do not have any tiles eligible for auction.");
			System.out.println("_______________________________________________\n");
		}
	}
	
	/**
	 * Method compares bid against previous player bids and returns true if a lesser one is present
	 * @param players
	 * @return
	 */
	public boolean compareBids(ArrayList<Player> players, int bid) {
	
		boolean lesserBid = false;
		for(Player p : players) {
			if(bid > p.getPlayerBid()) {
				lesserBid = false;
			} else {
				lesserBid = true;
				break; // break loop if greater bid is detected
			}
		}	
		return lesserBid;
	}
	
	/**
	 * Method resets all players bids to zero
	 */
	public void resetBids(ArrayList<Player> players) {
		for(Player p : players) {
			p.setPlayerBid(0);
		}
	}
	
	/**
	 * Method returns the index of player based on their playerID being passed in
	 */
	public int playerIndexFromID(ArrayList<Player> players, int playerID) {
		int playerIndex = -1;
		for(Player p : players) {
			if(playerID == p.getPlayerID()) {
				playerIndex = players.indexOf(p);
				break;
			}
		}
		return playerIndex;
	}
	
	/**
	 * Method returns a clone of the player list passed in
	 * @param players
	 * @return
	 */
	public ArrayList<Player> cloneList(ArrayList<Player> players) {
		
		ArrayList<Player> clonedList = new ArrayList<Player>(players.size());
		for (Player p : players) {
			clonedList.add(new Player(p));
		}
		return clonedList;
	}
	
	/**
	 * Method determines and displays winner of the game
	 * @param players
	 * @param gameVars
	 * @return
	 */
	public void gameOver(ArrayList<Player> players, GameVariables gameVars) {

		Player winner = players.get(0);
		System.out.println("Closing Balances (" + gameVars.getCurrency() + "):\n");
		System.out.printf("%-20s %-10s\n", "Name", "Balance");
		for(Player p : players) {
			System.out.printf("%-20s %-10d\n", p.getPlayerName(), p.getResourceBalance());
			if (p.getResourceBalance() > winner.getResourceBalance()) {
				winner = p;
			}
		}
		System.out.println("\n******************************");
		System.out.println("The Victor is " + winner.getPlayerName() + "!!!");
		System.out.println("******************************\n");
		System.out.println(winner.getPlayerName() + " has shown the competition how 'ruthless corporate execuitive' should be done!");
		System.out.println("Perhaps the defeated should reconsider their positions, perhaps sign up for the Apprentice!");
		System.out.println("\n******************");
		System.out.println("**  Game Over!  **");
		System.out.println("******************");
		gameOver = true;
	}		
	
}