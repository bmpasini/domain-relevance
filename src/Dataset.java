
public class Dataset {
	
	public static final int NUM_OF_URLS = 1000000; // 1,000,000 urls
	public static final int NUM_OF_CYCLES = 60; // 60 cycles
	public static final int NUM_OF_FEATURES = 3; // 3 features [changed (boolean), numOfNewLinks (int), numOfRelevantNewLinks (int)]
	
	private int[][][] dataset = new int[NUM_OF_URLS][NUM_OF_CYCLES][NUM_OF_FEATURES];
	
	public Dataset(int[][][] dataset) {
		this.dataset = dataset;
	}
	
	// return true if url changed between certain cycles (days)
	public boolean changedBetweenCycles(int urlId, int lastVisitCycle, int currentCycle) {
		int[] cycleFeatures;
		
        for (int i = lastVisitCycle; i < currentCycle; i++) {
        	cycleFeatures = getFeaturesFromUrlAndCycle(urlId, i);
            if (cycleFeatures[0] == '1') return true; // assuming that 1 means that the URL changed in that cycle
        }
        return false;
    }
	
	// return the number of new (relevant) links between certain cycles (days)
	public int numOfNewLinksBetweenCycles(int urlId, int lastVisitCycle, int currentCycle, boolean isLinkRelevant) {
		int numOfNewLinks = 0;
		int[] cycleFeatures;
		
        for (int i = lastVisitCycle; i < currentCycle; i++) {
        	cycleFeatures = getFeaturesFromUrlAndCycle(urlId, i);
        	if (!isLinkRelevant) numOfNewLinks += cycleFeatures[1]; // assuming that cycleFeatures[1] means the number of new links in that cycle
        	else numOfNewLinks += cycleFeatures[2]; // assuming that cycleFeatures[2] means the number of new relevant links in that cycle
        }
        return numOfNewLinks;
    }
	
	public int[] getFeaturesFromUrlAndCycle (int urlId, int cycle) {
		return dataset[urlId][cycle];
	}
}
