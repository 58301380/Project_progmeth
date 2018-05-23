package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.DrawingUtility;

public class SelectField {

	private int width;
	private int height;
	private Block[] blocks;

	public SelectField(Board board, int n) {
		this.width = board.getWidth();
		this.height = 100;
		createBlocks(n);

	}

	public void createBlocks(int n) {
		blocks = new Block[n];
		for (int i = 0; i < n; i++) {
			blocks[i] = new Block();
			blocks[i].setPlate(NumberPlate.generateRandom());
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Block[] getBlocks() {
		return blocks;
	}

	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.rgb(249, 210, 15));
		gc.fillRoundRect(x, y, width, height, 50, 50);
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].draw(gc, x + width / (blocks.length + 1) * (i + 1) - DrawingUtility.CELL_SIZE / 2, y + DrawingUtility.CELL_SIZE / 2);
		}
	}
}
