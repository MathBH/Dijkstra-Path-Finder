
public class Node {
	
	private int index;
	private boolean marked;
	private Edge edgeTo;
	private float distanceTo;
	
	public Node(int i) throws AssertionError{
		if (i < 0){
			System.err.println("Error: Attempted to pass negative node [" + i + "].");
			throw new AssertionError();
		}
		index = i;
		marked = false;
		edgeTo = null;
		distanceTo = Float.POSITIVE_INFINITY;
	}
	
	public int getIndex(){
		return index;
	}
	
	public boolean isMarked(){
		return marked;
	}
	
	public void mark(){
		marked = true;
	}
	
	public void unmark(){
		marked = false;
	}
	
	/**
	 * I had considered putting EdgeTo in an Entry class but decided 
	 * against it when the time came to write my code. In retrospect,
	 * I should have put it in the Entry class rather than Node class
	 * as this would have been more intuitive. Also, I do not think
	 * EdgeTo is a graph property that is commonly expected to be of 
	 * access in a Node class.
	 * 
	 * I was going to put DistanceTo in an Entry class though I was low
	 * on time so I opted for this instead.
	 * @return
	 */
	
	public Edge getEdgeTo(){
		return edgeTo;
	}
	
	public void setEdgeTo(Edge e){
		edgeTo = e;
	}
	
	public void setDistanceTo(float newDistance){
		assert(newDistance > 0f);
		distanceTo = newDistance;
	}
	
	public float getDistanceTo(){
		return distanceTo;
	}
	
	public String toString(){
		return Integer.toString(index);
	}
}
