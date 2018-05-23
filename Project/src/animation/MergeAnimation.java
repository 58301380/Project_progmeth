package animation;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import object.Board;
import object.NumberPlate;
import ui.GameScreen;

public class MergeAnimation extends AnimationTimer {
	private List<NumberPlate> aroundPlates;
	private NumberPlate mainPlate, currentMovingPlate;
	private int dirX, dirY, speed;
	private GameScreen gameScreen;
	private Board board;
	private int x, y, score;
	private GraphicsContext gc;

	public MergeAnimation(NumberPlate mainPlate, List<NumberPlate> aroundPlates, GameScreen gameScreen,
			GraphicsContext gc, Board board, int x, int y) {
		this.mainPlate = mainPlate;
		this.aroundPlates = aroundPlates;
		this.gc = gc;
		this.board = board;
		this.x = x;
		this.y = y;
		this.score = mainPlate.getLabel() * (aroundPlates.size() + 1);
		this.gameScreen = gameScreen;
		currentMovingPlate = aroundPlates.get(0);
		this.aroundPlates.remove(0);
		generateCurrentDirection();
		speed = 5;
	}

	@Override
	public void handle(long now) {
		gameScreen.drawScreen();
		currentMovingPlate.draw(gc, currentMovingPlate.getX() + speed * dirX, currentMovingPlate.getY() + speed * dirY);
		for (NumberPlate p : aroundPlates)
			p.draw(gc, p.getX(), p.getY());
		mainPlate.draw(gc, mainPlate.getX(), mainPlate.getY());
		if (currentMovingPlate.isSamePosition(mainPlate)) {
			mainPlate.increaseLabel();
			if (aroundPlates.size() == 0) {
				gameScreen.increaseScore(score);
				gameScreen.drawScreen();
				this.stop();
				mainPlate.work(board, x, y, gameScreen);
			} else {
				currentMovingPlate = aroundPlates.get(0);
				aroundPlates.remove(0);
				generateCurrentDirection();
			}
		}
	}

	private void generateCurrentDirection() {
		if (currentMovingPlate.getX() > mainPlate.getX()) {
			dirX = -1;
			dirY = 0;
		} else if (currentMovingPlate.getX() < mainPlate.getX()) {
			dirX = 1;
			dirY = 0;
		} else if (currentMovingPlate.getY() > mainPlate.getY()) {
			dirX = 0;
			dirY = -1;
		} else {
			dirX = 0;
			dirY = 1;
		}
	}
}
