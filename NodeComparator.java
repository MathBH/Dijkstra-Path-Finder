import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node n1, Node n2) {
		return Float.compare(n1.getDistanceTo(), n2.getDistanceTo());
	}

}
