package hr.fer.zemris.nasp.algorithm;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Map.Entry;

import hr.fer.zemris.nasp.elements.Node;
import hr.fer.zemris.nasp.elements.Wire;

public class Dijkstrin extends Algorithm {

	@Override
	public void run(Graphics2D g2d) {
		Queue<Wire> wires = new LinkedList<>();
		List<String>nodesName = new ArrayList<>();
		Map<String, List<String>> tree = new HashMap<>();
		
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
		
		while(!wires.isEmpty()) {
			Wire W = wires.poll();
			Node fromNode = W.getFromNode();
			Node toNode = W.getToNode();
		
			tree.get(fromNode.getName()).add(toNode.getName());
			tree.get(toNode.getName()).add(fromNode.getName());
			Wire w = hasCycle(fromNode);
			if(w != null) {
				tree.remove(w.getFromNode().getName()).remove(w.getToNode().getName());
				tree.remove(w.getToNode().getName()).remove(w.getFromNode().getName());
				unSelectThatWire(w);
				continue;
			}
			
			
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

	private void unSelectThatWire(Wire W) {
		for(Entry<String,Node> E : nodes.entrySet()) {
			for(Wire _W : V.get(E.getKey())) {
				if(_W.getFromNode().getName().compareTo(W.getFromNode().getName()) == 0 && 
				   _W.getToNode().getName().compareTo(W.getToNode().getName()) == 0	) {
					_W.setUsed(false);
					return;
				}
			}
		}
		
	}

	private Wire hasCycle(Node fromNode, Map<String,List<String>> tree) {
		
		Stack<Node> stack = new Stack<>();
		stack.add(fromNode);
		while(!stack.isEmpty()) {
			Node pos = stack.pop();
			for(String name : tree.get(pos.getName())) {
				
			}
			break;
		}
		return null;
	}
	
	
	
}
