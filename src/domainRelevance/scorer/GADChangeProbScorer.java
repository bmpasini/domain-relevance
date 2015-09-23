package domainRelevance.scorer;

import domainRelevance.Url;

public class GADChangeProbScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChangeProbabilityGAD(cycle);
	}

	@Override
	public String getName() {
		return "GAD_prob";
	}

}