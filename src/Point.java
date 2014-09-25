import java.util.concurrent.ThreadLocalRandom;

public class Point implements Comparable<Point> {

	int x, y;
	private static ThreadLocalRandom rand = ThreadLocalRandom.current();

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static Point random() {

		int x = rand.nextInt();
		int y = rand.nextInt();

		return new Point(x, y);
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
	public int compareTo(Point that) {
	
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
		if (o instanceof Point) {
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
