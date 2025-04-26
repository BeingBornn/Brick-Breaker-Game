package model;

public class Brick {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean brickExist;
	private int score;
	
	
	public Brick(int x, int y, int width, int height, boolean brickExist, int score) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setBrickExist(brickExist);
		this.setScore(score);
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
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


	public boolean isBrickExist() {
		return brickExist;
	}


	public void setBrickExist(boolean brickExist) {
		this.brickExist = brickExist;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

}
