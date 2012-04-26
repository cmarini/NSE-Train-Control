// Track Module Main class 

package trackmodel;

import global.*;
import java.util.logging.*;

public class Track
{
	final static Logger log = Logger.getLogger(Track.class.getName());
	
	private double elevation;
	private double grade;
	private int speedLimit;
	private int dispatchLimit;
	private int authority;
	private boolean occupied;
	private boolean open;
	protected ID trackID;
	private String trackInfo;
	private TrackFault failure;
	private Light trafficLight;
	private double blockLength;
	protected Track A;
	protected Track B;
	protected boolean direction = true;
	
	//Track module constructor	
	public Track(double iElevate, double iGrade, int spLimit, double blkLen, ID trkID)
	{
		elevation = iElevate;
		grade = iGrade;
		speedLimit = spLimit;
		dispatchLimit = speedLimit;
		occupied = false;
		trackID = trkID;
		failure = TrackFault.NONE;
		trafficLight = Light.NONE;
		blockLength = blkLen;
		open = true;
		authority = 0;
		log.config("Track " + trackID + ": Created");
	}
	
	public void setFailure(TrackFault f) // set track to fail (set value to 1)
	{
		failure = f;
		log.info("Track " + trackID + ": Failure set (" + f + ")");
	}
	
	public void setDispatchLimit(int dLimit) // set dispatcher speed limit
	{
		dispatchLimit = dLimit;
		log.info("Track " + trackID + ": Dispatcher limit set to " + dLimit);
	}
	
	public void setAuthority(int auth) // set authority
	{
		authority = auth;
		// log.info("Track " + trackID + ": authority set to " + auth);
	}
	
	public void setFix() // call this method to fix track
	{
		failure = TrackFault.NONE;
		log.info("Track " + trackID + ": Track fixed");
	}
	
	public void setOccupied(Track from) // set block to occupied
	{
		if(from == null)
		{
			direction = false;
		}
		else if(A.equals(from))
		{
			direction = false;
		}
		else
		{
			direction = true;
		}
		occupied = true;
		log.info("Track " + trackID + ": set to OCCUPIED");
	}
	
	public void setUnoccupied()
	{
		occupied = false;
		log.info("Track " + trackID + ": set to UNOCCUPIED");
	}
	
	public void setOpen(boolean iOpen) // set track to open
	{
		open = iOpen;
		log.info("Track " + trackID + ": Open set to " + iOpen);
	}
	
	public void setTrafficLight(Light lightState) // set traffic light
	{
		trafficLight = lightState;
		log.info("Track " + trackID + ": Light set to " + lightState);
	}
        
	public void setPrev(Track t)
	{
		A = t;
		log.config("Track " + trackID + ": A set to " + t);
                //System.out.println("Track " + trackID + ": A set to " + t.getID());
	}

	public void setNext(Track t)
	{
		B = t;
		log.config("Track " + trackID + ": B set to " + t);
                //System.out.println("Track " + trackID + ": B set to " + t.getID());
	}
	
//---------------------------------------------------------------------------------------	
	
	public boolean isOccupied()
	{
		return occupied;
	}
        
	public boolean isOpen()
	{
		return open;
	}
        
	public TrackFault isFailed()
	{
		return failure;
	}
	
	public Track getNext()
	{
		return (direction ? A : B);
	}
	
	public Track getNext(boolean dir)
	{
		direction = dir;
		return getNext();
	}
	
	public boolean getDirection()
	{
		return direction;
	}
        
	public int getInherentSpeedLimit() // returns track speed limit
	{
		log.fine("Track " + trackID + ": Sent speed limit: " + speedLimit);
		return speedLimit;
	}
	
	public int getSpeedLimit()
	{
		return dispatchLimit;
	}
        
	public int getAuthority() // returns authority
	{
		log.fine("Track " + trackID + ": Sent authority: " + authority);
		return authority;
	}

	public double getElevation() // returns elevation
	{
		log.fine("Track " + trackID + ": Sent elevation: " + elevation);
		return elevation;
	}
	
	public ID getID() // returns track ID
	{
		log.fine("Track " + trackID + ": Sent track ID: " + trackID);
		return trackID;
	}
	
	public double getGrade() // returns grade
	{
		log.fine("Track " + trackID + ": Sent grade: " + grade);
		return grade;
	}
	
	public String getTrackUpdate()
	{
		return trackInfo;
	}
        
	public double getBlockLength()
	{
		return blockLength;
	}

	public Light getLightState()
	{
	return trafficLight;
	}
}
