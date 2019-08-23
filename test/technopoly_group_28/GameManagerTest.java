package technopoly_group_28;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class GameManagerTest {
	
	GameManager test= new GameManager();
	boolean gameOver = false;
	
	@Before
	public void setUp() throws Exception {
		
	}
	@Test
	public void test() {
		
		
		assertNotNull(test);
		
	}
	

	
	
	
	@Test
	public void testBooleanGameOver() {
		
		test.setGameOver(gameOver);
		assertEquals(gameOver, test.isGameOver());
		
	}
	
	
}
