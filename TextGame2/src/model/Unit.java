package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Unit {
	private Image bg;
	protected GameMap map;
	protected int xLoc;
	protected int yLoc;
	private Player character;

	public Unit() {
		ImageIcon i = new ImageIcon(this.getClass().getResource(getBackgroundUrl()));
		bg = i.getImage();
	}

	protected String getBackgroundUrl() {
		return "default.png";
	}

	public void setLocation(GameMap map, int x, int y) {
		this.map = map;
		xLoc = x;
		yLoc = y;
	}

	public int getX() {
		return xLoc;
	}

	public int getY() {
		return yLoc;
	}

	public GameMap getMap() {
		return map;
	}

	public abstract String toFile();

	public Unit getAdjacent(Direction dir) throws InvalidMoveException {
		switch (dir) {
		case UP:
			return getAdjacent(xLoc, yLoc - 1);
		case DOWN:
			return getAdjacent(xLoc, yLoc + 1);
		case RIGHT:
			return getAdjacent(xLoc + 1, yLoc);
		case LEFT:
			return getAdjacent(xLoc - 1, yLoc);
		default:
			return this;
		}
	}

	private Unit getAdjacent(int x, int y) {
		if (map == null || map.get(x, y) == null)
			throw new InvalidMoveException();
		return map.get(x, y);
	}

	public Unit approach() {
		if (character != null)
			throw new InvalidMoveException();
		return this;
	}

	public Image getBG() {
		return bg;
	}

	public void leave() {
		character = null;
	}

	public void enter(Player newComer) {
		character = newComer;
	}

	public boolean hasCharacter() {
		return character != null;
	}

	public Player getCharacter() {
		return character;
	}

	public String prompt() {
		if (character != null)
			return character.prompt();
		return "";
	}
}