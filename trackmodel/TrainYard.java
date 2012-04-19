// this class has the track property Train Yard, it is responsible 
// for creating and removing trains from the system

package trackmodel;

public class TrainYard extends Track
{
	private int xPos, yPos;
	private String yInfo;
	
	// constructor for TrainYard constructor
	public TrainYard(double iElevate, double iGrade, int spLimit, String trkID)
	{
		super(iElevate, iGrade, spLimit, trkID);
	}
	
	public void setYardPosition(int X, int Y) // set train yard coordinates
	{
		xPos = X;
		yPos = Y;
		yInfo = "info: yard position set";
	}
	
	public int getYardPositionX() // returns position x of location
	{
		yInfo = "info: sent yard position X";
		return xPos;
	}
	
	public int getYardPositionY() // returns position y of location
	{
		yInfo = "info: sent yard position Y";		
		return yPos;
	}

	public String getYardInfo() // returns yard updates
	{
		return yInfo;
	}
}
