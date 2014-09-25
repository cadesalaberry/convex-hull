import java.util.concurrent.TimeUnit;


public class Timer {
	public static String prettify(long millis) {
		return String.format("%02d min, %02d sec", 
			    TimeUnit.MILLISECONDS.toMinutes(millis),
			    TimeUnit.MILLISECONDS.toSeconds(millis) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
			);
	}
	
	public static void printTiming(int numOfThreads, int numOfEntities, long startTime, long stopTime) {
		
		long milli = stopTime - startTime;
		
		String pretty = Timer.prettify(milli);
		
		System.out.print("It took " + pretty + " (" + milli + "ms) ");
		System.out.print("to generate " + numOfEntities + " entities, ");
		System.out.println("with " + numOfThreads + " threads.");
	}
}
