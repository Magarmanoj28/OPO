package breakout;

import java.util.Arrays;


// TODO: implement, document
public class BreakoutState {
	
	private Point bottomRight;
	private BallState[] balls;
	private PaddleState paddle;
	private BlockState[] blocks;
	private Vector paddleStepRight;
	private Vector paddleStepLeft;

	
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		int stepSize = 100;
		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
		this.paddleStepRight = new Vector(stepSize,0); 
		this.paddleStepLeft = new Vector(-stepSize,0);
	}
	
	
	public BallState[] getBalls() {
		return balls;
	}

	public BlockState[] getBlocks() {
		return blocks;
	}

	public PaddleState getPaddle() {
		return paddle;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

	public void tick(int paddleDir) {
		final int xMin = 0;
		final int xMax = bottomRight.getX();
		final int yMin = 0;
		final int yMax = bottomRight.getY();
		
		// Move all balls according to their current velocity.

		for (int i = 0; i < balls.length; i++) {
			balls[i] = new BallState (balls[i]);
		}
		
		// Check whether any balls hit the walls on the left, right and top side of the game area, in which case they must bounce back.
		
		for (int i = 0; i < balls.length; i++) {
			if (balls[i].getTopLeft().getX() <= xMin) {
				Rectangle left = new Rectangle(bottomRight, bottomRight);
				left.bounceLeft(left, paddleStepLeft);
				// bounce left
			}
			else if (balls[i].getBottomRight().getX() >= xMax) {
				// bounce right
				Rectangle right = new Rectangle(bottomRight, bottomRight);
				right.bounceRight(right, paddleStepLeft);
			}
			
			else if (balls[i].getTopLeft().getY() <= yMin) {
				// bounce top
				Rectangle top = new Rectangle(bottomRight, bottomRight);
				top.bounceTop(top, paddleStepLeft);
			}
		}
		
		// Check whether any balls hit the bottom of the field, in which case they must be removed from the game.
		
		for (int i = 0; i < balls.length; i++) {
			 if (balls[i].getBottomRight().getY() >= yMax) {
					balls[i] = null;
			 }
		}
		balls = Arrays.stream(balls).filter(x -> x != null).toArray(BallState[]::new);
		
		
		// Check whether any ball hit any block, in which case the block must be removed from the game and the ball must bounce back.
		// Check whether any ball hit the paddle, in which case it must bounce back.
		// Additionally, the ball must speed up by one fifth of the current velocity of the paddle.

	}
	

	public void movePaddleRight() {
		paddle = new PaddleState(paddle, paddleStepRight);
	}

	public void movePaddleLeft() {
		paddle = new PaddleState(paddle, paddleStepLeft);
	}
	
	public boolean isWon() {
		return balls.length > 0 && blocks.length <= 0;
	}

	public boolean isDead() {
		return balls.length <= 0;
	}
}
//todoo nogg
