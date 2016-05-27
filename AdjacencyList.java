import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;


public class AdjacencyList implements Iterable<Edge>{

	private ArrayList<Edge> edges;
	private int numEdges;
	
	public AdjacencyList(){
		edges = new ArrayList();
		numEdges = 0;
	}
	
	public int getNumEdges(){
		return numEdges;
	}
	
	public void addEdge(Edge e){
		edges.add(e);
		numEdges ++;
	}
	
	protected Edge getEdgeAt(int index){
		if ( (index < 0) || (index >= numEdges) ){ return null; }
		return edges.get(index);
	}
	
	@Override
	public Iterator iterator() {
		return new AdjacencyListIterator(this);
	}

}
