package com.d3games.pokemon;

public class MenuCreator {
	private static MenuCreator menuCreator;
	private Menu mainMenu;

	private MenuCreator() {
		
	}

	public static MenuCreator getInstance() {
		if (menuCreator == null)
			menuCreator = new MenuCreator();
		return menuCreator;
	}
	
	public Menu getMainMenu() {
		return mainMenu;
	}

	public void generateMenu() {
		mainMenu = new Menu();
		mainMenu.add("Player Stats", new UselessItem());
		Menu items = new Menu();
		items.add("Pokeball", new UselessItem("What the heck is this?"));
		items.add("Pokeflute", new UselessItem("Looks like a regular flute to me..."));
		items.add("Bicycle", new UselessItem("I don't know how to ride a bike..."));
		mainMenu.add("Items", items);
		Save save = new Save();
		mainMenu.add("Save", save);
		GameManager.getInstance().setMainMenu(mainMenu);
	}

}
