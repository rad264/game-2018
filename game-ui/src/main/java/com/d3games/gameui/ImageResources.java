package com.d3games.gameui;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.d3games.engine.map.Door;
import com.d3games.engine.map.NPC;
import com.d3games.engine.map.Obstacle;
import com.d3games.engine.map.Player;
import com.d3games.engine.map.Space;
import com.d3games.engine.map.Unit;

public class ImageResources {
	
	private static Map<Class<?>, SimpleImage> simpleImages = new HashMap<>();
	static {
		put(Space.class, "grass.png");
		put(Door.class, "door.png");
		put(Obstacle.class, "brick.png");
	}
	private static Map<Class<?>, CharacterImage> characterImages = new HashMap<>();
	static {
		put(NPC.class, new CharacterImage(getURL("dog.png")));
		put(Player.class, "playerFront.png", "playerBack.png", "playerLeft.png", "playerRight.png");
	}
	
	private static void put(Class<? extends Unit> clazz, String imagePath) {
		put(clazz, new SimpleImage(getURL(imagePath)));
	}
	
	private static void put(Class<? extends Player> clazz, String upPath, String downPath, String leftPath, String rightPath) {
		put(clazz, new CharacterImage(getURL(upPath), getURL(downPath), getURL(leftPath), getURL(rightPath)));
	}

	private static URL getURL(String imagePath) {
		return ImageResources.class.getResource(imagePath);
	}
	
	public static void put(Class<? extends Unit> clazz, SimpleImage image) {
		simpleImages.put(clazz, image);
	}
	
	public static void put(Class<? extends Player> clazz, CharacterImage image) {
		characterImages.put(clazz, image);
	}
	
	public static Image getImage(Unit space) {
		return simpleImages.get(space.getClass()).getImage();
	}
	
	public static Image getImage(Player player) {
		return characterImages.get(player.getClass()).getImage(player);
	}

}
