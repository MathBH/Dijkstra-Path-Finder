import java.util.LinkedList;
import java.util.Stack;
import java.util.LinkedList;

public class PathFinder {

	/**
	 * This algorithm was originally supposed to be a generic algorithm class,
	 * the type of which would be specified when calling the constructor. I
	 * am low on time though so I skipped this feature.
	 */
	
	/**
	 * When moving, the pathFinder will note every intermediate node to a path.
	 * If a waypoint cannot be reached, this point will be stored in the path with
	 * as -(waypoint + 1). When printing, we then know this represents and unreachable
	 * waypoint since a node cannot have a negative index. The +1 is to make sure we do
	 * not have a 0 for which we could not tell the polarity.
	 */
	private LinkedList<Integer> path;
	private Stack<Integer> stack;
	private Graph graph;
	private DijkstraAlgorithm algorithm; 
	
	public PathFinder(Graph g){
		graph = g;
		algorithm = new DijkstraAlgorithm(graph);
		path = new LinkedList<Integer>();
		stack = new Stack();
	}
	
	public void move(int iStart, int iEnd){
		algorithm.shortestPath(iStart);

		int i = iEnd;
		
		while( i != iStart ){
			if (graph.getNode(i).getEdgeTo() == null){
				stack.clear();
				stack.push(-(iEnd+1));
				i = iStart;
				break;
			}
			stack.push(i);
			i = graph.getNode(i).getEdgeTo().getTail().getIndex();
		}
		stack.push(i);
		
		//Check if the head of the current path is the same as the start of
		//the path in our stack. Pop this first node if so.
		if (path.size() > 0){
			if (path.getLast() == stack.peek()){ stack.pop();}
		}
		
		loadPath();
	}
	
	private void loadPath(){
		while(!stack.isEmpty()){
			path.add(stack.pop());
		}
	}
	
	public void multiPath(int[] waypoints){
		
		/**
		 * This will call the move function for each waypoint and its
		 * successor. Since the function uses i as a starting point to
		 * then move to a next i + 1 waypoint, and the final waypoint
		 * has no successor, it is reduced by 1.
		 */
		for (int i = 0; i < waypoints.length - 1;){
			move(waypoints[i], waypoints[++i]);
		}
	}
	
	public void printPath(){
		System.out.print("Path: [");
		for (int i = 0; i < path.size(); i ++){
			int node = path.get(i);
			if (node < 0){
				System.out.print("NO PATH TO [" + (-(node+1)) + "]");
			} else {
				System.out.print(node);
			}
			if (i < path.size() - 1){
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
	
	public void resetPath(){
		path.clear();
	}
	
}
