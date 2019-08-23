package technopoly_group_28;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Sean McCann
 */
public class DiceTest {
	
	GameVariables gameVars = new GameVariables();
	Dice test= new Dice(gameVars);
	
	int dieFaceValid, dieFaceInvalid;
	
	@Before
	public void setUp() throws Exception {
		
		dieFaceValid=4;
		dieFaceInvalid=8;
		
	}
	
	@Test
	public void testDiceDefaultConstructor() {
		assertNotNull(test);
	}
	
	@Test
	public void testDiceConstructorWithargs() {
		Dice test= new Dice(dieFaceValid);
		assertNotNull(test);
		
		assertEquals(dieFaceValid, test.getDieFaces());
		
		
	}
	@Test
	public void testGetDieFaces() {
		
		int expected=6;
		test.setDieFaces(expected);
		int actual= test.getDieFaces();
		assertEquals(expected, actual);
			
	}
	
	
	@Test (expected=Exception.class)
	public void testSetDieFaces() throws Exception {
		test.setDieFaces(dieFaceInvalid);
		
	}
	
}