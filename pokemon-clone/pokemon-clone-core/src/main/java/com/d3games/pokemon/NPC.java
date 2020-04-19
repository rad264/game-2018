package com.d3games.pokemon;

public class NPC extends Player {

	public NPC() {
		super();
	}

	public NPC(Unit pos) {
		super(pos);
	}

	protected String getCharacterImageUrl(Direction dir) {
		return "dog.png";
	}

	public String toFile() {
		return "n" + pos.getMap().getId() + pos.getX() + pos.getY();
	}

	public String prompt() {
		if (Math.random() > 0.5)
			return "Arf!";
		else
			return "Woof";
	}

}
