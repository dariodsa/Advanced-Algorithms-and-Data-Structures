package hr.fer.zemris.nasp.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Objects;

public class Wire {
	
	private static final float RED_LINE_WIDTH = 3;
	private static final float BLACK_LINE_WIDTH = 1;
	
	private Node fromNode;
	private Node toNode;
	private int distance;
	private boolean used;
	
	public Wire(Node fromNode, Node toNode, int distance) {
		Objects.requireNonNull(fromNode);
		Objects.requireNonNull(toNode);
		
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.distance = distance;
		this.used = false;
	}

	public Node getFromNode() {
		return fromNode;
	}
	
	public Node getToNode() {
		return toNode;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return this.distance;
	}
	
	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public void draw(Graphics2D g2d) {
		if(isUsed()) {
			g2d.setStroke(new BasicStroke(RED_LINE_WIDTH));
		}
		draw(g2d, isUsed()?Color.RED:Color.BLACK);
		g2d.setStroke(new BasicStroke(BLACK_LINE_WIDTH));
		
	}
		
	private void draw(Graphics2D g2d, Color color) {
		
		double t1 = getT(fromNode, toNode);
		
		double deltaX = toNode.getX()-fromNode.getX();
		double deltaY = toNode.getY()-fromNode.getY();
		
		int x1 = fromNode.getX() + (int)(deltaX * t1);
		int y1 = fromNode.getY() + (int)(deltaY * t1);
		
		deltaX = -deltaX;
		deltaY = -deltaY;
		double t2 = getT(toNode, fromNode);
		int x2 = toNode.getX() + (int)(deltaX * t2);
		int y2 = toNode.getY() + (int)(deltaY * t2);
		
		g2d.setColor(color);
		g2d.drawLine(x1, y1, x2, y2);
		g2d.setColor(Color.BLACK);
		
		deltaX = -deltaX;
		deltaY = -deltaY;
		int x3 = fromNode.getX() + (int)(deltaX * 0.5);
		int y3 = fromNode.getY() + (int)(deltaY * 0.5);
		
		double y = (deltaX * 0.5)/(deltaY);
		double x = 0.5;
		double dist = Math.sqrt(x*x+y*y);
		x /= dist; 
		y /= dist;
		
		int fontHeight = g2d.getFontMetrics().getHeight();
		
		g2d.drawString(Integer.valueOf(distance).toString(),(int)(x3 + x*10), (int)(y3 + y*10));
		
	}

	private double getT(Node fromNode, Node toNode) {
		double ans = 0;
		int deltaX = toNode.getX()-fromNode.getX();
		int deltaY = toNode.getY()-fromNode.getY();
		
		double a = deltaX*deltaX + deltaY*deltaY;
		double b = 0;
		double c = -(Node.CIRCLE_RADIUS/2.0 * Node.CIRCLE_RADIUS/2.0);
		
		ans = (-b+ Math.sqrt(b*b - 4*a*c)) / (2*a); 
		
		return ans;
	}
	
	@Override
	public String toString() {
		return String.format("%s <----> %s", fromNode.getName(), toNode.getName());
	}
	
}
