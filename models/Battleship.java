package models;

public class Battleship extends Ship {
	
	private static int length = 4;
	
	public Battleship(int xStart, int xEnd, int yStart, int yEnd) {
		super(xStart, xEnd, yStart, yEnd);
	}
	
	public static int getLength() {
		return length;
	}
}
