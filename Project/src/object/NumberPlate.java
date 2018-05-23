package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import animation.MergeAnimation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import ui.GameScreen;
import utility.AudioUtility;
import utility.DrawingUtility;

public class NumberPlate implements Renderable {
	private static int generateMax = 3;
	private int label;
	private int x, y;
	private GraphicsContext gc;
	private static Random rand = new Random();

	public NumberPlate(int number) {
		label = (int) Math.pow(2,(double) number);
	}

	public int getLabel() {
		return label;
	}

	public void increaseLabel() {
		label=label*2;
		if ((int) (Math.log(label)/Math.log(2)) > generateMax)
			generateMax = (int) (Math.log(label)/Math.log(2));
	}

	public boolean isSameLabel(NumberPlate otherPlate) {
		return this.label == otherPlate.label;
	}


	public void work(Board board, int x, int y, GameScreen gameScreen) {
		List<Block> nearBlocks = board.getNearBlocks(x, y);
		List<NumberPlate> sameLabelPlates = new ArrayList<>();
		for (Block block : nearBlocks) {
			if (!block.isEmpty())
				if (((NumberPlate) block.getPlate()).isSameLabel(this)) {
					sameLabelPlates.add((NumberPlate) block.getPlate());
					block.remove();
				}
		}

		if (sameLabelPlates.size() > 0) {
			AudioUtility.playMulti(sameLabelPlates.size());
			new MergeAnimation(this, sameLabelPlates, gameScreen, gc, board, x, y).start();
		} else {
			if (board.isFull()) {
				AudioUtility.playEnd();

				String showScore = "Your score is : " + gameScreen.getScore();
				Alert alert = new Alert(AlertType.INFORMATION, showScore, ButtonType.OK);
				alert.setHeaderText(null);
				alert.setTitle("END");
				alert.showAndWait();
			}
		}
	}

	public void chooseColor() {
		if (label == 2)
			gc.setFill(Color.rgb(189, 189, 189));
		else if (label == 4)
			gc.setFill(Color.rgb(158, 158, 158));
		else if (label == 8)
			gc.setFill(Color.rgb(174, 213, 129));
		else if (label == 16)
			gc.setFill(Color.rgb(139, 195, 74));
		else if (label == 32)
			gc.setFill(Color.rgb(100, 221, 23));
		else if (label == 64)
			gc.setFill(Color.rgb(118, 255, 3));
		else if (label == 128)
			gc.setFill(Color.rgb(178, 223, 219));
		else if (label == 256)
			gc.setFill(Color.rgb(38, 166, 154));
		else if (label == 512)
			gc.setFill(Color.rgb(0, 150, 136));
		else if (label == 1024)
			gc.setFill(Color.rgb(121, 134, 203));
		else if (label == 2048)
			gc.setFill(Color.rgb(63, 81, 181));
		else if (label == 4096)
			gc.setFill(Color.rgb(26, 35, 126));
		else if (label == 8192)
			gc.setFill(Color.rgb(244, 143, 177));
		else if (label == 16384)
			gc.setFill(Color.rgb(245, 0, 87));
		else if (label == 32768)
			gc.setFill(Color.rgb(229, 115, 115));
		else if (label == 65536)
			gc.setFill(Color.rgb(229, 57, 53));
		else if (label == 131072)
			gc.setFill(Color.rgb(255, 171, 64));
		else if (label == 262144)
			gc.setFill(Color.rgb(255, 183, 77));
		else if (label == 524288)
			gc.setFill(Color.rgb(255, 245, 157));
		else if (label == 1048576)
			gc.setFill(Color.rgb(255, 241, 118));
		else
			gc.setFill(Color.rgb(52, 73, 94));
	}

	public void draw(GraphicsContext gc, int x, int y) {
		this.gc = gc;
		chooseColor();

		gc.setStroke(Color.DARKGOLDENROD);
		int cellSize = DrawingUtility.CELL_SIZE;
		int cellArc = DrawingUtility.CELL_ARC;
		gc.fillRoundRect(x, y, cellSize, cellSize, cellArc, cellArc);
		gc.strokeRoundRect(x, y, cellSize, cellSize, cellArc, cellArc);

		Font font = Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 30);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontWidth = fontLoader.computeStringWidth(label+"", font);
		double fontHeight = fontLoader.getFontMetrics(font).getLineHeight();

		gc.setFont(font);
		gc.setFill(Color.SADDLEBROWN);
		gc.fillText("" + label, x + (DrawingUtility.CELL_SIZE - fontWidth) / 2,
				y + (DrawingUtility.CELL_SIZE + fontHeight - 10) / 2);

		this.x = x;
		this.y = y;

	}

	public static NumberPlate generateRandom() {
		/*
		 * use for increase chance to random a high value number by random again
		 * if value from 1st random is less than 40% of difference between 1 and
		 * generateMax
		 */
		
		double reRandomChance = 0.4;
		int x = 1 + rand.nextInt(generateMax - 1);
		if (x >= generateMax * (1 - reRandomChance))
			return new NumberPlate(x);
		else
			return new NumberPlate(1 + rand.nextInt(generateMax - 1));
	}

	public static void setGenerateMax(int value) {
		if (value < 2)
			generateMax = 2;
		else
			generateMax = value;
	}

	public boolean isSamePosition(NumberPlate n) {
		return this.x == n.x && this.y == n.y;
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}
}
