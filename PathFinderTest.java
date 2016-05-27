import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PathFinderTest {

	Graph g;
	Graph discG;
	PathFinder pf;
	PathFinder discPf;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		g = new Graph("src/tinyEWD.txt");
		discG = new Graph("src/DisconectedGraph.txt");
		pf = g.getPathFinder();
		discPf = discG.getPathFinder();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() throws Exception {
		g = null;
		pf = null;
		//System.setOut(null);
	}

	@Test
	public void testSmallMove() {
		pf.move(4, 7);
		pf.printPath();
		assertTrue(outContent.toString().contains("Path: [4, 7]"));
	}
	
	@Test
	public void testNormalMove() {
		pf.move(2, 6);
		pf.printPath();
		assertTrue(outContent.toString().contains("Path: [2, 7, 3, 6]"));
	}
	
	@Test
	public void testResetAndMove() {
		pf.move(4, 7);
		pf.printPath();
		assertTrue(outContent.toString().contains("Path: [4, 7]"));
		pf.resetPath();
		pf.move(1,5);
		pf.resetPath();
		pf.move(0, 7);
		pf.printPath();
		assertTrue(outContent.toString().contains("Path: [0, 2, 7]"));
	}
	
	@Test
	public void testMoveBack() {
		pf.move(4, 7);
		pf.move(7, 4);
		pf.printPath();
		assertTrue(outContent.toString().contains("Path: [4, 7, 5, 4]"));
	}
	
	@Test
	public void testMultiMove() {
		int[] waypoints = {0,4,1,7,5};
		pf.multiPath(waypoints);
		pf.printPath();
		assertTrue(outContent.toString().contains("Path: [0, 4, 5, 1, 3, 6, 2, 7, 5]"));
	}
	
	@Test
	public void testDisconnectedMove() {
		discPf.move(9, 3);
		discPf.printPath();
		//assertTrue(outContent.toString().contains("Path: [9, -3]"));
		assertTrue(outContent.toString().contains("Path: [9, NO PATH TO [3]]"));
	}
	
	@Test
	public void testDisconnectedMoves() {
		discPf.move(9, 0);
		discPf.move(0, 3);
		discPf.move(3, 4);
		discPf.move(4, 8);
		discPf.printPath();
		assertTrue(outContent.toString().contains("Path: [9, NO PATH TO [0], 0, 1, 2, 3, NO PATH TO [4], 4, 5, 6, 7, 8]"));
	}

}
