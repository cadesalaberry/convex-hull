package main;

import java.awt.Point;

public class MyPoint extends Point implements Comparable<MyPoint> {

	private static final long serialVersionUID = 1842874633097775802L;

	public MyPoint(int x, int y) {
		super(x, y);
	}

	/**
	 * Compares this {@link Point} to that {@link Point} by y-coordinate,
	 * breaking ties by x-coordinate.
	 * 
	 * @param that
	 *            the other point
	 * @return { a negative integer, zero, a positive integer } if this point is
	 *         { less than, equal to, greater than } that point
	 */
	public int compareTo(MyPoint that) {

		if (this.y < that.y)
			return -1;
		if (this.y > that.y)
			return +1;
		if (this.x < that.x)
			return -1;
		if (this.x > that.x)
			return +1;

		return 0;

	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MyPoint) {
			return this.hashCode() == o.hashCode();
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 * Three points are a counter-clockwise turn if ccw > 0, clockwise if ccw <
	 * 0, and collinear if ccw = 0 because ccw is a determinant that gives twice
	 * the signed area of the triangle formed by p1, p2 and p3. Taken from
	 * wikipedia.
	 */
	public static int ccw(MyPoint p1, MyPoint p2, MyPoint p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
	}

}
