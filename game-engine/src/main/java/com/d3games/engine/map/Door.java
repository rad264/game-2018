package com.d3games.engine.map;

import com.d3games.engine.GameManager;
import com.d3games.engine.InvalidMoveException;

public class Door extends Unit {
	private int targetKey;
	private int targetX;
	private int targetY;

	public Door() {
		super();
	}

	public Door(GameMap target, int x, int y) {
		this();
		setTarget(target.getId(), x, y);
	}

	public Door(int target, int x, int y) {
		this();
		setTarget(target, x, y);
	}

	@Override
	protected String getBackgroundUrl() {
		return "door.png";
	}

	public void setTarget(int targetKey, int x, int y) {
		this.targetKey = targetKey;
		targetX = x;
		targetY = y;
	}

	@Override
	public Unit approach() {
		Unit target = GameManager.getInstance().get(targetKey).get(targetX, targetY);
		if (target == null)
			throw new InvalidMoveException("Door locked!");
		return target;
	}

	@Override
	public String prompt() {
		return "It's a door.";
	}

	@Override
	public String toFile() {
		return "d" + map.getId() + xLoc + yLoc + targetKey + targetX + targetY;
	}

}
