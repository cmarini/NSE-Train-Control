/*
*	Program Name:	CTCModel.java
*	Lead Programmer:	Zachary Sweigart
*	Description:	
*	Date Modified:	4/19/12
*/

package ctc;

import parser.*;
import global.*;
import java.io.IOException;
import trackmodel.*;
import java.util.Stack;
//import wayside.*;

/**
 * 
 * @author AM
 */
public class CTCModel 
{
    private static boolean debugMode;
    private static int clockRate = 60;
    private int throughput;
    private int capacity;
    private int occupancy; 
    private String[] trackControllers = {"A1", "A2", "A3"};
    private TrackParser parser;
//    private Wayside [] greenTrackControllers = new Wayside[9];
//    private Wayside [] redTrackControllers = new Wayside[9];
    
    /**
     * 
     */
    public CTCModel()
    {
        try
        {
            parser = new TrackParser("GreenLine.csv");
            initializeTrack(parser);
        }
        catch(IOException e)
        {
           System.out.println("Invalid File or File Format");
        }
        
        try
        {
            parser = new TrackParser("RedLine.csv");
            initializeTrack(parser);
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
        boolean prevLinkBack = false;
        Track prev = null;
        Track t;
        Stack switches = new Stack();
        
        while (parser.next())
        {
            double elevation = parser.getElevation();
            double grade = parser.getGrade();
            int speedLimit = parser.getSpeedLimit();
            int blockLength = parser.getBlock();
            TrackType type = parser.getTrackType();
            char waysideChar = parser.getSection();
            boolean linkback = parser.isLinkback();
            
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
                if(type.equals(TrackType.STATION) || type.equals(TrackType.SWITCHTY))
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
            
            if(prev == null)
            {
                prev = t;
                prevLinkBack = linkback;
                
                if(type.equals(TrackType.SWITCH))
                {
                    switches.push(t);
                }
                
//                if(idNum.getLine().equals(Line.GREEN))
//                {
//                    switch (idNum.getSection())
//                    {
//                        case 'A':
//                            greenTrackControllers[0].setTrack(t);
//                        case 'B':
//                            greenTrackControllers[1].setTrack(t);
//                        case 'C':
//                            greenTrackControllers[2].setTrack(t);
//                        case 'D':
//                            greenTrackControllers[3].setTrack(t);
//                        case 'E':
//                            greenTrackControllers[4].setTrack(t);
//                        case 'F':
//                            greenTrackControllers[5].setTrack(t);
//                        case 'G':
//                            greenTrackControllers[6].setTrack(t);
//                        case 'H':
//                            greenTrackControllers[7].setTrack(t);
//                        case 'I':
//                            greenTrackControllers[8].setTrack(t);
//                        case 'J':
//                            greenTrackControllers[9].setTrack(t);    
//                    }
//                }
//                else
//                {
//                    switch (id.getSection())
//                    {
//                        case 'A':
//                            return redTrackControllers[0].setTrack(t);
//                        case 'B':
//                            return redTrackControllers[1].setTrack(t);
//                        case 'C':
//                            return redTrackControllers[2].setTrack(t);
//                        case 'D':
//                            return redTrackControllers[3].setTrack(t);
//                        case 'E':
//                            return redTrackControllers[4].setTrack(t);
//                        case 'F':
//                            return redTrackControllers[5].setTrack(t);
//                        case 'G':
//                            return redTrackControllers[6].setTrack(t);
//                        case 'H':
//                            return redTrackControllers[7].setTrack(t);
//                        case 'I':
//                            return redTrackControllers[8].setTrack(t);
//                        case 'J':
//                            return redTrackControllers[9].setTrack(t);    
//                    }
//                }
            }
            else
            {
                if(type.equals(TrackType.SWITCH))
                {
                    switches.push(t);
                }
                
//                if(idNum.getLine().equals(Line.GREEN))
//                {
//                    switch (idNum.getSection())
//                    {
//                        case 'A':
//                            greenTrackControllers[0].setTrack(t);
//                        case 'B':
//                            greenTrackControllers[1].setTrack(t);
//                        case 'C':
//                            greenTrackControllers[2].setTrack(t);
//                        case 'D':
//                            greenTrackControllers[3].setTrack(t);
//                        case 'E':
//                            greenTrackControllers[4].setTrack(t);
//                        case 'F':
//                            greenTrackControllers[5].setTrack(t);
//                        case 'G':
//                            greenTrackControllers[6].setTrack(t);
//                        case 'H':
//                            greenTrackControllers[7].setTrack(t);
//                        case 'I':
//                            greenTrackControllers[8].setTrack(t);
//                        case 'J':
//                            greenTrackControllers[9].setTrack(t);    
//                    }
//                }
//                else
//                {
//                    switch (id.getSection())
//                    {
//                        case 'A':
//                            redTrackControllers[0].setTrack(t);
//                        case 'B':
//                            redTrackControllers[1].setTrack(t);
//                        case 'C':
//                            redTrackControllers[2].setTrack(t);
//                        case 'D':
//                            redTrackControllers[3].setTrack(t);
//                        case 'E':
//                            redTrackControllers[4].setTrack(t);
//                        case 'F':
//                            redTrackControllers[5].setTrack(t);
//                        case 'G':
//                            redTrackControllers[6].setTrack(t);
//                        case 'H':
//                            redTrackControllers[7].setTrack(t);
//                        case 'I':
//                            redTrackControllers[8].setTrack(t);
//                        case 'J':
//                            redTrackControllers[9].setTrack(t);    
//                    }
//                } 
                if(linkback)
                {
                    if(!prevLinkBack)
                    {
                        Switch u = (Switch)switches.pop();
                        t.setPrev(prev);
                        prev.setNext(t);
                        t.setNext(u);
                        u.setNext(t);
                    }
                    else
                    {
                        Switch u = (Switch)switches.pop();
                        t.setPrev(u);
                        u.setNext(t);
                    }
                }
                else
                {
                    t.setPrev(prev);
                    prev.setNext(t);
                }
            }
        }
    }
    
    /**
     * 
     * @param d
     */
    public void setDebugMode(boolean d)
    {
        debugMode = d;
    }
    
    /**
     * 
     * @param c
     */
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

    /**
     * 
     */
    public void run()
    {
        
    }

    /**
     * 
     * @return
     */
//    public Wayside [] getControllers(Line line)
//    {
//        switch(line)
//        {
//            case GREEN:
//                return greenTrackControllers;
//            case RED:
//                return redTrackControllers;
//            default
//                return new Wayside []
//        }
//    }
    
    /**
     * 
     * @return
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
    
    /**
     * 
     * @return
     */
    public String [] getTrackIDs(String dispatcherID)
    {
        String s[] = new String [trackControllers.length];
        for(int i = 0; i < trackControllers.length; i++)
        {
            s[i] = trackControllers[i];
        }
        return s;
    }
    
    /**
     * 
     * @return
     */
//    public String [] getTrackIDs(ID selectedWaysideID)
//    {
//        Wayside w [];
//        switch(selectedWaysideID.getLine())
//        {
//            case GREEN:
//                w = greenTrackControllers;
//            case RED:
//                w = redTrackControllers;
//        }  
//        switch(selectedWaysideID.getSection())
//        {
//            case 'A':
//                return w[0].getTrackIDs();
//                break;
//            case 'B':
//                return w[1].getTrackIDs();
//                break;
//            case 'C':
//                return w[2].getTrackIDs();
//                break;
//            case 'D':
//                return w[3].getTrackIDs();
//                break;
//            case 'E':
//                return w[4].getTrackIDs();
//                break;
//            case 'F':
//                return w[5].getTrackIDs();
//                break;
//            case 'G':
//                return w[6].getTrackIDs();
//                break;
//            case 'H':
//                return w[7].getTrackIDs();
//                break;
//            case 'I':
//                return w[8].getTrackIDs();
//                break;
//            case 'J':
//                return w[9].getTrackIDs();
//                break;
//        }
//    }
    
    /**
     * 
     * @return
     */
    public int getThroughput()
    {
        if(debugMode)
        {
            System.out.println("CTC Model: throughput: " + throughput);
        }
        return throughput;
    }
    
    /**
     * 
     * @return
     */
    public int getCapacity()
    {
        if(debugMode)
        {
            System.out.println("CTC Model: capacity: " + capacity);
        }
        return capacity;
    }
    
    /**
     * 
     * @return
     */
    public int getOccupancy()
    {
        if(debugMode)
        {
            System.out.println("CTC Model: occupancy: " + occupancy);
        }
        return occupancy;
    }
    
    /**
     * 
     * @return
     */
//    public Track [] getWaysideTrack(Line l, int i)
//    {
//        if(l.equals(Line.GREEN))
//        {
//            return greenTrackControllers[i].getTrack();
//        }
//        else
//        {
//            if(l.equals(Line.RED))
//            {
//                return redTrackControllers[i].getTrack();
//            }
//        }
//    }
    
//    public Track getTrack(ID id)
//    {
//        if(id.getLine().equals(Line.GREEN))
//        {
//            switch (id.getSection())
//            {
//                case 'A':
//                    return greenTrackControllers[0].getTrack(id.getUnit());
//                case 'B':
//                    return greenTrackControllers[1].getTrack(id.getUnit());
//                case 'C':
//                    return greenTrackControllers[2].getTrack(id.getUnit());
//                case 'D':
//                    return greenTrackControllers[3].getTrack(id.getUnit());
//                case 'E':
//                    return greenTrackControllers[4].getTrack(id.getUnit());
//                case 'F':
//                    return greenTrackControllers[5].getTrack(id.getUnit());
//                case 'G':
//                    return greenTrackControllers[6].getTrack(id.getUnit());
//                case 'H':
//                    return greenTrackControllers[7].getTrack(id.getUnit());
//                case 'I':
//                    return greenTrackControllers[8].getTrack(id.getUnit());
//                case 'J':
//                    return greenTrackControllers[9].getTrack(id.getUnit());    
//            }
//        }
//        else
//        {
//            switch (id.getSection())
//            {
//                case 'A':
//                    return redTrackControllers[0].getTrack(id.getUnit());
//                case 'B':
//                    return redTrackControllers[1].getTrack(id.getUnit());
//                case 'C':
//                    return redTrackControllers[2].getTrack(id.getUnit());
//                case 'D':
//                    return redTrackControllers[3].getTrack(id.getUnit());
//                case 'E':
//                    return redTrackControllers[4].getTrack(id.getUnit());
//                case 'F':
//                    return redTrackControllers[5].getTrack(id.getUnit());
//                case 'G':
//                    return redTrackControllers[6].getTrack(id.getUnit());
//                case 'H':
//                    return redTrackControllers[7].getTrack(id.getUnit());
//                case 'I':
//                    return redTrackControllers[8].getTrack(id.getUnit());
//                case 'J':
//                    return redTrackControllers[9].getTrack(id.getUnit());    
//            }
//        }
//    }
    
}
