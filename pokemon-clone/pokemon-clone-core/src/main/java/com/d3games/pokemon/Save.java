package com.d3games.pokemon;

import java.io.IOException;

public class Save implements MenuItem {
	
	@Override
	public void trigger() throws GameMessage {
		try {
			MapCreator.getInstance().saveMap();
			throw new GameMessage("Game saved.");
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new GameMessage("Save failed.");
		}
	}
	
}
