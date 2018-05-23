package ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import object.*;
import utility.AudioUtility;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Main extends Application {
	public static final Main instance = new Main();

	private Stage primaryStage;
	private Scene gameScene, optionScene;
	private Board board;
	private OptionPanel optionPanel;
	private GameScreen gameScreen;
	private boolean isGameScreenShow;
		
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		optionPanel = new OptionPanel();
		optionScene = new Scene(optionPanel);
		isGameScreenShow = false;
	
		primaryStage.setScene(optionScene);
		optionPanel.getStartButton().setOnAction(e -> {
			AudioUtility.playPlaying();
			toggleScene();
		});
		
		primaryStage.setTitle("Setting");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
		AudioUtility.playWait();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void toggleScene() {
		isGameScreenShow = !isGameScreenShow;
		if (isGameScreenShow) {
			board = new Board(optionPanel.getColumnValue(), optionPanel.getRowValue());
			NumberPlate.setGenerateMax((int) Math.sqrt( Math.ceil(((double) (board.getColumn() + board.getRow())) / 2) - 1));
			gameScreen = new GameScreen(board, optionPanel.getSelValue());
			gameScene = new Scene(gameScreen);
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Fusion X");
			primaryStage.centerOnScreen();
		} else {
			primaryStage.setScene(optionScene);
			primaryStage.setTitle("Game Setting");
			primaryStage.centerOnScreen();
		}
	}
}
