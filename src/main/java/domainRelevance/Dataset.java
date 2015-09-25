package domainRelevance;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
	
	public class PageHistory {
		
		public class PageProperties {
			
			private boolean changed;
			private int numOfNewLinks;
			private int numOfNewRelevantLinks;
			
			public PageProperties(boolean changed, int numOfNewLinks, int numOfRelevantNewLinks) {
				this.changed = changed;
				this.numOfNewLinks = numOfNewLinks;
				this.numOfNewRelevantLinks = numOfRelevantNewLinks;
			}
			
			public boolean getChanged() {
				return changed;
			}
			
			public int getNumOfNewLinks() {
				return numOfNewLinks;
			}
			
			public int getNumOfNewRelevantLinks() {
				return numOfNewRelevantLinks;
			}
			
		}
		
		private List<PageProperties> cycles = new ArrayList<>();
	        
        public PageHistory(List<PageProperties> cycles) {
        	this.cycles = cycles;
        }
        
        // return true if url changed between certain cycles (days)
    	public boolean changedBetweenCycles(int lastVisitCycle, int currentCycle) {
    		PageProperties cycleFeatures;

    		for (int i = lastVisitCycle; i < currentCycle; i++) {
    			cycleFeatures = cycles.get(i);
    			if (cycleFeatures.getChanged() == true)
    				return true;
    		}
    		return false;
    	}

		// return the number of new (relevant) links between certain cycles (days)
		public int numOfNewLinksBetweenCycles(int urlId, int lastVisitCycle, int currentCycle, boolean isLinkRelevant) {
			int numOfNewLinks = 0;
    		PageProperties cycleFeatures;
	
			for (int i = lastVisitCycle; i < currentCycle; i++) {
				cycleFeatures = cycles.get(i);
				if (!isLinkRelevant)
					numOfNewLinks += cycleFeatures.getNumOfNewLinks();
				else
					numOfNewLinks += cycleFeatures.getNumOfNewRelevantLinks();
			}
			return numOfNewLinks;
		}
		
		// Auxiliary methods
		public int getNumOfCycles() {
    		return cycles.size();
    	}

        public List<PageProperties> getCycles() {
        	return cycles;
        }
		
	}
	
	private List<PageHistory> dataset = new ArrayList<>();
	
	// get page history of url in the index i of the dataset
	public PageHistory getPageHistory(int i) {
		return dataset.get(i);
	}
	
	// number of urls in the dataset
	public int numOfUrls() {
        return dataset.size();
    }
	
	// number of cycles a PageHistory instance has (assuming that all of them have the same number of cycles)
	public int numOfCycles() {
        return getPageHistory(0).getNumOfCycles();
    }
	
	// add new PageHistory instance to the dataset
    public void add(PageHistory pageHistory) {
    	dataset.add(pageHistory);
    }
	
}
