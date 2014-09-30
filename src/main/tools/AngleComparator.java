package main.tools;

import java.util.Comparator;

import main.MyPoint;

public class AngleComparator implements Comparator<MyPoint> {

	MyPoint center;

	public AngleComparator() {
		this(new MyPoint(0, 0));

	}

	public AngleComparator(MyPoint p) {
		super();
		this.center = p;

	}

	public int compare(MyPoint p1, MyPoint p2) {

		if (p1.equals(p2))
			return 0;
		else if (less(center, p1, p2))
			return -1;
		else
			return 1;
	}

	/**
	 * Really cool method retrieved from
	 * http://stackoverflow.com/questions/6989100/sort-points-in-clockwise-order
	 * 
	 * @param center
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean less(MyPoint center, MyPoint a, MyPoint b) {

		if (a.x - center.x >= 0 && b.x - center.x < 0)
			return true;

		if (a.x - center.x < 0 && b.x - center.x >= 0)
			return false;

		if (a.x - center.x == 0 && b.x - center.x == 0) {

			if (a.y - center.y >= 0 || b.y - center.y >= 0)
				return a.y > b.y;
			return b.y > a.y;
		}

		// Computes the cross product of vectors (center -> a) x (center -> b)
		int det = (a.x - center.x) * (b.y - center.y) - (b.x - center.x)
				* (a.y - center.y);
		if (det < 0)
			return true;
		if (det > 0)
			return false;

		// Points a and b are on the same line from the center
		// check which point is closer to the center
		int d1 = (a.x - center.x) * (a.x - center.x) + (a.y - center.y)
				* (a.y - center.y);
		int d2 = (b.x - center.x) * (b.x - center.x) + (b.y - center.y)
				* (b.y - center.y);
		return d1 > d2;
	}
}