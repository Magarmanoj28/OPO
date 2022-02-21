package breakout;

import java.util.ArrayList;

public class GameMap {
	
	private static final int INIT_BALL_DIAMETER = 700;
	private static final int HEIGHT = 30000;
	private static final int WIDTH = 50000;
	private static int BLOCK_LINES = 9;
	private static int BLOCK_COLUMNS = 10;
	private static final Vector INIT_BALL_VELOCITY = new Vector(5,7);

	private static BlockState createBlock(Point bottomLeft) {
		Vector marginBL = new Vector(20,20);
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS-70,HEIGHT/BLOCK_LINES-70);
		Point blockTL = bottomLeft.plus(marginBL);
		Point blockBR = blockTL.plus(size);
		// TODO: return a block with given top left (`blockTL`) and bottom right (`blockBR`) Point  
		return null;
	}
	private static PaddleState createPaddle(Point bottomLeft) {
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		Point center = bottomLeft.plus(size);
		// TODO: return a paddle with given center 
		return null;
	}
	private static BallState createBall(Point bottomLeft) {
		Vector centerD = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		Point center = bottomLeft.plus(centerD);
		int diameter = INIT_BALL_DIAMETER;
		// TODO: return a ball with given `center`, `diameter` and initial speed `initSpeed` 
		return null;
	}
		
	/**
	 * Return the initial breakout state represented by string `description`.
	 * @pre | description != null
	 * @post | result != null
	 */
	public static BreakoutState createStateFromDescription(String description) {
		String[] lines = description.split("\n", -1);
		
		Vector unitVecRight = new Vector(WIDTH/BLOCK_COLUMNS,0);
		Vector unitVecDown = new Vector(0,HEIGHT/BLOCK_LINES);
		ArrayList<BlockState> blocks = new ArrayList<BlockState>();
		ArrayList<BallState> balls = new ArrayList<BallState>();
		PaddleState paddle = null;
		
		Point topLeft = new Point(0,0);
		assert lines.length < BLOCK_LINES;
		for(String line : lines) {
			assert line.length() < BLOCK_COLUMNS;
			Point cursor = topLeft;
			for(char c : line.toCharArray()) {
				switch(c) {
				case '#': blocks.add(createBlock(cursor)); break;
				case 'o': balls.add(createBall(cursor)); break;
				case '=': paddle = createPaddle(cursor); break;
				}
				cursor = cursor.plus(unitVecRight);
			}
			topLeft = topLeft.plus(unitVecDown);
		}
		Point topRight = new Point(WIDTH, HEIGHT);
		
		return new BreakoutState(balls.toArray(new BallState[] {}), blocks.toArray(new BlockState[] {}), topRight, paddle);
	}
}