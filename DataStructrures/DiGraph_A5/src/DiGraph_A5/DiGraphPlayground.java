package DiGraph_A5;

public class DiGraphPlayground {

	public static void main(String[] args) {
		DiGraph d = new DiGraph();
		d.addNode(1, "a");
		d.addNode(2, "b");
		d.addNode(3, "c");
		d.addNode(4, "d");
		d.addEdge(1, "a", "b", 1, null);
		d.addEdge(2, "b", "c", 2, null);
		d.addEdge(3, "c", "a", 3, null);
		d.addEdge(5, "c", "d", 2, null);
		d.addEdge(4, "d", "b", 1, null);
		
		System.out.println("It's juicy time");
		
		ShortestPathInfo[] juicy = d.shortestPath("a");
		
		for(int i = 0; i < juicy.length; i++) {
			System.out.println("Node: " + juicy[i].getDest() + " and Distance: " + juicy[i].getTotalWeight());
		}
	}

}
