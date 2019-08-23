/**
 * 
 */
package technopoly_group_28;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class to import .properties file to populate Technopoly game variables and GameTile objects
 * @author David McElhill
 *
 */
public class ImportData {
	
	// control variables for populating the game board array
	private int MID_TILE_INDEX;
	private int OWNABLE_TILES;

	/**
	 * 	
	/**
	 * Method to import Technopoly data file when passed Game Variables and GameTile ArrayList Objects
	 * Calls other sub-methods to call in: variables, property tiles, starting tile and mid tile separately
	 * @param gameVars
	 * @param gameBoard
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws NullPointerException
	 */
	public void importGameData(GameVariables gameVars, ArrayList<GameTile> gameBoard) throws IOException, FileNotFoundException, NullPointerException {
		
		String filePath = ConfigFiles.CONFIG2_FILE.getCustomName();
		InputStream input = null;
		// initialise properties object to hold properties from file
		Properties properties = new Properties();
		try {			
			input = new FileInputStream(new File(filePath));
			// load properties object with input from file
			properties.load(input);
			// method to import variables 
			importVariables(gameVars, properties);			
			// method to import game tile info			
			importGameTiles(gameBoard, properties);
			importStartTile(gameBoard, properties);
			importMidTile(gameBoard, properties);			
						
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	/**
	 * Method imports game variables from file
	 * @param gameVariables
	 * @param properties
	 */
	public void importVariables(GameVariables gameVariables, Properties properties) {
			
		gameVariables.setCurrency(properties.getProperty("currency.symbol"));
		gameVariables.setStartingResources(Integer.valueOf(properties.getProperty("starting.resources")));
		gameVariables.setPassStartResources(Integer.valueOf(properties.getProperty("pass.start.resources")));
		gameVariables.setMaxPlayers(Integer.valueOf(properties.getProperty("max.players")));
		gameVariables.setDieFaces(Integer.valueOf(properties.getProperty("die.faces")));
		// Initialisation of two control variables
		OWNABLE_TILES = Integer.valueOf(properties.getProperty("ownable.tiles"));
		gameVariables.setOwnableTiles(OWNABLE_TILES);
		MID_TILE_INDEX = (OWNABLE_TILES / 2) + 1;
	}
	
	/**
	 * Method imports game tile information and adds to Game board
	 * @param gameBoard
	 * @param properties
	 */
	private void importGameTiles(ArrayList<GameTile> gameBoard, Properties properties) {
		
		for(int loop = 1; loop <= OWNABLE_TILES; loop++) {
			gameBoard.add(new OwnableTile((properties.getProperty("tile" + loop + ".name")), (properties.getProperty("tile" + loop + ".field")),
			Integer.valueOf(properties.getProperty("tile" + loop + ".cost")), Integer.valueOf(properties.getProperty("tile" + loop + ".upgrade.cost")),
			Integer.valueOf(properties.getProperty("tile" + loop + ".base.rent"))));
		}
		
	}
	
	/**
	 * Method imports starting tile information and adds to Game board  at index 0
	 * @param gameBoard
	 * @param properties
	 */
	private void importStartTile(ArrayList<GameTile> gameBoard, Properties properties) {
		gameBoard.add(0, new StartingTile(properties.getProperty("start.tile.name")));
	}
	
	/**
	 * Method imports starting tile information and adds to Game board at the halfway point
	 * @param gameBoard
	 * @param properties
	 */
	private void importMidTile(ArrayList<GameTile> gameBoard, Properties properties) {
		gameBoard.add(MID_TILE_INDEX, new MidTile(properties.getProperty("mid.tile.name")));
	}
	
}