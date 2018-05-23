package utility;

public class MouseUtility {
	private static int mouseX, mouseY;
	private static boolean mousePressed=false; 
	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		MouseUtility.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		MouseUtility.mouseY = mouseY;
	}
	
	public static void setMousePressed(boolean mousePressed){
		MouseUtility.mousePressed = mousePressed;
	}
	
	public static boolean isMousePressed(){
		return mousePressed;
	}
}
