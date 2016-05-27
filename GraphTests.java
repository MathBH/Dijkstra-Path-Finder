import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


//Credits: Code for checking the err stream from "dfa" at http://stackoverflow.com/questions/1119385/junit-test-for-system-out-println

public class GraphTests {

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
	
	Graph g;
	
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
		n4 = new Node(4);
		n5 = new Node(5);
		
		e1 = new Edge(n1,n2,1.15f);
		e2 = new Edge(n3,n4,0.38f);
		e3 = new Edge(n1,n3,51.807f);
		e4 = new Edge(n3,n5,101.017f);
		e6 = new Edge(n1,null,1.15f);
		e7 = new Edge(null,n2,0.38f);
		e8 = new Edge(null,null,51.807f);
		
		al1 = new AdjacencyList();
		al2 = new AdjacencyList();
		al3 = new AdjacencyList();
		
		g = new Graph(5);
		
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
		
		g = null;
		
		System.setErr(null);
		
	}

	@Test
	public void testNumNodesInit() {
		assertTrue(g.getNumNodes() == 0);
	}
	
	@Test
	public void testAddNodeOutOfBounds() {
		g.addNode(n5);
		assertTrue(g.getNumNodes() == 0);
		assertEquals(null, g.getNode(0));
		assertEquals(null, g.getNode(4));
	}
	
	@Test
	public void testAddNodeNumNodes() {
		g.addNode(n1);
		assertTrue(g.getNumNodes() == 1);
		g.addNode(n2);
		assertTrue(g.getNumNodes() == 2);
		g.addNode(n3);
		g.addNode(n4);
		assertTrue(g.getNumNodes() == 4);
	}
	
	@Test
	public void testAddNodeNumNodesSameNode() {
		g.addNode(n1);
		assertTrue(g.getNumNodes() == 1);
		g.addNode(n1);
		assertTrue(g.getNumNodes() == 1);
		g.addNode(n1);
		g.addNode(n1);
		assertTrue(g.getNumNodes() == 1);
	}
	
	@Test
	public void testGetNullNodeEmpty() {
		assertEquals(null,g.getNode(0));
	}
	
	@Test
	public void testGetNodeOutOfBoundsInit() {
		assertEquals(null,g.getNode(200));
	}
	
	@Test
	public void testAddGetNode() {
		g.addNode(n1);
		assertEquals(n1,g.getNode(1));
		g.addNode(n4);
		assertEquals(n4,g.getNode(4));
		g.addNode(n2);
		assertEquals(n2,g.getNode(2));
	}
	
	@Test
	public void testGetNodeOutOfBounds() {
		g.addNode(n1);
		g.addNode(n5);
		g.addNode(n2);
		assertEquals(null,g.getNode(10));
	}
	
	@Test
	public void testGetNodeOutOfBoundsNeg() {
		g.addNode(n1);
		g.addNode(n5);
		g.addNode(n2);
		assertEquals(null,g.getNode(-1));
	}
	
	@Test
	public void testGetNodeOutOfBoundsMix() {
		g.addNode(n1);
		assertEquals(null,g.getNode(10));
		assertEquals(n1,g.getNode(1));
		g.addNode(n4);
		assertEquals(null,g.getNode(-37));
		assertEquals(n4,g.getNode(4));
		g.addNode(n2);
		assertEquals(n2,g.getNode(2));
	}
	
	@Test
	public void testGetNullNode() {
		g.addNode(n1);
		g.addNode(n5);
		g.addNode(n2);
		assertEquals(null,g.getNode(3));
	}
	
	@Test
	public void testGetNullNodeMix() {
		g.addNode(n1);
		assertEquals(null,g.getNode(4));
		assertEquals(n1,g.getNode(1));
		g.addNode(n4);
		assertEquals(null,g.getNode(2));
		assertEquals(n4,g.getNode(4));
		g.addNode(n2);
		assertEquals(n2,g.getNode(2));
	}
	
	@Test
	public void testGetNumEdgesInit() {
		assertTrue(g.getNumEdges() == 0);
	}
	
	@Test
	public void testAddGetNumEdges() {
		assertTrue(g.getNumEdges() == 0);
		g.addEdge(e1);
		assertTrue(g.getNumEdges() == 1);
		g.addEdge(e2);
		assertTrue(g.getNumEdges() == 2);
		g.addEdge(e3);
		g.addEdge(e4);
		assertTrue(g.getNumEdges() == 4);
	}

	@Test
	public void testGetEdgesAroundIntInit0() {
		AdjacencyList alTest = g.getEdgesAround(0);
		assertTrue(alTest.getNumEdges() == 0);
	}
	
	@Test
	public void testGetEdgesAroundIntInitNum() {
		AdjacencyList alTest = g.getEdgesAround(4);
		assertTrue(alTest.getNumEdges() == 0);
	}
	
	@Test
	public void testGetEdgesAroundIntNeg() {
		assertEquals(null, g.getEdgesAround(-4));
	}
	
	@Test
	public void testGetEdgesAroundIntOutOfBounds() {
		assertEquals(null, g.getEdgesAround(11));
	}
	
	@Test
	public void testGetEdgesAroundIntNoEdges() {
		AdjacencyList alTest;
		g.addNode(n1);
		alTest = g.getEdgesAround(1);
		assertTrue(alTest.getNumEdges() == 0);
	}
	
	@Test
	public void testAddGetEdgesIntAround() {
		
		AdjacencyList alTest;
		
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		
		g.addEdge(e1);
		
		alTest = g.getEdgesAround(1);
		
		assertTrue(alTest.getNumEdges() == 1);
		assertEquals(e1,alTest.getEdgeAt(0));
		
		alTest = g.getEdgesAround(2);
		
		assertTrue(alTest.getNumEdges() == 0);
		
		g.addEdge(e2);
		
		alTest = g.getEdgesAround(3);
		
		assertTrue(alTest.getNumEdges() == 1);
		assertEquals(e2,alTest.getEdgeAt(0));
		
		alTest = g.getEdgesAround(4);
		
		assertTrue(alTest.getNumEdges() == 0);
		
		alTest = g.getEdgesAround(1);
		
		assertTrue(alTest.getNumEdges() == 1);
		assertEquals(e1,alTest.getEdgeAt(0));
		
		alTest = g.getEdgesAround(2);
		
		assertTrue(alTest.getNumEdges() == 0);		
		
		g.addEdge(e3);
		
		alTest = g.getEdgesAround(1);
		
		assertTrue(alTest.getNumEdges() == 2);
		assertEquals(e1,alTest.getEdgeAt(0));
		assertEquals(e3,alTest.getEdgeAt(1));
		
		alTest = g.getEdgesAround(3);
		
		assertTrue(alTest.getNumEdges() == 1);
		assertEquals(e2,alTest.getEdgeAt(0));
		
	}
	
	@Test
	public void testGetEdgesAroundNodeInit0() {
		Node n0 = new Node(0);
		AdjacencyList alTest = g.getEdgesAround(n0);
		assertTrue(alTest.getNumEdges() == 0);
	}
	
	@Test
	public void testGetEdgesAroundNodeInitNum() {
		AdjacencyList alTest = g.getEdgesAround(n4);
		assertTrue(alTest.getNumEdges() == 0);
	}
	
	@Test
	public void testGetEdgesAroundNodeNoEdges() {
		AdjacencyList alTest;
		g.addNode(n1);
		alTest = g.getEdgesAround(n1);
		assertTrue(alTest.getNumEdges() == 0);
	}
	
	@Test
	public void testAddGetEdgesNodeAround() {
		
		AdjacencyList alTest;
		
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		
		g.addEdge(e1);
		
		alTest = g.getEdgesAround(n1);
		
		assertTrue(alTest.getNumEdges() == 1);
		assertEquals(e1,alTest.getEdgeAt(0));
		
		alTest = g.getEdgesAround(n2);
		
		assertTrue(alTest.getNumEdges() == 0);
		
		g.addEdge(e2);
		
		alTest = g.getEdgesAround(n3);
		
		assertTrue(alTest.getNumEdges() == 1);
		assertEquals(e2,alTest.getEdgeAt(0));
		
		alTest = g.getEdgesAround(n4);
		
		assertTrue(alTest.getNumEdges() == 0);
		
		alTest = g.getEdgesAround(n1);
		
		assertTrue(alTest.getNumEdges() == 1);
		assertEquals(e1,alTest.getEdgeAt(0));
		
		alTest = g.getEdgesAround(n2);
		
		assertTrue(alTest.getNumEdges() == 0);		
		
		g.addEdge(e3);
		
		alTest = g.getEdgesAround(n1);
		
		assertTrue(alTest.getNumEdges() == 2);
		assertEquals(e1,alTest.getEdgeAt(0));
		assertEquals(e3,alTest.getEdgeAt(1));
		
		alTest = g.getEdgesAround(n3);
		
		assertTrue(alTest.getNumEdges() == 1);
		assertEquals(e2,alTest.getEdgeAt(0));
		
	}
	
	@Test
	public void testLoadFromInexistantFile() {
		System.setErr(new PrintStream(errContent));
		g.loadFromFile("dsadas");
		assertTrue(errContent.toString().contains("java.io.FileNotFoundException: dsadas (The system cannot find the file specified)"));
	}
	
	@Test
	public void testLoadFromFileErr2() {
		System.setErr(new PrintStream(errContent));
		try {
			g.loadFromFile("src/GraphTestErr2.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Invalid Edge [5, csda, 0.28] at line position 5."));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFileEmpty() throws IOException {
		g.loadFromFile("src/GraphTestEmpty.txt");
		assertTrue(g.getNumNodes() == 0);
		assertTrue(g.getNumEdges() == 0);
	}
	
	@Test
	public void testLoadFromFileBlankLine() {
		System.setErr(new PrintStream(errContent));
		try {
			g.loadFromFile("src/GraphTestBlankLine.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Invalid File Type: Empty line at pos: 4"));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFileErr3() {
		System.setErr(new PrintStream(errContent));
		try {
			g.loadFromFile("src/GraphTestErr3.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Invalid Edge [a, 5, 0.35] at line position 2."));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFileNegativeNumNodes() {
		System.setErr(new PrintStream(errContent));
		try {
			g.loadFromFile("src/GraphTestNegNodes.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Invalid File Type: Tried to pass -8 node count."));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFileNegativeNumEdges() {
		System.setErr(new PrintStream(errContent));
		try {
			g.loadFromFile("src/GraphTestNegEdges.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Invalid File Type: Tried to pass -5 edge count."));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFileTooManyEdges() {
		System.setErr(new PrintStream(errContent));
		try {
			g.loadFromFile("src/GraphTestTooManyEdges.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Invalid File Type: asserted edge count: 5, actual edge count: 7."));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFileInvalidWeights() {
		System.setErr(new PrintStream(errContent));
		try{
			g.loadFromFile("src/GraphTestInvalidWeights.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Invalid Edge [4, 5, 0.adsad] at line position 6."));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFileTooManyTokens() {
		System.setErr(new PrintStream(errContent));
		try{
			g.loadFromFile("src/GraphTestTooManyTokens.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Edge entry at line 3 has 4 token(s). (Edge entry format only accepts 3 tokens: \"int int float\")"));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFileTooFewTokens() {
		System.setErr(new PrintStream(errContent));
		try{
			g.loadFromFile("src/GraphTestTooFewTokens.txt");
		} catch (AssertionError e) {
			assertTrue(errContent.toString().contains("Error: Edge entry at line 4 has 1 token(s). (Edge entry format only accepts 3 tokens: \"int int float\")"));
			return;
		}
		fail("error was not detected.");
	}
	
	@Test
	public void testLoadFromFile() throws AssertionError {
		Graph tg = new Graph(8);
		g.loadFromFile("src/GraphTest1.txt");
		int tnumNodes = 8;
		
		Node[] tn = new Node[tnumNodes];
		
		for (int i = 0; i < tnumNodes; i ++){
			tn[i] = new Node(i);
		}
		
		assertNull(tn[0].getEdgeTo());
		
		for (int i = 0; i < tnumNodes; i ++){
			tg.addNode(tn[i]);
		}
		
		Edge[] et = new Edge[5];
		
		et[0] = new Edge(tn[4],tn[5],0.35f);
		et[1] = new Edge(tn[0],tn[4],0.35f);
		et[2] = new Edge(tn[4],tn[7],0.37f);
		et[3] = new Edge(tn[5],tn[7],0.28f);
		et[4] = new Edge(tn[7],tn[1],0.35f);
		
		for (int i = 0; i < 5; i ++){
			tg.addEdge(et[i]);
		}
		
		assertTrue(g.getNumNodes() == tg.getNumNodes());
		assertTrue(g.getNumEdges() == tg.getNumEdges());
		
		for (int i = 0; i < 11; i ++){
			if (tg.getNode(i) == null & g.getNode(i) == null && i >= tnumNodes){
				continue;
			}
			assertTrue(tg.getNode(i).getIndex() == g.getNode(i).getIndex());
			assertTrue(tg.getNode(i).isMarked() == g.getNode(i).isMarked());
			assertEquals(tg.getNode(i).getEdgeTo(), g.getNode(i).getEdgeTo());
		}
		for (int i = 0; i < 13; i ++){
			AdjacencyList altctrl = tg.getEdgesAround(i);
			AdjacencyList altreal = g.getEdgesAround(i);
			
			int j = 0;
			if (altctrl != null && altreal != null){
				for (Edge e : altreal){
					assertTrue(e.getWeight() == altctrl.getEdgeAt(j).getWeight());
					assertTrue(e.getTail().getIndex() == altctrl.getEdgeAt(j).getTail().getIndex());
					assertTrue(e.getTail().isMarked() == altctrl.getEdgeAt(j).getTail().isMarked());
					assertEquals(altctrl.getEdgeAt(j).getTail().getEdgeTo(),e.getTail().getEdgeTo());
					assertTrue(e.getHead().getIndex() == altctrl.getEdgeAt(j).getHead().getIndex());
					assertTrue(e.getHead().isMarked() == altctrl.getEdgeAt(j).getHead().isMarked());
					assertEquals(altctrl.getEdgeAt(j).getHead().getEdgeTo(),e.getHead().getEdgeTo());
					j ++;
				}
			} else {
				assertEquals(altctrl, altreal);
			}
		}	
	}
	
	@Test
	public void testMakeFromFile() throws IOException {
		Graph tg = new Graph("src/mediumEWD.txt");
		AdjacencyList tal = tg.getEdgesAround(244);
		Edge te = tal.getEdgeAt(0);
		assertTrue(te.getHead().getIndex() == 246);
		assertTrue(te.getWeight() == 0.11712f);
	}
	
	@Test
	public void testMakeFromBigFile() throws IOException {
		Graph tg = new Graph("src/10000EWD.txt");
	}
}
