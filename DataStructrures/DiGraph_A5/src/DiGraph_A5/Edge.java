package DiGraph_A5;

import java.util.HashMap;

public class Edge {
	long id;
	String label;
	String Dlabel;
	long weight;
	String eLabel;
	HashMap<Node, String> node;
	public Edge(long id, String label, String Dlabel, long weight, String eLabel) {
		this.id = id;
		this.label = label;
		this.Dlabel = Dlabel;
		this.weight = weight;
		this.eLabel = eLabel;
	}

}
