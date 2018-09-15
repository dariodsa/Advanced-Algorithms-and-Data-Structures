package hr.fer.zemris.nasp.elements;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


public class Node {
	
	public static final int CIRCLE_RADIUS = 30;
	
	private List<Wire> connected;
	
	private String name;
	private int x;
	private int y;
	private boolean visited;
	
	public Node(String name, int x, int y) {  
		this.name = name;
		this.x = x;
		this.y = y;
		this.connected = new ArrayList<>();
		this.visited = false;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
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
	
	public void draw(Graphics2D g2d) {
		draw(g2d, visited);
	}
	
	private void draw(Graphics2D g2d, boolean visited) {
		
		if(visited) {
			g2d.setColor(java.awt.Color.YELLOW);
			g2d.fillOval(x - CIRCLE_RADIUS/2 , y - CIRCLE_RADIUS/2, CIRCLE_RADIUS, CIRCLE_RADIUS);
			g2d.setColor(java.awt.Color.BLACK);
		} else {
			g2d.drawOval(x - CIRCLE_RADIUS/2 , y - CIRCLE_RADIUS/2, CIRCLE_RADIUS, CIRCLE_RADIUS);
		}
		
		int fontHeight = g2d.getFontMetrics().getHeight();
		int textWidth = g2d.getFontMetrics().stringWidth(name);
		g2d.drawString(name, x - textWidth/2 , y + fontHeight/2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
