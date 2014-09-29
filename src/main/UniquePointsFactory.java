package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class UniquePointsFactory implements Runnable {

	private static Random rand = new Random(42);
	
	private int numOfThreads, numOfPoints;

	private Set<MyPoint> points = new HashSet<MyPoint>();

	public static Object lock = new Object();

	public UniquePointsFactory(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}

	/**
	 * Returns a list of random points containing the given number of points.
	 * 
	 * @return
	 */
	public List<MyPoint> randomize(int numOfPoints) {

		this.numOfPoints = numOfPoints;
		
		points = new HashSet<MyPoint>();

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
	private boolean addPoint(MyPoint point) {
		synchronized (points) {
			return points.add(point);
		}
	}

	private List<MyPoint> getAsList() {
		synchronized (points) {
			return new ArrayList<MyPoint>((Collection<? extends MyPoint>) points);
		}
	}

	@Override
	public void run() {

		// System.out.println("" + Thread.currentThread() + " starting...");

		int count;

		for (count = 0; points.size() < numOfPoints; count++) {
			
			// Loop until a unique point has been added
			while (!this.addPoint(randomPoint()))
				;
		}

		synchronized (lock) {
			lock.notify();
		}

		// System.out.print("Thread " + Thread.currentThread());
		// System.out.println(" added " + count + " points.");
	}
	
	public static MyPoint randomPoint() {

		int x = rand.nextInt();
		int y = rand.nextInt();

		return new MyPoint(x, y);
	}
}
