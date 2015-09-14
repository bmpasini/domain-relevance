
public class Dataset {
	
	private int[][][] dataset = new int[1000000][60][3]; // 1,000,000 urls
														 // 60 cycles
														 // 3 features [changed (boolean), numOfNewLinks (int), numOfRelevantNewLinks (int)]
	
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
	
	// return the number of new links between certain cycles (days)
	public int numOfNewLinksBetweenCycles(int urlId, int lastVisitCycle, int currentCycle) {
		int numOfNewLinks = 0;
		int[] cycleFeatures;
		
        for (int i = lastVisitCycle; i < currentCycle; i++) {
        	cycleFeatures = getFeaturesFromUrlAndCycle(urlId, i);
        	numOfNewLinks += cycleFeatures[1]; // assuming that cycleFeatures[1] means the number of new links in that cycle
        }
        return numOfNewLinks;
    }
	
	// return the number of new relevant links between certain cycles (days)
	public int numOfNewRelevantLinksBetweenCycles(int urlId, int lastVisitCycle, int currentCycle) {
		int numOfNewRelevantLinks = 0;
		int[] cycleFeatures;
		
        for (int i = lastVisitCycle; i < currentCycle; i++) {
        	cycleFeatures = getFeaturesFromUrlAndCycle(urlId, i);
        	numOfNewRelevantLinks += cycleFeatures[2]; // assuming that cycleFeatures[2] means the number of new relevant links in that cycle
        }
        return numOfNewRelevantLinks;
    }
	
	public int[] getFeaturesFromUrlAndCycle (int urlId, int cycle) {
		return dataset[urlId][cycle];
	}
	
}
