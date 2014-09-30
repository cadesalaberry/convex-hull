package main;

import java.util.List;

import main.tools.Timer;

public class Graham {

	final static int[] threads = { 2, 4, 8, 16, 32 };
	final static int[] questions = { 1, 2, 3, 4 };

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 3) {
			//test();
			//for (int j : questions) {
				for (int i : threads) {
					solveQuestion(4, 2222222, i);
				}
			//}
			return;
		}

		int q, n, p;

		q = Integer.parseInt(args[0]);
		n = Integer.parseInt(args[1]);
		p = Integer.parseInt(args[2]);

		if (!validateArgs(q, n, p)) {
			return;
		}
	}

	public static void solveQuestion(int q, int n, int p) {

		List<MyPoint> points;
		long tq1, tq2, tq3, temp;

		tq1 = System.currentTimeMillis();

		points = new UniquePointsFactory(p).randomize(n);
		
		temp = System.currentTimeMillis();
		Timer.printTiming(p, n, tq1, temp);
		tq1 = temp - tq1;

		if (q < 2)
			return;

		MTArrayList<MyPoint> mt = new MTArrayList<>(points);

		tq2 = System.currentTimeMillis();
		MyPoint min = mt.min();

		temp = System.currentTimeMillis();
		Timer.printTiming(p, n, tq2, temp);
		tq2 = temp - tq2;
		
		if (q < 3)
			return;

		tq3 = System.currentTimeMillis();

		//Comparator comp = new AngleComparator(min);
		mt.sort();
		
		temp = System.currentTimeMillis();
		Timer.printTiming(p, n, tq3, temp);
		tq3 = temp - tq3;

		if (q < 4)
			return;
		
		System.out.println("tq1, tq2, tq3");
		System.out.println(tq1 + "," + tq2 + "," + tq3);

	}

	public static void test() {

		System.out.println("nbOfThreads, time");
		String report;
		int n = 2222222;

		for (int i : threads) {

			report = "";
			long t0 = System.currentTimeMillis();

			new UniquePointsFactory(i).randomize(n);

			long tx = System.currentTimeMillis();

			report += i + ",";
			report += tx - t0;

			System.out.println(report);
		}

	}

	public static boolean validateArgs(int q, int n, int p) {

		boolean valid = true;

		// Checks that the question number is between 1 and 4
		if (!(1 <= q && q <= 4)) {

			System.err.println("Error: q should be between 1 and 4.");
			valid = false;
		}

		// Checks that we have a valid problem size
		if (!(1 <= n)) {

			System.err
					.println("Error: n should be between greater than or equal to 1.");
			valid = false;
		}

		// Checks that the number of threads is below the size of the problem
		if (!(1 <= p && p <= n)) {

			System.err.println("Error: p should be between 1 and n.");
			valid = false;
		}

		return valid;
	}
}
