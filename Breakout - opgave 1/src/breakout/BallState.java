package breakout;
/**
 * @immutable
 * @invar | getTopLeft < getBottomRight
 */
public class BallState {
	/**
	 * @invar | topLeft < bottomRight
	 * @post getCenter = center 
	 * @post getVelocity = velocity
	 * @post getDiameter = diameter
	 */	
	private final Point center;
	private final int diameter;
	private final Vector velocity;
	
	public BallState (Point center, int diameter, Vector velocity) {
		this.center = center;
		this.diameter = diameter;
		this.velocity = velocity;
	}
	/**
	 * @post getDiameter = b.getDiameter()
	 * @post getVelocity = b.getVelocity()
	 * @post getCenter = b.getCenter().plus(velocity)
	 */
	public BallState (BallState b) {
		this.diameter = b.getDiameter();
		this.velocity = b.getVelocity();
		this.center = b.getCenter().plus(velocity);
	}
	
	public Point getCenter() {
		return center;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public int getDiameter() {
		return diameter;
	}
	
	public Point getTopLeft() {
		return center.minus(new Vector(diameter / 2, diameter / 2));
	}
	
	public Point getBottomRight() {
		return center.plus(new Vector(diameter / 2, diameter / 2));
	}
	
}
//TODO: implement // Done
