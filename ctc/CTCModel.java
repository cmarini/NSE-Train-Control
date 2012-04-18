/*
*	Program Name:	File.java
*	Lead Programmer:	First Last
*	Description:	This class/file/program will…
*	Date Modified:	1/20/12
*/

package ctc;

public class CTCModel 
{
    private static boolean debugMode;
    private int throughput;
    private int capacity;
    private int occupancy; 
    private String[] trackControllers = {"A1", "A2", "A3"};
    //private TrackControllers greenTrackControllers;
    //private TrackControllers redTrackControllers;
    
    public CTCModel()
    {
    }
    
    public void setDebugMode(boolean d)
    {
        debugMode = d;
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
