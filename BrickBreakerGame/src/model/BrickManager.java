package model;

public class BrickManager {

	// Bricks grid
	private Brick[][] bricks;


	// where the drawing starts
	private int startX;
	private int startY;
	// rows and columns
	private int cols;
	private int rows;
	
	
	// Constructor
	public BrickManager(int startX, int startY, int cols, int rows){
		this.startX = startX;
		this.startY = startY;
		this.cols = cols;
		this.rows = rows;
		bricks = new Brick[rows][cols];
	}
	
	// Making brickGrid by getting brick size and spacing between each and scores of each row as parameters
	public void brickGridCreator(int brickWidth, int brickHeight, int spacing, int baseScore, int scoreIncrement) {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;j++) {
				int x = startX + j * (brickWidth + spacing);
				int y = startY + i * (brickHeight + spacing);
				int score = baseScore + ((rows - 1 - i) * scoreIncrement);
				bricks[i][j] = new Brick(x, y, brickWidth, brickHeight, true, score);
			}
		}
	}
	
	// Getter and Setter
	public Brick[][] getBricks() {
		return bricks;
	}

	public void setBricks(Brick[][] bricks) {
		this.bricks = bricks;
	}
}

