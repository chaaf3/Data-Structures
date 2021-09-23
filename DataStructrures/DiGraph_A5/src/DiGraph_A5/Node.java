package DiGraph_A5;

import java.util.HashMap;

public class Node implements Comparable<Node>{
	long id;
	String label;
	HashMap<String, Edge> outEdge;
	HashMap<String, Edge> inEdge;
	boolean visited;
	long shortest;
	
	public Node(long id, String label) {
		this.id = id;
		this.label = label;
		this.shortest = 0;
		this.visited = false;
		outEdge = new HashMap<String, Edge>();
		inEdge = new HashMap<String, Edge>();
	}

	@Override
	public int compareTo(Node o) {
		return (int) this.shortest - (int) o.shortest;
	}
	
	

}
