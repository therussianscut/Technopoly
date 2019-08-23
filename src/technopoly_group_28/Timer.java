/**
 * 
 */
package technopoly_group_28;

/**
 * A bit of fun! Class creates a countdown timer for decision making
 * @author David McElhill
 *
 */
public class Timer implements Runnable {
	
	/**
	 * constant for countdown timer (seconds)
	 */
	private final int DECISION_TIMER = 30;
	
	@Override
	public void run() {
		this.countDown();		
	}
	
	/**
	 * Method to prompt player to hurry up!
	 */
	public void countDown() {

		try {
			Thread.sleep(1000L * DECISION_TIMER);
			System.out.println("\nHurry up! How can you hope to Technopolise, with this level of indecisiveness!");
			Thread.sleep(1000L * DECISION_TIMER);
			System.out.println("Hurry up!!!");
			Thread.sleep(1000L * DECISION_TIMER);
			System.out.println("Are you sure you're cut out for these high pressure decisions?!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
