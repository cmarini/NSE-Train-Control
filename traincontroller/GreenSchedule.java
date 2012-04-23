import java.io.*;


public class GreenSchedule extends TrainController 
{	
	private boolean stopped;      
	private String currentStop;  
	private String nextStop;
	private int stationCounter = 0; 
	private double currentTime; //actual time it took to get to the stop
	private double dwellTime;
	private double timetoStation;
	private double clockrate;
	private boolean Late; 
	private static double[] times = {2.3, 2.3, 2.4, 2.7, 2.6, 1.9, 2.0, 2.0, 2.2, 2.5, 2.2, 4.4, 2.2, 2.5, 2.2, 4.4, 2.2, 2.3, 2.4, 2.1, 2.0, 2.0};
	 //make a parallel times and stations
	
	public GreenSchedule(double[] time)  
	{
		times = time; 
	}
    public void greenSchedule()
	{	
		Schedule(times);
	}
	
	public void Schedule(double[] times)
	{
		for(int i=0;i<times.length;i++) 
		{
			times[i]=times[i]*60; //times converted to seconds
		}
		updateSchedule(times); 
	} 
	
	public void updateSchedule(double[] times)
	{
		stopped = trainStopped();
		clockrate = getClockRate(); 
		
		//System.out.println("Approaching " + stops[stationCounter] + "Station"); 
		
		if(stopped == true) 
		{
			stationCounter++; //increment stationCounter 
			if(stationCounter > times.length)//If at the end of the schedule start back at 1 
			{
				stationCounter = 0; 
			} 
			timetoStation = times[stationCounter] + timetoStation; 	
		}
		timetoStation -= 60/clockrate * .001;  
	
		//make a onSchedule method that returns a boolean 
		
		if(timetoStation <0 )
		{
			Late = false; 
			
		}
		
		//boolean stay at station until time station = array value - 60 seconds if time > array value elee 60 sec 
	}
	
	public boolean onSchedule()
	{
		return true; 
	}
}
	
		