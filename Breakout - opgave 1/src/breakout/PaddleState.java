package breakout;
/**
 * @immutable 
 * @invar | getTopLeft < getBottomRight
 *
 */

public class PaddleState {
	/**
	 * @invar | topLeft < bottomRight
	 * @post | getCenter = center
	 * @post | getSize = size
	 */
	private final Point center;
	private final Vector size;
	
	
	public PaddleState (Point center, Vector size) {
		this.center = center;
		this.size = size;
	}
	/**
	 * 
	 * @post getCenter = p.getCenter().plus(v)
	 * @post getSize = p.getSize()
	 */
	
	public PaddleState (PaddleState p, Vector v) {
		this.center = p.getCenter().plus(v);
		this.size = p.getSize();
	}
	public Point getTopLeft() {
		return center.minus(size);
	}

	public Point getBottomRight() {
		return center.plus(size);
	}

	public Point getCenter() {
		return center;
	}

	public Vector getSize() {
		return size;
	}

}
// TODO: implement //Done
