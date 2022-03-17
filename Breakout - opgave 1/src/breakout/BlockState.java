package breakout;

public class BlockState {
	private final Point topLeft;
	private final Point bottomRight;
	
	public BlockState(Point topLeft, Point bottomRight){
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}
	
	public Point getTopLeft() {
		return topLeft;
	}
	public Point getBottomRight() {
		return bottomRight;
	}

}
//TODO: implement // Done
