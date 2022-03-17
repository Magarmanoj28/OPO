package breakout;

public class PaddleState {
	private final Point center;
	private final Vector size;
	
	public PaddleState(Point center, Vector size) {
		this.center = center;
		this.size = size;
	}
	public Point getTopLeft() {
		return center.plus(size);
	}
	public Point getBottomRight() {
		return center.minus(size);
	}
}
//TODO: implement // Done
