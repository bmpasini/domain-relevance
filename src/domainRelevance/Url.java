package domainRelevance;

import java.util.ArrayList;
import java.util.List;

public class Url implements Comparable<Url> {
	
	private static Sum kSum;
	private static final int MAX_SUM = 200;

	private int id;
	private double score;
	private int numOfVisits = 0; // x1
	private int timesChanged = 0; // x2
	private int lastVisitCycle = 0; // x3
	// private int numberOfNewLinks = 0; // x4
	// private int numberOfNewRelevantLinks = 0; // y
	private List<Integer> history = new ArrayList<>();
	private int NADCachedCycle;
	private int AADCachedCycle;
	private int GADCachedCycle;
	private double NADCachedValue;
	private double AADCachedValue;
	private double GADCachedValue;

	public Url(int id) {
		this.id = id;
	}

	// update counters after each Url "visit"
	public void update(int cycle, boolean changed) {
		if (cycle == 0) {
			System.out.println("Invalid cycle value: " + cycle);
			System.exit(0);
		}
		numOfVisits++;
		if (changed)
			timesChanged++;
		lastVisitCycle = cycle;
	}

	@Override
	public int compareTo(Url url) {
		if (this.getScore() < url.getScore())
			return -1;
		else if (this.getScore() > url.getScore())
			return 1;
		else
			return 0;
	}
	
	public double getAge(int cycle) {
        return cycle - lastVisitCycle;
    }
	
	// Cho's change rate estimator
    public double getChoChangeRate() {
        return - Math.log((numOfVisits - timesChanged + 0.5) / (numOfVisits + 0.5));
    }
    
    // Non adaptive change rate (NAD)
    public double getNADChangeRate(int cycle) {
    	if(NADCachedCycle == cycle) return NADCachedValue;
    	
        double weight = 1.0 / (cycle - 1);
        double NAD = 0.0;
        
        for (int h = 0; h < history.size(); ++h)
        	NAD += weight;
        
        NADCachedCycle = cycle;
        NADCachedValue = NAD;
        
        return NAD;
    }
    
    // Shortsighted adaptive change rate (SAD)
    public double getSADChangeRate(int cycle) {
        if(!history.isEmpty() && history.get(history.size() - 1) == cycle - 1) return 1.0;
        else return 0.0;
    }
    
    // Arithmetically adaptive change rate (AAD)
    public double getAADChangeRate(int cycle) {
        if(AADCachedCycle == cycle) return AADCachedValue;
        
        double AAD = 0.0;
        
        for (int h = 0; h < history.size(); h++) {
            double i = history.get(h);
            double weight = i / kSum.AAD(cycle - 1);
            AAD += weight;
        }

        AADCachedCycle = cycle;
        AADCachedValue = AAD;

        return AAD;
    }
    
    // Geometrically adaptive change rate (GAD)
    public double getGADChangeRate(int cycle) {
        if(GADCachedCycle == cycle) return GADCachedValue;
        
        double GAD = 0.0;
        
        for (int h = 0; h < history.size(); ++h) {
            int i = history.get(h);
            double weight = kSum.Pow2i1(i) / kSum.GAD(cycle - 1);
            GAD += weight;
        }

        GADCachedCycle = cycle;
        GADCachedValue = GAD;

        return GAD;
    }
    
    // Change probability scorers
    public double getChangeProbabilityCho(int cycle) {
        return 1.0 - Math.exp(- getChoChangeRate() * getAge(cycle));
    }

    public double getChangeProbabilityNAD(int cycle) {
        return 1.0 - Math.exp(- getNADChangeRate(cycle) * getAge(cycle));
    }

    public double getChangeProbabilitySAD(int cycle) {
        return 1.0 - Math.exp(- getSADChangeRate(cycle) * getAge(cycle));
    }

    public double getChangeProbabilityAAD(int cycle) {
        return 1.0 - Math.exp(- getAADChangeRate(cycle) * getAge(cycle));
    }

    public double getChangeProbabilityGAD(int cycle) {
        return 1.0 - Math.exp(- getGADChangeRate(cycle) * getAge(cycle));
    }

	public int getId() {
		return id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getNumOfVisits() {
		return numOfVisits;
	}

	public int getTimesChanged() {
		return timesChanged;
	}

	public int getLastVisitCycle() {
		return lastVisitCycle;
	}
	
	public class Sum {
		
		private double[] sumAAD = new double[MAX_SUM + 1];
		private double[] sumGAD = new double[MAX_SUM + 1];
		private double[] pow2iMinus1 = new double[MAX_SUM + 1];
		
		public Sum() {
			for (int w_i = 0; w_i <= MAX_SUM; w_i++) {
				sumAAD[w_i] = 0;
		        for (int i = 1; i <= w_i; i++) sumAAD[w_i] += i;
		    }
		    for (int w_i = 0; w_i <= MAX_SUM; w_i++) {
		    	sumGAD[w_i] = 0;
		        for (int i = 1; i <= w_i; i++) sumGAD[w_i] += Math.pow(2, i - 1);
		    }
		    for (int i = 0; i <= MAX_SUM; i++) pow2iMinus1[i] = Math.pow(2, i - 1);
		}
		
		public double AAD(int i) {
            return sumAAD[i];
        }
		public double GAD(int i) {
            return sumGAD[i];
        }
		public double Pow2i1(int i) {
            return pow2iMinus1[i];
        }
		
	}

}
