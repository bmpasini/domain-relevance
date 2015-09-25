package domainRelevance.scorer;

import domainRelevance.Url;

public class ChoChangeRateScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChoChangeRate();
	}

	@Override
	public String getName() {
		return "cho";
	}

}
