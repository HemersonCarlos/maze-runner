package com.dev.maze.model;

public abstract class Walk {
	protected char[][] maze; 
	protected Point currentPoint;
	private boolean valid = false;
	
	
	public Walk(char[][] maze, Point currentPoint) {
		this.maze = maze;
		this.currentPoint = currentPoint;
	}
	
	public abstract Point move();
	
	public boolean isValid() {
		return valid;
	}

	protected  boolean validate( char[][] maze, int coordinatesX, int coordinatesY ) {
		char character = maze[coordinatesX][coordinatesY];
		if( character == '.' || character == 'C' ) {
			valid = true;
		}
		return valid;
	}	
	
	
	
}