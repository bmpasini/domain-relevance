package domainRelevance.scorer;

import domainRelevance.Url;

public class ChoChangeProbScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChangeProbabilityCho(cycle);
	}

	@Override
	public String getName() {
		return "cho_prob";
	}

}