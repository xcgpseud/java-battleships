package services;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import models.Board;
import models.Point;
import models.Ship;

public class ShipService {
	
	public static Ship generateRandomShip(int length, Board board) {
		
		Random rand = new Random();
		
		List<Ship> possibleShips = generateNonIntersectingShips(length, board);
		int index = rand.nextInt(possibleShips.size());
		Ship ship = possibleShips.get(index);

		return ship;
	}
	
	private static List<Ship> generateNonIntersectingShips(
			int length, Board board){
		List<Ship> ships = generateAllPossibleShips(length, board);
		List<Ship> validShips = getNonIntersectingShips(ships, board);
		return validShips;
	}
	
	private static List<Ship> getNonIntersectingShips(List<Ship> ships, Board board){
		List<Ship> nonIntersectingShips = new ArrayList<Ship>();
		List<Ship> currentShips = board.getShips();
		for(Ship ship : ships) {
			boolean intersects = false;
			for(Ship otherShip : currentShips) {
				intersects = shipsIntersect(ship, otherShip);
				if(intersects) {
					break;
				}
			}
			if(!intersects) {
				nonIntersectingShips.add(ship);
			}
		}
		return nonIntersectingShips;
	}
	
	private static boolean shipsIntersect(Ship ship, Ship otherShip) {
		
		Line2D first = new Line2D.Float(
				ship.getXStart(), ship.getXEnd(),
				ship.getYStart(), ship.getYEnd());
		Line2D second = new Line2D.Float(
				otherShip.getXStart(), otherShip.getXEnd(),
				otherShip.getYStart(), otherShip.getYEnd());
		
		return first.intersectsLine(second);
	}

	private static ArrayList<Ship> generateAllPossibleShips(int length, Board board){
		
		ArrayList<Ship> ships = new ArrayList<Ship>();
		
		Arrays.stream(board.getAllowedLengths())
			.forEach(n -> {
				ships.addAll(generateShips(board, length, Ship.Orientation.HORIZONTAL));
				ships.addAll(generateShips(board, length, Ship.Orientation.VERTICAL));
	
			});
			
		return ships;
	}
	
	private static ArrayList<Ship> generateShips(
			Board board, int length, Ship.Orientation orientation) {

		int xMax = board.getWidth();
		int yMax = board.getHeight();
		
		switch(orientation) {
		
		case HORIZONTAL:
			xMax -= length;
			break;
		case VERTICAL:
			yMax -= length;
			break;
		
		}
		
		ArrayList<Ship> ships = new ArrayList<Ship>();
		for(int x = 1; x <= xMax; x++) {
			for(int y = 1; y <= yMax; y++) {
				int xStart = x;
				int yStart = y;
				int xEnd, yEnd;
				if(orientation == Ship.Orientation.HORIZONTAL) {
					xEnd = x + length - 1;
					yEnd = y;
				}else {
					xEnd = x;
					yEnd = y + length - 1;
				}
				ships.add(new Ship(xStart, xEnd, yStart, yEnd));
			}
		}
		return ships;
	}
}
