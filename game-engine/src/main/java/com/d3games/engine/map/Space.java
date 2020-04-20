package com.d3games.engine.map;

public class Space extends Unit {

	public Space() {
		super();
	}

	@Override
	protected String getBackgroundUrl() {
		return "grass.png";
	}

	@Override
	public String toFile() {
		return "s" + map.getId() + xLoc + yLoc;
	}
}
