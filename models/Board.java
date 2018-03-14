package models;

import java.util.ArrayList;
import java.util.List;

import services.ShipService;

public class Board {

	private List<Point> points;
	private List<Ship> ships;
	
	// Defaults
	private int width;
	private int height;
	
	private int[] allowedLengths = {
			Destroyer.getLength(), 
			Battleship.getLength()
	};
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		
		points = new ArrayList<Point>();
		ships = new ArrayList<Ship>();
		
		initPoints();
	}
	
	public void addShip(Ship ship) {
		ships.add(ship);
	}
	
	public void addRandomShip(int length) {
		Ship ship = ShipService.generateRandomShip(length, this);
		ships.add(ship);
		
		// Set these points on the board so they contain a ship
		ships.stream().forEach(s ->
			getPoints(s.getPointCoords()).forEach(p ->
				p.setContainsShip(true)
			)
		);
	}
	
	private List<Point> getPoints(List<int[]> vectors){
		// Vectors should be [x,y]
		List<Point> pts = new ArrayList<>();
		vectors.stream().forEach(v -> 
			points.stream().filter(p ->
				p.getX() == v[0] &&
				p.getY() == v[1])
			.forEach(p -> pts.add(p))
		);
		return pts;
	}
	
	private void initPoints() {
		
		for(int y = 1; y <= height; y++) {
			for(int x = 1; x <= width; x++) {
				points.add(new Point(x, y));
			}
		}
	}
	
	public void initShips() {
		addRandomShip(Destroyer.getLength());
		addRandomShip(Battleship.getLength());
		addRandomShip(Battleship.getLength());
	}
	
	public boolean pointHasShip(Point point) {
		
		// todo: try and get filter working here without comparing actual coords
		return (points.stream().filter((p) ->
			p.getX() == point.getX() &&
			p.getY() == point.getY())
		.findFirst().orElse(null) != null) 
			? true : false;
		
	}
	
	public boolean isGameFinished() {
		return (points.stream().filter((p) -> 
			p.getContainsShip() &&
			!p.getIsHit())
		.findFirst().orElse(null) == null)
			? true : false;
	}
	
	public void registerPointPress(int x, int y) {
		Point point = getPoint(x, y);
		if(!point.equals(null)) {
			point.hit();
		}
	}
	
	public void restartGame() {
		points.clear();
		ships.clear();
		
		initPoints();
		initShips();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public List<Point> getPoints(){
		return points;
	}
	
	public Point getPoint(int x, int y) {
		return points.stream().filter((p) ->
			p.getX() == x &&
			p.getY() == y
		).findFirst().orElse(null);
	}
	
	public int[] getAllowedLengths() {
		return allowedLengths;
	}
	
	public List<Ship> getShips(){
		return ships;
	}
}
