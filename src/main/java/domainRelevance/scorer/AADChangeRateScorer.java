package domainRelevance.scorer;

import domainRelevance.Url;

public class AADChangeRateScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getAADChangeRate(cycle);
	}

	@Override
	public String getName() {
		return "AAD";
	}

}