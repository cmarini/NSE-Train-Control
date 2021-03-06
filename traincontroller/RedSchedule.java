package traincontroller;

import java.io.*;
import java.util.logging.*;


public class RedSchedule
{
    private TrainController tc;
    private boolean stopped;
    private boolean Late;
    private String currentStop;   //
    private String nextStop;
    private int stationCounter = 0;
    private double currentTime; //actual time it took to get to the stop
    private double dwellTime;
    private double timetoStation;
    private double clockrate;
    public double[] times = {3.7, 2.3, 1.5, 1.8, 2.1, 2.1, 1.7, 2.3};

    public RedSchedule() 
    {
    }

    public void redSchedule() {
        Schedule(times);
    }

    public void Schedule(double[] times) 
    {
        for (int i = 0; i < times.length; i++) 
        {
            times[i] = times[i] * 60; //times converted to seconds
        }
        updateSchedule(times);
    }
    //if has Transponder and its stopped in station -- increment station 

    public void updateSchedule(double[] times) 
    {
        stopped = tc.trainStopped();
        clockrate = tc.getClockRate();

        //System.out.println("Approaching " + stops[stationCounter] + "Station"); 

        if (stopped == true) 
        {
            stationCounter++; //increment stationCounter 
            if (stationCounter > times.length)//If at the end of the schedule start back at 1 
            {
                stationCounter = 0;
            }
            timetoStation = times[stationCounter] + timetoStation;
        }
        timetoStation -= 60 / clockrate * .001;

        if (timetoStation < 0) 
        {
            Late = true;
        }
        //boolean stay at station until time station = array value - 60 seconds if time > array value elee 60 sec 
    }

    public boolean onSchedule() 
    {
        return true;
    }
}
