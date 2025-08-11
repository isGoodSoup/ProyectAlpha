package es.java.utils;

public class Menu {

	public static void printMenu(String[] menu) {
		
		for (int i = 0, j = 1; i < menu.length; i++, j++) {
			System.out.println(j + ") " + menu[i]);
		}
		System.out.println("Insert choice: ");
	}
}
