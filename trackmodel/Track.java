// Track Module Main class 

package trackmodel;


public class Track
{
	private double elevation;
        private double grade;
	private int speedLimit;
        private int dispatchLimit;
	private boolean occupied;
        private boolean open;
	private String trackID;
        private String trackInfo;
	private int failure;
        private int trafficLight;
        private int blockLength;
	
	//Track module constructor	
	public Track(double iElevate, double iGrade, int spLimit, int blkLen, String trkID)
	{
		elevation = iElevate;
		grade = iGrade;
		speedLimit = spLimit;
		dispatchLimit = speedLimit;
		occupied = false;
		trackID = trkID;
		failure = 0;
		trafficLight = 0;
                blockLength = blkLen;
		open = true;
	}
	
	public void setFailure(int fail) // set track to fail (set value to 1)
	{
		trackInfo = "info: track failed";
		failure = fail;
	}
	
	public void setDispatchLimit(int dLimit) // set dispatcher speed limit
	{
		trackInfo = "info: dispatcher limit set to: "+dLimit;
		dispatchLimit = dLimit;
	}
	
	public void setFix() // call this method to fix track
	{
		trackInfo = "info: track fixed";
		failure = 0;
	}
	
	public void OccupiedStatus(boolean iOccupy) // set block to occupied
	{
		trackInfo = " info: track set to: "+iOccupy;
		occupied = iOccupy;
	}
	
	public void openStatus(boolean iOpen) // set track to open
	{
		trackInfo = "info: track set to: "+iOpen;
		open = iOpen;
	}
	
	public void setTrafficLight(int lightState) // set traffic light
	{
		trackInfo = "info: traffic light set to: "+lightState;
		trafficLight = lightState;
	}
	
//---------------------------------------------------------------------------------------	
	public int getSpeedLimit() // returns track speed limit
	{
		trackInfo = "info: sent speed limit: "+speedLimit;
		return speedLimit;
	}

	public double getElevation() // returns elevation
	{
		trackInfo = "info: sent elevation: "+elevation;
		return elevation;
	}
	
	public String getTrackID() // returns track ID
	{
		trackInfo = "info: sent track ID: "+trackID;
		return trackID;
	}
	
	public double getGrade() // returns grade
	{
		trackInfo = "info: sent grade: "+grade;
		return grade;
	}
	
	public String getTrackUpdate()
	{
		return trackInfo;
	}
        
        public int getBlockLength()
        {
            return blockLength;
        }
}
