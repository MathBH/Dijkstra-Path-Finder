import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DijkstraTest {
	
	DijkstraAlgorithm d;
	Graph g;
	DijkstraAlgorithm d2;
	Graph g2;
	
	//Here we are saving the default error output for future reference.
	PrintStream stderr = System.err;
		
	//Here we are initializing an output stream which we will use to evaluate
	//error output for certain tests.
	ByteArrayOutputStream errContent = new ByteArrayOutputStream();
			
	@Before
	public void setUp() throws Exception {
		g = new Graph("src/tinyEWD.txt");
		d = new DijkstraAlgorithm(g);
		g2 = new Graph("src/microEWD.txt");
		d2 = new DijkstraAlgorithm(g2);
		
		/**
		 * Here I am reseting the error output stream. I do this because some
		 * test cases will require this to be modified. If this is not then
		 * reset before another test is executed, some errors might go undetected
		 * by JUnit.
		 */
		System.setErr(stderr);
		//System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		g = null;
		d = null;
		System.setErr(null);
	}
	
	@Test
	public void testGetNodesInit() {
		System.setErr(new PrintStream(errContent));
		assertEquals(null, d.getNodes());
		assertTrue(errContent.toString().contains("Warning: DijkstraAlgorithm has no nodes."));
	}
	
	@Test
	public void testAddGetNodes() {
		d.addNode(5);
		assertEquals(g.getNode(5),d.getNodes()[0]);
		d.addNode(6);
		assertEquals(g.getNode(6),d.getNodes()[1]);
		d.addNode(7);
		assertEquals(g.getNode(7),d.getNodes()[2]);
		assertNotEquals(g.getNode(7),d.getNodes()[0]);
		assertNotEquals(g.getNode(5),d.getNodes()[2]);
	}
	
	@Test
	public void testRelaxIdx() {
		d.addNode(4);
		d.addNode(5);
		d.addNode(7);
		g.getNode(4).setDistanceTo(0);
		d.relax(4);
		Node[] nodes = d.getNodes();
		
		assertEquals(g.getNode(5),nodes[1]);
		assertEquals(g.getNode(4), nodes[1].getEdgeTo().getOtherEnd(5));
		assertTrue(nodes[1].getEdgeTo().getWeight() == 0.35f);
		
		assertEquals(g.getNode(7),nodes[2]);
		assertEquals(g.getNode(4), nodes[2].getEdgeTo().getOtherEnd(7));
		assertTrue(nodes[2].getEdgeTo().getWeight() == 0.37f);
	}
	
	@Test
	public void testRelaxNode() {
		d.addNode(4);
		d.addNode(5);
		d.addNode(7);
		g.getNode(4).setDistanceTo(0);
		d.relax(g.getNode(4));
		Node[] nodes = d.getNodes();
		
		assertEquals(g.getNode(5),nodes[1]);
		assertEquals(g.getNode(4), nodes[1].getEdgeTo().getOtherEnd(5));
		assertTrue(nodes[1].getEdgeTo().getWeight() == 0.35f);
		
		assertEquals(g.getNode(7),nodes[2]);
		assertEquals(g.getNode(4), nodes[2].getEdgeTo().getOtherEnd(7));
		assertTrue(nodes[2].getEdgeTo().getWeight() == 0.37f);
	}
	
	@Test
	public void testShortestPath() {
		d2.shortestPath(1);
		int[] expected2 = {6, 2};
		Node currNode2 = g2.getNode(6);
		int j = 0;
		while (currNode2.getIndex() != 1){
			assertTrue(currNode2.getIndex() == expected2[j++]);
			currNode2 = currNode2.getEdgeTo().getTail();
		}
		assertTrue(currNode2.getIndex() == 1);
		
		d.shortestPath(1);
		int[] expected = {7, 2, 6, 3};
		Node currNode = g.getNode(7);
		int i = 0;
		while (currNode.getIndex() != 1){
			assertTrue(currNode.getIndex() == expected[i++]);
			currNode = currNode.getEdgeTo().getTail();
		}
		assertTrue(currNode.getIndex() == 1);
	}
	
	

}
