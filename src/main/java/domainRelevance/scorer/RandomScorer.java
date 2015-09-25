package domainRelevance.scorer;

import domainRelevance.Url;

public class RandomScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return Math.random();
	}

	@Override
	public String getName() {
		return "random";
	}

}
