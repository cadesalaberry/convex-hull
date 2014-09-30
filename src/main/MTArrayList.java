package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import main.tools.Helpers;
import main.tools.NaturalComparator;

public class MTArrayList<T extends Comparable<T>> extends ArrayList<T>
		implements Runnable {

	private static final long serialVersionUID = -2315498187469528631L;

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

		// Again, ugly cast from the Comparable interface.
		return (T) Collections.min(pool);
	}

	synchronized public MTArrayList<T> sort() {
		return this.sort(new NaturalComparator<T>());
	}

	/**
	 * The sorting algorithm is a modified mergesort (in which the merge is
	 * omitted if the highest element in the low sublist is less than the lowest
	 * element in the high sublist). This algorithm offers guaranteed n log(n)
	 * performance.
	 * 
	 * @return
	 */
	synchronized public MTArrayList<T> sort(final Comparator<? super T> comp) {
		ExecutorService es = Executors.newFixedThreadPool(numOfThreads);

		final List<List<T>> splittedList = Helpers.split(this, numOfThreads);

		for (final List<T> list : splittedList) {
			es.submit(new Runnable() {
				@Override
				public void run() {
					Collections.sort(list, comp);
				}
			});
		}
		es.shutdown();

		try {
			es.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collections.sort(this, comp);

		return this;
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
