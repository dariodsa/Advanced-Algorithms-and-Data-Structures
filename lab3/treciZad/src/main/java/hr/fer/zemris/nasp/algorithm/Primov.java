package hr.fer.zemris.nasp.algorithm;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import hr.fer.zemris.nasp.elements.Node;
import hr.fer.zemris.nasp.elements.Wire;

public class Primov extends Algorithm{

	@Override
	public void run(Graphics2D g2d) {
		int numOfNodes = nodes.size();
		
		String first = nodes.keySet().stream().findFirst().get();
		nodes.get(first).setVisited(true);
		PriorityQueue<Wire> priorityQueue = new PriorityQueue(new WireComparator());
		for(Wire w : V.get(first)) {
			priorityQueue.add(w);
			
		}
		
		int i = 0;
		while(i < numOfNodes - 1) {
			//find shortest wire 
			while(!priorityQueue.isEmpty()) {
				Wire W = priorityQueue.poll();
				if(!W.getToNode().isVisited()) {
					W.getToNode().setVisited(true);
					W.setUsed(true);
					Node toNode = W.getToNode();
					//two way paths
					for(Wire w : V.get(toNode.getName())) {
						if(w.getToNode() == W.getFromNode()) {
							w.setUsed(true);
							break;
						}
					}
					
					//add more wires
					
					for(Wire w : V.get(W.getToNode().getName())) {
						if(!w.getToNode().isVisited()) {
							priorityQueue.add(w);
						}
					}
					
					break;
				}
			}
			showResult(g2d);
			try {
				Thread.sleep(Algorithm.SLEEP_TIMEOUT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			++i;
		}
		
	}
	
	private static class WireComparator implements Comparator<Wire> {

		@Override
		public int compare(Wire w1, Wire w2) {
			return Integer.compare(w1.getDistance(), w2.getDistance());
		}
		
	}

}
