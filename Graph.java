import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {
	
	private Node[] nodes;
	private AdjacencyList[] edges;
	
	private int numNodes;
	private int numEdges;
	
	//This exists for testing reasons only.
	protected Graph(int nNodes){
		
		nodes = new Node[nNodes];
		edges = new AdjacencyList[nNodes];
		
		for (int i = 0; i < edges.length; i ++){
			edges[i] = new AdjacencyList();
		}
		
		numNodes = 0;
		numEdges = 0;
		
	}
	
	public Graph(String filePath) throws IOException{
		this.loadFromFile(filePath);
	}
	
	public Node getNode(int i){
		if (i < 0 || i >= nodes.length){ return null; }
		return nodes[i];
	}
	
	public Node[] getNodes(){
		return nodes;
	}
	
	public int getNumEdges(){
		return numEdges;
	}
	
	public int getNumNodes(){
		return numNodes;
	}
	
	public AdjacencyList getEdgesAround(int i){
		if (i < 0 || i >= nodes.length){ return null; }
		return edges[i];
	}
	
	public AdjacencyList getEdgesAround(Node n){
		return getEdgesAround(n.getIndex());
	}
	
	protected void addNode(Node n){
		if (n.getIndex() < 0 || n.getIndex() >= nodes.length){ return; }
		if (nodes[n.getIndex()] != null) { return; }
		nodes[n.getIndex()] = n;
		numNodes ++;
	}
	
	protected void addEdge(Edge e){
		Node n = e.getTail();
		if (n == null){ return; }
		if (n.getIndex() < 0 || n.getIndex() >= nodes.length){ return; }
		edges[n.getIndex()].addEdge(e);
		numEdges ++;
	}
	
	public PathFinder getPathFinder(){
		return new PathFinder(this);
	}
	
	//This would normally be private but set to protected for testing purposes.
	protected void loadFromFile(String path) throws AssertionError{
		
		File file = new File(path);
		String linebuff = null;
		int tailbuff;
		int headbuff;
		float weightbuff;
		
		int assertedNumEdges = 0;
		numNodes = 0;
		numEdges = 0;
		
		int lineNum = 0;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			while((linebuff = reader.readLine()) != null) {
				
				if(linebuff.length() == 0) {
					System.err.println("Error: Invalid File Type: Empty line at pos: "+lineNum);
					throw new AssertionError();
				}
				
				//Load numNodes and initialize nodes and adjacency list.
				if (lineNum == 0){
					numNodes = Integer.valueOf(linebuff);
					if (numNodes < 0){
						System.err.println("Error: Invalid File Type: Tried to pass " + numNodes +" node count.");
						throw new AssertionError();
					}
					nodes = new Node[numNodes];
					edges = new AdjacencyList[numNodes];
					for (int i = 0; i < edges.length; i++){edges[i] = new AdjacencyList(); nodes[i] = new Node(i);}
				}
				
				//Take note of asserted Edge Count for later verification.
				if (lineNum == 1){
					assertedNumEdges = Integer.valueOf(linebuff);
					if (assertedNumEdges < 0){
						System.err.println("Error: Invalid File Type: Tried to pass " + assertedNumEdges +" edge count.");
						throw new AssertionError();
					}
				}
				
				if (lineNum != 0 && lineNum != 1){
					Edge edge;
					
					String[] tokens = linebuff.trim().split(" +");
					
					//Check that there are only 3 tokens
					if (tokens.length != 3){
						System.err.println("Error: Edge entry at line " + lineNum + " has " + tokens.length + " token(s). (Edge entry format only accepts 3 tokens: \"int int float\")");
						throw new AssertionError();
					}
					
					//Check if token[0], token[1] and token[2] are of int, int, float format.
					try {
						Integer.parseInt(tokens[0]);
						Integer.parseInt(tokens[1]);
						Float.parseFloat(tokens[2]);
					} catch(NumberFormatException e){
						System.err.println("Error: Invalid Edge [" + tokens[0] + ", " + tokens[1] + ", " + tokens[2] +"] at line position " + lineNum + ".");
						throw new AssertionError();
					}
					
					//Load token values to corresponding parameters.
					tailbuff = Integer.valueOf(tokens[0].trim());
					headbuff = Integer.valueOf(tokens[1].trim());
					weightbuff = Float.valueOf(tokens[2].trim());
					
					//Create and load edge.
					edge = new Edge(nodes[tailbuff], nodes[headbuff], weightbuff);
					addEdge(edge);
					
				}
				
				lineNum++;
				
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(numEdges != assertedNumEdges){
			System.err.println("Error: Invalid File Type: asserted edge count: " + assertedNumEdges + ", actual edge count: " + numEdges + ".");
			throw new AssertionError();
		}
	}
	
}
