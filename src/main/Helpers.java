package main;
import java.util.ArrayList;
import java.util.List;

public class Helpers {

	/**
	 * Splits a list into the given number of slices
	 * 
	 * @param threadID
	 * @return
	 */
	public static <E> List<List<E>> split(List<E> list, int nbSlices) {

		if (nbSlices <= 0)
			return null;

		List<List<E>> splittedArray = new ArrayList<List<E>>();

		int total = list.size();
		int perSlice = total / nbSlices;
		int remainder = total % nbSlices;

		// Figures out range of dictionary it's suppose to look at!
		int fromIndex, toIndex;
		
		// Uses nbSlices as an index
		int sliceID = nbSlices - 1;
		
		for (int i = 0; i <= sliceID; i++) {

			if (i == 0)
				fromIndex = 0;
			else
				fromIndex = remainder + i * perSlice;

			toIndex = remainder + (i + 1) * perSlice;
			
			List<E> subList = list.subList(fromIndex, toIndex);

			splittedArray.add(subList);
		}

		return splittedArray;
	}
}
