/*
*	Program Name:	File.java
*	Lead Programmer:	First Last
*	Description:	This class/file/program will…
*	Date Modified:	1/20/12
*/

package ctc;

import parser.*;
import global.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import trackmodel.*;
import wayside.*;

public class CTCModel 
{
    private static boolean debugMode;
    private static int clockRate = 60;
    private int throughput;
    private int capacity;
    private int occupancy; 
    private String[] trackControllers = {"A1", "A2", "A3"};
    private TrackParser parser;
    //private TrackControllers greenTrackControllers;
    //private TrackControllers redTrackControllers;
    
    public CTCModel()
    {
        try
        {
            parser = new TrackParser("GreenLine.csv");
        }
        catch(IOException e)
        {
           System.out.println("Invalid File or File Format");
        }
        
        initializeTrack(parser);
        
        try
        {
            parser = new TrackParser("RedLine.csv");
        }
        catch(IOException e)
        {
           System.out.println("Invalid File or File Format");
        }
    }
    
    private void initializeTrack(TrackParser parser)
    {
        int blockCounter = 0;
        char previous = '.';
        Track prev = null;
        Track t;
        while (parser.next())
        {
            double elevation = parser.getElevation();
            double grade = parser.getGrade();
            int speedLimit = parser.getSpeedLimit();
            int blockLength = parser.getBlock();
            TrackType type = parser.getTrackType();
            char waysideChar = parser.getSection();
            
            if(waysideChar != previous)
            {
                previous = waysideChar;
                blockCounter = 0;
            }

            ID idNum = new ID(parser.getLine(), waysideChar, blockCounter);
            blockCounter++;            
            
            if(type.equals(TrackType.CROSSING))
            {
                t = new Crossing(elevation, grade, blockLength, speedLimit, idNum);
            }
            else
            {
                if(type.equals(TrackType.STATION))
                {
                    t = new Station(elevation, grade, blockLength, speedLimit, idNum);
                }
                else
                {
                    if(type.equals(TrackType.SWITCH))
                    {
                        t = new Switch(elevation, grade, blockLength, speedLimit, idNum);
                    }
                    else
                    {
                        t = new Track(elevation, grade, blockLength, speedLimit, idNum);
                    }
                }
            }
            
        }
    }
    
    public void setDebugMode(boolean d)
    {
        debugMode = d;
    }
    
    public void setClockRate(int c)
    {
        clockRate = c;
        
//        for(int i = 0; i < greenTrackControllers.length; i++)
//        {
//            greenTrackControllers[i].setClockRate(clockRate);
//        }
//        
//        for(int i = 0; i < redTrackControllers.length; i++)
//        {
//            redTrackControllers[i].setClockRate(clockRate);
//        }
    }
    
    /*public Track getTrack(String TrackID)
     * {
     *      
     * }   
    */
    public String [] getTrackIDs()
    {
        String s[] = new String [trackControllers.length];
        for(int i = 0; i < trackControllers.length; i++)
        {
            s[i] = trackControllers[i];
        }
        return s;
    }
    
    public int getThroughput()
    {
        if(debugMode)
        {
            System.out.println("CTC Model: throughput: " + throughput);
        }
        return throughput;
    }
    
    public int getCapacity()
    {
        if(debugMode)
        {
            System.out.println("CTC Model: capacity: " + capacity);
        }
        return capacity;
    }
    
    public int getOccupancy()
    {
        if(debugMode)
        {
            System.out.println("CTC Model: occupancy: " + occupancy);
        }
        return occupancy;
    }
    
}
