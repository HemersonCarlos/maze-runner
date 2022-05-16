package com.dev.maze.model;

import java.util.ArrayList;
import java.util.List;

import com.dev.maze.util.Utils;

public class BusinessRule {
	
	public static List<Integer> walkRight(char[][] maze, List<Integer> currentPoint) {
		int coordinatesX = currentPoint.get(0) - 1;
		int coordinatesY = currentPoint.get(1);
		List<Integer> point = new ArrayList<Integer>();
		if(Utils.isValidWay(maze, coordinatesX, coordinatesY)) {
			point.add(coordinatesX);
			point.add(coordinatesY);
		}
		return point;
	}
	
	public static List<Integer> walkFront(char[][] maze, List<Integer> currentPoint) {
		int coordinatesX = currentPoint.get(0);
		int coordinatesY = currentPoint.get(1) + 1;
		List<Integer> point = new ArrayList<Integer>();
		if(Utils.isValidWay(maze, coordinatesX, coordinatesY)) {
			point.add(coordinatesX);
			point.add(coordinatesY);
		}
		return point;
	}
	
	public static List<Integer> walkLeft(char[][] maze, List<Integer> currentPoint) {
		int coordinatesX = currentPoint.get(0) + 1;
		int coordinatesY = currentPoint.get(1);
		List<Integer> point = new ArrayList<Integer>();
		if(Utils.isValidWay(maze, coordinatesX, coordinatesY)) {
			point.add(coordinatesX);
			point.add(coordinatesY);
		}
		return point;
	}
	
	public static List<Integer> walkBehind(char[][] maze, List<Integer> currentPoint) {
		int coordinatesX = currentPoint.get(0);
		int coordinatesY = currentPoint.get(1) - 1;
		List<Integer> point = new ArrayList<Integer>();
		if(Utils.isValidWay(maze, coordinatesX, coordinatesY)) {
			point.add(coordinatesX);
			point.add(coordinatesY);
		}
		return point;
	}
}