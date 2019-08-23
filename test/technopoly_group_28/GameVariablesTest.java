package technopoly_group_28;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameVariablesTest {

	GameVariables test = new GameVariables();
	String validCurrency = "ValidCurrency";
	int startingResources = 1000;
	int getpassStartresources=1;
	int maxPlayers=4;
	int dieFaces=6;
	int OwnableTiles=1;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGameVariables() {

		assertNotNull(test);
	}

	@Test
	public void testGetCurrency() {
		test.setCurrency(validCurrency);
		assertEquals(validCurrency, test.getCurrency());

	}

	@Test
	public void testGetStartingResources() {
		test.setStartingResources(startingResources);
		assertEquals(startingResources, test.getStartingResources());

	}

	@Test
	public void testGetPassStartResources() {
		test.setPassStartResources(getpassStartresources);
		assertEquals(getpassStartresources, test.getPassStartResources());
	}

	@Test
	public void testGetMaxPlayers() {
		test.setMaxPlayers(maxPlayers);
		assertEquals(maxPlayers, test.getMaxPlayers());
	}

	@Test
	public void testGetDieFaces() {
		test.setDieFaces(dieFaces);
		assertEquals(dieFaces, test.getDieFaces());
		
	}

	@Test
	public void testGetOwnableTiles() {
		test.setOwnableTiles(OwnableTiles);
		assertEquals(OwnableTiles, test.getOwnableTiles());
		
	}

}
