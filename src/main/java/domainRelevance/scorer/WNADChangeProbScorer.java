package domainRelevance.scorer;

import domainRelevance.Url;

public class WNADChangeProbScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChangeProbabilityWNAD(cycle);
	}

	@Override
	public String getName() {
		return "WNAD_prob";
	}

}
