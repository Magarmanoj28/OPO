package breakout;

import java.awt.event.KeyEvent;

// TODO: implement, document
public class BreakoutState {
	private Point bottomRight;
	private BallState[] balls;
	private PaddleState paddle;
	private BlockState[] blocks;
	
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
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
	}

	public void movePaddleRight() {

		
	}

	public void movePaddleLeft() {
		
		
	}
	
	public boolean isWon() {
		return false;
	}

	public boolean isDead() {
		return false;
	}
}
