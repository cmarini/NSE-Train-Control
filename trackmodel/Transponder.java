package trackmodel;

import global.ID;

public class Transponder extends Track
{
	private String transponderName, transponderType, tInfo;
	
	// Transponder constructor
	public Transponder(double iElevate, double iGrade, int spLimit, int blkLen, ID trkID)
	{
		super(iElevate, iGrade, spLimit, blkLen, trkID);
	}
	
	public void setTransponder(String name, String type) // simulator set transponder info
	{
		transponderName = name;
		transponderType = type;
		tInfo = "info: transponder parameters set";
	}
	
	public String getTrasponderName() // returns transponder's name
	{
		tInfo = "info: sent transponder name";
		return transponderName;
	}
	
	public String getTrasponderType() // returns transponder type
	{
		tInfo = "info: sent transponder type";
		return transponderType;
	}

	public String TransponderInfo() // returns transponder update
	{
		return tInfo;
	}
}
