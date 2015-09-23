package domainRelevance;

import java.util.ArrayList;
import java.util.List;

public class Url implements Comparable<Url> {

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

}
