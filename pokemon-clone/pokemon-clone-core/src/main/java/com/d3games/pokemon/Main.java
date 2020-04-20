package com.d3games.pokemon;

import java.io.File;

import com.d3games.gameui.GameGUI;

public class Main {

	public static void main(String[] args) {
		new GameGUI(new File(Main.class.getResource("world3.txt").getFile()));
	}

}
