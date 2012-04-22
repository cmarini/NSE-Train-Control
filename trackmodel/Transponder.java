package trackmodel;

import global.ID;

public class Transponder extends Track
{
	public enum Type
	{
		UNDERGROUND, STATION;
	}
	
	private String transponderName, tInfo;
        private Type transponderType;
	
	// Transponder constructor
	public Transponder(double iElevate, double iGrade, int spLimit, int blkLen, ID trkID)
	{
		super(iElevate, iGrade, spLimit, blkLen, trkID);
	}
	
	public void setTransponder(Type type, String name) // simulator set transponder info
	{
		transponderType = type;
		transponderName = name;
		tInfo = "info: transponder parameters set";
	}
	
	public Type getType() // returns transponder type
	{
		tInfo = "info: sent transponder type";
		return transponderType;
	}
	
	public String getStationName() // returns station name
	{
		tInfo = "info: sent transponder name";
		return transponderName;
	}
}
