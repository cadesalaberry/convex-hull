package main;
public class Graham {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 3) {
			return;
		}

		int q, n, p;

		q = Integer.parseInt(args[0]);
		n = Integer.parseInt(args[1]);
		p = Integer.parseInt(args[2]);

		if (!validateArgs(q, n, p)) {
			return;
		}

		//for (int j = 0; j <= 10; j++) {
			
			//System.out.println("Try #" + j + ":");
			
			for (int i = 1; i <= p; i++) {
			
				long t0 = System.currentTimeMillis();
				
				new UniquePointsFactory(i).randomize(n);
				
				Timer.printTiming(i, n, t0, System.currentTimeMillis());
				

			}
		//}
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
