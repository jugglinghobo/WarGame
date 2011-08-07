package warGameTests;

import static org.junit.Assert.*;
import org.junit.*;
import ch.aplu.jgamegrid.*;
import warGame.*;


public class CityTest {
	
	
	City Bern, Basel;
	
	@Before
	public void initialize() {
		Input.setInput(new MockInput());
		Bern = new City("Bern", new Location(20, 20), null);
		Basel = new City("Basel", new Location(40, 40), null);
	}
	
	@Test
	public void testToString() {
		assertEquals(Bern.toString(), "Bern");
		assertEquals(Basel.toString(), "Basel");
	}
}
