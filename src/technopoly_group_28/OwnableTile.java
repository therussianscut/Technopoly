/**
 * 
 */
package technopoly_group_28;

import java.util.ArrayList;

/**
 * Class allows creation of ownable tiles and mwthods for managing them
 * @author David McElhill
 *
 */
public class OwnableTile extends GameTile {

	private String fieldName;
	private int tileCost;
	private int upgradeLevel = 0;
	private int upgradeCost;
	private int baseCharge;
	private int upgrade1Charge;
	private int upgrade2Charge;
	private int upgrade3Charge;
	private int upgrade4Charge;
	private int ownerID = 0;
	private boolean upgradable = false;
	
	// control variables
	private final int MIN_LEVEL = 0;
	private final int TILE_UNOWNED = 0;
	
	//constant to be accessed by Game manager method
	public static final int MAX_LEVEL = 4;
	
	/**
	 * Constructor with args
	 * @param tileName
	 * @param fieldName
	 * @param tileCost
	 * @param upgradeLevel
	 * @param upgradeCost
	 * @param baseCharge
	 * @param upgrade1Charge
	 * @param upgrade2Charge
	 * @param upgrade3Charge
	 * @param upgrade4Charge
	 * @param ownerID
	 */
	public OwnableTile(String tileName, String fieldName, int tileCost, int upgradeCost,
			int baseCharge) {
		super(tileName);
		this.fieldName = fieldName;
		this.tileCost = tileCost;
		this.upgradeCost = upgradeCost;
		this.baseCharge = baseCharge;
		this.upgrade1Charge = baseCharge * 2;
		this.upgrade2Charge = baseCharge * 3;
		this.upgrade3Charge = baseCharge * 4;
		this.upgrade4Charge = baseCharge * 5;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the tileCost
	 */
	public int getTileCost() {
		return tileCost;
	}

	/**
	 * @param tileCost the tileCost to set
	 */
	public void setTileCost(int tileCost) {
		this.tileCost = tileCost;
	}

	/**
	 * @return the upgradeCost
	 */
	public int getUpgradeCost() {
		return upgradeCost;
	}

	/**
	 * @param upgradeCost the upgrade1Cost to set
	 */
	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	/**
	 * @param baseCharge the baseCharge to set
	 */
	public void setBaseCharge(int baseCharge) {
		this.baseCharge = baseCharge;
	}

	/**
	 * @param upgrade1Charge the upgrade1Charge to set
	 */
	public void setUpgrade1Charge(int upgrade1Charge) {
		this.upgrade1Charge = upgrade1Charge;
	}

	/**
	 * @param upgrade2Charge the upgrade2Charge to set
	 */
	public void setUpgrade2Charge(int upgrade2Charge) {
		this.upgrade2Charge = upgrade2Charge;
	}

	/**
	 * @param upgrade3Charge the upgrade3Charge to set
	 */
	public void setUpgrade3Charge(int upgrade3Charge) {
		this.upgrade3Charge = upgrade3Charge;
	}


	/**
	 * @param upgrade4Charge the upgrade4Charge to set
	 */
	public void setUpgrade4Charge(int upgrade4Charge) {
		this.upgrade4Charge = upgrade4Charge;
	}
	
	/**
	 * returns the tile's charge (rent) amount based on its upgrade level 
	 * @param upgrade4Charge the upgrade4Charge to set
	 */
	public int determineCharge() {
		switch (upgradeLevel) {
		case 0:
			return baseCharge;
		case 1:
			return upgrade1Charge;
		case 2:
			return upgrade2Charge;
		case 3:
			return upgrade3Charge;
		case 4:
			return upgrade4Charge;
		default:
			return 0;
		}
		
	}
	
	/**
	 * Method returns a string representing the upgrade level of a tile
	 * @return String 
	 */
	public String upgradelevelAsString() {
		switch (upgradeLevel) {
		case 0:
			return "None";
		case 1:
			return "Bronze";
		case 2:
			return "Silver";
		case 3:
			return "Gold";
		case 4:
			return "Platinum";
		default:
			return "None";
		}
	}
	
	/**
	 * Method to return 'Yes' or 'No' instead of true or false  
	 * @return
	 */
	public String isUpgradeableAsString() {
		if (isUpgradable()) {
			return "Yes";
		} else {
			return "No";
		}
	}
	

	/**
	 * @return the ownerID
	 */
	public int getOwnerID() {
		return ownerID;
	}

	/**
	 * @param ownerID the ownerID to set
	 */
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	
	/**
	 * @return the upgradeLevel
	 */
	public int getUpgradeLevel() {
		return upgradeLevel;
	}

	/**
	 * increase (upgrade) level of the tile
	 */
	public void increaseUpgradeLevel(Player player) {
		if (upgradeLevel >= MIN_LEVEL && upgradeLevel < MAX_LEVEL) {
			player.decreaseResource(upgradeCost);
			upgradeLevel++;
		} else if (upgradeLevel == MAX_LEVEL) {
			System.out.println("Tile cannot be upgraded any further!");
		} else {
			System.out.println("Something went wrong");
		}
	}
	
	/**
	 * decrease (upgrade) level of the tile
	 */
	public void decreaseUpgradeLevel(Player player) {
		if (upgradeLevel > MIN_LEVEL && upgradeLevel <= MAX_LEVEL) {
			upgradeLevel--;
			player.increaseResource(upgradeCost / 2);
		} else if (upgradeLevel == MIN_LEVEL) {
			System.out.println("Tile cannot be downgraded any further!");
		} else {
			System.out.println("Something went wrong");
		}
			
	}
	
	/**
	 * @return the upgradable
	 */
	public boolean isUpgradable() {
		return upgradable;
	}
	
	/**
	 * @param upgradable the upgradable to set
	 */
	public void setUpgradable(boolean upgradable) {
		this.upgradable = upgradable;
	}
	
	/**
	 * Method to check if tile is upgradable based on ownership of all tiles in the same field
	 * Designed for use after a tile has just being purchased, as that was convenient..
	 * @param gameBoard
	 * @return
	 */
	public boolean checkFieldUpgradable(ArrayList<GameTile> gameBoard) {
		
		int totCount = 0;
		int ownedCount = 0;
		boolean fieldUpgradeable = false;	
		
		// evaluate only if tile's upgradable is currently false
		if (!isUpgradable()) { 
			for (GameTile g : gameBoard) {
				// evaluate only ownable tiles and exclude others (starting, mid)
				if(g.getClass().equals(OwnableTile.class)) {
					// check the tile's field name and count the total occurrences in gameBoard
					if(getFieldName().equals(((OwnableTile)g).getFieldName())) {
						totCount++;
						// counts how many tiles from the field the player owns 
						if(getOwnerID() == ((OwnableTile)g).getOwnerID()) {
							ownedCount++;
						}
					}
				}
			}
			if (totCount == ownedCount) {
				fieldUpgradeable = true;
			} else {
				fieldUpgradeable = false;
			}
		}
		return fieldUpgradeable;		
	}
	
	/**
	 * Method to set tile as others in the same field as upgradable
	 * @param gameBoard
	 */
	public void setFieldUpgradable(ArrayList<GameTile> gameBoard) {
		for (GameTile g : gameBoard) {
			if(g.getClass().equals(OwnableTile.class)) {
				if(getFieldName().equals(((OwnableTile)g).getFieldName())) {
					((OwnableTile)g).setUpgradable(true);
				}
			}
		}
	}

	/**
	 * Method to buy a tile - set owner and deduct the cost from them
	 * @param player
	 */
	public void buyTile(Player player, GameManager gameManager) {
		player.decreaseResource(tileCost);
		setOwnerID(player.getPlayerID());
		// as its convenient to do so after tile purchase, check if tile and entire field is now upgradable
		 if (checkFieldUpgradable(gameManager.gameBoard)) {
			 // set field as upgradable
			 setFieldUpgradable(gameManager.gameBoard);
		 }
	}
	
	/**
	 * Method to buy a tile using auction bid cost instead of default tile cost
	 * @param player
	 * @param bid
	 * @param gameManager
	 */
	public void buyTile(Player player, int bid, GameManager gameManager) {
		player.decreaseResource(bid);
		setOwnerID(player.getPlayerID());
		// as its convenient to do so on tile purchase, check if tile and entire field is now upgradable
		 if (checkFieldUpgradable(gameManager.gameBoard)) {
			// set field as upgradable
			 setFieldUpgradable(gameManager.gameBoard);
		 }
	}
	
	/**
	 * Method to auction off a tile when player lands on, and decides not to buy.
	 * This method includes the initiating player in the bidding process
	 * @param gameVars
	 * @param input
	 * @param gameManager
	 */
	public void auctionTile(GameVariables gameVars, java.util.Scanner input, GameManager gameManager) {
		
		int bid, winnerIndex;
		bid = 0;
		ArrayList<Player> cloneList = gameManager.cloneList(gameManager.players);
		
		do {
			for(int loop = 0; loop < cloneList.size(); loop++) {
				System.out.println("\n" + cloneList.get(loop).getPlayerName() + "\nCurrent Balance: " + cloneList.get(loop).getResourceBalance() + " " + gameVars.getCurrency());
				System.out.println("Please enter your bid or '0' to opt out:");
				bid = input.nextInt();
				// valid bidding range is between 0 and player's current resource balance
				if(bid >= 0 && bid <= cloneList.get(loop).getResourceBalance()) {					
					if(bid == 0) {
						// zero bid removes player from the bidding process
						cloneList.remove(cloneList.get(loop));
						loop--; // to compensate for the removal of a player and process next player, now at same index position as opted out player was
						System.out.println("You have opted out of bidding.");
						if ((cloneList.size() == 1)){
							break; //exit looping if only 1 player remains so they d0 not need to bid against themselves					
						}
					} else if (bid > 0 && bid < 50){
						System.out.println("Starting bid must be greater than 50 " + gameVars.getCurrency());
						loop--; // allow same player to bid again when loop restarts
						continue;
					} else if (gameManager.compareBids(cloneList, bid)) {
						System.out.println("You must bid higher than the previous player!");
						loop--; // allow same player to bid again when loop restarts
						continue;
					} else {
						cloneList.get(loop).setPlayerBid(bid);
					}
				// invalid bid -> above players current resource balance
				} else if (bid > cloneList.get(loop).getResourceBalance()){
					System.out.println("You can't afford that much! Your current balance is " + cloneList.get(loop).getResourceBalance() + " " + gameVars.getCurrency() + "!");
					loop--; // allow same player to bid again when loop restarts
					continue;
				// bid entered was less than zero
				} else {
					System.out.println("Bid must be greater than zero!");
					loop--; // allow same player to bid again when loop restarts
					continue;
				}
			}
		} while(cloneList.size() > 1);
		
		System.out.println("\nWinning bid is " + cloneList.get(0).getPlayerBid() + " " + gameVars.getCurrency() + " from " + cloneList.get(0).getPlayerName() + "!");
		System.out.println(getTileName() + " now acquired.\n");
		winnerIndex = gameManager.playerIndexFromID(gameManager.players, cloneList.get(0).getPlayerID());
		buyTile(gameManager.players.get(winnerIndex), cloneList.get(0).getPlayerBid(), gameManager);
		
		cloneList.clear(); // clear cloneList
		gameManager.resetBids(gameManager.players); //resets player bids for next time..
		input.nextLine(); //clear newline character from int input
		
	}
	
	/**
	 * Method to auction off a tile already owned by a player, when they want to raise some resources.
	 * Initiating player is excluded from the bidding.
	 * @param player
	 * @param gameVars
	 * @param input
	 * @param gameManager
	 */
	public void auctionTile(Player player, GameVariables gameVars, java.util.Scanner input, GameManager gameManager) {
		
		int bid, winnerIndex;
		bid = 0;
		ArrayList<Player> cloneList = gameManager.cloneList(gameManager.players);
		cloneList.remove(gameManager.playerIndexFromID(gameManager.players, player.getPlayerID()));
		
		do {
			for(int loop = 0; loop < cloneList.size(); loop++) {
				System.out.println("\n" + cloneList.get(loop).getPlayerName() + "\nCurrent Balance: " + cloneList.get(loop).getResourceBalance() + " " + gameVars.getCurrency());
				System.out.println("Please enter your bid or '0' to opt out:");
				bid = input.nextInt();
				// valid bidding range is between 0 and player's current resource balance
				if(bid >= 0 && bid <= cloneList.get(loop).getResourceBalance()) {					
					if(bid == 0) {
						// zero bid removes player from the bidding process
						cloneList.remove(cloneList.get(loop));
						loop--; // to compensate for the removal of a player and process next player, now at same index position as opted out player
						System.out.println("You have opted out of bidding.");
						if ((cloneList.size() == 1)){
							break; //exit looping if only 1 player remains so they d0 not need to bid against themselves			
						}
					} else if (bid > 0 && bid < 50){
						System.out.println("Starting bid must be greater than 50 " + gameVars.getCurrency());
						loop--; // allow same player to bid again when loop restarts
						continue;
					} else if (gameManager.compareBids(cloneList, bid)) {
						System.out.println("You must bid higher than the previous player!");
						loop--; // allow same player to bid again when loop restarts
						continue;
					} else {
						cloneList.get(loop).setPlayerBid(bid);
					}
				// invalid bid -> above players current resource balance
				} else if (bid > cloneList.get(loop).getResourceBalance()){
					System.out.println("You can't afford that much! Your current balance is " + cloneList.get(loop).getResourceBalance() + " " + gameVars.getCurrency() + "!");
					loop--; // allow same player to bid again when loop restarts
					continue;
				// bid entered was less than zero
				} else {
					System.out.println("Bid must be greater than zero!");
					loop--; // allow same player to bid again when loop restarts
					continue;
				}
			}
		} while(cloneList.size() > 1);
		
		System.out.println("\nWinning bid is " + cloneList.get(0).getPlayerBid() + " " + gameVars.getCurrency() + " from " + cloneList.get(0).getPlayerName() + "!");
		System.out.println(getTileName() + " now acquired.\n");
		
		winnerIndex = gameManager.playerIndexFromID(gameManager.players, cloneList.get(0).getPlayerID());
		// deducts the winning bid amount from buyer
		buyTile(gameManager.players.get(winnerIndex), cloneList.get(0).getPlayerBid(), gameManager);
		player.increaseResource(cloneList.get(0).getPlayerBid()); // pays the seller the winning bid amount
		
		cloneList.clear(); // clear cloneList
		gameManager.resetBids(gameManager.players); //resets player bids for next time..
		input.nextLine(); //clear newline character
		
	}
		
	/**
	 * Method carries out tile's function on the game board
	 */
	@Override
	public void tileFunction(Player player, GameVariables gameVars, java.util.Scanner input, GameManager gameManager) {
		String proceedFlag;
		System.out.println("Landed on\t: " + getTileName() + "\nField Name\t: " + getFieldName() + "\nInvest Level\t: " + upgradelevelAsString() + "\n");
		// if tile is currently unowned and player has sufficient resources to buy
		if (this.ownerID == TILE_UNOWNED && player.getResourceBalance() >= getTileCost()) {
				System.out.println("Tile is currently unowned and costs " + getTileCost() + " " + gameVars.getCurrency() + ".\nWould you like to acquire it? [Y/N]");
				proceedFlag = gameManager.confirmChoice(input);
			// player wants to buy tile
			if(proceedFlag.equalsIgnoreCase("Y")) {
				System.out.println("A wise decision! You have acquired " +  getTileName() + " and been deducted " + getTileCost() +" " + gameVars.getCurrency() + "!\n");
				buyTile(player, gameManager);
			// player doesn't want to buy tile, it gets auctioned off
			} else if (proceedFlag.equalsIgnoreCase("N")){
				System.out.println(getTileName() + " is going up for auction!\n");
				auctionTile(gameVars, input, gameManager);
			}
			// if tile is currently unowned and player has insufficient resources to buy
		} else if (this.ownerID == TILE_UNOWNED && player.getResourceBalance() < getTileCost()) {
			System.out.println("You have insufficient funds to acquire this tile, so it will go up for auction!");
			auctionTile(gameVars, input, gameManager);
		// if player already owns tile, end turn as they already have tile management options at beginning of turn
		} else if(this.ownerID == player.getPlayerID()) {
			System.out.println("You already own this tile!");
		// tile is owned by someone else, pay rent
		} else {
			System.out.println("Tile is controlled by " + gameManager.getOwner(getOwnerID() - 1).getPlayerName()
					+ ", so you've been charged " + determineCharge() + " " + gameVars.getCurrency() + " for the privilege!\n");
			player.decreaseResource(determineCharge());
			gameManager.getOwner(getOwnerID() - 1).increaseResource(determineCharge());
			if (player.isBroke()) {
				gameManager.setGameOver(true);
			}

		}
	}

	/* 
	 * Returns String with all tile's information
	 */
	@Override
	public String tileInfo() {
		
		return "__________________________________________________\nTile Name\t\t: " + getTileName() + "\nField Name\t\t: " 
				+ fieldName + "\nTile Cost\t\t: " + tileCost + "\nUpgrade Level\t\t: " + upgradelevelAsString() + "\nUpgrade Cost\t\t: " 
				+ upgradeCost + "\nBase Charge\t\t: " + baseCharge + "\nUpgrade 1 Charge\t: " + upgrade1Charge
				+ "\nUpgrade 2 Charge\t: "+ upgrade2Charge + "\nUpgrade 3 Charge\t: " + upgrade3Charge + "\nUpgrade 4 Charge\t: "
				+ upgrade4Charge + "\nOwner ID\t\t: " + ownerID;
	}
		
}