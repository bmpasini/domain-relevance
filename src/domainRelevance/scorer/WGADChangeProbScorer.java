package domainRelevance.scorer;

import domainRelevance.Url;

public class WGADChangeProbScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getChangeProbabilityWGAD(cycle);
	}

	@Override
	public String getName() {
		return "WGAD_prob";
	}

}
