package hr.fer.zemris.nasp.graphical;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import hr.fer.zemris.nasp.algorithm.Algorithm;

public class GraphicalWindow extends JFrame {
	
	private static final int WINDOW_HEIGHT = 400;
	private static final int WINDOW_WIDTH = 800;
	private static final String TITLE = "Treci lab";
	private static final String BTN_START = "Kreni";
	
	private static final String KRUSKALOV = "Kruskalov";
	private static final String DIJKSTRIN = "Dijkstrin";
	private static final String PRIMOV = "Primov";
	
	private JButton btnStart;
	private JList selectorAlg;
	private JPanel drawPanel;
	
	private Algorithm algorithm;
	
	
	
	public GraphicalWindow() {
		
		super(TITLE);
		setVisible(true);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		initGUI();
	}
	
	private void initGUI() {
		btnStart = new JButton(BTN_START);
		selectorAlg = new JList<>(new String[] {KRUSKALOV, DIJKSTRIN, PRIMOV});
		
		btnStart.addActionListener((l)->{btnStartCliked();});
		
		JPanel formPanel = new JPanel(new GridLayout(0, 1));
		formPanel.add(selectorAlg);
		formPanel.add(btnStart);
		
		drawPanel = new JPanel();
		add(formPanel, BorderLayout.NORTH);
		add(drawPanel,BorderLayout.CENTER);
	}
	
	private void btnStartCliked() {
		if(selectorAlg.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(this, "Odaberi algoritam");
			return;
		}
		String algorithmName = selectorAlg.getSelectedValue().toString();
		JOptionPane.showMessageDialog(this, String.format("Počinje obrada sa algoritmom: %s",algorithmName));
		
		if(algorithmName.compareTo(KRUSKALOV) == 0) {
			algorithm = new Kruskalov();
		} else if(algorithmName.compareTo(DIJKSTRIN) == 0) {
			algorithm = new Dijkstrin();
		} else if(algorithmName.compareTo(PRIMOV) == 0) {
			algorithm = new Primov();
		}
		algorithm.load("");
		algorithm.run();
		drawPanel = algorithm.showResult();
	}
	
}
