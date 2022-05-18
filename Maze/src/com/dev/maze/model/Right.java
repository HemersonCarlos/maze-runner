package com.dev.maze.model;

public class Right extends Walk {

	public Right(char[][] maze, Point currentPoint) {
		super(maze, currentPoint);
	}

	@Override
	public Point move() {
		int coordinatesX = currentPoint.getX() + 1;
		int coordinatesY = currentPoint.getY();
		Point point = new Point( 0, 0 );
		if( this.validate( maze, coordinatesX, coordinatesY ) ) {
			point.setX( coordinatesX );
			point.setY( coordinatesY );
		}
		return point;
	}

}
