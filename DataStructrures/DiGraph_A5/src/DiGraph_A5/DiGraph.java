package DiGraph_A5;

import java.util.HashMap;
import java.util.*;

public class DiGraph implements DiGraphInterface {
	int edgeCounter;
	int nodeCounter;
	HashMap<Integer, Node> id;
	HashMap<String, Node> nodeLabel;
	HashMap<Integer, Edge> eId;
	HashMap<String, Edge> eLabel;
	HashMap<String, Integer> djikstra;

	public DiGraph() {
		this.id = new HashMap<Integer, Node>();
		this.nodeLabel = new HashMap<String, Node>();
		this.eId = new HashMap<Integer, Edge>();
		this.eLabel = new HashMap<String, Edge>();
		this.edgeCounter = 0;
		this.nodeCounter = 0;
		this.djikstra = new HashMap<String, Integer>();
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if (this.nodeLabel.containsKey(label)) {
			return false;
		}
		if (id.containsKey((int)idNum)) {
			return false;
		}
		if (idNum < 0) {
			return false;
		}
		if (label == null) {
			return false;
		}
		else {
			Node n = new Node(idNum, label);
			id.put((int)idNum, n);
			nodeLabel.put(label, n);
			nodeCounter++;
			return true;
		}
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (eId.containsKey((int)idNum) || idNum < 0) {
			return false;
		}
		if (!nodeLabel.containsKey(sLabel)) {
			return false;
		}
		if (!nodeLabel.containsKey(dLabel)) {
			return false;
		}
		if (nodeLabel.get(sLabel).outEdge.containsKey(dLabel)  && nodeLabel.get(sLabel).outEdge.get(dLabel) != null) {
			return false;
		}
		Edge holder = new Edge(idNum, sLabel, dLabel, weight, eLabel);
		this.eLabel.put(eLabel, holder);
		this.eId.put((int)idNum, holder);
		nodeLabel.get(sLabel).outEdge.put(dLabel, holder);
		nodeLabel.get(dLabel).inEdge.put(sLabel, holder);
		edgeCounter++;
		return true;
	}

	@Override
	public boolean delNode(String label) {
		if (!nodeLabel.containsKey(label)) {
			return false;
		}
		Node n = nodeLabel.get(label);
		Iterator<Edge> list = n.outEdge.values().iterator();
		while(list.hasNext()) {
			Edge arrayList = (Edge) list.next();
			Node source = nodeLabel.get(arrayList.label);
			source.outEdge.remove(n.label);
			list.remove();
			edgeCounter--;
		}
		Iterator<Edge> heap = n.inEdge.values().iterator();
		while(heap.hasNext()) {
			Edge arrayList = (Edge) heap.next();
			Node dest = nodeLabel.get(arrayList.Dlabel);
			dest.inEdge.remove(n.label);
			list.remove();
			edgeCounter--;
		}
		
		nodeLabel.remove(label);
		id.remove((int) n.id);
		nodeCounter--;
		
		return true;

	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if (!nodeLabel.containsKey(sLabel)) {
			return false;
		}
		if (!nodeLabel.containsKey(dLabel)) {
			return false;
		}
		if (!nodeLabel.get(sLabel).outEdge.containsKey(dLabel)) {
			return false;
		}
		else {
		    Edge e = nodeLabel.get(sLabel).outEdge.get(dLabel);
		    Node sNode = nodeLabel.get(sLabel);
		    Node dNode = nodeLabel.get(dLabel);
			sNode.outEdge.remove(dLabel);
			dNode.inEdge.remove(sLabel);
			eId.remove((int)e.id);
			eLabel.remove(e.eLabel);
			edgeCounter--;
			return true;
		}
	}

	@Override
	public long numNodes() {
		return nodeCounter;
	}

	@Override
	public long numEdges() {
		return edgeCounter;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		for (Node n : nodeLabel.values()) {
			n.shortest = 0;
			n.visited = false;
		}
		ShortestPathInfo[] returner = new ShortestPathInfo[(int) nodeCounter];
		djikstra.clear();
		if (!nodeLabel.containsKey(label) || label == null) {
			Iterator<String> i =  nodeLabel.keySet().iterator();
			int counter = 0;
			while(i.hasNext()) {
				String mips = i.next();
				returner[counter] = new ShortestPathInfo(mips, -1);
				counter++;
			}
			return returner;
		}
		Node holder = nodeLabel.get(label);
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		holder.shortest = 0;
		holder.visited = true;
		pq.add(holder);
		djikstra.put(label, (int) holder.shortest);
		Iterator<Edge> i =  holder.outEdge.values().iterator();
		
		while (i.hasNext()) {
			Edge e = i.next();
			String destString = e.Dlabel;
			Node dest = nodeLabel.get(destString);
			dest.shortest = e.weight;
			pq.add(dest);
		}
		while (pq.size() > 0) {
			Node garbage = pq.remove();
			if(garbage.visited) {
				continue;
			} else {
				garbage.visited = true;
			}
			djikstra.put(garbage.label, (int) garbage.shortest);
			Iterator<Edge> ii = garbage.outEdge.values().iterator();
			
			while(ii.hasNext()) {
				Edge e = ii.next();
				Node juice = nodeLabel.get(e.Dlabel);
				if (pq.contains(juice)) {
					if (juice.shortest > e.weight + garbage.shortest) {
						juice.shortest = e.weight + garbage.shortest;
					}
					
				}
				else {
					juice.shortest = e.weight + garbage.shortest;
				}
				pq.add(juice);
			}
		}
		Iterator<Node> nodeI = nodeLabel.values().iterator();
		while(nodeI.hasNext()) {
			Node obj = nodeI.next();
			if (obj.visited == false) {
				djikstra.put(obj.label, -1);
				
			}
		}
		int count = 0;
		Iterator<String> j = djikstra.keySet().iterator();
		while(j.hasNext()) {
			String moo = j.next();
			int showty = djikstra.get(moo);
			returner[count] = new ShortestPathInfo(moo, showty);
			count++;
		} 
		
		return returner;
	}

}
