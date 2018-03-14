package controllers;

import models.Battleship;
import models.Destroyer;
import models.Point;

public class GameController {

	private models.Board model;
	private views.Board view;
	
	public GameController(models.Board model, views.Board view) {
		this.model = model;
		this.view = view;
		startGame();
	}
	
	public void startGame() {
		model.initShips();
		model.getPoints().stream().filter(p -> p.getContainsShip())
			.forEach(p -> System.out.println(p.getX() + ":" + p.getY()));
	}
	
	public void restartGame() {
		
	}
}