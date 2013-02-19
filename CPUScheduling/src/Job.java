/** Richard The (2796)
 *  CS431 Project (Dr. Gilbert Young)
 *  Date: 3/5/13
 *  Filename: Job.java 
 */

public class Job implements Comparable<Job> {
	private String name;
	private int cost;
	
	// Job constructor
	public Job(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}

    // returns the amount after decrement
    public int decrement(int amt) {
        this.cost = this.cost - amt;
        return this.cost;
    }

    public int compareTo(Job job) {
        return this.cost - ((Job) job).cost;
    }
    
	public String toString() {
		return "(" + this.name + ", " + this.cost + ")";
	}
    
    public String getName() {
    	return this.name;
    }
    
    public int getCost() {
    	return this.cost;
    }
}