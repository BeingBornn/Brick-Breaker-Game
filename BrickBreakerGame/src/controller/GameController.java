package controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Ball;
import model.Brick;
import model.Paddle;
import view.GamePanel;
import view.GameView;

public class GameController  {

	private Timer timer;
	private GamePanel panel;
	private Ball ball;
	private Paddle paddle;
	private CollisionController collisionController;
	private int lives; 
	private int score;
	private int level;
	private boolean ballAttachedToPaddle = true;
	
	public GameController(Ball ball, GamePanel panel, Paddle paddle) {
		this.ball = ball;
		this.panel = panel;
		this.paddle = paddle;
	    this.lives = 3; // <-- ADD THIS here
	    this.score = 0;
	    this.level = 1;
		this.collisionController = new CollisionController(panel.getBrickManager().getBricks(), ball, this);
		setUpTimer();
	}
	
	public void setUpTimer() {
		int delay = 10; // miliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(ballAttachedToPaddle) {
					ball.setX(paddle.getX() + paddle.getWidth() / 2 - Ball.BALL_SIZE / 2);
					ball.setY(paddle.getY() - Ball.BALL_SIZE);
				} else {
					ball.move();
				}
				
				// check if the ball fell below the screen
				if(ball.getY() > panel.getHeight()) {
					loseLife();
					return;
				}
				
				paddle.move();
				bounce();
				paddleBounce();
				collisionController.brickBreaker();
				
				if (allBricksBroken()) {
				    nextLevel();
				}
				
				panel.repaint();
			}
		};
		this.timer = new Timer(delay, taskPerformer);
		this.timer.start();
	}

	public void bounce() {
		// Accounting for left and right 10px borders
	    // Bounce off left/right walls
	    if (ball.getX() + Ball.BALL_SIZE >= panel.getWidth() - 10 || ball.getX() <= 10) {
	        ball.reverseX();
	    }

	    // Bounce off top only
	    if (ball.getY() <= 10) {
	        ball.reverseY();
	    }  
	}
	public void paddleBounce() {
	    if (((ball.getY() + Ball.BALL_SIZE >= paddle.getY()) && (ball.getY() <= paddle.getY() + paddle.getHeight()))
	            && (ball.getX() + Ball.BALL_SIZE >= paddle.getX() && ball.getX() <= paddle.getX() + paddle.getWidth())) {
	        
	    	// Find center X position of paddle and ball
	    	int paddleCenter = paddle.getX() + paddle.getWidth() / 2;
	    	int ballCenter = ball.getX() + Ball.BALL_SIZE / 2;
	    	// How far is the ball from paddle center
	    	// Negative = left side hit, positive = right side hit
	    	int distanceFromCenter = ballCenter - paddleCenter;
	    	
	    	// Distance into percent (-1 to 1)
	    	double percent = (double) distanceFromCenter / (paddle.getWidth() / 2);
	    	
	    	// Max allowed bounce angle (60 degrees)
	    	double maxBounceAngle = Math.toRadians(60);
	    	
	    	// Final Bounce angle based on hit position
	    	double bounceAngle = percent * maxBounceAngle;
	    	
	    	// Keep ball speed constant
	    	double speed = Math.sqrt(ball.getSpeedX() * ball.getSpeedX() + ball.getSpeedY() * ball.getSpeedY());
	    	
	    	//set new ball speeds based on bounce angle
	    	ball.setSpeedX(speed * Math.sin(bounceAngle));
	    	ball.setSpeedY(-speed * Math.cos(bounceAngle));
	    	
	    	
	    }
	}
	
	// losing life
	public void loseLife() {
		lives--;
		if(lives > 0) {
			// Reset ball position
			ball.reset(panel.getWidth(), panel.getHeight());
		} else {
			// no lives => game over
			gameOver();
		}
	}
	// for losing the game
	private void gameOver() {
		timer.stop();
		panel.setGameOver(true);
		panel.repaint();
	}
	
	// adding score
	public void addScore(int value) {
	    this.setScore(this.getScore() + value);
	}

	// Check if every bricks are broken
	private boolean allBricksBroken() {
		Brick[][] bricks = panel.getBrickManager().getBricks();
		for(int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[i].length; j++) {
				Brick brick = bricks[i][j];
				if (brick != null && brick.isBrickExist()) {
	                return false; // at least one brick still exists
	            }
			}
		}
		return true; // all bricks are broken
	}
	// excuting the next level
	private void nextLevel() {
		level++;

	    // Increase speed but limit maximum
	    double newSpeedX = ball.getSpeedX() * 1.3;
	    double newSpeedY = ball.getSpeedY() * 1.3;
	    
	    if (Math.abs(newSpeedX) > 20) newSpeedX = Math.signum(newSpeedX) * 20;
	    if (Math.abs(newSpeedY) > 20) newSpeedY = Math.signum(newSpeedY) * 20;
	    
	    ball.setSpeedX(newSpeedX);
	    ball.setSpeedY(newSpeedY);

	    panel.getBrickManager().brickGridCreator(80, 25, 10, 50, 50);
	    ballAttachedToPaddle = true;
	    
	}
	
	// releasing the ball from the paddle
	public void releaseBall() {
		if(ballAttachedToPaddle) {
			ballAttachedToPaddle = false;
			ball.setSpeedX(level);
			double speed = Math.sqrt(ball.getSpeedX() * ball.getSpeedX() + ball.getSpeedY() * ball.getSpeedY());
			ball.setSpeedX(0);   // start straight up
	        ball.setSpeedY(-speed); // use current speed, only change direction
		}
	}
	
	// Getters and Setters
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLives() {
		// TODO Auto-generated method stub
		 return lives;
	}
	public void setLives(int lives) {
	    this.lives = lives;
	}
}
