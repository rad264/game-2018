package com.d3games.gameui;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class SimpleImage {
	
	private Image image;
	
	public SimpleImage(URL imageURL) {
		this.image = new ImageIcon(imageURL).getImage();
	}

	public Image getImage() {
		return image;
	}

}
