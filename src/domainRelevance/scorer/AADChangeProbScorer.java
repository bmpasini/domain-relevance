package domainRelevance.scorer;

import domainRelevance.Url;

public class AADChangeProbScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChangeProbabilityAAD(cycle);
	}

	@Override
	public String getName() {
		return "AAD_prob";
	}

}