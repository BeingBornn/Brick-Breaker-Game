package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Paddle;

public class InputHandler extends KeyAdapter {
	private Paddle paddle;
	private GameController controller;

	public InputHandler(Paddle paddle, GameController controller) {
		this.paddle = paddle;
		this.controller = controller;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		if(key == KeyEvent.VK_LEFT) {
			paddle.moveLeft();
		}
		if(key == KeyEvent.VK_RIGHT) {
			paddle.moveRight();
		}
		if(key == KeyEvent.VK_UP) {
			controller.releaseBall();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			paddle.stop();
		}
	}
	
	
}
