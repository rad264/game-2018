package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Menu extends Throwable implements MenuItem {
	private static final long serialVersionUID = 1L;
	
	private List<String> itemNames = new ArrayList<String>();
	private List<MenuItem> items = new ArrayList<MenuItem>();
	private int selected = 0;
	private Menu parentMenu;

	public void add(String displayName, MenuItem menuItem) {
		if (menuItem instanceof Menu)
			((Menu) menuItem).setParentMenu(this);
		itemNames.add(displayName);
		items.add(menuItem);
	}

	public void setParentMenu(Menu parent) {
		parentMenu = parent;
	}

	public Collection<String> getDisplayNames() {
		return itemNames;
	}

	public MenuItem get(String key) {
		return items.get(itemNames.indexOf(key));
	}

	public String getSelected() {
		return itemNames.get(selected);
	}

	public void up() {
		if (selected == 0)
			throw new InvalidMoveException();
		selected--;
	}

	public void down() {
		if (selected == items.size() - 1)
			throw new InvalidMoveException();
		selected++;
	}

	public Menu back() {
		if (parentMenu == null)
			throw new InvalidMoveException();
		selected = 0;
		return parentMenu;
	}
	
	public void accessSelected() throws Menu {
		MenuItem selectedItem = items.get(selected);
		if (selectedItem instanceof Menu)
			((Menu) selectedItem).trigger();
		else
			throw new InvalidMoveException();
	}

	public void triggerSelected() throws Menu, GameMessage {
		items.get(selected).trigger();
	}

	@Override
	public void trigger() throws Menu {
		throw this;
	}
}
