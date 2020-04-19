package com.d3games.pokemon;

import java.io.IOException;

import javax.swing.JFrame;

public class GameGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameGUI() {
		MapCreator.getInstance();
		MenuCreator.getInstance();
		try {
			MapCreator.getInstance().generateMap();
			MenuCreator.getInstance().generateMenu();
			add(new GameScreen());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(357, 380);
			setLocationRelativeTo(null);
			setTitle("GAME TITLE");
			setResizable(false);
			setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GameGUI();
	}

}
