/**
 * 
 */
package technopoly_group_28;

/**
 * Class to allow creation of a game variables object to hold file input variables
 * @author David McElhill
 *
 */
public class GameVariables {
	
	private String currency;
	private int startingResources;
	private int passStartResources;
	private int maxPlayers;
	private int dieFaces;
	private int ownableTiles;
	
	/**
	 * No args constructor
	 */
	public GameVariables() {

	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the startingResources
	 */
	public int getStartingResources() {
		return startingResources;
	}

	/**
	 * @param startingResources the startingResources to set
	 */
	public void setStartingResources(int startingResources) {
		this.startingResources = startingResources;
	}

	/**
	 * @return the passStartResources
	 */
	public int getPassStartResources() {
		return passStartResources;
	}

	/**
	 * @param passStartResources the passStartResources to set
	 */
	public void setPassStartResources(int passStartResources) {
		this.passStartResources = passStartResources;
	}

	/**
	 * @return the maxPlayers
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}

	/**
	 * @param maxPlayers the maxPlayers to set
	 */
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	/**
	 * @return the dieFaces
	 */
	public int getDieFaces() {
		return dieFaces;
	}

	/**
	 * @param dieFaces the dieFaces to set
	 */
	public void setDieFaces(int dieFaces) {
		this.dieFaces = dieFaces;
	}

	/**
	 * @return the ownableTiles
	 */
	public int getOwnableTiles() {
		return ownableTiles;
	}

	/**
	 * @param ownableTiles the ownableTiles to set
	 */
	public void setOwnableTiles(int ownableTiles) {
		this.ownableTiles = ownableTiles;
	}

}