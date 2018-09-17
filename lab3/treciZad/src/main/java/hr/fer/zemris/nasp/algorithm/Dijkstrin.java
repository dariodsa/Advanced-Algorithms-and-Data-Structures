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
			tree.put(E.getKey(), new ArrayList<>());
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
			W.setUsed(true);
			System.out.println(W);
			
			tree.get(fromNode.getName()).add(toNode.getName());
			tree.get(toNode.getName()).add(fromNode.getName());
			Wire w = hasCycle(fromNode,tree);
			if(w != null) {
				tree.get(w.getFromNode().getName()).remove(w.getToNode().getName());
				tree.get(w.getToNode().getName()).remove(w.getFromNode().getName());
				System.out.println("UNSELECT WIRE");
				unSelectThatWire(w);
				continue;
			}
			
			
			
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

	private void unSelectThatWire(Wire W) throws RuntimeException {
		System.out.println("UNSELECTING " + W.toString());
		for(Entry<String,Node> E : nodes.entrySet()) {
			for(Wire _W : V.get(E.getKey())) {
				if(_W.getFromNode().getName().compareTo(W.getFromNode().getName()) == 0 && 
				   _W.getToNode().getName().compareTo(W.getToNode().getName()) == 0	) {
					_W.setUsed(false);
					return;
				}
			}
		}
		throw new RuntimeException(String.format("I didn't unselect anything. %s",W.toString()));
	}

	private Wire hasCycle(Node fromNode, Map<String,List<String>> tree) {
		
		Map<String, Boolean> bio = new HashMap<>();
		for(String nodeName : tree.keySet()) {
			bio.put(nodeName, false);
		}
		
		List<String> list = new ArrayList<>();
		list.add(fromNode.getName());
		return dfs(fromNode, fromNode.getName(), tree, bio, list);
	}
	
	private Wire dfs(Node parent, String fromNode, Map<String,List<String>> tree, Map<String, Boolean> bio, List<String> res) {
		bio.put(fromNode, true);
		for(String nodeName : tree.get(fromNode)) {
			if(bio.get(nodeName) == false) {
				res.add(nodeName);
				Wire W = dfs(parent, nodeName, tree, bio, res);
				if(W != null) return W;
				res.remove(nodeName);
			} else if(nodeName.compareTo(parent.getName()) == 0) {
				if(res.size() > 2) {
					System.out.println("CYCLE");
					res.add(parent.getName());
					
					Wire resultWire = null;
					int maxWeight = -1;
					
					for(int i=1;i<res.size(); ++i) {
						for(Wire w : V.get(res.get(i-1))) {
							if(w.getToNode().getName().compareTo(res.get(i)) == 0) {
								if(w.getDistance() > maxWeight) {
									maxWeight = w.getDistance();
									if(res.get(i).compareTo(res.get(i-1)) < 0) {
										resultWire = new Wire(nodes.get(res.get(i)), nodes.get(res.get(i-1)),0);
									}
									else {
										resultWire = w;
									}
								}
							}
						}
					}
					System.out.println("REMOVING " + resultWire.getFromNode().getName() +" " + resultWire.getToNode().getName());
					return resultWire;
				}
			}
		}
		bio.put(fromNode, false);
		return null;
	}
	
	
	
}
