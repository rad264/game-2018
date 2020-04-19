package com.d3games.pokemon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapCreator {
	private static MapCreator mc;

	private MapCreator() {

	}

	public static MapCreator getInstance() {
		if (mc == null)
			mc = new MapCreator();
		return mc;
	}

	public void generateMap() throws IOException {
		Scanner scanner;
		File file = new File("world3.txt");

		if (file.canRead()) {
			scanner = new Scanner(file);
		} else {
			scanner = new Scanner(this.getClass().getResourceAsStream("world3.txt"));
		}

		GameManager gameManager = GameManager.getInstance();
		String line;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			int mapRef = Character.getNumericValue(line.charAt(1));
			int x = Character.getNumericValue(line.charAt(2));
			int y = Character.getNumericValue(line.charAt(3));
			if (line.charAt(0) == 'm') {
				gameManager.add(new GameMap(mapRef, x, y));
				mapRef++;
			} else if (line.charAt(0) == 's') {
				gameManager.get(mapRef).add(new Space(), x, y);
			} else if (line.charAt(0) == 'o') {
				gameManager.get(mapRef).add(new Obstacle(), x, y);
			} else if (line.charAt(0) == 'd') {
				int targetRef = Character.getNumericValue(line.charAt(4));
				int targetX = Character.getNumericValue(line.charAt(5));
				int targetY = Character.getNumericValue(line.charAt(6));
				gameManager.get(mapRef).add(
						new Door(targetRef, targetX, targetY), x, y);
			} else if (line.charAt(0) == 'p') {
				gameManager.setPlayer(new Player(gameManager.get(mapRef).get(x,
						y)));
			} else if (line.charAt(0) == 'n') {
				new NPC(gameManager.get(mapRef).get(x, y));
			}
		}

		scanner.close();
	}

	public void saveMap() throws IOException {
		File outputFile = new File("world3.txt");
		FileWriter fw = new FileWriter(outputFile, false);
		BufferedWriter bw = new BufferedWriter(fw);
		for (GameMap map : GameManager.getInstance().getAll()) {
			bw.write("m" + map.getId() + map.getWidth() + map.getHeight());
			bw.newLine();
			for (int x = 0; x < map.getWidth(); x++) {
				for (int y = 0; y < map.getHeight(); y++) {
					if (map.get(x, y) != null) {
						bw.write(map.get(x, y).toFile());
						bw.newLine();
						if (map.get(x, y).hasCharacter()) {
							bw.write(map.get(x, y).getCharacter().toFile());
							bw.newLine();
						}
					}
				}
			}
		}
		fw.flush();
		bw.flush();
		fw.close();
		bw.close();
	}
}