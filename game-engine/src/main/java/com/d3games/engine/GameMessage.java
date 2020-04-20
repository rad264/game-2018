package com.d3games.engine;

public class GameMessage extends Throwable {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public GameMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
