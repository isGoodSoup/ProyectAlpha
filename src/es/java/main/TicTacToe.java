package es.java.main;

import java.util.Random;

import es.java.utils.Menu;

public class TicTacToe {

	static Random rdom = new Random();
	static String[][] grid = {
								{" ", " ", " "}, 
								{" ", " ", " "},  
								{" ", " ", " "} 
							};
	static boolean isPlayerActive = true;
	static boolean isPlayer2Active = false;
	static boolean isMachineActive = true;
	static String machineReply = "";
	static String playerReply = "";
	static String player2Reply = "";
	static String vline = "|";
	static int pos;
	static int row;
	static int col;
	static int replyCheck = 1;
	static int option;
	
	public static void main(String[] args) {

		do {
			System.out.println("TIC TAC TOE");
			String[] menu = {"Against PC", "CO-OP", "Exit"};
			Menu.printMenu(menu);
			
			switch (option) {
				
				case 1: classicMode();
						break;
				
				case 2: coopMode();
						break;
						
				case 3: System.out.println("Goodbye!");
						System.exit(0);
			}
			
		} while (option != 4);
	}
	
	public static void classicMode() {
		
		isPlayer2Active = false;
		isMachineActive = true;
		gridToPlay();
	}
	
	public static void coopMode() {
		
		isPlayer2Active = true;
		isMachineActive = false;
		gridToPlay();
	}
	
	public static void printGrid() {
		
    	for (int i = 0; i < grid.length; i++) {
	        for (int j = 0; j < grid[i].length; j++) {
	        	
	            System.out.print(" " + grid[i][j] + " ");
	            
	            if (j < grid[i].length - 1) {
	                System.out.print(vline);
	            }
	        }
	        System.out.println();

	        if (i < grid.length - 1) {
	            System.out.println("---+---+---");
	        }
	    }
	}
	
	public static void gridToPlay() {
	    boolean isGameOn = true;
	    String message = "";
	    
	    while (isGameOn) {
	        printGrid();
	        
	        if (checkWin(grid)) {
	            message = "We have a winner!";
	            break;
	        } else if (isBoardFull()) {
	            message = "Draw";
	            break;
	        }
	        
	        playerTurn(1);
	        
	        if (checkWin(grid) || isBoardFull()) continue;
	        
	        if (!isPlayer2Active) {
	            machineTurn();
	        } else {
	            playerTurn(2);
	        }
	    }
	    
	    System.out.println("\n" + message);
	    System.out.println();
	    gameReset();
	}

	
	public static void playerTurn(int player) {
		
		System.out.println();
		
    	if (player == 1) {
    		
    		if (playerReply.isEmpty()) {
        		System.out.print("Your turn: ");
        	}
        	
//        	System.out.print("[Player 1] In position (1-9): ");
        	
        	row = (pos - 1) / 3;
        	col = (pos - 1) % 3;
        	
        	if (grid[row][col].equals(" ")) {
        		grid[row][col] = playerReply;
        	} else {
        		System.err.println("[Player 1] Busy spot");
        	}
    	}
    	
    	if (player == 2) {
    		
    		if (player2Reply.isEmpty()) {
        		System.out.print("Tu turno: ");
        	}
        	
//        	System.out.print("[Player 2] In position (1-9): ");
        	System.out.println();
        	
        	row = (pos - 1) / 3;
        	col = (pos - 1) % 3;
        	
        	if (grid[row][col].equals(" ")) {
        		grid[row][col] = player2Reply;
        	} else {
        		System.err.println("[Player 2] Busy spot");
        	}
    	}
	}
	
	public static void machineTurn() {
		
		do {
		outer:
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid.length; j++) {
					
					if (grid[i][j].equalsIgnoreCase("X")) {
						
						machineReply = "O";
						replyCheck = 0;
						break outer;
					}
					
					if (grid[i][j].equalsIgnoreCase("O")) {
						
						machineReply = "X";
						replyCheck = 0;
						break outer;
					}
				}
			}
		} while (replyCheck != 0);
		
		System.out.println();
		String reply = machineReply;
		
		do {
			
			pos = rdom.nextInt(9) + 1;
	    	row = (pos - 1) / 3;
	    	col = (pos - 1) % 3;
		} while (!grid[row][col].equals(" "));
			
		grid[row][col] = reply;
	}
	
	public static boolean checkWin(String[][] grid) {
		
	    for (int i = 0; i < 3; i++) {
	        if (!grid[i][0].equals(" ") && 
	            grid[i][0].equals(grid[i][1]) && 
	            grid[i][1].equals(grid[i][2])) {
	            return true;
	        }
	    }
	    
	    for (int j = 0; j < 3; j++) {
	        if (!grid[0][j].equals(" ") && 
	            grid[0][j].equals(grid[1][j]) && 
	            grid[1][j].equals(grid[2][j])) {
	            return true;
	        }
	    }
	    
	    if (!grid[0][0].equals(" ") && 
	        grid[0][0].equals(grid[1][1]) && 
	        grid[1][1].equals(grid[2][2])) {
	        return true;
	    }
	    
	    if (!grid[0][2].equals(" ") && 
	        grid[0][2].equals(grid[1][1]) && 
	        grid[1][1].equals(grid[2][0])) {
	        return true;
	    }
	    
	    return false;
	}
	
	public static boolean isBoardFull() {
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				
				if (grid[i][j].equalsIgnoreCase(" ")) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void gameReset() {
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = " ";
			}
		}
	}
}
