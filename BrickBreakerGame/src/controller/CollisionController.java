package controller;

import model.Ball;
import model.Brick;

public class CollisionController {

	private Ball ball;
	private Brick[][] bricks;
	private GameController controller;
	
	public CollisionController(Brick[][] bricks, Ball ball, GameController controller) {
		this.ball = ball;
		this.bricks = bricks;
		this.controller = controller;
	}
	
	public void brickBreaker(){
		for(int i = 0; i < bricks.length;i++) {
			for(int j = 0; j < bricks[i].length; j++) {
				Brick brick = bricks[i][j];
				if (brick == null || !brick.isBrickExist()) {
				    continue; // skip broken bricks
				}
				
				boolean collisionX = ball.getX() + Ball.BALL_SIZE > brick.getX() &&
								 ball.getX() < brick.getX() + brick.getWidth();
				boolean collisionY = ball.getY() + Ball.BALL_SIZE > brick.getY() &&
									 ball.getY() < brick.getY() + brick.getHeight();
				
				if(collisionX && collisionY) {
					brick.setBrickExist(false);
					controller.addScore(brick.getScore());
					System.out.println("Brick hit! Score: " + brick.getScore());
					// Ball sides
					int ballLeft = ball.getX();
					int ballRight = ball.getX() + Ball.BALL_SIZE;
					int ballTop = ball.getY();
					int ballBottom = ball.getY() + Ball.BALL_SIZE;
					
					// Brick sides
				    int brickLeft = brick.getX();
				    int brickRight = brick.getX() + brick.getWidth();
				    int brickTop = brick.getY();
				    int brickBottom = brick.getY() + brick.getHeight();
				    
				    //callculate how mich ball is overlapping from each side
				    int overlapLeft = ballRight - brickLeft;
				    int overlapRight = brickRight - ballLeft;
				    int overlapTop = ballBottom - brickTop;
				    int overlapBottom = brickBottom - ballTop;
				    
				    // find smallest overlap
				    int minHorizontal = Math.min(overlapLeft, overlapRight);
				    int minVertical =  Math.min(overlapTop, overlapBottom);
				    
				    if(minHorizontal < minVertical) {	//hit from left or right
				    	ball.reverseX();
				    } else {	// hit from top or bottom
				    	ball.reverseY(); 
				    }
				}
			}
		}
	}
}
