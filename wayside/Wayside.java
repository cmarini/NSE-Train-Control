package wayside;

import java.util.*;
import trackmodel.*;
import trainmodel.*;

abstract class Wayside implements Runnable
{
	private List<Track> track;
	private String id;
	
	private boolean trainDir;
	
	public Wayside a;
	public Wayside b;
	
	public Wayside(String id, Wayside a, Wayside b)
	{
		this.id = id;
		this.a = a;
		this.b = b;
		this.track = new ArrayList<Track>();
		this.trainDir = true;
	}
	
	public void run()
	{
		if(hasTrain())
		{
			runLogic();
		}
	}
	
	/*
	 * Runs the logic for its track section which is assumed to have a train.
	 */
	abstract void runLogic();
	
	/*
	 * Think this should set something in the track, not directly on the 
	 * train model/controller
	 */
	public void setAuthority(String trainID, int authority)
	{
		/* VALIDATE */
		/* Find specified train */
		/* set train's authority */
	}
	
	public void setDispatchLimit(String trackID, int speed)
	{
		Track t;
		if((speed < 0) || ((t = findTrack(trackID)) == null))
		{
			return;
		}
		t.setDispatchLimit(speed);
	}
	
	private Track findTrack(String trackID)
	{
		/* Scan track section for ID */
		for (Track t : track)
		{
			if (t.getID().equals(trackID))
			{
				return t;
			}
		}
		return null;
	}
	
	/* 
	 * Not sure if this should return a Train or trainID.
	 * This may not even be needed at all.
	 */
	private Train findTrain(String trainID)
	{
		/* Scan track section for specified train ID */
	}
	
	public boolean hasTrain()
	{
		/* Scan track for any train */
		for (Track t : track)
		{
			if (t.isOccupied())
			{
				return true;
			}
		}
		return false;
	}
	
	public void addTrack(Track t)
	{
		track.add(t);
	}
	
	public String getID()
	{
		return id;
	}

	public String toString()
	{
		return id.toString();
	}
}
