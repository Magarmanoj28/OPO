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
	private Rectangle leftWall;
	private Rectangle rightWall;
	private Rectangle topWall;
	private int xMax;
	private int xMin;

	
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		if( null == balls || null == blocks || null == bottomRight || null == paddle) { 
			throw new java.lang.IllegalArgumentException(); 
		}
		int stepSize = 100;
		int yMax = 30000;
		int yMin = 0;
		this.xMax = 50000;
		this.xMin = 0;
		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
		this.paddleStepRight = new Vector(stepSize,0); 
		this.paddleStepLeft = new Vector(-stepSize,0);
		this.topWall =  new Rectangle(new Point(xMin, -yMax), new Point(xMin, -yMax));
		this.rightWall = new Rectangle(new Point(xMax, yMin), new Point(2 * xMax, yMax)); 
		this.leftWall = new Rectangle(new Point(-xMax, yMin), new Point(xMin, yMax));
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
		final int yMax = bottomRight.getY();
		
		// Move all balls according to their current velocity.

		for (int i = 0; i < balls.length; i++) {
			balls[i] = new BallState (balls[i]);
		}
		
		// Check whether any balls hit the bottom of the field, in which case they must be removed from the game.
		
		for (int i = 0; i < balls.length; i++) {
			 if (balls[i].getBottomRight().getY() >= yMax) {
					balls[i] = null;
			 }
		}
		balls = Arrays.stream(balls).filter(x -> x != null).toArray(BallState[]::new);
		
		
		// Check whether any balls hit the walls on the left, right and top side of the game area, in which case they must bounce back.
		
		for (int i = 0; i < balls.length; i++) {
			Rectangle ball = new Rectangle(balls[i].getTopLeft(), balls[i].getBottomRight());
			Vector vectorResultLeft = leftWall.bounceRight(ball, balls[i].getVelocity());
			balls[i] = new BallState(balls[i].getCenter(), balls[i].getDiameter(), vectorResultLeft);
			Vector vectorResultRight = rightWall.bounceLeft(ball, balls[i].getVelocity());
			balls[i] = new BallState(balls[i].getCenter(), balls[i].getDiameter(), vectorResultRight);
			Vector vectorResultTop = topWall.bounceBottom(ball,balls[i].getVelocity());
			balls[i] = new BallState(balls[i].getCenter(), balls[i].getDiameter(), vectorResultTop);
			
		}
			
		// Check whether any ball hit any block, in which case the block must be removed from the game and the ball must bounce back.
		
		for (int j = 0; j < blocks.length; j ++) {
			Rectangle blokken = new Rectangle(blocks[j].getTopLeft(), blocks[j].getBottomRight());
			for (int i = 0; i < balls.length; i++) {
				Rectangle ball = new Rectangle(balls[i].getTopLeft(), balls[i].getBottomRight());
				Vector velocity = balls[i].getVelocity();
				Vector vectorResultBottom = blokken.bounceBottom(ball, velocity);
				if(velocity.minus(vectorResultBottom).getSquareLength() > 0) {
					balls[i] = new BallState(balls[i].getCenter(), balls[i].getDiameter(), vectorResultBottom);
					blocks[j] = null;
				} else {
					Vector vectorResultLeft = blokken.bounceLeft(ball, velocity);
					if (velocity.minus(vectorResultLeft).getSquareLength() > 0) {
						balls[i] = new BallState(balls[i].getCenter(), balls[i].getDiameter(), vectorResultLeft);
						blocks[j] = null;
					} else {
						Vector vectorResultTop = blokken.bounceTop(ball, velocity);
						if (velocity.minus(vectorResultTop).getSquareLength() > 0) {
							balls[i] = new BallState(balls[i].getCenter(), balls[i].getDiameter(), vectorResultTop);
							blocks[j] = null;
						} else {
							Vector vectorResultRight = blokken.bounceRight(ball, velocity);
							if (velocity.minus(vectorResultRight).getSquareLength() > 0) {
								balls[i] = new BallState(balls[i].getCenter(), balls[i].getDiameter(), vectorResultRight);
								blocks[j] = null;
							}
						}
					}
				} 
				
			}
		}
		blocks = Arrays.stream(blocks).filter(x -> x != null).toArray(BlockState[]::new);
		
		// Check whether any ball hit the paddle, in which case it must bounce back.
		// Additionally, the ball must speed up by one fifth of the current velocity of the paddle.
		
		for (int i = 0; i < balls.length; i++) {
			Rectangle ball = new Rectangle(balls[i].getTopLeft(), balls[i].getBottomRight());
			Rectangle paddleRectangle = new Rectangle(paddle.getTopLeft(), paddle.getBottomRight());
			Vector vectorResult = paddleRectangle.bounceTop(ball, balls[i].getVelocity());
			if (balls[i].getVelocity().minus(vectorResult).getSquareLength() > 0) {
				if (paddleDir > 0 ) {
					vectorResult.plus(paddleStepRight.scaledDiv(5));
				}else if (paddleDir < 0) {
					vectorResult.plus(paddleStepLeft.scaledDiv(5));
				}
			}
			balls[i] = new BallState(balls[i].getCenter(), balls[i].getDiameter(), vectorResult);

		}
	}
	

	public void movePaddleRight() {
		if (paddle.getBottomRight().getX() + paddleStepRight.getX() <= xMax) {
		paddle = new PaddleState(paddle, paddleStepRight);
		}
	}

	public void movePaddleLeft() {
		if (paddle.getTopLeft().getX() + paddleStepLeft.getX() >= xMin) {
			paddle = new PaddleState(paddle, paddleStepLeft);
		}
	}
	
	public boolean isWon() {
		return balls.length > 0 && blocks.length <= 0;
	}

	public boolean isDead() {
		return balls.length <= 0;
	}
}
//todoo nogg
