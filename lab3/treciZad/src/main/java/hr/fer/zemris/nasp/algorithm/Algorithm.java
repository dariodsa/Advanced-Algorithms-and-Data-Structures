package hr.fer.zemris.nasp.algorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import hr.fer.zemris.nasp.elements.Node;
import hr.fer.zemris.nasp.elements.Wire;

public abstract class Algorithm {

	public static final long SLEEP_TIMEOUT = 10;
	
	protected Map<String,Node> nodes;
	protected Map<String, List<Wire>> V;
	
	public void load(String fileName, Graphics2D g2d) throws NumberFormatException, IOException {
		
		this.nodes = new HashMap<>();
		this.V = new HashMap<>();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 2000, 2000);
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		
		BufferedReader buffReader = new BufferedReader(new FileReader(file));
		int numOfNodes = Integer.parseInt(buffReader.readLine());
		
		for(int i=0;i<numOfNodes;++i) {
			String[] elements = buffReader.readLine().split(" +");
			String name = elements[0];
			int x = Integer.parseInt(elements[1]);
			int y = Integer.parseInt(elements[2]);
			
			Node node = new Node(name,x, y);
			node.draw(g2d);
			nodes.put(name, node);
			this.V.put(name, new ArrayList<>());
		}
		String line;
		while ((line = buffReader.readLine()) != null) {
			String[] elements = line.split(" +");
			String from = elements[0];
			String to = elements[1];
			int weight = Integer.parseInt(elements[2]);
			
			Node fromNode = nodes.get(from);
			Node toNode = nodes.get(to);
			
			Wire W = new Wire(fromNode,toNode,weight);
			Wire W2 = new Wire(toNode,fromNode,weight);
			W.draw(g2d);
			V.get(from).add(W);
			V.get(to).add(W2);
	    }
		buffReader.close();
		
		
	}

	public abstract void run(Graphics2D g2d);

	public void showResult(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 2000, 2000);
		g2d.setColor(Color.BLACK);
		for(Entry<String,Node> E : nodes.entrySet()) {
			E.getValue().draw(g2d);
		}
		
		List<Wire> wires = new ArrayList<>();
		for(Entry<String,Node> E : nodes.entrySet()) {
			for(Wire W : V.get(E.getKey())) {
				
				Node fromNode = W.getFromNode();
				Node toNode = W.getToNode();
				
				if(fromNode.getName().compareTo(toNode.getName()) < 0) {
					wires.add(W);
				}
			}
		}
		for(Wire W : wires) {
			W.draw(g2d);
		}
		
	}
	
}
