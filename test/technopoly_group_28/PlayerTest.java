package technopoly_group_28;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.Throwables;
public class PlayerTest {
	
	GameVariables gameVars = new GameVariables();
	Player test = new Player();
	String playerNameValid;
	int validPlayerBid=1;
	int resourcebalance=500;
	int increaseResource=500;
	
	@Before
	public void setUp() throws Exception {
		playerNameValid="ValidName";
		
	}
	@Test
	public void testDefaultConstructor() {
		assertNotNull(test);
	}
	
	
	@Test
	public void testPlayerConstructorArgs() {
		Player test = new Player();
		assertNotNull(test);
	}
	
	@Test
	public void testGetPlayerName() {
		String expected= "Sean McCann";
		test.setPlayerName(expected);
		String actual= test.getPlayerName();
		assertEquals(expected, actual);
		
		
	}
	@Test
	public void testGetResourceBalance() {
		int expected=1000;
		test.setResourceBalance(expected);
		int actual= test.getResourceBalance();
		assertEquals(expected, actual);
	}
	@Test
	public void testGetCurrentPosition() {
		int expected=4;
		test.setCurrentPosition(expected);
		int actual= test.getCurrentPosition();
		assertEquals(expected, actual);
	}
	@Test
	public void testGetBroke() {
		boolean expected= true;
		test.setBroke(expected);
		boolean actual= test.isBroke();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testValidBid() {
		test.setPlayerBid(validPlayerBid);
		assertEquals(validPlayerBid, test.getPlayerBid());
	}
	
	@Test
	public void testIncreaseResource() {
		
		test.setResourceBalance(resourcebalance + increaseResource);
		assertEquals(resourcebalance + increaseResource, test.getResourceBalance());
		
	}
	
}
