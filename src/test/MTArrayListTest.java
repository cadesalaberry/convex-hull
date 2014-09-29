package test;

import static org.junit.Assert.assertEquals;

import main.MTArrayList;
import main.MyPoint;

import org.junit.Test;

public class MTArrayListTest {

	@Test
	public void testMin() {

		MTArrayList<Integer> list = new MTArrayList<Integer>();
		list.setNumThreads(4);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		int result = list.min();

		assertEquals(1, result);

	}

	@Test
	public void testMinBig() {

		MTArrayList<Integer> list = new MTArrayList<Integer>();
		list.setNumThreads(4);

		for (int i = 0; i < 10000000; i++) {
			list.add(i);
		}

		int result = list.min();

		assertEquals(0, result);

	}

	@Test
	public void testSort() {

		MTArrayList<MyPoint> list = new MTArrayList<>();
		list.setNumThreads(1);

		list.add(new MyPoint(1, 1));
		list.sort();

		for (int i = 0; i < 1000; i++) {
			assertEquals(new MyPoint(1,1), list.get(i));
		}
	}
	
	@Test
	public void testSortBig() {

		MTArrayList<Integer> list = new MTArrayList<Integer>();
		list.setNumThreads(4);

		for (int i = 0; i < 10000000; i++) {
			list.add(-i);
		}
		list.sort();

		for (int i = 0; i < 10000000; i++) {
			assertEquals(i - 10000000 + 1, (int) list.get(i));
		}
	}

}
