public class Wayside implements Runnable
{
	private ArrayList<Track> track;
	private PLC plc;
	private String id;
	
	public Wayside nextRight;
	public Wayside nextLeft;
	public Wayside prevRight;
	public Wayside prevLeft;
	
	public Wayside(String id, Wayside nextRight, Wayside nextLeft, Wayside prevRight, Wayside prevLeft)
	{
		this.id = id;
		this.nextRight = nextRight;
		this.nextLeft = nextLeft;
		this.prevRight = prevRight;
		this.prevLeft = prevLeft;
		this.track = new ArrayList<Track>();
	}
	
	public void run()
	{
		
	}
	
	public void setAuthority(String trainID, int authority)
	{
		/* VALIDATE */
		/* Find specified train */
		/* set train's authority */
	}
	
	public void setSpeedLimit(String trackID, int speed)
	{
		/* VALIDATE */
		/* Find specified track */
		/* set track's speed limit */
	}
	
	private Track findTrack(String trackID)
	{
		/* Scan track section for ID */
	}
	
	private Train findTrain(String trainID)
	{
		/* Scan track section for specified train ID */
	}
	
	public void addTrack(Track t)
	{
		track.add(t);
	}
	
	public String getID()
	{
		return id;
	}

	public String toString()
	{
		return new String();
	}
}
