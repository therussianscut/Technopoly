/**
 * 
 */
package technopoly_group_28;

/**
 * Class allows creation of a Starting tile for Technopoly game.
 * @author David McElhill
 *
 */
public class StartingTile extends GameTile {

	/**
	 * Constructor with name arg
	 * 
	 * @param tileName
	 */
	public StartingTile(String tileName) {
		super(tileName);				
	}
	
	/* 
	 * Method carries out tile's function on the game board
	 */
	@Override
	public void tileFunction(Player player, GameVariables gameVars, java.util.Scanner input, GameManager gameManager) {
		System.out.println("You have passed " + getTileName() + " and been awarded "
				+ gameVars.getPassStartResources() + " " + gameVars.getCurrency() + "!\n");
		player.increaseResource(gameVars.getPassStartResources());
	}

	/*
	 * Returns String with all tile's information
	 */
	@Override
	public String tileInfo() {
		return "__________________________________________________\nTile Name\t\t: " + getTileName();
	}

}