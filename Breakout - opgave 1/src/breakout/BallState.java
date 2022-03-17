package breakout;

public class BallState {
	
	private final Point center;
	private final int diameter;
	private final Vector velocity;
	
	
	
	
	public BallState(Point center, int diameter, Vector velocity) {
		this.center = center;
		this.diameter = diameter;
		this.velocity = velocity;
	}

	public Point getCenter() {
		return center;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	public Point getTopleft() {
		return center.plus(new Vector(diameter/2, diameter/2));
	}
	public Point getBottomRight() {
		return center.minus(new Vector(diameter/2, diameter/2));
	}
	
}

//TODO: implement // Done
