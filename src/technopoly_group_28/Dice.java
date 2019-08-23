/**
 * 
 */
package technopoly_group_28;

import java.util.Random;

/**
 * Class allows creation of Dice with between 1 and 6 faces.
 * @author David McElhill
 *
 */
public class Dice {
	
	private int dieFaces;
	private final Random rand = new Random();
	
	// constants to limit number of faces
	private final int MIN_FACES = 1;
	private final int MAX_FACES = 6;
		
	/**
	 * Die constructor with user specified faces from config.properties file
	 */
	public Dice(GameVariables gameVars) {
		this.dieFaces = gameVars.getDieFaces();
	}

	/**
	 * Dice constructor allowing variable number of faces
	 * @param dieFaces
	 */
	public Dice(int dieFaces) {
		// calls the setter to enforce the rules on face number
		this.setDieFaces(dieFaces);
	}

	/**
	 * @return the dieFaces
	 */
	public int getDieFaces() {
		return dieFaces;
	}

	/**
	 * Set dice faces, limited 1 - 6
	 * @param dieFaces the dieFaces to set
	 */
	public void setDieFaces(int dieFaces) throws IllegalArgumentException {
		if (dieFaces >= MIN_FACES && dieFaces <= MAX_FACES) {
			this.dieFaces = dieFaces;
		} else {
			throw new IllegalArgumentException("Number of faces must be between 1 and 6 inclusive");
		}
	}
	
	/**
	 * Method to return int simulating the roll of a die
	 * @return
	 */
	public int dieRoll() {
		return rand.nextInt(dieFaces) + 1;		
	}
	
}