package com.d3games.engine.menu;

import com.d3games.engine.GameMessage;

public interface MenuItem {
	
	public void trigger() throws Menu, GameMessage;

}
