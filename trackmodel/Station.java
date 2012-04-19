package trackmodel;

import java.util.Random;
import global.ID;

public class Station extends Track
{
	private String stationName;
        private String stInfo;
	private int stopPoint;
	private int passengerCount;
	
	// Station constructor
	public Station(double iElevate, double iGrade, int spLimit, int blkLen, ID trkID)
	{
		super(iElevate, iGrade, spLimit, blkLen, trkID);
	}
        
	public void setStationInfo(String name, int stop)
	{
		stationName = name;
		stopPoint = stop;
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
	
	public String getStationInfo() // returns station activity update
	{
		return stInfo;
	}
}
