package com.d3games.gameui;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import com.d3games.engine.map.Player;

public class CharacterImage {

	private Image facingDown;
	private Image facingUp;
	private Image facingLeft;
	private Image facingRight;

	public CharacterImage(URL facingDown, URL facingUp, URL facingLeft, URL facingRight) {
		this.facingDown = getImage(facingDown);
		this.facingUp = getImage(facingUp);
		this.facingLeft = getImage(facingLeft);
		this.facingRight = getImage(facingRight);
	}

	public CharacterImage(URL facingDown) {
		this.facingDown = getImage(facingDown);
	}

	private static Image getImage(URL imageURL) {
		return new ImageIcon(imageURL).getImage();
	}

	public Image getImage(Player character) {
		switch (character.getGaze()) {
		case UP:
			return facingUp;
		case LEFT:
			return facingLeft;
		case RIGHT:
			return facingRight;
		default:
			return facingDown;
		}
	}

}
