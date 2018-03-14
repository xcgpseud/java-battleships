package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Ship implements IShip{

	public static enum Orientation{
		HORIZONTAL, VERTICAL
	};
	
	private int xStart, xEnd;
	private int yStart, yEnd;
	
	public Ship(int xStart, int xEnd, int yStart, int yEnd) {
		this.xStart = xStart;
		this.xEnd = xEnd;
		this.yStart = yStart;
		this.yEnd = yEnd;
	}
	
	public int getXStart() {
		return xStart;
	}
	
	public int getXEnd() {
		return xEnd;
	}
	
	public int getYStart() {
		return yStart;
	}
	
	public int getYEnd() {
		return yEnd;
	}
	
	public List<int[]> getPointCoords(){
		
		List<int[]> vectors = new ArrayList<>();
		if(getOrientation() == Orientation.HORIZONTAL) {
			for(int x = xStart; x <= xEnd; x++) {
				int[] coords = {x, yStart};
				vectors.add(coords);
			}
		} else {
			for(int y = yStart; y <= yEnd; y++) {
				int[] coords = {xStart, y};
				vectors.add(coords);
			}
		}
		
		return vectors;
	}
	
	private Orientation getOrientation() {
		if(xStart != xEnd) {
			return Orientation.HORIZONTAL;
		} else {
			return Orientation.VERTICAL;
		}
	}
}
