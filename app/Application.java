package app;

import controllers.GameController;

public class Application {

	public static void main(String[] args) {
		
		models.Board model = new models.Board(10, 10);
		views.Board view = new views.Board(model);
		new GameController(model, view);
	}

}
