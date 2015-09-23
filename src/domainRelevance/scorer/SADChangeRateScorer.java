package domainRelevance.scorer;

import domainRelevance.Url;

public class SADChangeRateScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getSADChangeRate(cycle);
	}

	@Override
	public String getName() {
		return "SAD";
	}

}