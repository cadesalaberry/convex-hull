package test;

import static org.junit.Assert.assertEquals;
import main.MyPoint;
import main.tools.AngleComparator;

import org.junit.Test;

public class MyPointTest {

	final static double pi = Math.PI;

	final MyPoint pE = new MyPoint(1, 0);
	final MyPoint pNE = new MyPoint(1, 1);
	final MyPoint pN = new MyPoint(0, 1);
	final MyPoint pNW = new MyPoint(-1, 1);
	final MyPoint pW = new MyPoint(-1, 0);
	final MyPoint pSW = new MyPoint(-1, -1);
	final MyPoint pS = new MyPoint(0, -1);
	final MyPoint pSE = new MyPoint(-1, 1);

	@Test
	public void testNaturalComparator() {

		assertEquals(0, pE.compareTo(pE));
		assertEquals(-1, pE.compareTo(pNE));
		assertEquals(-1, pE.compareTo(pSE));

	}

	@Test
	public void testAngleComparator() {
		
		AngleComparator comp = new AngleComparator();
		
		assertEquals(0, comp.compare(pE, pE));
		assertEquals(-1, comp.compare(pNE, pE));
		assertEquals(1, comp.compare(pSE, pE));
		assertEquals(1, comp.compare(pS, pN));
	}

}
