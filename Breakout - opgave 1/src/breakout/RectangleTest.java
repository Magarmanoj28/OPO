package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RectangleTest {

	@Test
	void test() {
		Vector v = new Vector(5,5);
		Point topLeftBlok = new Point (0,0);
		Point bottomRightBlok = new Point(10,10);
		Point topLeftBall1 = new Point(2,2);
		Point bottomRightBall1 = new Point(4,4);
		Point topLeftBall2 = new Point(18,18);
		Point bottomRightBall2 = new Point(20,20);
		Rectangle blok = new Rectangle(topLeftBlok, bottomRightBlok);
		Rectangle ball1 = new Rectangle(topLeftBall1, bottomRightBall1);
		Rectangle ball2 = new Rectangle(topLeftBall2, bottomRightBall2);
		Vector resultTopBall1 = blok.bounceTop(ball1, v);
		assertEquals(new Vector(5,-5), resultTopBall1);
		Vector resultLeftBall1 = blok.bounceLeft(ball1, v);
		assertEquals(new Vector(-5,5), resultLeftBall1);
		Vector resultRight = blok.bounceRight(ball1, v);
		assertEquals(v, resultRight);
		Vector resultBottom = blok.bounceBottom(ball1, v);
		assertEquals(v, resultBottom);
		Vector resultTopBall2 = blok.bounceTop(ball2, v);
		assertEquals(v, resultTopBall2);
		Vector resultLeftBall2 = blok.bounceLeft(ball2, v);
		assertEquals(v,resultLeftBall2);
	}

}
