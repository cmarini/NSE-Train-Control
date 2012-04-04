/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CTC;

public class CTCModel 
{
    private int throughput;
    private int capacity;
    private int occupancy; 
    private String[] trackControllers = {"A1", "A2", "A3"};
    //private TrackControllers trackControllers;
    
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
        return throughput;
    }
    
    public int getCapacity()
    {
        return capacity;
    }
    
    public int getOccupancy()
    {
        return occupancy;
    }
    
}
