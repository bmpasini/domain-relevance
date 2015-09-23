package domainRelevance.scorer;

import domainRelevance.Url;

public class NADChangeProbScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChangeProbabilityNAD(cycle);
	}

	@Override
	public String getName() {
		return "NAD_prob";
	}

}