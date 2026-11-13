package view;

import javax.swing.JFrame;

/**
 * Creates and configures the main Brick Breaker game window.
 * Initializes the game panel and starts the application.
 */
public class GameView extends JFrame{
	
	public static final int SCREEN_WIDTH = 1024; 
	public static final int SCREEN_HEIGHT = 768; 
	
	public GameView() {

		setTitle("Brick Breaker");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(new GamePanel());
		setVisible(true);  
	}
	
public static void main(String[] args) {

	new GameView();
}
}
 