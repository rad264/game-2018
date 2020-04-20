package com.d3games.engine.menu;

import org.apache.commons.lang3.StringUtils;

import com.d3games.engine.GameMessage;

public class UselessItem implements MenuItem {
	private String description;

	public UselessItem() {

	}

	public UselessItem(String description) {
		this.description = description;
	}

	@Override
	public void trigger() throws GameMessage {
		throw new GameMessage(StringUtils.defaultString(description, "Not sure what this is..."));
	}

}
