package main;

import java.awt.Point;

public class MyPoint extends Point implements Comparable<MyPoint> {

	int x, y;

	public MyPoint(int x, int y) {
		super(x, y);
	}

	/**
	 * Compares this point to that point by y-coordinate, breaking ties by
	 * x-coordinate.
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
	public int hashCode() {
		return x ^ y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
