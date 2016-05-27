import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AdjacencyListTests {

	Node n1;
	Node n2;
	Node n3;
	Node n4;
	Node n5;
	
	Edge e1;
	Edge e2;
	Edge e3;
	Edge e4;
	Edge e5;
	Edge e6;
	Edge e7;
	Edge e8;
	
	AdjacencyList al1;
	AdjacencyList al2;
	AdjacencyList al3;
	
	@Before
	public void setUp() throws Exception {
		
		n1 = new Node(1);
		n2 = new Node(2);
		n3 = new Node(3);
		n4 = new Node(4);
		n5 = new Node(5);
		
		e1 = new Edge(n1,n2,1.15f);
		e2 = new Edge(n3,n4,0.38f);
		e3 = new Edge(n1,n3,51.807f);
		e4 = new Edge(n3,n5,101.017f);
		e5 = new Edge(n1,n2,-51.2f);
		e6 = new Edge(n1,null,1.15f);
		e7 = new Edge(null,n2,0.38f);
		e8 = new Edge(null,null,51.807f);
		
		al1 = new AdjacencyList();
		al2 = new AdjacencyList();
		al3 = new AdjacencyList();
	}

	@After
	public void tearDown() throws Exception {
		n1 = null;
		n2 = null;
		n3 = null;
		n4 = null;
		n5 = null;
		
		e1 = null;
		e2 = null;
		e3 = null;
		e4 = null;
		e5 = null;
		e6 = null;
		e7 = null;
		e8 = null;
		
		al1 = null;
		al2 = null;
		al3 = null;
	}

	@Test
	public void testGetNumEdgesInit() {
		assertTrue(al1.getNumEdges() == 0);
		assertTrue(al2.getNumEdges() == 0);
		assertTrue(al3.getNumEdges() == 0);
	}
	
	@Test
	public void testGetNumEdges() {
		assertTrue(al1.getNumEdges() == 0);
		assertTrue(al2.getNumEdges() == 0);
		assertTrue(al3.getNumEdges() == 0);
		al1.addEdge(e1);
		al2.addEdge(e3);
		al2.addEdge(e4);
		al3.addEdge(e7);
		assertTrue(al1.getNumEdges() == 1);
		assertTrue(al2.getNumEdges() == 2);
		assertTrue(al3.getNumEdges() == 1);
		al1.addEdge(e5);
		al1.addEdge(e2);
		al3.addEdge(e8);
		al1.addEdge(e6);
		assertTrue(al1.getNumEdges() == 4);
		assertTrue(al2.getNumEdges() == 2);
		assertTrue(al3.getNumEdges() == 2);
	}
	
	@Test
	public void testGetAddEdge() {
		assertEquals(null, al1.getEdgeAt(0));
		assertEquals(null, al2.getEdgeAt(0));
		assertEquals(null, al3.getEdgeAt(0));
		al1.addEdge(e1);
		al2.addEdge(e3);
		al2.addEdge(e4);
		al3.addEdge(e7);
		assertEquals(e1, al1.getEdgeAt(0));
		assertEquals(e3, al2.getEdgeAt(0));
		assertEquals(e4, al2.getEdgeAt(1));
		assertEquals(e7, al3.getEdgeAt(0));
		al1.addEdge(e5);
		al1.addEdge(e2);
		al3.addEdge(e8);
		al1.addEdge(e6);
		assertEquals(e1, al1.getEdgeAt(0));
		assertEquals(e5, al1.getEdgeAt(1));
		assertEquals(e2, al1.getEdgeAt(2));
		assertEquals(e6, al1.getEdgeAt(3));
		assertEquals(e3, al2.getEdgeAt(0));
		assertEquals(e4, al2.getEdgeAt(1));
		assertEquals(e7, al3.getEdgeAt(0));
		assertEquals(e8, al3.getEdgeAt(1));
	}
	
	@Test
	public void testEnhancedForLoopEmpty() {
		int i = 0;
		for ( Edge e : al1){
			i ++;
		}
		assertTrue(i == 0);
	}
	
	@Test
	public void testEnhancedForLoop() {
		al1.addEdge(e1);
		al1.addEdge(e5);
		al1.addEdge(e2);
		al1.addEdge(e6);
		al2.addEdge(e3);
		al3.addEdge(e7);
		al3.addEdge(e8);
		al3.addEdge(e4);
		
		Edge[] set1 = new Edge[4];
		Edge[] set2 = new Edge[1];
		Edge[] set3 = new Edge[3];
		
		set1[0] = e1;
		set1[1] = e5;
		set1[2] = e2;
		set1[3] = e6;
		set2[0] = e3;
		set3[0] = e7;
		set3[1] = e8;
		set3[2] = e4;
		
		int i = 0;
		for (Edge e : al1){
			assertEquals(set1[i],e);
			i ++;
		}
		
		i = 0;
		for (Edge e : al2){
			assertEquals(set2[i],e);
			i ++;
		}
		
		i = 0;
		for (Edge e : al3){
			assertEquals(set3[i],e);
			i ++;
		}
	}

}
