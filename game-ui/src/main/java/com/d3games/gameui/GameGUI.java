package com.d3games.gameui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.d3games.engine.map.MapCreator;
import com.d3games.engine.menu.MenuCreator;

public class GameGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameGUI(File world) {
		MapCreator.getInstance();
		MenuCreator.getInstance();
		try {
			MapCreator.getInstance().generateMap(world);
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

}
