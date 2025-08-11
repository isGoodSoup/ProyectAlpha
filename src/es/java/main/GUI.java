package es.java.main;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	
	public static TicTacToe game;
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	
	public GUI() {
		game = new TicTacToe();
		bootGUI();
	}
	
	public static void bootGUI() {
		
		panel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
		
		frame.setTitle("Very Basic Tic Tac Toe!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
