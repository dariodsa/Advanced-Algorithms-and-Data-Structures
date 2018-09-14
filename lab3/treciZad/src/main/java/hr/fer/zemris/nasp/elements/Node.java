package hr.fer.zemris.nasp.elements;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String name;
	private List<Wire> connected;
	
	private int x;
	private int y;
	
	public Node(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.connected = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void addWire(Wire w) {
		this.connected.add(w);
	}
	
	public List<Wire> getConnected() {
		return this.connected;
	}
}
