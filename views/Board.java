package views;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.GameController;
import models.Point;

public class Board extends JFrame {

	private models.Board model;
	private GameController controller;

	private Container cp;
	private JPanel mainPanel;
	private JLabel bottomText;
	
	private boolean gameEnded;

	public Board(models.Board model) {
		// this.controller = controller;
		this.model = model;
		
		cp = getContentPane();
		mainPanel = new JPanel();
		bottomText = new JLabel("Enjoy your game!", SwingConstants.CENTER);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		cp.add(mainPanel);
		cp.add(bottomText);
		
		mainPanel.setLayout(
				new GridLayout(model.getWidth() + 1, model.getHeight() + 1));
		cp.setLayout(new GridLayout(2, 1));
		displayBoard(model.getWidth(), model.getHeight(), model.getPoints());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void clearBoard() {
		
	}
	
	public void gameOver() {
		// clearBoard();
		gameEnded = true;
		bottomText.setText("Game Over, Click any button to restart.");
	}

	public void displayBoard(int x, int y, List<Point> points) {

		mainPanel.add(new JLabel());
		for (int top = 1; top <= x; top++) {
			String letter = Character.toString((char) (top + 64));
			JLabel label = new JLabel(letter, SwingConstants.CENTER);
			mainPanel.add(label);
		}

		for (Point point : points) {
			if (point.getX() == 1) {
				JLabel label = new JLabel(Integer.toString(point.getY()));
				mainPanel.add(label);
			}

			JButton button = new JButton(formatButtonText(point));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(gameEnded) {
						gameEnded = false;
						model.restartGame();
						clearBoard();
						displayBoard(x, y, model.getPoints());
					}
					model.registerPointPress(point.getX(), point.getY());
					if(model.isGameFinished()) {
						gameOver();
					}
					((JButton)e.getSource()).setText(formatButtonText(point));
				}
			});
			mainPanel.add(button);
		}
	}
	
	private String formatButtonText(Point point) {
		String output = "O";
		if(point.getIsHit()) {
			if(point.getContainsShip()) {
				output = "X";
			} else {
				output = "/";
			}
		}
		return output;
	}
}
