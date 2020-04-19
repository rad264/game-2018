package com.d3games.pokemon;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
	private static GameManager manager;
	private List<GameMap> maps = new ArrayList<GameMap>();
	private Player player;
	private Menu mainMenu;

	private GameManager() {
		
	}

	public static GameManager getInstance() {
		if (manager == null)
			manager = new GameManager();
		return manager;
	}

	public void add(GameMap map) {
		maps.add(map);
	}

	public GameMap get(int i) {
		return maps.get(i);
	}

	public List<GameMap> getAll() {
		return maps;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Menu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(Menu mainMenu) {
		this.mainMenu = mainMenu;
	}
}
