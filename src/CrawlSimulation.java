
public class CrawlSimulation {
	
	public static final int NUM_OF_URLS = 1000000; // 1,000,000 urls
	public static final int NUM_OF_CYCLES = 60; // 60 cycles
	public static final int NUM_OF_FEATURES = 3; // 3 features [changed (boolean), numOfNewLinks (int), numOfRelevantNewLinks (int)]
	
	private int[][][] resources = new int[NUM_OF_URLS][NUM_OF_CYCLES][NUM_OF_FEATURES];
	private double[] changeRate = new double[NUM_OF_CYCLES];
	private Url[] data = new Url[NUM_OF_URLS];
	
	public static void run (Scorer scorer, Dataset dataset, double resources, int warmUp) {
		
	}

}
