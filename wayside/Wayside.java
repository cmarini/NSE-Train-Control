package wayside;

import java.util.*;
import global.*;
import trackmodel.*;
import trainmodel.*;

abstract class Wayside implements WaysideInterface, Runnable
{
	private List<Track> track;
	private ID id;
	
	private boolean trainDir;
	
	public Wayside nextL;
	public Wayside nextR;
	public Wayside prevL;
	public Wayside prevR;
	
	public Wayside(ID id)
	{
		this.id = id;
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
	
	abstract void trainIncFrom(Wayside w);
	
	/*
	 * Think this should set something in the track, not directly on the 
	 * train model/controller
	 */
	public void setAuthority(ID trackID, int authority)
	{
		/* VALIDATE */
		/* Find specified train */
		/* set train's authority */
	}
	
	public void setDispatchLimit(ID trackID, int speed)
	{
		Track t;
		if((speed < 0) || ((t = findTrack(trackID)) == null))
		{
			return;
		}
		t.setDispatchLimit(speed);
	}
	
	private Track findTrack(ID trackID)
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
	
	public void addWaysideNextLeft(Wayside w)
	{
		nextL = w;
	}
	
	public void addWaysideNextRight(Wayside w)
	{
		nextR = w;
	}
	
	public void addWaysidePrevLeft(Wayside w)
	{
		prevL = w;
	}
	
	public void addWaysidePrevRight(Wayside w)
	{
		prevR = w;
	}
	
	public ID getID()
	{
		return id;
	}

	public String toString()
	{
		return id.toString();
	}
}
