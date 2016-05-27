
public class Edge {

	private Node tail;
	private Node head;
	private float weight;
	
	public Edge(Node t, Node h, float w) throws AssertionError{
		if (w < 0){
			System.err.println("Error: Attempted to pass negative weight: " + w + ".");
			throw new AssertionError();
		}
		tail = t;
		head = h;
		weight = w;
	}
	
	public Node getHead(){
		return head;
	}
	
	public Node getTail(){
		return tail;
	}
	
	public Node getOtherEnd(int i){
		if (tail.getIndex() == i){
			return head;
		}
		if (head.getIndex() == i){
			return tail;
		}
		return null;
	}
	
	public Node getOtherEnd(Node n){
		return getOtherEnd(n.getIndex());
	}
	
	public float getWeight(){
		return weight;
	}
	
}
