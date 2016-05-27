import java.util.Iterator;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
	
	private Graph graph;
	private PriorityQueue<Node> nodeQueue;
	
	protected DijkstraAlgorithm(Graph g){
		graph = g;
		nodeQueue = new PriorityQueue<Node>(new NodeComparator());
	}
	
	public void addNode(int nodeIndex){
		Node n = graph.getNode(nodeIndex);
		nodeQueue.add(n);
	}
	
	public void addNode(Node n){
		addNode(n.getIndex());
	}

	public Node[] getNodes(){
		if (nodeQueue.size() == 0){
			System.err.println("Warning: DijkstraAlgorithm has no nodes.");
			return null;
		}
		
		Node[] nodes = new Node[nodeQueue.size()];
		Iterator<Node> itt = nodeQueue.iterator();
		for (int i = 0; i < nodes.length; i++){
			nodes[i] = itt.next();
		}
		return nodes;
	}
	
	protected void relax(int i){
		Node v = graph.getNode(i);
		for (Edge e : graph.getEdgesAround(v)){
			Node w = e.getOtherEnd(v);
			if (w.getDistanceTo() > (v.getDistanceTo() + e.getWeight())){
				w.setDistanceTo(v.getDistanceTo() + e.getWeight());
				w.setEdgeTo(e);
				if (!nodeQueue.contains(w)){
					nodeQueue.add(w);
				}
			}
		}
	}
	
	protected void relax(Node n){
		relax(n.getIndex());
	}
	
	public void shortestPath(int startIndex){
		
		nodeQueue = new PriorityQueue<Node>(new NodeComparator());
		Node s = graph.getNode(startIndex);
		
		for (Node u : graph.getNodes()){
			u.setDistanceTo(Float.POSITIVE_INFINITY);
			u.setEdgeTo(null);
		}
		
		s.setDistanceTo(0f);
		nodeQueue.add(s);
		
		while (!nodeQueue.isEmpty()){
			relax(nodeQueue.remove());
		}
		
	}
}
