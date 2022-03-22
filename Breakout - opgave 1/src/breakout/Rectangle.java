package breakout;

public class Rectangle {
	
		private Point topLeft;
		private Point bottomRight;
		private Vector topD;
		private Vector bottomD;
		private Vector rightD;
		private Vector leftD;
		
		
		public Rectangle(Point topLeft, Point bottomRight) {
			int size = 1;
			this.topLeft = topLeft;
			this.bottomRight = bottomRight;
			this.topD = new Vector(0,size);
			this.bottomD = new Vector(0,-size);
			this.leftD = new Vector(size,0);
			this.rightD = new Vector(-size,0);
		}
		
		public Point getTopLeft() {
			return topLeft;
		}
		
		public Point getBottomRight() {
			return bottomRight;
		}
		
		public Vector bounceTop(Rectangle r, Vector v) {
			int sp = topD.product(v);
			if (sp > 0 ) {
				int xMin = this.getTopLeft().getX();
				int xMax = this.getBottomRight().getX();
				int rLeftX = r.getTopLeft().getX();
				int rRightX = r.getBottomRight().getX();
				boolean rtlx = rLeftX >= xMin && rLeftX <= xMax;
				boolean rbrx = rRightX >= xMin && rRightX <= xMax;
				if(rtlx || rbrx) {
					if (r.getBottomRight().getY() >= this.getTopLeft().getY()) {
						if (r.getBottomRight().minus(v).getY() <= this.getTopLeft().getY()) {
							Vector vMirror = v.mirrorOver(topD);
							return vMirror;
						}
					}
				}	
			}
			return v;
		}
		
		public Vector bounceBottom(Rectangle r, Vector v) {
			int sp = bottomD.product(v);
			if (sp > 0) {
				int xMin = this.getTopLeft().getX();
				int xMax = this.getBottomRight().getX();
				int rLeftX = r.getTopLeft().getX();
				int rRightX = r.getBottomRight().getX();
				boolean rtlx = rLeftX >= xMin && rLeftX <= xMax;
				boolean rbrx = rRightX >= xMin && rRightX <= xMax;
				if(rtlx || rbrx) {
					if (r.getBottomRight().getY() <= this.getTopLeft().getY()) {
						if (r.getBottomRight().minus(v).getY() >= this.getTopLeft().getY()) {
							Vector vMirror = v.mirrorOver(topD);
							return vMirror;
						}
					}
				}	
			}
			return v;		
		}
		
		public Vector bounceRight(Rectangle r, Vector v) {
			int sp = rightD.product(v);
			if (sp > 0) {
				int yMin = this.getTopLeft().getY();
				int yMax = this.getBottomRight().getY();
				int rTopY = r.getTopLeft().getY();
				int rBottomY = r.getBottomRight().getY();
				boolean rtly = rTopY >= yMin && rTopY <= yMax;
				boolean rbry = rBottomY >= yMin && rBottomY <= yMax;
				if(rtly || rbry) {
					if(r.getTopLeft().getX() <= this.getBottomRight().getX()) {
						if(r.getTopLeft().minus(v).getX() >= this.getBottomRight().getX()) {
							Vector vMirror = v.mirrorOver(rightD);
							return vMirror;
						}
					}
				}
			}
			return v;
		}
		
		public Vector bounceLeft(Rectangle r, Vector v) {
			int sp = leftD.product(v);
			if (sp > 0) {
				int yMin = this.getTopLeft().getY();
				int yMax = this.getBottomRight().getY();
				int rTopY = r.getTopLeft().getY();
				int rBottomY = r.getBottomRight().getY();
				boolean rtly = rTopY >= yMin && rTopY <= yMax;
				boolean rbry = rBottomY >= yMin && rBottomY <= yMax;
				if(rtly || rbry) {
					if(r.getBottomRight().getX() >= this.getTopLeft().getX()) {
						if(r.getBottomRight().minus(v).getX() <= this.getTopLeft().getX()) {
							Vector vMirror = v.mirrorOver(leftD);
							return vMirror;
						}
					}
				}
			}
			return v;
		}
		
}
