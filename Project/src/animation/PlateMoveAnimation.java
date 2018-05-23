package animation;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import object.NumberPlate;
import ui.GameScreen;
import utility.DrawingUtility;
import utility.MouseUtility;

public class PlateMoveAnimation extends AnimationTimer {
	private NumberPlate plate;
	private GraphicsContext gc;
	private GameScreen gameScreen;

	public PlateMoveAnimation(GameScreen gameScreen, GraphicsContext gc, NumberPlate plate) {
		this.plate = plate;
		this.gc = gc;
		this.gameScreen = gameScreen;
	}

	@Override
	public void handle(long Times) {
		gameScreen.drawScreen();
		plate.draw(gc, MouseUtility.getMouseX() - DrawingUtility.CELL_SIZE / 2,
				MouseUtility.getMouseY() - DrawingUtility.CELL_SIZE / 2);
	}

	public NumberPlate getPlate() {
		return plate;
	}
}
