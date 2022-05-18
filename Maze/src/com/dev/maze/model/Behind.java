package com.dev.maze.model;

public class Behind extends Walk {

	public Behind(char[][] maze, Point currentPoint) {
		super(maze, currentPoint);
	}

	@Override
	public Point move()	 {
		int coordinatesX = currentPoint.getX();
		int coordinatesY = currentPoint.getY() == 0 ? 0 : currentPoint.getY() - 1;
		Point point = new Point( 0, 0 );
		if( this.validate( maze, coordinatesX, coordinatesY ) ) {
			point.setX( coordinatesX );
			point.setY( coordinatesY );
		}
		return point;
	}
}