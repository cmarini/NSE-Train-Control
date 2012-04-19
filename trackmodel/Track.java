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
	private String trainID;
	private String trackInfo;
	private int failure;
	private int trafficLight;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	//Track module constructor	
	public Track(double iElevate, double iGrade, int spLimit, String trkID, int X1, int Y1, int X2, int Y2)
	{
		elevation = iElevate;
		grade = iGrade;
		speedLimit = spLimit;
		dispatchLimit = speedLimit;
		occupied = false;
		trackID = trkID;
		failure = 0;
		trafficLight = 0;
		open = true;
		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
	}
	
	public void setFailure() // set track to fail
	{
		trackInfo = "info: track failed";
		failure = fail;
	}
	
	public void setDispatchLimit(int dLimit) // set dispatcher speed limit
	{
		trackInfo = "info: dispatcher limit set to: "+dLimit;
		dispatchLimit = dLimit;
	}
	
	public void setFix() // fix track
	{
		trackInfo = "info: track fixed";
		failure = 0;
	}
	
	public void setOccupied(boolean iOccupy) // set block to occupied
	{
		trackInfo = " info: track set to: "+iOccupy;
		occupied = iOccupy;
	}
	
	public void setOpen(boolean iOpen) // set track to open
	{
		trackInfo = "info: track set to: "+iOpen;
		open = iOpen;
	}
	
	public void setTrafficLight(int lightState) // set traffic light
	{
		trackInfo = "info: traffic light set to: "+lightState;
		trafficLight = lightState;
	}
	
	public void setTrainID(String id) // set current trainID
	{
		trainID = id;
		trackInfo = "info: trainID set to: " + trainID;
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
	
	// position data
	public int getX1()
	{
		trackInfo = "sent x1: "+x1;
		return x1;
	}
	
	public int getY1()
	{
		trackInfo = "sent y1: "+y1;
		return y1;
	}

	public int getX2()
	{
		trackInfo = "sent x2: "+x2;
		return x2;
	}

	public int getY2()
	{
		trackInfo = "sent y2: "+y2;
		return y2;
	}
	
	public String getID() // returns track ID
	{
		trackInfo = "info: sent track ID: "+trackID;
		return trackID;
	}
	
	public String getTrainID() // returns train ID
	{
		trackInfo = "info: sent train ID: "+trainID;
		return trainID;
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
}
