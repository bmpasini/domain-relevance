
public class Url implements Comparable<Url> {
	
	private int id;
	private double score;
	private int numOfVisits = 0; // x1
	private int timesChanged = 0; // x2
	private int lastVisitCycle = 0; // x3
//	private int numberOfNewLinks = 0; // x4
//	private int numberOfNewRelevantLinks = 0; // y
	
	public Url (int id) {
		setId(id);
	}
	
	// update counters after each Url "visit"
	public void update(int cycle, boolean changed) {
		if (cycle == 0) {
            System.out.println("Invalid cycle value: " + cycle);
            System.exit(0);
        }
		numOfVisits++;
		if(changed) timesChanged++;
		lastVisitCycle = cycle;
	}
	
	@Override
	public int compareTo(Url url) {
        if (this.getScore() < url.getScore()) return -1;
        else if (this.getScore() > url.getScore()) return -1;
        else return 0;
    }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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

	public int getTimesChanged(){
        return timesChanged;
    }

	public int getLastVisitCycle() {
        return lastVisitCycle;
    }
	
}
