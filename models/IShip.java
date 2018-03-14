package models;

import java.util.List;

public interface IShip {

	public int getXStart();
	public int getXEnd();
	public int getYStart();
	public int getYEnd();
	public List<int[]> getPointCoords();
	
}
