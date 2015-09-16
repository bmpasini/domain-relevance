import java.util.ArrayList;
import java.util.Arrays;

public class CrawlSimulation {
	
	public static final int NUM_OF_URLS = 1000000; // 1,000,000 urls
	public static final int NUM_OF_CYCLES = 60; // 60 cycles
	public static final int NUM_OF_FEATURES = 3; // 3 features [changed (boolean), numOfNewLinks (int), numOfRelevantNewLinks (int)]
	
	private static ArrayList<Double> learningRate = new ArrayList<>();
	private static Url[] repository = new Url[NUM_OF_URLS];
	
	public static void run (Scorer scorer, Dataset dataset, int warmUp, int k) {
		
		// create repository of new urls
		for (int i = 0; i < NUM_OF_URLS; i++) {
			repository[i] = new Url(i);
		}
		
		int cycle = 1;
		
		// start warmup cycles, so our scorer can have something to start working with
		for (; cycle <= warmUp; cycle++) {
			for (int i = 0; i < NUM_OF_URLS; i++) {
				if(dataset.changedBetweenCycles(i, repository[i].getLastVisitCycle(), cycle)) {
					repository[i].update(cycle, true);
				} else {
					repository[i].update(cycle, false);
				}
			}
		}
		
		// examine until the last collected cycle
		for (; cycle <= NUM_OF_CYCLES; cycle++) {
			
			// give score to each Url for each cycle
			for (int i = 0; i < NUM_OF_URLS; i++) {
//				repository[i].setScore(scorer.score(repository[i], cycle)));
			}
			
			// sort repository of Urls in relation to their scores
			Arrays.sort(repository);
			
			int changes = 0;
			
			// update Urls data after each cycle
			for (int i = 0; i < NUM_OF_URLS; i++) {
				if(dataset.changedBetweenCycles(i, repository[i].getLastVisitCycle(), cycle)) {
					changes++;
					repository[i].update(cycle, true);
				} else {
					repository[i].update(cycle, false);
				}
			}
			
			learningRate.add(changes / ((double) k)); // store the learning rate for each cycle (how many of the selected k pages have changed, and later, how many have relevant links in the k pages selected)
		}
	}
}
