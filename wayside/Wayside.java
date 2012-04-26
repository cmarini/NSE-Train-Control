package wayside;

import java.util.*;
import java.util.logging.*;
import global.*;
import trackmodel.*;
import trainmodel.*;

/**
 * Controls top-level generic wayside logic that is not specific to any
 * section of track. Abstract fucnctions are defined for logic which is
 * specific to the section of track on which the subclass will operate.
 * @author Christian Marini
 */
public abstract class Wayside implements WaysideInterface, Runnable
{
	final static Logger log = Logger.getLogger(Wayside.class.getName());
	
	protected List<Track> track;
	protected ID id;
	
	protected boolean direction;
	
	protected Wayside nextL;
	protected Wayside nextR;
	protected Wayside prevL;
	protected Wayside prevR;
	
	/**
	 * Creates a new <code>Wayside</code>
	 * @param waysideID unique {@link global.ID} 
	 */
	public Wayside(ID waysideID)
	{
		id = waysideID;
		track = new ArrayList<Track>();
		direction = true;
		log.config(logPrefix() + "Created");
	}
	
	/**
	 * Runs the wayside's core logic for every clock tick
	 */
	public void run()
	{
		if(hasTrain())
		{
			runLogic();
		}
	}
	
	/**
	 * Runs the logic for its track section which is assumed to have a train.
	 */
	abstract void runLogic();
	
	/**
	 * Sets the specified track piece to the specified authority
	 * @param trackID desired track's {@link global.ID}
	 * @param auth desired authority to set
	 */
	public void setAuthority(ID trackID, int auth)
	{
		/* Possible performance improvement if tracks are also hashed by ID */
		Track t;
		if (auth < 0)
		{
			log.warning(logPrefix() + "Invalid authority " + auth);
			return;
		}
		if ((t = findTrack(trackID)) == null)
		{
			return;
		}
		t.setAuthority(auth);
	}
	
	/**
	 * Sets the specified track piece to the specified speed limit
	 * @param trackID desired track's {@link global.ID}
	 * @param speed desired speed limit to set
	 */
	public void setDispatchLimit(ID trackID, int speed)
	{
		/* Possible performance improvement if tracks are also hashed by ID */
		Track t;
		if (speed < 0)
		{
			log.warning(logPrefix() + "Invalid speed " + speed);
			return;
		}
		if ((t = findTrack(trackID)) == null)
		{
			return;
		}
		int limit = (t.getInherentSpeedLimit() < speed) ? t.getInherentSpeedLimit() : speed;
		t.setDispatchLimit(limit);
	}
	
	/**
	 * Returns a reference to the track with the specified ID
	 * @param trackID desired track's {@link global.ID}
	 * @return a reference to the track with the specified ID
	 */
	public Track findTrack(ID trackID)
	{
		for (Track t : track)
		{
			if (t.getID().equals(trackID))
			{
				return t;
			}
		}
		log.warning(logPrefix() + "Cannot find track " + trackID);
		return null;
	}

	/**
	 * Returns <code>true</code> if there is a train in the section
	 * @return <code>true</code> if there is a train in the section
	 */
	public boolean hasTrain()
	{
		for (Track t : track)
		{
			if (t.isOccupied())
			{
				boolean dir = t.getDirection();
				if (direction != dir)
				{
					direction = dir;
					log.fine(logPrefix() + "Direction changed to " + direction);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns <code>true</code> if the section is clear to receive a train
	 * @return <code>true</code> if the section is clear to receive a train
	 */
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
		/* 
		 * All track currently gets authority 1 because
		 * of incorrect track model setup
		 */
		for (Track t : track)
		{
			t.setAuthority(1);
		}
/*
		int dir = direction ? 1 : -1;
		for (int i = trackStart(); i < trackEnd(); i += dir)
		{
			track.get(i).setAuthority(auth);
			auth++;
			if (track.get(i).isOccupied())
			{
				auth = -1;
			}
		}
*/
	}
	
	/**
	 * Returns a reference to the wayside of the next section
	 * @return a reference to the wayside of the next section
	 */ 
	protected Wayside nextLeft()
	{
		return (direction ? nextL : prevL);
	}
	
	/**
	 * Returns a reference to the wayside of the previous section
	 * @return a reference to the wayside of the previous section
	 */ 
	protected Wayside nextRight()
	{
		return (direction ? nextR : prevR);
	}
	
	/**
	 * Returns the index of the start of the Track List
	 * @return the index of the start of the Track List
	 */ 
	protected int trackStart()
	{
		return direction ? 0 : track.size();
	}
	
	/**
	 * Returns the index of the end of the Track List
	 * @return the index of the end of the Track List
	 */ 
	protected int trackEnd()
	{
		return direction ? track.size() : 0;
	}
	
	/**
	 * Adds a {@link trackmodel.Track} to the wayside's section
	 * @param t {@link trackmodel.Track} to add
	 */
	public void addTrack(Track t)
	{
		track.add(t);
		log.config(logPrefix() + "Track " + t.getID() + " added");
	}
       
	/**
	 * Returns an ArrayList of all track of this section
	 * @return an ArrayList of all track of this section
	 */ 
	public ArrayList <Track> getTrackBlocks()
	{
		return (ArrayList)track;
	}
	
	/**
	 * Creates a link to the appropriate adjacent wayside
	 */ 
	public void setWaysideNextLeft(Wayside w)
	{
		nextL = w;
		log.config(logPrefix() + "NextLeft set to" + w.getID());
	}
	
	/**
	 * Creates a link to the appropriate adjacent wayside
	 */ 
	public void setWaysideNextRight(Wayside w)
	{
		nextR = w;
		log.config(logPrefix() + "NextRight set to" + w.getID());
	}
	
	/**
	 * Creates a link to the appropriate adjacent wayside
	 */ 
	public void setWaysidePrevLeft(Wayside w)
	{
		prevL = w;
		log.config(logPrefix() + "PrevLeft set to" + w.getID());
	}
	
	/**
	 * Creates a link to the appropriate adjacent wayside
	 */ 
	public void setWaysidePrevRight(Wayside w)
	{
		prevR = w;
		log.config(logPrefix() + "PrevRight set to" + w.getID());
	}
	
	/**
	 * Returns the {@link global.ID}
	 * @return the {@link global.ID}
	 */ 
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
	
	/**
	 * Returns a String with the wayside ID used for prefixing log messages
	 * @return a String with the wayside ID used for prefixing log messages
	 */ 
	protected String logPrefix()
	{
		return "Wayside " + id.toString() + ": ";
	}
}
