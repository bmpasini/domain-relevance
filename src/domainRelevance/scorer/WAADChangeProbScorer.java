package domainRelevance.scorer;

import domainRelevance.Url;

public class WAADChangeProbScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChangeProbabilityWAAD(cycle);
	}

	@Override
	public String getName() {
		return "WAAD_prob";
	}

}
