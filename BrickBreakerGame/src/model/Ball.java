package model;

/**
 * Represents the ball in the game.
 * Manages its position, velocity, movement, and direction changes.
 * Preserves fractional positions for accurate movement and
 * provides rounded coordinates for rendering.
 */
public class Ball {

	//attributes
	private double speedX, speedY;
	private double x, y; 
	public static final int BALL_SIZE = 25; // ball diameter
	
	//Constructor
	public Ball(int x, int y, int speedX, int speedY){
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
	}
	// method for movement by speed
	public void move() {
	    x += speedX;
	    y += speedY;
	}
	
	// methods for changing direction
	public void reverseX(){
		x -= speedX;   // undo the movement
		speedX *= -1;
	}
	public void reverseY() {
		y -= speedY;   // undo the movement
		speedY *= -1;
	}
	
	// reseting ball position
	public void reset(int panelWidth, int panelHeight) {
		this.x = (panelWidth - BALL_SIZE) / 2;
		this.y = panelHeight - 100;
	}
	
	//Getter and Setter
	public int getX() {
		return (int) Math.round(x);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int) Math.round(y);
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedx) {
		this.speedX = speedx;
	}
	public double getSpeedY() {
		return speedY;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
}
