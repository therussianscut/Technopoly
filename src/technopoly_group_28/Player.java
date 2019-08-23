/**
 * 
 */
package technopoly_group_28;

/**
 * Class allows creation of a Starting tile for gameboard.
 * @author David McElhill
 *
 */
public class Player {
	
	private String playerName;
	private int playerID;
	private int resourceBalance;
	private int currentPosition = 0;
	private boolean isBroke = false;
	private int playerBid = 0;
	
	/**
	 * No args constructor
	 * @param player
	 */
	public Player() {
		
	}
	
	/**
	 * Player constructor with Player object arg. 
	 * To create a clone of passed in player for the auction tile methods
	 * @param player
	 */
	public Player(Player player) {
		this.playerName = player.playerName;
		this.playerID = player.playerID;
		this.resourceBalance = player.resourceBalance;
		this.currentPosition = player.currentPosition;
	}
	
	/**
	 * Constructor with name arg and 2 default variables
	 * @param playerName
	 * @param resourceBalance
	 * @param currentPosition
	 */
	public Player(String playerName, int playerID, GameVariables gameVars) {
		this.playerName = playerName;
		this.setPlayerID(playerID, gameVars);
		this.resourceBalance = gameVars.getStartingResources();
		this.currentPosition = 0;
		this.isBroke = false;
	}
	
	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	/**
	 * @return the playerNumber
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * @param playerNumber the playerNumber to set
	 */
	public void setPlayerID(int playerID, GameVariables gameVars) throws IllegalArgumentException {
		if (playerID > 0 && playerID <= gameVars.getMaxPlayers()) {
			this.playerID = playerID;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return the resourceBalance
	 */
	public int getResourceBalance() {
		return resourceBalance;
	}

	/**
	 * @param resourceBalance the resourceBalance to set
	 */
	public void setResourceBalance(int resourceBalance) {
		this.resourceBalance = resourceBalance;
	}

	/**
	 * @return the currentPosition
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @param currentPosition the currentPosition to set
	 */
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	/**
	 * @return the isBroke
	 */
	public boolean isBroke() {
		return isBroke;
	}
	
	/**
	 * @param isBroke the isBroke to set
	 */
	public void setBroke(boolean isBroke) {
		this.isBroke = isBroke;
	}
	
	/**
	 * @return the playerBid
	 */
	public int getPlayerBid() {
		return playerBid;
	}

	/**
	 * @param playerBid the playerBid to set
	 */
	public void setPlayerBid(int playerBid) {
		this.playerBid = playerBid;
	}
	
	/**
	 * increases player's resource amount
	 * @param amount
	 */
	public void increaseResource(int amount) {
		System.out.println("Resource update for " + getPlayerName() + ":\n\nOld Balance\t: " + getResourceBalance());
		this.resourceBalance += amount;
		System.out.println("New Balance\t: " + getResourceBalance() + "\n");
	}
	
	/**
	 * decreases player's resource amount
	 * @param amount
	 */
	public void decreaseResource(int amount) {
		System.out.println("Resource update for " + getPlayerName() + ":\n\nOld Balance\t: " + getResourceBalance());
		this.resourceBalance -= amount;
		if (getResourceBalance() > 0) {
			System.out.println("New Balance\t: " + getResourceBalance() + "\n");
		} else {
			System.out.println(getPlayerName() + " is broke!!!\n");	
			setBroke(true);
		}
	}

	/**
	 * 
	 * @return playerInfo
	 */
	public String playerInfo() {
		return "__________________________________________________\nPlayer Name\t\t: " 
				+ playerName + "\nPlayer ID\t\t: " + playerID + "\nCurrent Resources\t: " + this.resourceBalance + 
				"\nCurrent Position\t: " + this.currentPosition + "\nIs Broke?\t\t: " 
				+ this.isBroke +"\n";
	}

}