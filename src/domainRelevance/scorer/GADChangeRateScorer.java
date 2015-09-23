package domainRelevance.scorer;

import domainRelevance.Url;

public class GADChangeRateScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getGADChangeRate(cycle);
	}

	@Override
	public String getName() {
		return "GAD";
	}

}