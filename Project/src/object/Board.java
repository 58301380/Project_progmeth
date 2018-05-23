package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import utility.DrawingUtility;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	private Block[][] blocks;

	private int column, row;
	private int width, height;
	private int padding, gap;
	private final static int defaultBoardSize = 5;

	public Board() {
		this(defaultBoardSize);
	}

	public Board(int size) {
		this(size, size);
	}

	public Board(int column, int row) {
		this.row = row;
		this.column = column;
		blocks = new Block[column][row];
		padding = 50;
		gap = 25;
		width = 2 * padding + (column - 1) * gap + column * DrawingUtility.CELL_SIZE;
		height = 2 * padding + (row - 1) * gap + row * DrawingUtility.CELL_SIZE;

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				blocks[i][j] = new Block();
			}
		}
		generateInitialPlates();
	}

	public int getColumn() {
		return column;
	}
  
  public int getRow() {
		return row;
	}

	public List<Block> getNearBlocks(int col, int row) {
		List<Block> list = new ArrayList<>();
		if (col - 1 >= 0)
			list.add(blocks[col - 1][row]);
		if (row - 1 >= 0)
			list.add(blocks[col][row - 1]);
		if (col + 1 < column)
			list.add(blocks[col + 1][row]);
		if (row + 1 < this.row)
			list.add(blocks[col][row + 1]);
		return list;
	}

	public void remove(int x, int y) {
		blocks[x][y].remove();
	}


	public void place(NumberPlate plate, int x, int y) {
		blocks[x][y].setPlate(plate);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public Block[][] getBlocks() {
		return blocks;
	}

	public void generateInitialPlates() {
		int blocksCount = column * row - 1;
		int logicFactor = 12;
		int platesCount = (int) Math.ceil((blocksCount) / logicFactor);
		Random rand = new Random();
		while (platesCount > 0) {
			
			if (rand.nextInt(blocksCount) < platesCount) {
				blocks[(blocksCount - (blocksCount % row)) / row][blocksCount % row]
						.setPlate(NumberPlate.generateRandom());
				platesCount--;
			}
			blocksCount--;
		}
	}

	public void draw(GraphicsContext gc, int x, int y) {
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				blocks[i][j].draw(gc, x + padding + i * (DrawingUtility.CELL_SIZE + gap),
						y + padding + j * (DrawingUtility.CELL_SIZE + gap));
			}
		}
	}

	public boolean isFull() {
		for (Block[] blocks : this.blocks) {
			for (Block block : blocks) {
				if (block.isEmpty())
					return false;
			}
		}
		return true;
	}
}
