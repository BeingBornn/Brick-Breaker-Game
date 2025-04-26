package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;

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
 