package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.Helpers;

import org.junit.Test;

public class HelpersTest {

	@Test
	public void splitTestSimple() {
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		List<List<Object>> result = Helpers.split(list, 2);

		assertEquals(2, result.get(0).size());
		assertEquals(2, result.get(1).size());
	}

	@Test
	public void splitTestEqual() {
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		List<List<Object>> result = Helpers.split(list, 4);

		assertEquals(1, result.get(0).size());
		assertEquals(1, result.get(1).size());
		assertEquals(1, result.get(2).size());
		assertEquals(1, result.get(3).size());
	}
	
	@Test
	public void splitTestLess() {
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		List<List<Object>> result = Helpers.split(list, 5);

		assertEquals(4, result.get(0).size());
		assertEquals(1, result.get(0).get(0)); // Checks content
		assertEquals(0, result.get(1).size());
		assertEquals(0, result.get(2).size());
		assertEquals(0, result.get(3).size());
	}
	
	@Test
	public void splitTestOddList() {
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add(3);

		List<List<Object>> result = Helpers.split(list, 2);

		assertEquals(2, result.get(0).size());
		assertEquals(1, result.get(0).get(0)); // Checks content
		assertEquals(1, result.get(1).size());
	}

}
