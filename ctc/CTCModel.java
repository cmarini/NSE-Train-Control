/*
*	Program Name:	CTCModel.java
*	Lead Programmer:	Zachary Sweigart
*	Date Modified:	4/19/12
*/

package ctc;

import parser.*;
import global.*;
import java.io.IOException;
import trackmodel.*;
import java.util.Stack;
import wayside.*;
import java.util.ArrayList;

/**
 * 
 * @author Zachary Swiegart
 */
public class CTCModel 
{
    private static boolean debugMode;
    private static int clockRate = 60;
    private TrackParser parser;
    private Wayside [] greenTrackControllers = {
        (new WaysideA(new ID(Line.GREEN, 'A', -1))), 
        (new WaysideB(new ID(Line.GREEN, 'B', -1))), 
        (new WaysideC(new ID(Line.GREEN, 'C', -1))), 
        (new WaysideD(new ID(Line.GREEN, 'D', -1))), 
        (new WaysideE(new ID(Line.GREEN, 'E', -1))), 
        (new WaysideF(new ID(Line.GREEN, 'F', -1))), 
        (new WaysideG(new ID(Line.GREEN, 'G', -1))),
        (new WaysideH(new ID(Line.GREEN, 'H', -1))),
        (new WaysideI(new ID(Line.GREEN, 'I', -1))),
        (new WaysideJ(new ID(Line.GREEN, 'J', -1)))};
    private Wayside [] redTrackControllers = new Wayside[9];
    
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
        Track t = null;
        Stack switches = new Stack();
        boolean first = true;
        Track firstBlock = null;
        
        while (parser.next())
        {
            double elevation = parser.getElevation();
            double grade = parser.getGrade();
            int speedLimit = parser.getSpeedLimit();
            double blockLength = parser.getLength();
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
                t = new Crossing(elevation, grade, speedLimit, blockLength, idNum);
            }
            else
            {
                if(type.equals(TrackType.STATION) || type.equals(TrackType.SWITCHTY))
                {
                    t = new Station(elevation, grade, speedLimit, blockLength, idNum);
                }
                else
                {
                    if(type.equals(TrackType.SWITCH))
                    {
                        t = new Switch(elevation, grade, speedLimit, blockLength, idNum);
                    }
                    else
                    {
                        t = new Track(elevation, grade, speedLimit, blockLength, idNum);
                    }
                }
            }
            
            if(first)
            {
                firstBlock = t;
                first = false;
            }
            
            if(prev == null)
            {
                prev = t;
                prevLinkBack = linkback;
                
                if(type.equals(TrackType.SWITCH))
                {
                    switches.push(t);
                }
                
                if(idNum.getLine().equals(Line.GREEN))
                {
                    switch (idNum.getSection())
                    {
                        case 'A':
                            greenTrackControllers[0].addTrack(t);
                            break;
                        case 'B':
                            greenTrackControllers[1].addTrack(t);
                            break;
                        case 'C':
                            greenTrackControllers[2].addTrack(t);
                            break;
                        case 'D':
                            greenTrackControllers[3].addTrack(t);
                            break;
                        case 'E':
                            greenTrackControllers[4].addTrack(t);
                            break;
                        case 'F':
                            greenTrackControllers[5].addTrack(t);
                            break;
                        case 'G':
                            greenTrackControllers[6].addTrack(t);
                            break;
                        case 'H':
                            greenTrackControllers[7].addTrack(t);
                            break;
                        case 'I':
                            greenTrackControllers[8].addTrack(t);
                            break;
                        case 'J':
                            greenTrackControllers[9].addTrack(t); 
                            break;
                    }
                }
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
                
                if(idNum.getLine().equals(Line.GREEN))
                {
                    switch (idNum.getSection())
                    {
                        case 'A':
                            greenTrackControllers[0].addTrack(t);
                            break;
                        case 'B':
                            greenTrackControllers[1].addTrack(t);
                            break;
                        case 'C':
                            greenTrackControllers[2].addTrack(t);
                            break;
                        case 'D':
                            greenTrackControllers[3].addTrack(t);
                            break;
                        case 'E':
                            greenTrackControllers[4].addTrack(t);
                            break;
                        case 'F':
                            greenTrackControllers[5].addTrack(t);
                            break;
                        case 'G':
                            greenTrackControllers[6].addTrack(t);
                            break;
                        case 'H':
                            greenTrackControllers[7].addTrack(t);
                            break;
                        case 'I':
                            greenTrackControllers[8].addTrack(t);
                            break;
                        case 'J':
                            greenTrackControllers[9].addTrack(t);    
                            break;
                    }
                }
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
        if(firstBlock != null && t != null)
        {
            firstBlock.setPrev(t);
            t.setNext(firstBlock);
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
     */
    public void run()
    {
        
    }

    /**
     * 
     * @return
     */
    public Wayside [] getControllers(Line line)
    {
        switch(line)
        {
            case GREEN:
                return greenTrackControllers;
            case RED:
                return redTrackControllers;
            default:
                return null;
        }
    }
        
    /**
     * 
     * @return
     */
    public String [] getTrackIDs(ID selectedWaysideID)
    {
        Wayside w [] = null;
        ArrayList <Track> trackBlocks = null;
        String trackIDs[];
        
        if(selectedWaysideID == null)
        {
            return new String[0];
        }
        
        switch(selectedWaysideID.getLine())
        {
            case GREEN:
                w = greenTrackControllers;
                break;
            case RED:
                w = redTrackControllers;
                break;
        } 
        
        if(w == null || w[0] == null)
        {
            return new String[0];
        }
        
        switch(selectedWaysideID.getSection())
        {
            case 'A':
                trackBlocks = w[0].getTrackBlocks();
                break;
            case 'B':
                trackBlocks = w[1].getTrackBlocks();
                break;
            case 'C':
                trackBlocks = w[2].getTrackBlocks();
                break;
            case 'D':
                trackBlocks = w[3].getTrackBlocks();
                break;
            case 'E':
                trackBlocks = w[4].getTrackBlocks();
                break;
            case 'F':
                trackBlocks = w[5].getTrackBlocks();
                break;
            case 'G':
                trackBlocks = w[6].getTrackBlocks();
                break;
            case 'H':
                trackBlocks = w[7].getTrackBlocks();
                break;
            case 'I':
                trackBlocks = w[8].getTrackBlocks();
                break;
            case 'J':
                trackBlocks = w[9].getTrackBlocks();
                break;
            default:
                return new String [0];
        }
        trackIDs = new String[trackBlocks.size()];
        for(int i = 0; i < trackBlocks.size(); i++)
        {
            trackIDs[i] = trackBlocks.get(i).getID().getSection() + "" + trackBlocks.get(i).getID().getUnit();
        }
        return trackIDs;
    }
    
    /**
     * 
     * @return
     */
    public ArrayList <Track> getWaysideTrack(Line l, int i)
    {
        if(l.equals(Line.GREEN))
        {
            return greenTrackControllers[i].getTrackBlocks();
        }
        else
        {
            if(l.equals(Line.RED))
            {
                return redTrackControllers[i].getTrackBlocks();
            }
        }
        return null;
    }
    
    public String [] getWaysides(Line l)
    {
        Wayside [] w;
        String [] waysideStrings;
        switch(l)
        {
            case GREEN:
                w = greenTrackControllers;
                break;
            default:
                w = redTrackControllers;
        }
        if(w == null || w[0] == null)
        {
            return new String[0];
        }
        waysideStrings = new String[w.length];
        for(int i = 0; i < w.length; i++)
        {
            waysideStrings[i] = w[i].getID().getSection() + "";
        }

        return waysideStrings;
    }
    
    public Track getTrack(ID id)
    {
        if(id.getLine().equals(Line.GREEN))
        {
            switch (id.getSection())
            {
                case 'A':
                    return greenTrackControllers[0].findTrack(id);
                case 'B':
                    return greenTrackControllers[1].findTrack(id);
                case 'C':
                    return greenTrackControllers[2].findTrack(id);
                case 'D':
                    return greenTrackControllers[3].findTrack(id);
                case 'E':
                    return greenTrackControllers[4].findTrack(id);
                case 'F':
                    return greenTrackControllers[5].findTrack(id);
                case 'G':
                    return greenTrackControllers[6].findTrack(id);
                case 'H':
                    return greenTrackControllers[7].findTrack(id);
                case 'I':
                    return greenTrackControllers[8].findTrack(id);
                case 'J':
                    return greenTrackControllers[9].findTrack(id);    
            }
        }
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
        return null;
    }
    
}
