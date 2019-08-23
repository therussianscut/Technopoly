/**
 * 
 */
package technopoly_group_28;

/**
 * Store file names, more for experimentation..
 * @author Daithi
 *
 */
public enum ConfigFiles {
	
	CONFIG_FILE("config.properties"), BACKUP_FILE("backupconfig.properties"), CONFIG2_FILE("config2.properties");
	
	private String customName;
	
	/**
	 * 
	 * @param fieldname
	 */
	private ConfigFiles(String customName) {
		this.customName = customName;
	}
	
	/**
	 * @return the customName
	 */
	public String getCustomName() {
		return this.customName;
	}	

}
