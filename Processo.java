package main;

public class Processo {
	
	private int pid; //process ID
	private int bt; //burst time, quanto gli manca al suo completamento
	private int at; //arrival time
	private int wt; //waiting time
	private int tat; //turn around time
	
	public Processo(int pid, int bt, int at) {
		this.pid = pid;
		this.bt = bt;
		this.at = at;
		this.wt = 0;
		this.tat = 0;
	}
	
	public int getAt() {
		return at;
	}
	
	public void setAt(int at) {
		this.at = at;
	}
	
	public int getBt() {
		return bt;
	}
	
	public void setBt(int bt) {
		this.bt = bt;
	}
	
	public int getPid() {
		return pid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getTat() {
		return tat;
	}
	
	public void setTat(int tat) {
		this.tat = tat;
	}
	
	public int getWt() {
		return wt;
	}
	
	public void setWt(int wt) {
		this.wt = wt;
	}
	
	@Override
	public String toString() {
		return "PID: " + pid + " | Arrival Time: " + at + " | Burst Time: " + bt
				+ " | Waiting Time: " + wt + " | Turn Around Time: " + tat;
	}

}
