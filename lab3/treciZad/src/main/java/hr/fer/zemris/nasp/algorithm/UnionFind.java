package hr.fer.zemris.nasp.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind<T> {
	
	private Map<T, T> parent;
	
	public UnionFind(List<T> elements) {
		parent = new HashMap<>();
		for(T element : elements) {
			parent.put(element, element);
		}
	}
	
	public void union(T x, T y) {
		parent.put(leader(x), leader(y));
	}
	
	public boolean find(T x, T y) {
		return leader(x).hashCode() == leader(y).hashCode();
	}

	private T leader(T x) {
		if(parent.get(x) == x) {
			return x;
		}
		parent.put(x, leader(parent.get(x)));
		return parent.get(x);
	}
}
