package com.d3games.pokemon;

public class UselessItem implements MenuItem {
	private String description;

	public UselessItem() {

	}

	public UselessItem(String description) {
		this.description = description;
	}

	@Override
	public void trigger() throws GameMessage {
		if (description != null)
			throw new GameMessage(description);
	}

}
