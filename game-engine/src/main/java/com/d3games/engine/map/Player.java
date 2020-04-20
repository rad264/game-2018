package com.d3games.engine.map;

import com.d3games.engine.InvalidMoveException;

public class Player {
	private Direction gaze = Direction.DOWN;
	protected Unit pos;

	public Player() {
	}

	public Player(Unit pos) {
		this();
		setPos(pos);
	}
	
	public Direction getGaze() {
		return gaze;
	}

	protected String getCharacterImageUrl(Direction dir) {
		switch (dir) {
		case UP:
			return "playerBack.png";
		case RIGHT:
			return "playerRight.png";
		case LEFT:
			return "playerLeft.png";
		default:
			return "playerFront.png";
		}
	}

	public String toFile() {
		return "p" + pos.getMap().getId() + pos.getX() + pos.getY();
	}

	public GameMap getMap() {
		return pos.getMap();
	}

	public int getXPos() {
		return pos.getX();
	}

	public int getYPos() {
		return pos.getY();
	}

	public void move(Direction dir) throws InvalidMoveException {
		if (pos == null)
			throw new InvalidMapSetupException();
		gaze = dir;
		Unit target = pos.getAdjacent(dir).approach();
		if (target != null) {
			setPos(target);
		}
	}

	public void moveUp() throws InvalidMoveException {
		move(Direction.UP);
	}

	public void moveDown() throws InvalidMoveException {
		move(Direction.DOWN);
	}

	public void moveRight() throws InvalidMoveException {
		move(Direction.RIGHT);
	}

	public void moveLeft() throws InvalidMoveException {
		move(Direction.LEFT);
	}

	public void setPos(Unit unit) {
		if (unit.getMap() == null)
			throw new InvalidMapSetupException();
		if (pos != null)
			pos.leave();
		unit.enter(this);
		pos = unit;
	}

	public String engage() {
		if (pos.getAdjacent(gaze) != null)
			return pos.getAdjacent(gaze).prompt();
		return "";
	}

	public String prompt() {
		return "It's like looking in a mirror!";
	}

}
