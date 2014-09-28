package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MTArrayList<T extends Comparable<T>> extends ArrayList<T>
		implements Runnable {
	
	// A static variable cannot be generic. ugly hack assuming comparables.
	private static volatile List<Comparable> pool = new ArrayList<>();

	private int numOfThreads = 1;

	public MTArrayList() {
		this(0, 1);
	}

	public MTArrayList(int initialCapacity) {
		this(initialCapacity, 1);
	}

	public MTArrayList(int initialCapacity, int numOfThreads) {
		super(initialCapacity);
		this.numOfThreads = numOfThreads;
	}

	public MTArrayList(Collection<T> c) {
		super(c);
		this.numOfThreads = 1;
	}

	synchronized public T min() {

		// Does not split the array if it is single threaded.
		if (numOfThreads <= 1) {
			return Collections.min(this);
		}
		
		pool.clear();

		final List<List<T>> splittedList = Helpers.split(this, numOfThreads);

		List<Thread> threads = new ArrayList<Thread>();

		// Assigns a thread to each part of the list
		for (List<T> l : splittedList) {

			Runnable r = new MTArrayList<>(l);
			Thread t = new Thread(r);

			threads.add(t);
		}

		for (Thread t : threads) {
			try {
				t.start();
				t.join();
			} catch (InterruptedException e) {
				System.out.println("Error: Shit daughter.");// e.printStackTrace();
			}
		}

		System.out.println(pool);
		
		// Again, ugly cast from the Comparable interface.
		return (T) Collections.min(pool);
	}

	public int getNumThreads() {
		return numOfThreads;
	}

	synchronized public void setNumThreads(int numThreads) {
		this.numOfThreads = numThreads;
	}

	public void addToPool(T e) {
		pool.add(e);
	}

	@Override
	public void run() {

		T e = Collections.min(this);

		this.addToPool(e);
	}

}
