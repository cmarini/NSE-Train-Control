package trackmodel;

public class Transponder extends Track
{
	private String transponderName, transponderType, tInfo;
	private int transponderPositionX, transponderPositionY;
	
	// Transponder constructor
	public Transponder(double iElevate, double iGrade, int spLimit, String trkID)
	{
		super(iElevate, iGrade, spLimit, trkID);
	}
	
	public void setTransponder(String name, String type, int posX, int posY) // simulator set transponder info
	{
		transponderName = name;
		transponderType = type;
		transponderPositionX = posX;
		transponderPositionY = posY;
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

	public int getTrasponderPositionX() // returns transponder's positionX
	{
		tInfo = "info: sent transponder position X";
		return transponderPositionX;
	}
	
	public int getTrasponderPositionY() // returns transponder's positionY
	{
		tInfo = "info: sent transponder position Y";
		return transponderPositionY;
	}	

	public String TransponderInfo() // returns transponder update
	{
		return tInfo;
	}
}
