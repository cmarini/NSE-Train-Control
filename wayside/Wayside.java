package wayside;

import java.util.*;
import global.*;
import trackmodel.*;
import trainmodel.*;

abstract class Wayside implements WaysideInterface, Runnable
{
	protected List<Track> track;
	protected ID id;
	
	protected boolean direction;
	
	protected Wayside nextL;
	protected Wayside nextR;
	protected Wayside prevL;
	protected Wayside prevR;
	
	public Wayside(ID waysideID)
	{
		id = waysideID;
		track = new ArrayList<Track>();
		direction = true;
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
	
	//abstract void trainIncFrom(Wayside w);
	
	public void setAuthority(ID trackID, int authority)
	{
		/* Possible performance improvement if tracks are also hashed by ID */
		Track t;
		if((authority < 0) || ((t = findTrack(trackID)) == null))
		{
			return;
		}
		t.setAuthority(authority);
	}
	
	public void setDispatchLimit(ID trackID, int speed)
	{
		/* Possible performance improvement if tracks are also hashed by ID */
		Track t;
		if((speed < 0) || ((t = findTrack(trackID)) == null))
		{
			return;
		}
		t.setDispatchLimit(speed);
	}
	
	protected Track findTrack(ID trackID)
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

	public boolean hasTrain()
	{
		/* Scan track for any train */
		for (Track t : track)
		{
			if (t.isOccupied())
			{
				direction = t.getDirection();
				return true;
			}
		}
		return false;
	}
	
	public boolean clearToReceiveFrom(Wayside w)
	{
		if (!hasTrain())
		{
			return true;
		}
		if (nextLeft().equals(w) || nextRight().equals(w))
		{
			return false;
		}
		if (!track.get(trackStart()).isOccupied())
		{
			return true;
		}
		return false;
	}
	
	protected void spreadAuthority(int auth)
	{
		int dir = direction ? -1 : 1;
		for (int i = trackStart(); i < trackEnd(); i += dir)
		{
			track.get(i).setAuthority(auth);
			auth++;
			/* Make sure a possible second train only has
			 * authority up to the first train */
			if (track.get(i).isOccupied())
			{
				auth = -1;
			}
		}
	}
	
	protected Wayside nextLeft()
	{
		return (direction ? nextL : prevL);
	}
	
	protected Wayside nextRight()
	{
		return (direction ? nextR : prevR);
	}
	
	protected int trackStart()
	{
		return direction ? 0 : track.size();
	}
	
	protected int trackEnd()
	{
		return direction ? track.size() : 0;
	}
	
	public void addTrack(Track t)
	{
		track.add(t);
	}
	
	public void setWaysideNextLeft(Wayside w)
	{
		nextL = w;
	}
	
	public void setWaysideNextRight(Wayside w)
	{
		nextR = w;
	}
	
	public void setWaysidePrevLeft(Wayside w)
	{
		prevL = w;
	}
	
	public void setWaysidePrevRight(Wayside w)
	{
		prevR = w;
	}
	
	public ID getID()
	{
		return id;
	}

	public boolean equals(Wayside w)
	{
		return id.equals(w.getID());
	}

	public String toString()
	{
		return id.toString();
	}
	
}
