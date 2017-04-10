package model.data;

public class Record {
	private int steps;
	private double time;
	
	public Record() {}
	
	public Record(int steps, double time) {
		this.steps = steps;
		this.time = time;
	}
	
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
}
