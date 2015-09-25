package domainRelevance.scorer;

import domainRelevance.Url;

public class WNADChangeRateScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getWNADChangeRate(cycle);
	}

	@Override
	public String getName() {
		return "WNAD";
	}

}