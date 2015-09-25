package domainRelevance;
import java.util.ArrayList;
import java.util.Arrays;

import domainRelevance.scorer.Scorer;

public class CrawlSimulation {

	private static ArrayList<Double> changeRate = new ArrayList<>();

	public static void run(Scorer scorer, Dataset dataset, int warmUp, int k) {
		
		final int numOfUrls = dataset.numOfUrls();
		final int numOfCycles = dataset.numOfCycles();
		
		Url[] repository = new Url[numOfUrls];
		int changes, cycle;
		
		changeRate.clear();

		// create repository of new urls
		for (int i = 0; i < numOfUrls; i++) {
			repository[i] = new Url(i);
		}

		cycle = 1;

		// start warmup cycles, so our scorer can have something to start working with
		for (; cycle <= warmUp; cycle++) {
			for (int i = 0; i < numOfUrls; i++) {
				if (dataset.getPageHistory(i).changedBetweenCycles(repository[i].getLastVisitCycle(), cycle)) {
					repository[i].update(cycle, true);
				} else {
					repository[i].update(cycle, false);
				}
			}
		}

		// examine until the last collected cycle
		for (; cycle <= numOfCycles; cycle++) {

			// give score to each Url for each cycle
			for (int i = 0; i < numOfUrls; i++) {
				repository[i].setScore(scorer.score(repository[i], cycle));
			}

			// sort repository of Urls in relation to their scores
			Arrays.sort(repository);

			changes = 0;

			// update Urls data after each cycle
			for (int i = 0; i < k; i++) {
				if (dataset.getPageHistory(i).changedBetweenCycles(repository[i].getLastVisitCycle(), cycle)) {
					changes++;
					repository[i].update(cycle, true);
				} else {
					repository[i].update(cycle, false);
				}
			}

			changeRate.add(changes / ((double) k)); // store the learning rate for each cycle (how many
													// of the selected k pages have changed, and later,
													// how many have relevant links in the k pages selected)
		}
	}
	
	public double averageChangeRate() {
        return listAverage(changeRate);
    }
	
	private double listAverage(ArrayList<Double> list) {
		
		if(list.size() == 0) return 0;
		
		double sum = 0.0;
        
        for (int i = 0; i < list.size(); i++)
            sum += list.get(i);
        
        return sum / list.size();
    }
	
}
