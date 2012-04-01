public class Wayside implements Runnable {
	private Track[] track;
	private PLC plc;
	private int id;
	public Wayside nextRight;
	public Wayside nextLeft;
	public Wayside prevRight;
	public Wayside prevLeft;
	
	public Wayside(int id, Track[] t, Wayside nextRight, Wayside nextLeft, Wayside prevRight, Wayside prevLeft) {
		this.id = id;
		this.track = t;
		this.nextRight = nextRight;
		this.nextLeft = nextLeft;
		this.prevRight = prevRight;
		this.prevLeft = prevLeft;
	}
	
	public void run() {
		
	}
	
	public void setAuthority(String trainID, int authority) {
		/* VALIDATE */
		/* Find specified train */
		/* set train's authority */
	}
	
	public void setSpeedLimit(String trackID, int speed) {
		/* VALIDATE */
		/* Find specified track */
		/* set track's speed limit */
	}
	
	public int getID() {
		return this.id;
	}

	public String toString() {
		return new String();
	}
}
