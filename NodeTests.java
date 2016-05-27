import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NodeTests {
	Node n1;
	Node n2;
	Node n3;
	Node n77;
	Node n0;
	Node nN1;
	Node nNBig;
	
	NodeComparator nc;
	
	Edge e1;
	Edge e2;
	Edge e3;
	
	//Here we are saving the default error output for future reference.
	PrintStream stderr = System.err;
		
	//Here we are initializing an output stream which we will use to evaluate
	//error output for certain tests.
	ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		n1 = new Node(1);
		n2 = new Node(2);
		n3 = new Node(3);
		n77 = new Node(77);
		n0 = new Node(0);
		
		nc = new NodeComparator();
		
		e1 = new Edge(n1,n77,0.5f);
		e2 = new Edge(n2,n0,3.14f);
		e3 = new Edge(n3,n77,1.51f);
		
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
	public void testNegativeNode() {
		System.setErr(new PrintStream(errContent));
		try {
			nN1 = new Node(-1);
		} catch(AssertionError e){
			assertTrue(errContent.toString().contains("Error: Attempted to pass negative node [-1]."));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testGetIndex() {
		assertTrue(n1.getIndex() == 1);
		assertTrue(n2.getIndex() == 2);
		assertTrue(n3.getIndex() == 3);
		assertTrue(n77.getIndex() == 77);
		assertTrue(n0.getIndex() == 0);
	}
	
	@Test
	public void testGetDistanceToInit() {
		assertTrue(n1.getDistanceTo() == Float.POSITIVE_INFINITY);
	}
	
	@Test
	public void testSetGetDistanceTo() {
		n1.setDistanceTo(1.10f);
		assertTrue(n1.getDistanceTo() == 1.10f);
		n1.setDistanceTo(13f);
		assertTrue(n1.getDistanceTo() == 13f);
	}
	
	@Test
	public void testCompareTo() {
		n1.setDistanceTo(10.3f);
		n2.setDistanceTo(5.7f);
		n3.setDistanceTo(10.3f);
		n0.setDistanceTo(53.101f);
		
		assertTrue(nc.compare(n1, n2) == 1);
		assertTrue(nc.compare(n1, n0) == -1);
		assertTrue(nc.compare(n1, n3) == 0);
	}
	
	@Test
	public void testDefaultMarking() {
		assertTrue(!n1.isMarked());
		assertTrue(!n2.isMarked());
		assertTrue(!n3.isMarked());
		assertTrue(!n77.isMarked());
		assertTrue(!n0.isMarked());
	}
	
	@Test
	public void testMarking() {
		n2.mark();
		n77.mark();
		assertTrue(!n1.isMarked());
		assertTrue(n2.isMarked());
		assertTrue(!n3.isMarked());
		assertTrue(n77.isMarked());
		assertTrue(!n0.isMarked());
	}
	
	@Test
	public void testUnmark() {
		n2.mark();
		n77.mark();
		n0.mark();
		assertTrue(n2.isMarked());
		assertTrue(n77.isMarked());
		assertTrue(n0.isMarked());
		n2.unmark();
		n77.unmark();
		n0.unmark();
		assertTrue(!n2.isMarked());
		assertTrue(!n77.isMarked());
		assertTrue(!n0.isMarked());
	}
	
	@Test
	public void testMultiMark() {
		n2.mark();
		n0.mark();
		assertTrue(n2.isMarked());
		assertTrue(n0.isMarked());
		n2.unmark();
		n3.mark();
		n0.unmark();
		n1.mark();
		assertTrue(!n2.isMarked());
		assertTrue(!n0.isMarked());
		assertTrue(n3.isMarked());
		assertTrue(n1.isMarked());
		n2.mark();
		n77.mark();
		n3.unmark();
		assertTrue(n2.isMarked());
		assertTrue(!n0.isMarked());
		assertTrue(!n3.isMarked());
		assertTrue(n77.isMarked());
		assertTrue(n1.isMarked());
	}
	
	@Test
	public void testGetEdgeToInit() {
		assertEquals(null,n1.getEdgeTo());
		assertEquals(null,n2.getEdgeTo());
		assertEquals(null,n3.getEdgeTo());
	}
	
	@Test
	public void testSetEdgeGetEdge() {
		n1.setEdgeTo(e1);
		n2.setEdgeTo(e3);
		n3.setEdgeTo(e2);
		
		assertEquals(e1,n1.getEdgeTo());
		assertEquals(e3,n2.getEdgeTo());
		assertEquals(e2,n3.getEdgeTo());
	}
	
	@Test
	public void testSetEdgeGetEdgex3() {
		n1.setEdgeTo(e1);
		n2.setEdgeTo(e3);
		n3.setEdgeTo(e2);
		
		assertEquals(e1,n1.getEdgeTo());
		assertEquals(e3,n2.getEdgeTo());
		assertEquals(e2,n3.getEdgeTo());
		
		Edge eN1 = new Edge(nN1,n0,1.54f);
		
		n1.setEdgeTo(e3);
		n3.setEdgeTo(e1);
		
		assertEquals(e3,n1.getEdgeTo());
		assertEquals(e1,n3.getEdgeTo());
		
		n2.setEdgeTo(e3);
		n3.setEdgeTo(e2);
		
		assertEquals(e3,n2.getEdgeTo());
		assertEquals(e2,n3.getEdgeTo());
	}

}
