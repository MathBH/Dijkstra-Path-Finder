import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EdgeTests {

	Node n1 = new Node(1);
	Node n2 = new Node(2);
	Node n3 = new Node(3);
	Node n4 = new Node(4);
	Node n5 = new Node(5);
	
	Edge e1;
	Edge e2;
	Edge e3;
	Edge e4;
	Edge eN0;
	Edge eN1;
	Edge eN2;
	Edge eN3;
	
	//Here we are saving the default error output for future reference.
	PrintStream stderr = System.err;
			
	//Here we are initializing an output stream which we will use to evaluate
	//error output for certain tests.
	ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		e1 = new Edge(n1,n2,1.15f);
		e2 = new Edge(n3,n4,0.38f);
		e3 = new Edge(n1,n3,51.807f);
		e4 = new Edge(n3,n5,101.017f);
		eN1 = new Edge(n1,null,1.15f);
		eN2 = new Edge(null,n2,0.38f);
		eN3 = new Edge(null,null,51.807f);
		
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
	public void testNegativeWeight() {
		System.setErr(new PrintStream(errContent));
		try {
			Edge nEdge = new Edge(n1, n2, -10.5f);
		} catch (AssertionError e){
			assertTrue(errContent.toString().contains("Error: Attempted to pass negative weight: -10.5."));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testGetHead() {
		assertEquals(n2, e1.getHead());
		assertEquals(n4, e2.getHead());
		assertEquals(n3, e3.getHead());
		assertEquals(n5, e4.getHead());
	}
	
	@Test
	public void testGetTail() {
		assertEquals(n1,e1.getTail());
		assertEquals(n3,e2.getTail());
		assertEquals(n1,e3.getTail());
		assertEquals(n3,e4.getTail());
	}
	
	@Test
	public void testGetOtherEndInt() {
		assertEquals(n1,e1.getOtherEnd(2));
		assertEquals(n3,e2.getOtherEnd(4));
		assertEquals(n3,e3.getOtherEnd(1));
		assertEquals(n5,e4.getOtherEnd(3));
	}
	
	@Test
	public void testGetOtherEndIntNone() {
		assertEquals(null,e1.getOtherEnd(5));
		assertEquals(null,e2.getOtherEnd(11));
	}
	
	@Test
	public void testGetOtherEndNode() {
		assertEquals(e1.getOtherEnd(n2),n1);
		assertEquals(e2.getOtherEnd(n4),n3);
		assertEquals(e3.getOtherEnd(n1),n3);
		assertEquals(e4.getOtherEnd(n3),n5);
	}
	
	@Test
	public void testGetOtherEndNodeNone() {
		assertEquals(null,e1.getOtherEnd(n3));
		assertEquals(null,e2.getOtherEnd(n5));
	}
	
	@Test
	public void testGetWeight() {
		assertTrue(e1.getWeight() == 1.15f);
		assertTrue(e2.getWeight() == 0.38f);
		assertTrue(e3.getWeight() == 51.807f);
		assertTrue(e4.getWeight() == 101.017f);
	}

}
