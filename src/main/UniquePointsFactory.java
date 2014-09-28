package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniquePointsFactory implements Runnable {

	private int numOfThreads, numOfPoints;

	private Set<Point> points = new HashSet<Point>();

	public static Object lock = new Object();

	public UniquePointsFactory(int numOfThreads, int numOfPoints) {
		this.numOfPoints = numOfPoints;
		this.numOfThreads = numOfThreads;
	}

	/**
	 * Returns a list of random points containing the given number of points.
	 * 
	 * @return
	 */
	public List<Point> randomize() {

		points.clear();

		// Spawns the given number of threads.
		for (int i = 0; i < numOfThreads; i++) {
			Thread t = new Thread(this, "Thread #" + i);
			t.start();
		}

		// Waits for the threads to be done generating the said number of
		// points.
		synchronized (lock) {
			while (points.size() < numOfPoints)
				try {
					lock.wait();
				} catch (InterruptedException e) {
					System.err.println("Error: Shit son.");
				}
		}

		return this.getAsList();
	}

	/**
	 * Add a point to the factory array. returns false if the number was already
	 * present in the set.
	 * 
	 * @param point
	 * @return
	 */
	public boolean addPoint(Point point) {
		synchronized (points) {
			return points.add(point);
		}
	}

	public List<Point> getAsList() {
		synchronized (points) {
			return new ArrayList<Point>((Collection<? extends Point>) points);
		}
	}

	@Override
	public void run() {

		// System.out.println("" + Thread.currentThread() + " starting...");

		int count = 0;

		for (count = 0; points.size() < numOfPoints; count++) {

			// Loop until a unique point has been added
			while (!this.addPoint(Point.random()))
				;// System.out.println("Duplicate@" + count + " in " +
					// Thread.currentThread());
		}

		synchronized (lock) {
			lock.notify();
		}

		// System.out.print("Thread " + Thread.currentThread());
		// System.out.println(" added " + count + " points.");
	}
}
