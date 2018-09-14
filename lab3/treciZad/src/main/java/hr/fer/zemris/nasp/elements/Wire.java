package hr.fer.zemris.nasp.elements;

import java.util.Objects;

public class Wire {
	private Node fromNode;
	private Node toNode;
	private int distance;
	
	public Wire(Node fromNode, Node toNode, int distance) {
		Objects.requireNonNull(fromNode);
		Objects.requireNonNull(toNode);
		
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.distance = distance;
	}

	public Node getFromNode() {
		return fromNode;
	}

	public Node getToNode() {
		return toNode;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
}
