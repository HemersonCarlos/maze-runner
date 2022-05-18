package com.dev.maze.start;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import com.dev.maze.util.MazeSolver;

public class StartService {
	
	public static void main( String[] args ) {
		
		MazeSolver maze = null;
		String currentPath = null;
		File file = null;
		
		try {
			currentPath = Paths.get( StartService.class.getProtectionDomain().getCodeSource().getLocation().toURI() ).getParent().toString();
		} catch ( URISyntaxException uriSyntaxException ) {
			System.out.println("URISyntaxException - Error - " + uriSyntaxException.getMessage());
		}
		
		if( currentPath != null ) {
			file = new File( currentPath + "\\resources\\Maze.txt" );
		}
		
		if( file != null ) {
			maze = new MazeSolver( file );
		}
	}
}