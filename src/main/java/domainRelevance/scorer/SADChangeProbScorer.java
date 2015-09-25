package domainRelevance.scorer;

import domainRelevance.Url;

public class SADChangeProbScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChangeProbabilitySAD(cycle);
	}

	@Override
	public String getName() {
		return "SAD_prob";
	}

}