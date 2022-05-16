package com.dev.maze.start;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.dev.maze.util.Utils;

public class StartService {
	
	public static void main(String[] args) {
		
		final char star = 'S';
		char [][] maze = null;
		String currentPath = null;
		File file = null;
		
		try {
			currentPath = Paths.get(StartService.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
		} catch (URISyntaxException uriSyntaxException) {
			System.out.println("URISyntaxException - Error - " + uriSyntaxException.getMessage());
		}
		
		if(currentPath != null) {
			file = new File(currentPath + "\\resources\\Maze.txt");
		}
		
		if(file != null) {
			maze = Utils.initializeMaze(file);
		}
				
		System.out.println("Start the maze to seach the exit...");
		Utils.possibilitiesOfSolutions(Utils.findStartingPoint(maze, star), maze);
		System.out.println("Finish maze...");		
	}
}