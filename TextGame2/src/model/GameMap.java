package model;

public class GameMap {
	private int id;
	private int size = 0;
	private int width = 0;
	private int height = 0;
	private Unit[][] map;

	public GameMap(int id, int x, int y) {
		this.id = id;
		map = new Unit[x][y];
		width = x;
		height = y;
	}

	public int getId() {
		return id;
	}

	public void add(Unit unit, int x, int y) {
		if (map[x][y] != null)
			throw new InvalidMapSetupException();
		map[x][y] = unit;
		unit.setLocation(this, x, y);
		size++;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int size() {
		return size;
	}

	public int maxSize() {
		return width * height;
	}

	public Unit get(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return null;
		return map[x][y];
	}

}
