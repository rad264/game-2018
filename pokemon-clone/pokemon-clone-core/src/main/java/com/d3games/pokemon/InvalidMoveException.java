package com.d3games.pokemon;

public class InvalidMoveException extends RuntimeException {
	private String e = "Invalid Move";

	public InvalidMoveException() {

	}

	public InvalidMoveException(String error) {
		e = error;
	}

	public String getError() {
		return e;
	}

	private static final long serialVersionUID = 1L;
}
