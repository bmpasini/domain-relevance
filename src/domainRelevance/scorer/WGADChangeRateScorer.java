package domainRelevance.scorer;

import domainRelevance.Url;

public class WGADChangeRateScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getWGADChangeRate(cycle);
	}

	@Override
	public String getName() {
		return "WGAD";
	}

}