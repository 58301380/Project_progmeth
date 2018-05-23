package object;

import javafx.scene.canvas.GraphicsContext;

public interface Renderable {
	void draw(GraphicsContext gc, int x, int y);
}
