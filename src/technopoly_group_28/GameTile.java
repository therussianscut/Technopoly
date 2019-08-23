/**
 * 
 */
package technopoly_group_28;

/**
 * Template for creation of Game Tiles for Technopoly game board
 * @author David McElhill
 *
 */
public abstract class GameTile {
	
	private String tileName;
	
	/**
	 * Game tile constructor with only tileName arg
	 * @param tileName
	 */
	public GameTile(String tileName) {
		this.setTileName(tileName);
	}

	/**
	 * @return the tileName
	 */
	public String getTileName() {
		return tileName;
	}

	/**
	 * Sets tile name - 'default' if none entered
	 * @param tileName the tileName to set
	 */
	public void setTileName(String tileName) {
		if(tileName == null) {
			System.out.println("No name entered!");
			this.tileName = "default";
		} else if (tileName.trim().length() == 0) {
			System.out.println("No name entered!");
			this.tileName = "default";
		} else {
			this.tileName = tileName;
		}
	}
	
	/* 
	 * Method carries out tile's function on the game board
	 */
	public abstract void tileFunction(Player player, GameVariables gameVars, java.util.Scanner input, GameManager gameManager);

	/* 
	 * Returns String with tile's information
	 */
	public abstract String tileInfo();
	}
