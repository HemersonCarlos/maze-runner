package com.dev.maze.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.dev.maze.model.BusinessRule;

public class Utils {
	
	public static char[][] initializeMaze(File file) {
		int mazeRows = 0;
		int mazeColumns = 0;
		char [][] maze = null;
		Path path = null;
		
		try {
			path = Paths.get(file.getPath());
			mazeRows = (int) Files.lines(path).count();
			mazeColumns = Files.readAllLines(path).get(0).length();
			maze = new char[mazeRows][mazeColumns];
		} catch (IOException ioException) {
			System.out.println("IOException - Error - " + ioException.getMessage());
		}
					
		for(int countMazeLine = 0; countMazeLine < mazeRows; countMazeLine++) {
			String line = null;
			try {
				line = Files.readAllLines(path).get(countMazeLine);
			} catch (IOException ioException) {
				System.out.println("IOException - Error - " + ioException);
			}
			for(int countMazeColumns = 0; countMazeColumns < mazeColumns; countMazeColumns++) {					
				maze[countMazeLine][countMazeColumns] = line.charAt(countMazeColumns);
			}
		}
		return maze;
	}
	
	public static List<Integer> findStartingPoint(char[][] maze, char start) {
		List<Integer> points = new ArrayList<Integer>();
		for(int rowsCount = 0; rowsCount < maze.length; rowsCount++) {
			for(int columnsCount = 0; columnsCount < maze[rowsCount].length; columnsCount++) {
				if(maze[rowsCount][columnsCount] == start) {
					points.add(rowsCount);
					points.add(columnsCount);
				}
			}
		}
		return points;
	}
	
	public static List<Integer> findFinishPoint(char[][] maze, char exit) {
		List<Integer> points = new ArrayList<Integer>();
		for(int rowsCount = 0; rowsCount < maze.length; rowsCount++) {
			for(int columnsCount = 0; columnsCount < maze[rowsCount].length; columnsCount++) {
				if(maze[rowsCount][columnsCount] == exit) {
					points.add(rowsCount);
					points.add(columnsCount);
				}
			}
		}
		return points;
	}
	
	public static List<List<Integer>> possibilitiesOfSolutions(List<Integer> currentPoint, char[][] maze) {
		final char cheese = 'C';
		List<Integer> wayMazeDirection = new ArrayList<Integer>();
		List<List<Integer>> possibilitiesWayToMazeList = new ArrayList<>();
		char currentCaracter = Utils.caractereMaze(maze, currentPoint);
		
		if(!BusinessRule.walkRight(maze, currentPoint).isEmpty()) {
			wayMazeDirection = BusinessRule.walkRight(maze, currentPoint);
			possibilitiesWayToMazeList.add(wayMazeDirection);
		}
		if(!BusinessRule.walkFront(maze, currentPoint).isEmpty()) {
			wayMazeDirection = BusinessRule.walkFront(maze, currentPoint);
			possibilitiesWayToMazeList.add(wayMazeDirection);
		}
		if(!BusinessRule.walkLeft(maze, currentPoint).isEmpty()) {
			wayMazeDirection = BusinessRule.walkLeft(maze, currentPoint);
			possibilitiesWayToMazeList.add(wayMazeDirection);
		}
		if(!BusinessRule.walkBehind(maze, currentPoint).isEmpty()) {
			wayMazeDirection = BusinessRule.walkBehind(maze, currentPoint);
			possibilitiesWayToMazeList.add(wayMazeDirection);
		}
		
		Utils.printMaze(maze);
		Utils.IAmHereInTheMaze(currentPoint, maze);
		
		if(currentCaracter == cheese) {
			System.out.println("BURRRP!!!");
		}
		
		for(int count = 0; count < possibilitiesWayToMazeList.size(); count++) {
			Utils.possibilitiesOfSolutions(possibilitiesWayToMazeList.get(count), maze);
		}
		
		return possibilitiesWayToMazeList;
	}
	
	public static char caractereMaze(char[][] maze, List<Integer> point) {
		char caractere = '-';
		int coordinatesX = point.get(0);
		int coordinatesY = point.get(1);
		if(!point.isEmpty()) {
			caractere = maze[coordinatesX][coordinatesY];
		}
		return caractere;
	}
	
	public static boolean isValidWay(char[][] maze, int coordinatesX, int coordinatesY) {
		boolean isValidate = false;
		char caractere = maze[coordinatesX][coordinatesY];
		if(caractere == '.' || caractere == 'C') {
			isValidate = true;
		}
		return isValidate;
	}	
	
	public static void IAmHereInTheMaze(List<Integer> currentPoint, char[][] maze) {
		int coordinatesX = currentPoint.get(0);
		int coordinatesY = currentPoint.get(1);
		char caractere = maze[coordinatesX][coordinatesY];
		char point = '.';
		char cheese = 'C';
		char start = 'S';
		char exit = 'E';
		
		if(caractere == point || caractere == cheese || caractere == start || caractere == exit) {
			maze[coordinatesX][coordinatesY] = '*'; 
		}
	}
	
	public static void printMaze(char [][] maze) {
		for (int rowsCount = 0; rowsCount < maze.length; rowsCount++) {
		    for (int columnCount = 0; columnCount < maze[rowsCount].length; columnCount++) {
		        System.out.print(maze[rowsCount][columnCount] + " ");
		    }
		    System.out.println();
		}
		System.out.println("***************");
	}
}