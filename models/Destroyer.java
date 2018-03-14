package models;

public class Destroyer extends Ship {

	private static int length = 5;
	
	public Destroyer(int xStart, int xEnd, int yStart, int yEnd) {
		super(xStart, xEnd, yStart, yEnd);
	}
	
	public static int getLength() {
		return length;
	}
}
