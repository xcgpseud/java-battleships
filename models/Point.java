package models;

public class Point {

	private int x;
	private int y;
	private boolean isHit;
	private boolean containsShip;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getIsHit() {
		return isHit;
	}
	
	public boolean getContainsShip() {
		return containsShip;
	}
	
	public void hit() {
		isHit = true;
	}
	
	public void setContainsShip(boolean contains) {
		containsShip = contains;
	}
	
	public boolean equals(Point point) {
		if(point == null) {
			return false;
		}
		if(point.getX() == getX() && point.getY() == getY()) {
			return true;
		}
		return false;
	}
}