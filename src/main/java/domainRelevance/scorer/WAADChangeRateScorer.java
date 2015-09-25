package domainRelevance.scorer;

import domainRelevance.Url;

public class WAADChangeRateScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getWAADChangeRate(cycle);
	}

	@Override
	public String getName() {
		return "WAAD";
	}

}