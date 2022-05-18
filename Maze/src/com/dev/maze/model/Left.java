package com.dev.maze.model;

public class Left extends Walk {

	public Left(char[][] maze, Point currentPoint) {
		super(maze, currentPoint);
	}

	@Override
	public Point move() {
		int coordinatesX = currentPoint.getX() == 0 ? 0 : currentPoint.getX() - 1;
		int coordinatesY = currentPoint.getY();
		Point point = new Point( 0, 0 );
		if( this.validate( maze, coordinatesX, coordinatesY ) ) {
			point.setX( coordinatesX );
			point.setY( coordinatesY );
		}
		return point;
	}
}
