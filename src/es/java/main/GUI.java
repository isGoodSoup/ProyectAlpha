package es.java.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {

	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	static JButton button = new JButton();
	static int currentPlayer;
	
	public GUI() {
		
		Object[] symbolOptions = {"X", "O"};
	    TicTacToe.playerReply = (String) JOptionPane.showInputDialog(
	        null,
	        "Player 1, choose your symbol:",
	        "Symbol Selection",
	        JOptionPane.QUESTION_MESSAGE,
	        null,
	        symbolOptions,
	        symbolOptions[0]
	    );
	    
	    TicTacToe.player2Reply = TicTacToe.playerReply.equals("X") ? "O" : "X";

	    String[] options = {"PC", "CO-OP", "Exit"};
	    int choice = JOptionPane.showOptionDialog(
	        null, "Select game mode", "Menu",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
	        null, options, options[0]
	    );
	    TicTacToe.option = choice + 1;

	    if (TicTacToe.option == 1) {
	        TicTacToe.isMachineActive = true;
	        TicTacToe.isPlayer2Active = false;
	    } else if (TicTacToe.option == 2) {
	        TicTacToe.isPlayer2Active = true;
	        TicTacToe.isMachineActive = false;
	    } else System.exit(0);

	    bootGUI();
	}
	
	public static void bootGUI() {
		
		panel.setLayout(new GridLayout(3, 3));
	    panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

	    for (int i = 0; i < 9; i++) {
	        JButton button = new JButton();
	        final int position = i + 1;
	        button.setPreferredSize(new Dimension (60, 60));
	        button.addActionListener(e -> handleButtonClick(position));
	        panel.add(button);
	    }

	    frame.setTitle("Tic Tac Toe");
	    frame.add(panel);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	public static void handleButtonClick(int position) {

	    int row = (position - 1) / 3;
	    int col = (position - 1) % 3;
	    
	    if (!TicTacToe.grid[row][col].equals(" ")) {
	    	
	    	String err = "Invalid move! Cell is taken";
	        JOptionPane.showMessageDialog(frame, err);
	        return;
	    }

	    TicTacToe.pos = position;

	    if (TicTacToe.isPlayer2Active) {

	        TicTacToe.playerTurn(currentPlayer);
	        updateGrid();
	        
	        if (checkGameEnd(currentPlayer)) {
	            return;
	        }
	        currentPlayer = currentPlayer == 1 ? 2 : 1;
	    } else {

	        TicTacToe.playerTurn(1);
	        updateGrid();
	        
	        if (checkGameEnd(1)) {
	            return;
	        }
	        
	        if (TicTacToe.isMachineActive) {

	            if (TicTacToe.machineReply.isEmpty()) {
	                TicTacToe.machineReply = TicTacToe.playerReply.equals("X") ? "O" : "X";
	            }
	            TicTacToe.machineTurn();
	            updateGrid();
	            checkGameEnd(2);
	        }
	    }
	}
	
	public static boolean checkGameEnd(int player) {
		
		if (TicTacToe.checkWin(TicTacToe.grid)) {
			
	        String message;
	        if (TicTacToe.isPlayer2Active) {
	            String winningSymbol = TicTacToe.grid[TicTacToe.row][TicTacToe.col];
	            message = winningSymbol.equals(TicTacToe.playerReply) ? "Player 1 wins!" : "Player 2 wins!";
	        } else {
	            message = player == 1 ? "Victory!" : "Defeat!";
	        }
	        
	        JOptionPane.showMessageDialog(frame, message);
	        TicTacToe.gameReset();
	        currentPlayer = 1;
	        updateGrid();
	        return true;
	        
	    } else if (TicTacToe.isBoardFull()) {
	    	
	        JOptionPane.showMessageDialog(frame, "Draw!");
	        TicTacToe.gameReset();
	        currentPlayer = 1;
	        updateGrid();
	        return true;
	    }
	    return false;
	}

	public static void updateGrid() {

	    Component[] components = panel.getComponents();
	    for (int i = 0; i < 9; i++) {
	        int row = i / 3;
	        int col = i % 3;
	        ((JButton) components[i]).setText(TicTacToe.grid[row][col]);
	    }
	}

	public static void main(String[] args) {
		new GUI();
	}
}
