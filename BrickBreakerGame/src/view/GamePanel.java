package view;

import model.*;
import controller.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	// Top HUD
	int hudHeight = 50; //space at top for score/lives/levels
	
	private boolean isGameOver = false;
	// border attributes
	int borderMargin = 10;
	
	// paddle attributes
	private Paddle paddle;
	int paddleWidth = 125;
	int paddleHeight = 15;
	int paddleSpeedX = 0;
	int PaddleCenterX = (GameView.SCREEN_WIDTH - paddleWidth)/2;
	
	// ball attributes
	private Ball ball;
	int ballY;
	int speedX = 4;
	int speedY = -4;
	int ballCenterX = (GameView.SCREEN_WIDTH - Ball.BALL_SIZE)/2;

	// Bricks attribute
	private BrickManager brickManager;
	int startY = hudHeight + 50;
	int cols = 1;
	int rows = 1;
	
	
	//BrickGrid
	int brickWidth = 80;
	int brickHeight = 25;
	int baseScore = 50;
	int scoreIncrement = 50;
	
	// GameController reference
	private GameController gameController;

	public GamePanel() {
		
		// the panel design
		setBackground(Color.black);
		setBorder(
				BorderFactory.createMatteBorder(borderMargin, borderMargin, borderMargin, borderMargin, Color.gray));
		
		setFocusable(true);

		//paddle object
		paddle = new Paddle(PaddleCenterX, paddleSpeedX, paddleWidth, paddleHeight, this);
		addKeyListener(new InputHandler(paddle)); //adding InputHandlers for paddle
		
		//ball object
		ballY = paddle.getY() - Ball.BALL_SIZE; // getting the default y for the ball at the start of the game
		ball = new Ball(ballCenterX, ballY, speedX, speedY);

		
		//bricks object
		int spacing = 10;
		int totalBrickWidth = (cols * brickWidth) + ((cols - 1) * spacing);
		int startX = (GameView.SCREEN_WIDTH - totalBrickWidth) / 2 - borderMargin;
		
		brickManager = new BrickManager(startX, startY, cols, rows);
		brickManager.brickGridCreator(brickWidth, brickHeight, spacing, baseScore, scoreIncrement);
		
		// Game Controller
		gameController = new GameController(ball, this, paddle); // this = the current object that I'm inside
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Clean the panel first
		
		if (gameController != null) {
		// HUD
		//		draw Lives

        int lives = gameController.getLives();
        int score = gameController.getScore();
        int level = gameController.getLevel();
        
        
		for(int i = 0; i < 3; i++) {
			int heartX = 20 + i * 30; // position for each heart
			int heartY = 20; 		  // top margin
			
			if(i < lives) {
				g.setColor(Color.RED); // filled heart
			} else {
				g.setColor(Color.BLACK); // Lost life 
			}
			
			// draw Left and right circles
			g.fillOval(heartX, heartY, 10, 10);
			g.fillOval(heartX + 10, heartY, 10, 10);
			
			// draw bottom triangle
			int[] triangleX = {heartX, heartX + 10, heartX + 20};
			int[] triangleY = {heartY + 5, heartY + 20, heartY + 5};
			g.fillPolygon(triangleX, triangleY, 3);

		}

		// Draw Score
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.setColor(Color.WHITE);
		String scoreText = String.format("%05d", score);
		int scoreTextWidth = g.getFontMetrics().stringWidth(scoreText);
		g.drawString(scoreText, (this.getWidth() - scoreTextWidth) / 2, 30);
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		// Draw Level
		g.drawString("Level: " + level, GameView.SCREEN_WIDTH - 100, 30);
		}
		// drawing the ball
		g.setColor(Color.BLUE);
		g.fillOval(ball.getX(), ball.getY(), Ball.BALL_SIZE, Ball.BALL_SIZE);
		
		//drawing the paddle
		g.setColor(Color.green);
		g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
		
		//drawing the bricks
		Color[] brickColors = { 	//bricks Color Collection
				Color.YELLOW, // gold
				new Color(128, 0, 128), // purple
			    Color.BLUE,
			    Color.GREEN,
			    Color.WHITE
		};
		
		Brick[][] grid = brickManager.getBricks();
		for(int i = 0; i < grid.length; i++) { 	// for every entire row of bricks in 2d array of bricks, 1 iteration
			Brick[] row = grid[i];
			Color rowColor;
			if(i < brickColors.length)
			{
				rowColor = brickColors[i];
			} else {
				rowColor = Color.WHITE; //default color to prevent errors if there was more rows
			}
			g.setColor(rowColor);
			for(Brick brick : row) { // for every brick in that row
				if(brick != null && brick.isBrickExist()) {
					g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
				}
			}
		}
		
		// Game Over
		if(isGameOver) {
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		String gameOverText = "GAME OVER";
		int gameOverTextWidth = g.getFontMetrics().stringWidth(gameOverText);
		g.drawString(gameOverText, (getWidth() - gameOverTextWidth) / 2, getHeight() / 2);
		}
		
	}
	
	// Getter and Setter
	public BrickManager getBrickManager() {
	    return brickManager;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
}
