package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import utility.DrawingUtility;
import utility.MouseUtility;

public class Block implements Renderable{

	private boolean isEmpty;
	private NumberPlate plate;
	private int x, y;

	public Block() {
		this.isEmpty = true;
		plate = null;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public NumberPlate getPlate() {
		return plate;
	}


	public void setPlate(NumberPlate plate) {
		this.plate = plate;
		isEmpty = false;
	}

	public void remove() {
		isEmpty = true;
		plate = null;
	}

	public void draw(GraphicsContext gc, int x, int y) {
		if (isEmpty) {
			gc.setFill(Color.rgb(247, 191, 5));
			int cellSize = DrawingUtility.CELL_SIZE;
			int cellArc = DrawingUtility.CELL_ARC;
			gc.fillRoundRect(x, y, cellSize, cellSize, cellArc, cellArc);
		} else {
			plate.draw(gc, x, y);
		}
		this.x = x;
		this.y = y;

	}

	public boolean isMouseOver() {
		int mouseX = MouseUtility.getMouseX();
		int mouseY = MouseUtility.getMouseY();
		return (x <= mouseX && mouseX <= x + 50 && y <= mouseY && mouseY <= y + 50);
	}

}
