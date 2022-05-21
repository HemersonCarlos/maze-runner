package com.dev.maze.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.dev.maze.model.Behind;
import com.dev.maze.model.Front;
import com.dev.maze.model.Left;
import com.dev.maze.model.Point;
import com.dev.maze.model.Right;
import com.dev.maze.model.Walk;

public class MazeSolver {
	private static final char START = 'S';
	private static final char CHEESE = 'C';
	private static final char EXIT = 'E';
	private static final char POINT = '.';
	
	
	public MazeSolver( File file ) {
		char maze[][] = null;
		maze = this.initializeMaze( file );
		System.out.println("Start the maze to seach the exit...");
		this.solve( this.gotoStart( maze, START ), maze );
		System.out.println("Finish maze...");	
		this.printMaze( maze );
	}
	
	private char[][] initializeMaze( File file ) {
		int mazeRows = 0;
		int mazeColumns = 0;
		char [][] maze = null;
		Path path = null;
		
		try {
			path = Paths.get( file.getPath() );
			mazeRows = (int) Files.lines( path ).count();
			mazeColumns = Files.readAllLines( path ).get(0).length();
			maze = new char[mazeRows][mazeColumns];
		} catch ( IOException ioException ) {
			System.out.println("IOException - Error - " + ioException.getMessage());
		}
					
		for(int countMazeLine = 0; countMazeLine < mazeRows; countMazeLine++) {
			String line = null;
			try {
				line = Files.readAllLines( path ).get( countMazeLine );
			} catch (IOException ioException) {
				System.out.println("IOException - Error - " + ioException);
			}
			for(int countMazeColumns = 0; countMazeColumns < mazeColumns; countMazeColumns++) {					
				maze[countMazeLine][countMazeColumns] = line.charAt( countMazeColumns );
			}
		}
		return maze;
	}
	
	private Point gotoStart( char[][] maze, char start ) {
        Point point = new Point( 0,0 );
		for(int rowsCount = 0; rowsCount < maze.length; rowsCount++) {
			for(int columnsCount = 0; columnsCount < maze[rowsCount].length; columnsCount++) {
				if( maze[rowsCount][columnsCount] == start ) {
					point.setX(rowsCount);
					point.setY(columnsCount);
				}
			}
		}
		return point;
	}
	
	private void solve( Point currentPoint, char[][] maze ) {
		List<Point> nextStep = new ArrayList<>();
		char currentCharacter = this.characterMaze( maze, currentPoint );
		
		if( currentCharacter == EXIT ) {
			return;
		} else {
            Walk[] movement = {
            		new Right( maze, currentPoint ), 
            		new Front( maze, currentPoint ), 
            		new Left( maze, currentPoint ), 
            		new Behind( maze, currentPoint ) 
            };
            
            for(int count = 0; count < movement.length; count++) {
				Point point = movement[count].move(); // Attention point
				if( movement[count].isValid() ) {
					nextStep.add( point );
				}
			}
			
			this.whereAmI( currentPoint, maze );
			
			if( currentCharacter == CHEESE ) {
				System.out.println("BURRRP!!!");
			}
			
			for(int count = 0; count < nextStep.size(); count++) {
				this.solve( nextStep.get( count ), maze );
			}
		}
	}
	
	private char characterMaze( char[][] maze, Point point ) {
		return maze[point.getX()][point.getY()];
	}
	
	private void whereAmI( Point currentPoint, char[][] maze ) {
		int coordinatesX = currentPoint.getX();
		int coordinatesY = currentPoint.getY();
		char character = maze[coordinatesX][coordinatesY];
		
		if(character == POINT || character == CHEESE || character == START || character == EXIT) {
			maze[coordinatesX][coordinatesY] = '@';
		}
	}
	
	private void printMaze( char [][] maze ) {
		for (int rowsCount = 0; rowsCount < maze.length; rowsCount++) {
		    for (int columnCount = 0; columnCount < maze[rowsCount].length; columnCount++) {
		        System.out.print(maze[rowsCount][columnCount] + " ");
		    }
		    System.out.println();
		}
	}
}