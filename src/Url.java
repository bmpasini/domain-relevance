
public class Url {
	
	private int id;
	private int numOfVisits = 0; // x1
	private int timesChanged = 0; // x2
	private int lastVisitCycle = 0; // x3
//	private int numberOfNewLinks = 0; // x4
//	private int numberOfNewRelevantLinks = 0; // y
	
	
	public void Update(int cycle, boolean changed) {
		if (cycle == 0) {
            System.out.println("Invalid cycle value: " + cycle);
            System.exit(0);
        }
		numOfVisits++;
		if(changed) timesChanged++;
		lastVisitCycle = cycle;
	}
	
	int getId() {
		return id;
	}
	
	void setId(int id) {
		this.id = id;
	}
	
	int getNumOfVisits() {
        return numOfVisits;
    }

    int getTimesChanged(){
        return timesChanged;
    }

    int getLastVisitCycle() {
        return lastVisitCycle;
    }
	
}
