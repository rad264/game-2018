package com.d3games.pokemon;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
	private GameMap map;
	private Space space1;
	private Space space2;
	private Player player;

	@Before
	public void setup() {
		map = new GameMap(0, 10, 10);
		space1 = new Space();
		space2 = new Space();
		player = new Player();
	}

	@Test
	public void initializePlayer() {
		map.add(space1, 5, 6);
		player.setPos(space1);
		assertEquals(5, player.getXPos());
		assertEquals(6, player.getYPos());
	}

	@Test(expected = InvalidMapSetupException.class)
	public void moveBeforePlayerInitialized() {
		player.moveUp();
	}

	@Test(expected = InvalidMapSetupException.class)
	public void initializePlayerInUnitWithoutMap() {
		player.setPos(space1);
	}

	@Test
	public void testSize() {
		assertEquals(100, map.maxSize());
		assertEquals(0, map.size());
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void attemptAddSpaceOutsideMapBoundaries() {
		map.add(space1, 10, 0);
		assertEquals(0, map.size());
	}

	@Test
	public void addSpaceToMap() {
		map.add(space1, 0, 0);
		assertEquals(1, map.size());
	}

	@Test(expected = InvalidMapSetupException.class)
	public void attemptAddSpaceWhereOneAlreadyExists() {
		map.add(space1, 0, 0);
		map.add(space2, 0, 0);
		assertEquals(1, map.size());
	}

	@Test(expected = InvalidMoveException.class)
	public void attemptMoveOutsideMapBoundaries() {
		map.add(space1, 0, 0);
		player.setPos(space1);
		player.moveLeft();
	}

	@Test(expected = InvalidMoveException.class)
	public void attemptMoveToNonExistentSpace() {
		map.add(space1, 0, 0);
		player.setPos(space1);
		player.moveUp();
	}

	@Test
	public void moveUp() {
		map.add(space1, 0, 0);
		map.add(space2, 0, 1);
		player.setPos(space2);
		assertEquals(0, player.getXPos());
		assertEquals(1, player.getYPos());
		player.moveUp();
		assertEquals(0, player.getXPos());
		assertEquals(0, player.getYPos());
	}

	@Test
	public void moveRight() {
		map.add(space1, 0, 0);
		map.add(space2, 1, 0);
		player.setPos(space1);
		assertEquals(0, player.getXPos());
		assertEquals(0, player.getYPos());
		player.moveRight();
		assertEquals(1, player.getXPos());
		assertEquals(0, player.getYPos());
	}
}
