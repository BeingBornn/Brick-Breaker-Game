package model;

import java.awt.Panel;

import controller.GameController;
import view.GamePanel;

public class Paddle {

	//attributes
	private final int baseSpeed = 13;
	private final int y = 680;
	private int x, speedX, width, height;
	GamePanel panel;
	private GameController controller;

	//Constructor
	public Paddle(int x, int speedX, int width, int height, GamePanel panel, GameController controller) {
		this.x = x;
		this.speedX = 0;
		this.width = width;
		this.height = height;
		this.panel = panel;
		this.controller = controller;
	}

	//method for paddle movement
	public void move() {
		x += speedX;
		//clamp to the left wall
		if(x  < 10 ) {
		x = 10;
		}
		//clamp to the right wall
		int max = panel.getWidth() - width- 10;
		if(x > max) {
		x = max;
		}
	}
	// methods for paddle movement by speed
	public void moveRight() {
	speedX = baseSpeed;
	}
	
	public void moveLeft() {
	speedX = -baseSpeed;
	}
	
	// method for stopping the paddle
	public void stop() {
	speedX = 0;	
	}
	
	// method for reseting the speed
	public void resetSpeed() {
		this.speedX = baseSpeed;
	}
	
	// getters and setters
	public GameController getController() {
	    return controller;
	}
	
	public void setController(GameController controller) {
	    this.controller = controller;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getY() {
		return y;
	}
}
