package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Paddle;

public class InputHandler extends KeyAdapter {
	private Paddle paddle;
	private GameController controller;

	// tracking paddle holding
	private boolean leftHeld = false;
	private boolean rightHeld = false;
	private int lastDirection = 0;

	public InputHandler(Paddle paddle, GameController controller) {
		this.paddle = paddle;
		this.controller = controller;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		if(key == KeyEvent.VK_LEFT) {
			if (!leftHeld) lastDirection = -1;
    		leftHeld = true;
		}
		if(key == KeyEvent.VK_RIGHT) {
			if (!rightHeld) lastDirection = 1;
    		rightHeld = true;
		}
		
		updateMovement();
		
		if(key == KeyEvent.VK_UP) {
			controller.releaseBall();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			leftHeld = false;
		}

		if (key == KeyEvent.VK_RIGHT) {
			rightHeld = false;
		}

		updateMovement();
	}

	private void updateMovement() {
    if (leftHeld && rightHeld) {
        if (lastDirection == -1) {
            paddle.moveLeft();
        } else {
            paddle.moveRight();
        }
    } else if (leftHeld) {
        paddle.moveLeft();
    } else if (rightHeld) {
        paddle.moveRight();
    } else {
        paddle.stop();
    }
}
	
	
}
