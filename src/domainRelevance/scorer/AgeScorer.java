package domainRelevance.scorer;

import domainRelevance.Url;

public class AgeScorer implements Scorer {

	@Override
	public double score(Url url, int cycle) {
		return url.getAge(cycle);
	}

	@Override
	public String getName() {
		return "Age";
	}

}
