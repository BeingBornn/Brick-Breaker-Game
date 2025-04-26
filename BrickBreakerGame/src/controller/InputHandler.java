package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Paddle;

public class InputHandler extends KeyAdapter {
	private Paddle paddle;
	
	public InputHandler(Paddle paddle) {
		this.paddle = paddle;
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
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			paddle.stop();
		}
	}
	
	
}
