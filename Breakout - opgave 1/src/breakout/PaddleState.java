package breakout;

public class PaddleState {
	private final Point center;
	private final Vector size;
	
	public PaddleState(Point center, Vector size) {
		this.center = center;
		this.size = size;
	}
	public Point getTopLeft() {
		return center.minus(size);
	}
	public Point getBottomRight() {
		return center.plus(size);
	}
}
//TODO: implement // Done
