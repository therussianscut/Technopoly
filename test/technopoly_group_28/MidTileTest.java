package technopoly_group_28;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Sean McCann
 */
public class MidTileTest {
	
	
	MidTile test= new MidTile(null);
	
	String tileNameValid, tileNameInvalid;
	
	
	@Before
	public void setUp() throws Exception {
		
		tileNameValid="validName";
		tileNameInvalid="InvalidName";
	}
	@Test
	public void testTileFunction() {
		MidTile test= new MidTile(tileNameValid);
		assertNotNull(test);
	}
	@Test
	public void testTileInformation() {
		MidTile test= new MidTile(tileNameValid);
		assertNotNull(test);
	}
	@Test
	public void testMidTile() {
		
		MidTile test= new MidTile(tileNameValid);
		assertNotNull(test);
		assertEquals(tileNameValid, test.getTileName());
		
	}
}