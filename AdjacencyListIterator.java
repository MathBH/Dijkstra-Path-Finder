import java.util.Iterator;


public class AdjacencyListIterator implements Iterator<Edge> {

	private AdjacencyList myList;
	private int index;
	
	public AdjacencyListIterator(AdjacencyList l){
		myList = l;
		index = 0;
	}
	
	@Override
	public boolean hasNext() {
		return (index < myList.getNumEdges());
	}

	@Override
	public Edge next() {
		return myList.getEdgeAt(index++);
	}

}
