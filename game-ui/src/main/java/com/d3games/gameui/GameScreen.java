package com.d3games.gameui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.d3games.engine.GameManager;
import com.d3games.engine.GameMessage;
import com.d3games.engine.InvalidMoveException;
import com.d3games.engine.map.GameMap;
import com.d3games.engine.map.Player;
import com.d3games.engine.map.Unit;
import com.d3games.engine.menu.Menu;

public class GameScreen extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Menu activeMenu;
	private Player player;
	private String message;
	private GameMode gameMode = GameMode.WORLD_MAP;

	public GameScreen() {
		GameManager gameManager = GameManager.getInstance();
		player = gameManager.getPlayer();
		activeMenu = gameManager.getMainMenu();
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.WHITE);
		g2d.drawRect(45, 45, 260, 260);

		if (gameMode == GameMode.WORLD_MAP) {
			GameMap playerMap = player.getMap();
			paintMapPlayerFixed(playerMap, g2d);
		} else if (gameMode == GameMode.MENU) {
			paintMenu(g2d);
		}

		if (message != null) {
			g2d.setColor(Color.WHITE);
			g2d.drawString(message, 50, 325);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private void paintMenu(Graphics2D g2d) {
		g2d.fillRect(50, 50, 250, 250);
		g2d.setColor(Color.BLACK);
		int counter = 0;
		for (String item : activeMenu.getDisplayNames()) {
			String selector = activeMenu.getSelected().equals(item) ? "> " : "    ";
			g2d.drawString(selector + item, 75, 75 + 25 * counter);
			counter++;
		}
	}

	@SuppressWarnings("unused")
	private void paintFixedMap(GameMap playerMap, Graphics2D g2d) {
		for (int i = 0; i < playerMap.getWidth(); i++) {
			for (int j = 0; j < playerMap.getHeight(); j++) {
				if (playerMap.get(i, j) != null) {
					Unit unit = playerMap.get(i, j);
					g2d.drawImage(ImageResources.getImage(unit), i * 50, j * 50, this);
					if (unit.hasCharacter())
						g2d.drawImage(ImageResources.getImage(unit.getCharacter()),
								i * 50, j * 50, this);
				}
			}
		}
		g2d.drawImage(ImageResources.getImage(player), player.getXPos() * 50,
				player.getYPos() * 50, this);
	}

	private void paintMapPlayerFixed(GameMap playerMap, Graphics2D g2d) {
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				if (playerMap.get(player.getXPos() + i, player.getYPos() + j) != null) {
					Unit unit = playerMap.get(player.getXPos() + i, player.getYPos() + j);
					g2d.drawImage(ImageResources.getImage(unit), (i + 3) * 50, (j + 3) * 50, this);
					if (unit.hasCharacter())
						g2d.drawImage(ImageResources.getImage(unit.getCharacter()),
								(i + 3) * 50, (j + 3) * 50, this);
				}
			}
		}
		g2d.drawImage(ImageResources.getImage(player), 3 * 50, 3 * 50, this);
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			message = null;
			int key = e.getKeyCode();
			try {
				if (gameMode == GameMode.WORLD_MAP) {
					if (key == KeyEvent.VK_LEFT)
						player.moveLeft();
					else if (key == KeyEvent.VK_RIGHT)
						player.moveRight();
					else if (key == KeyEvent.VK_UP)
						player.moveUp();
					else if (key == KeyEvent.VK_DOWN)
						player.moveDown();
					else if (key == KeyEvent.VK_SPACE)
						message = player.engage();
					else if (key == KeyEvent.VK_P) {
						activeMenu = GameManager.getInstance().getMainMenu();
						gameMode = GameMode.MENU;
					}
				} else if (gameMode == GameMode.MENU) {
					if (key == KeyEvent.VK_P)
						gameMode = GameMode.WORLD_MAP;
					else if (key == KeyEvent.VK_UP)
						activeMenu.up();
					else if (key == KeyEvent.VK_DOWN)
						activeMenu.down();
					else if (key == KeyEvent.VK_RIGHT)
						activeMenu.accessSelected();
					else if (key == KeyEvent.VK_SPACE)
						activeMenu.triggerSelected();
					else if (key == KeyEvent.VK_LEFT)
						activeMenu = activeMenu.back();
				}
			} catch (InvalidMoveException ex) {
				System.out.println(ex.getError());
			} catch (Menu newMenu) {
				activeMenu = newMenu;
			} catch (GameMessage gameMessage) {
				message = gameMessage.getMessage();
			}
			repaint();
		}
	}
}
