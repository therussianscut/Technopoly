/**
 * 
 */
package technopoly_group_28;

/**
 * Class allows creation of Middle tile of the game board.
 * @author David McElhill
 *
 */
public class MidTile extends GameTile {
	
		/**
		 * Constructor with name arg and sets game board index 
		 * as the halfway point on the board
		 * @param tileName
		 */
		public MidTile(String tileName) {
			super(tileName);					
		}
		
		/* 
		 * Method carries out tile's function on the game board
		 */
		@Override
		public void tileFunction(Player player, GameVariables gameVars, java.util.Scanner input, GameManager gameManager) {
			System.out.println("You have landed on " +  getTileName() + " and can take no further action this turn!\n");
		}

		/*
		 * Returns String with all tile's information
		 */
		@Override
		public String tileInfo() {
			return "__________________________________________________\nTile Name\t\t: " + getTileName();
		}

}