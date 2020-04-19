package model;

public class Obstacle extends Unit{
	
	public Obstacle() {
		super();
	}
	
	@Override
	protected String getBackgroundUrl() {
		return "brick.png";
	}
	
	@Override
	public Unit approach() {
		throw new InvalidMoveException();
	}
	
	@Override
	public String toFile() {
		return "o"+map.getId()+xLoc+yLoc;
	}
}
