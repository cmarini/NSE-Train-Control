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
    private static boolean debugMode = false; // Used to print out debugging statements
    private TrackParser parser; // Loads in track data from csv files
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
    private Wayside [] redTrackControllers = {
        (new WaysideA(new ID(Line.RED, 'A', -1))), 
        (new WaysideB(new ID(Line.RED, 'B', -1))), 
        (new WaysideC(new ID(Line.RED, 'C', -1))), 
        (new WaysideD(new ID(Line.RED, 'D', -1))), 
        (new WaysideE(new ID(Line.RED, 'E', -1))), 
        (new WaysideF(new ID(Line.RED, 'F', -1))), 
        (new WaysideG(new ID(Line.RED, 'G', -1))),
        (new WaysideH(new ID(Line.RED, 'H', -1))),
        (new WaysideI(new ID(Line.RED, 'I', -1))),
        (new WaysideJ(new ID(Line.RED, 'J', -1))),
        (new WaysideJ(new ID(Line.RED, 'K', -1)))};;
    
    /**
     * Create a new CTCModel object and read in the data for the two lines
     */
    public CTCModel()
    {
        try
        {
            parser = new TrackParser("src/parser/GreenLine.csv");
            initializeTrack(parser);
        }
        catch(IOException e)
        {
           System.out.println("Invalid File or File Format");
        }
        
        try
        {
            parser = new TrackParser("src/parser/RedLine.csv");
            initializeTrack(parser);
        }
        catch(IOException e)
        {
           System.out.println("Invalid File or File Format");
        }
    }
    
    private void initializeTrack(TrackParser parser)
    {
        int blockCounter = 0;   // used to determine the location of a block in a section
        char previous = '.';    // used to determine when a new block section has been reached
        boolean prevLinkBack = false;   // used to determine if the previous block was a linkback block
        Track prev = null;  // the previous block loaded
        Track t = null; // the current block being loaded
        Stack switches = new Stack();   // holds the switch blocks as they are encountered
        boolean first = true;   // used to determine if the block being loaded is the first in the file
        Track firstBlock = null;    // holds the first block loaded
        TrackType prevType = null;  // used to determine the track type of the current block
        boolean prevBackwards = false;  // used to determine the orientation of a switch block
        
        while (parser.next())
        {
            /*
             * get and store the data the parser reads from the CSV
             */
            double elevation = parser.getElevation();
            double grade = parser.getGrade();
            int speedLimit = parser.getSpeedLimit();
            double blockLength = parser.getLength();
            TrackType type = parser.getTrackType();
            char waysideChar = parser.getSection();
            boolean linkback = parser.isLinkback();
            boolean backwards = parser.isBackwards();
            
            if(waysideChar != previous)
            {
                previous = waysideChar;
                blockCounter = 0;
            }

            ID idNum = new ID(parser.getLine(), waysideChar, blockCounter);
            blockCounter++;            
            
            /*
             * Create the proper type of block
             */
            if(type.equals(TrackType.CROSSING))
            {
                t = new Crossing(elevation, grade, speedLimit, blockLength, idNum);
            }
            else
            {
                if(type.equals(TrackType.STATION))
                {
                    t = new Station(elevation, grade, speedLimit, blockLength, idNum);
                }
                else
                {
                    if(type.equals(TrackType.SWITCH) || type.equals(TrackType.SWITCHTY))
                    {
                        t = new Switch(elevation, grade, speedLimit, blockLength, idNum);
                    }
                    else
                    {
                        t = new Track(elevation, grade, speedLimit, blockLength, idNum);
                    }
                }
            }
            
            if(debugMode)
            {
                System.out.println(type + " " + t.getID() + " Created");
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
                else
                {
                    switch (idNum.getSection())
                    {
                        case 'A':
                            redTrackControllers[0].addTrack(t);
                            break;
                        case 'B':
                            redTrackControllers[1].addTrack(t);
                            break;
                        case 'C':
                            redTrackControllers[2].addTrack(t);
                            break;
                        case 'D':
                            redTrackControllers[3].addTrack(t);
                            break;
                        case 'E':
                            redTrackControllers[4].addTrack(t);  
                            break;
                        case 'F':
                            redTrackControllers[5].addTrack(t);  
                            break;
                        case 'G':
                            redTrackControllers[6].addTrack(t);  
                            break;
                        case 'H':
                            redTrackControllers[7].addTrack(t);  
                            break;
                        case 'I':
                            redTrackControllers[8].addTrack(t);  
                            break;
                        case 'J':
                            redTrackControllers[9].addTrack(t);  
                            break;
                        case 'K':
                            redTrackControllers[10].addTrack(t);  
                            break;
                    }
                }
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
                else
                {
                    switch (idNum.getSection())
                    {
                        case 'A':
                            redTrackControllers[0].addTrack(t);
                            break;
                        case 'B':
                            redTrackControllers[1].addTrack(t);
                            break;
                        case 'C':
                            redTrackControllers[2].addTrack(t);
                            break;
                        case 'D':
                            redTrackControllers[3].addTrack(t);
                            break;
                        case 'E':
                            redTrackControllers[4].addTrack(t); 
                            break;
                        case 'F':
                            redTrackControllers[5].addTrack(t);
                            break;
                        case 'G':
                            redTrackControllers[6].addTrack(t);
                            break;
                        case 'H':
                            redTrackControllers[7].addTrack(t);
                            break;
                        case 'I':
                            redTrackControllers[8].addTrack(t);
                            break;
                        case 'J':
                            redTrackControllers[9].addTrack(t);
                            break;
                        case 'K':
                            redTrackControllers[10].addTrack(t); 
                            break;
                    }
                } 
                
                /*
                 * links the blocks together
                 */
                if(backwards)
                {
                    if(type == TrackType.SWITCHTY)
                    {
                        if(prevType == TrackType.SWITCH || prevType == TrackType.SWITCHTY)
                        {
                            ((Switch)t).setPrev(prev);
                            ((Switch)prev).setNext(t);
                            prevType = type;
                            prevBackwards = true;
                            prevLinkBack = false;
                            prev = t;
                        }
                        else
                        {
                            ((Switch)t).setNext(prev, false);
                            prev.setNext(t);
                            prevType = type;
                            prevBackwards = true;
                            prevLinkBack = false;
                            prev = t;
                        }
                    }
                    else
                    {
                        if(t instanceof Switch)
                        {
                            if(prev instanceof Switch)
                            {
                                ((Switch)t).setNext(prev);
                                ((Switch)prev).setNext(t);
                                prevType = type;
                                prevBackwards = true;
                                prevLinkBack = false;
                                prev = t;
                            }
                            else
                            {
                                ((Switch)t).setNext(prev);
                                prev.setNext(t);
                                prevType = type;
                                prevBackwards = true;
                                prevLinkBack = false;
                                prev = t;
                            }
                        }
                        else
                        {
                            if(prev instanceof Switch)
                            {
                                t.setNext(prev);
                                ((Switch)prev).setNext(t);
                                prevType = type;
                                prevBackwards = true;
                                prevLinkBack = false;
                                prev = t;
                            }
                            else
                            {
                                t.setNext(prev);
                                prev.setNext(t);
                                prevType = type;
                                prevBackwards = true;
                                prevLinkBack = false;
                                prev = t;
                            }
                        }
                    }
                }
                else
                {
                    if(!linkback)
                    {
                        if(type == TrackType.SWITCHTY)
                        {
                            if(prev instanceof Switch)
                            {
                                ((Switch)prev).setNext(t, false);
                                ((Switch)t).setPrev(prev);
                                prevType = type;
                                prevBackwards = false;
                                prevLinkBack = false;
                                prev = t;
                            }
                            else
                            {
                                prev.setNext(t);
                                ((Switch)t).setPrev(prev);
                                prevType = type;
                                prevBackwards = false;
                                prevLinkBack = false;
                                prev = t; 
                            }
                        }
                        else
                        {
                            if(prevType == TrackType.SWITCHTY)
                            {
                                if(prevBackwards)
                                {
                                    if(t instanceof Switch)
                                    {
                                        ((Switch)prev).setNext(t, false);
                                        ((Switch)t).setPrev(prev);
                                        prevType = type;
                                        prevBackwards = false;
                                        prevLinkBack = false;
                                        prev = t;
                                    }
                                    else
                                    {
                                        ((Switch)prev).setPrev(t);
                                        t.setPrev(prev);
                                        prevType = type;
                                        prevBackwards = false;
                                        prevLinkBack = false;
                                        prev = t;
                                    }
                                }
                                else
                                {
                                    if(t instanceof Switch)
                                    {
                                        ((Switch)prev).setNext(t, false);
                                        ((Switch)t).setPrev(prev);
                                        prevType = type;
                                        prevBackwards = false;
                                        prevLinkBack = false;
                                        prev = t;
                                    }
                                    else
                                    {
                                        ((Switch)prev).setNext(t, true);
                                        t.setPrev(prev);
                                        prevType = type;
                                        prevBackwards = false;
                                        prevLinkBack = false;
                                        prev = t;
                                    }
                                }
                            }
                            else
                            {
                                if(prevBackwards)
                                {
                                    if(prev instanceof Switch)
                                    {
                                        ((Switch)prev).setPrev(t);
                                        t.setPrev(prev);
                                        prevType = type;
                                        prevBackwards = false;
                                        prevLinkBack = false;
                                        prev = t;
                                    }
                                    else
                                    {
                                        prev.setPrev(t);
                                        t.setPrev(prev);
                                        prevType = type;
                                        prevBackwards = false;
                                        prevLinkBack = false;
                                        prev = t;
                                    }
                                }
                                else
                                {
                                    if(prev instanceof Switch)
                                    {
                                        if(t instanceof Switch)
                                        {
                                            ((Switch)prev).setNext(t);
                                            ((Switch)t).setPrev(prev);
                                            prevType = type;
                                            prevBackwards = false;
                                            prevLinkBack = false;
                                            prev = t;
                                        }
                                        else
                                        { 
                                            ((Switch)prev).setNext(t);
                                            t.setPrev(prev);
                                            prevType = type;
                                            prevBackwards = false;
                                            prevLinkBack = false;
                                            prev = t;
                                        }
                                    }
                                    else
                                    {
                                        if(t instanceof Switch)
                                        {
                                            prev.setNext(t);
                                            ((Switch)t).setPrev(prev);
                                            prevType = type;
                                            prevBackwards = false;
                                            prevLinkBack = false;
                                            prev = t;
                                        }
                                        else
                                        {
                                            prev.setNext(t);
                                            t.setPrev(prev);
                                            prevType = type;
                                            prevBackwards = false;
                                            prevLinkBack = false;
                                            prev = t;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        if(!prevLinkBack)
                        {
                            Switch u = (Switch)switches.pop();
                            t.setPrev(prev);
                            prev.setNext(t);
                            t.setNext(u);
                            ((Switch)u).setNext(t, true);
                            prevType = type;
                            prevBackwards = false;
                            prevLinkBack = true;
                            prev = t;
                        }
                        else
                        {
                            Switch u = (Switch)switches.pop();
                            t.setPrev(u);
                            ((Switch)u).setNext(t, false);
                            ((Switch)u).swapNext();
                            prevType = type;
                            prevBackwards = false;
                            prevLinkBack = true;
                            prev = t;
                        }
                    }
                }
            }
        }
        /*
         * Link the first block and last blocks to form a circuit 
         */
        t = (Switch)switches.pop();
        if(firstBlock != null && t != null)
        {
            firstBlock.setPrev(t);
            ((Switch)t).setNext(firstBlock, true);
        }
    }
    
    /**
     * set the debug mode flag
     * @param d boolean debug mode flag
     */
    public void setDebugMode(boolean d)
    {
        debugMode = d;
    }

    /**
     * return the controllers for a certain line
     * @param line Line for the desired controllers
     * @return array of waysides
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
     * get the track for a specific section
     * @param selectedWaysideID ID for desired wayside
     * @return array of strings of track block IDs in the section
     */
    public String [] getTrackIDs(ID selectedWaysideID)
    {
        Wayside w [] = null;    // holds the waysides from a specific line
        ArrayList <Track> trackBlocks = null;   // holds the track blocks from a specific section
        String trackIDs[];  // holds the string track ids for a specific section
        
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
            case 'K':
                trackBlocks = w[10].getTrackBlocks();
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
     * get the track blocks in a specific section
     * @param l Line where the blocks are located
     * @param i character section where the blocks are located
     * @return ArrayList of Track in the desired section
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
    
    /**
     * get the wayside IDs on a specific line
     * @param l Line where the waysides are located
     * @return String array of the waysides
     */
    public String [] getWaysides(Line l)
    {
        Wayside [] w;   // holds the waysides on a line
        String [] waysideStrings;   // holds the string IDs for the waysides
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
    
    /**
     * get a specific track block object
     * @param id ID for the track block
     * @return Track 
     */
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
        else
        {
            switch (id.getSection())
            {
                case 'A':
                    return redTrackControllers[0].findTrack(id);
                case 'B':
                    return redTrackControllers[1].findTrack(id);
                case 'C':
                    return redTrackControllers[2].findTrack(id);
                case 'D':
                    return redTrackControllers[3].findTrack(id);
                case 'E':
                    return redTrackControllers[4].findTrack(id);
                case 'F':
                    return redTrackControllers[5].findTrack(id);
                case 'G':
                    return redTrackControllers[6].findTrack(id);
                case 'H':
                    return redTrackControllers[7].findTrack(id);
                case 'I':
                    return redTrackControllers[8].findTrack(id);
                case 'J':
                    return redTrackControllers[9].findTrack(id);
                case 'K':
                    return redTrackControllers[10].findTrack(id);
            }
        }
        return null;
    }
    
    /**
     * get the wayside object for a specific section
     * @param l Line where the wayside is located
     * @param section character section for the wayside
     * @return wayside object
     */
    public Wayside getWayside(Line l, char section)
    {
        if(l.equals(Line.GREEN))
        {
            switch (section)
            {
                case 'A':
                    return greenTrackControllers[0];
                case 'B':
                    return greenTrackControllers[1];
                case 'C':
                    return greenTrackControllers[2];
                case 'D':
                    return greenTrackControllers[3];
                case 'E':
                    return greenTrackControllers[4];
                case 'F':
                    return greenTrackControllers[5];
                case 'G':
                    return greenTrackControllers[6];
                case 'H':
                    return greenTrackControllers[7];
                case 'I':
                    return greenTrackControllers[8];
                case 'J':
                    return greenTrackControllers[9];   
            }
        }
        else
        {
            switch (section)
            {
                case 'A':
                    return redTrackControllers[0];
                case 'B':
                    return redTrackControllers[1];
                case 'C':
                    return redTrackControllers[2];
                case 'D':
                    return redTrackControllers[3];
                case 'E':
                    return redTrackControllers[4];
                case 'F':
                    return redTrackControllers[5];
                case 'G':
                    return redTrackControllers[6];
                case 'H':
                    return redTrackControllers[7];
                case 'I':
                    return redTrackControllers[8];
                case 'J':
                    return redTrackControllers[9];
                case 'K':
                    return redTrackControllers[10];
            }
        }
        return null;
    }
    
}
