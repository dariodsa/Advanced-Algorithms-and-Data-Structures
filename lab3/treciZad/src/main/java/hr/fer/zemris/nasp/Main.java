package hr.fer.zemris.nasp;

import javax.swing.SwingUtilities;

import hr.fer.zemris.nasp.graphical.GraphicalWindow;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{new GraphicalWindow();});
	}
}
