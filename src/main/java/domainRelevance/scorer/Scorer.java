package domainRelevance.scorer;

import domainRelevance.Url;

public interface Scorer {
	public double score(Url instance, int cycle);
	public String getName();
}
