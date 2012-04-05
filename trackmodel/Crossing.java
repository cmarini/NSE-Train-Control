package trackmodel;

public class Crossing extends Track
{
	private boolean crossState = false;
	private String cInfo;
	
	// crossing constructor
	public Crossing(double iElevate, double iGrade, int spLimit, String trkID, int X1, int Y1, int X2, int Y2) 
	{
		super (iElevate, iGrade, spLimit, trkID, X1, Y1, X2, Y2);		
	}
	
	// crossing lights and bar are treated as one entity
	public void setCrossingState(boolean cState) // wayside to set crossing light status
	{
		crossState = cState;
		cInfo = "info: crossing lights and bar are set to: " + crossState;
	}
	
	public boolean getCrossingState() // simulator get crossing light state
	{
		cInfo = "info: sent crossing state";
		return crossState;
	}
	
	public String CrossingInfo() // used to show component activity
	{
		return cInfo;
	}	
}
