import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestToolTest {

	TestTool t;
	
	//Here we are saving the default error output for future reference.
	PrintStream stderr = System.err;
		
	//Here we are initializing an output stream which we will use to evaluate
	//error output for certain tests.
	ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		t = new TestTool();
		
		/**
		 * Here I am reseting the error output stream. I do this because some
		 * test cases will require this to be modified. If this is not then
		 * reset before another test is executed, some errors might go undetected
		 * by JUnit.
		 */
		System.setErr(stderr);
	}

	@After
	public void tearDown() throws Exception {
		System.setErr(null);
	}

	@Test
	public void loadWithWhiteSpace() throws IOException {
		t.loadWayPoints("src/waypointWS");
		int[] waypoints = t.getWayPoints();
		int[] expected = {5, 6, 4, 3};
		for (int i = 0; i < waypoints.length; i++){
			assertTrue(waypoints[i] == expected[i]);
		}
	}
	
	@Test
	public void loadWithNewLine() throws IOException {
		t.loadWayPoints("src/waypointNL");
		int[] waypoints = t.getWayPoints();
		int[] expected = {1, 4, 3, 2, 6};
		for (int i = 0; i < waypoints.length; i++){
			assertTrue(waypoints[i] == expected[i]);
		}
	}
	
	@Test
	public void loadBlankLine() {
		System.setErr(new PrintStream(errContent));
		try {
			t.loadWayPoints("src/waypointBlankLine");
		} catch (IOException e) {
			assertTrue(errContent.toString().contains("Error: Invalid File Type: Empty line at pos: 1"));
			return;
		}
		fail("error was not detected");
	}

}
