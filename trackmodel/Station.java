package trackmodel;

import java.util.Random;

public class Station extends Track
{
	private String stationName;
        private String stInfo;
	private int stopPoint;
	private int passengerCount;
	private int positionX;
        private int positionY;
	
	// Station constructor
	public Station(double iElevate, double iGrade, int spLimit, String trkID)
	{
		super(iElevate, iGrade, spLimit, trkID);
	}
        
	public void setStationInfo(String name, int stop, int posX, int posY)
	{
		stationName = name;
		stopPoint = stop;
		positionX = posX;
		positionY = posY;
		stInfo = "station parameters set";
	}
	
	public int getPassengerCount() // returns passenger count
	{
		Random randomGen = new Random();
		
		int passengerPlusMinus = randomGen.nextInt(1);
		
		if(passengerPlusMinus == 0)
		{
			passengerCount = randomGen.nextInt(222)*(-1);
		}
		else
		{
			passengerCount = randomGen.nextInt(222);
		}
		stInfo = "sent passenger count update";
		return passengerCount;
	}
	
	public String getStationName() // returns station name
	{
		stInfo = "sent station name";
		return stationName;
	}
	
	public int getStopPoint() //returns train stopping point
	{
		stInfo = "sent station stopping point";
		return stopPoint;
	}
	
	public int getStationPosX() // returns station position X
	{
		stInfo = "sent station position X";
		return positionX;
	}
	
	public int getStationPosY() // returns station position Y
	{
		stInfo = "sent station positionY";
		return positionY;
	}
	
	public String getStationInfo() // returns station activity update
	{
		return stInfo;
	}
}
