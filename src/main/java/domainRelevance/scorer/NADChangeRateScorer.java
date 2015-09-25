package domainRelevance.scorer;

import domainRelevance.Url;

public class NADChangeRateScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getNADChangeRate(cycle);
	}

	@Override
	public String getName() {
		return "NAD";
	}

}