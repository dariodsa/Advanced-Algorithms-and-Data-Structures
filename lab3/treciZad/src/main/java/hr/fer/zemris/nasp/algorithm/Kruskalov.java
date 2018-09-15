package hr.fer.zemris.nasp.algorithm;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import hr.fer.zemris.nasp.elements.Node;
import hr.fer.zemris.nasp.elements.Wire;

public class Kruskalov extends Algorithm {

	
	@Override
	public void run(Graphics2D g2d) {
		PriorityQueue<Wire> wires = new PriorityQueue<>(new WireComparator());
		List<String>nodesName = new ArrayList<>();
		
		
		for(Entry<String,Node> E : nodes.entrySet()) {
			nodesName.add(E.getKey());
			for(Wire w : V.get(E.getKey())) {
				Node fromNode = w.getFromNode();
				Node toNode   = w.getToNode();
				
				if(fromNode.getName().compareTo(toNode.getName()) < 0) {
					wires.add(w);
				}
			}
		}
		
		UnionFind<String> unionFind = new UnionFind<>(nodesName);
		
		while(!wires.isEmpty()) {
			Wire W = wires.poll();
			Node fromNode = W.getFromNode();
			Node toNode = W.getToNode();
			if(unionFind.find(fromNode.getName(), toNode.getName())) {
				continue;
			}
			unionFind.union(fromNode.getName(), toNode.getName());
			W.setUsed(true);
			fromNode.setVisited(true);
			toNode.setVisited(true);
			
			showResult(g2d);
			try {
				Thread.sleep(SLEEP_TIMEOUT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private static class WireComparator implements Comparator<Wire> {

		@Override
		public int compare(Wire w1, Wire w2) {
			return Integer.compare(w1.getDistance(), w2.getDistance());
		}
		
	}

}
