package parser;

import java.util.*;
import java.io.*;
import global.*;

/**
 * Parses and holds csv track information until requested.
 * Track information is retrieved for the 'current' track piece only.
 * <code>next()</code> then steps to the next track piece.
 * <p>
 * The simple implementation would look similar to the follwing:
 * <blockquote><pre>
 *	TrackParser tp = new TrackParser("File.csv");
 *	while (tp.next())
 *	{
 *	    // Get track info
 *	}
 * </pre></blockquote>
 * @author Christian Marini
 */
 
public class TrackParser
{

	private class Entry
	{
		public Line line;
		public char section;
		public int block;
		public double length;
		public double grade;
		public int limit;
		public TrackType tracktype;
		public String station;
		public boolean underground;
		public double elevation;
		public double cumuElevation;
		public boolean linkback;
		public boolean backwards;
		
		public String toString()
		{
			String s = "%-6s %c %3d %6.2f %5.2f %3d %8s %-20s %-5b %5.2f %5.2f %-5b";
			String f = String.format(s, 
				line, 
				section, 
				block, 
				length, 
				grade, 
				limit, 
				tracktype, 
				station,
				underground,
				elevation,
				cumuElevation,
				linkback);
			return f;
		}
	}
	
	private int index;
	private ArrayList<Entry> entries;
	
	/**
	 * Creates a new <code>TrackParser</code> for the specified file
	 * @param filename the track data csv file to be parsed
	 */
	public TrackParser(String filename) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader(filename));
		String in;
		entries = new ArrayList<Entry>();
		while ((in = f.readLine()) != null)
		{
			in = in.replaceAll(":", ";");
			in = in.replaceAll(";[ ]*", ";");
			String[] ss = in.split(",");
			Entry e = new Entry();

			e.line = ss[0].toLowerCase().equals("green") ? Line.GREEN : Line.RED;
			e.section = ss[1].charAt(0);
			e.block = Integer.parseInt(ss[2]);
			e.length = Double.parseDouble(ss[3]);
			e.grade = Double.parseDouble(ss[4]);
			e.limit = Integer.parseInt(ss[5]);
			
			String infoStr = ss[6].toUpperCase();
			String[] info = infoStr.split(";");
			
			e.tracktype = TrackType.TRACK;
			e.underground = false;
			e.station = "";
			
			if (infoStr.contains("STATION"))
			{
				e.tracktype = TrackType.STATION;
				e.station = info.length > 1 ? info[1] : "UNNAMED";
			}
			if (infoStr.contains("SWITCH"))
			{
				if (infoStr.contains("YARD"))
				{
					e.tracktype = TrackType.SWITCHTY;
				}
				else
				{
					e.tracktype = TrackType.SWITCH;
				}
			}
			if (infoStr.equals("UNDERGROUND"))
			{
				e.underground = true;
			}
			if (infoStr.equals("RAILWAY CROSSING"))
			{
				e.tracktype = TrackType.CROSSING;
			}
			
			e.elevation = Double.parseDouble(ss[8]);
			e.cumuElevation = Double.parseDouble(ss[9]);
			
			e.linkback = false;
			e.backwards = false;
			if (ss.length > 10)
			{
				if (ss[10].toUpperCase().contains("LINKBACK"))
				{
					e.linkback = true;
				}
				if (ss[10].toUpperCase().contains("BAKCWARDS"))
				{
					e.backwards = true;
				}
			}
			
			entries.add(e);
		}
		index = -1;
	}
	
	/**
	 * Steps to the next track piece.
	 * @return <code>true</code> if there is a next item
	 */
	public boolean next()
	{
		index++;
		if (index >= entries.size())
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the size of the track list
	 * @return the size of the track list
	 */
	public int size()
	{
		return entries.size();
	}
	
	/**
	 * Returns the line type
	 * @return the line type
	 */
	public Line getLine()
	{
		return entries.get(index).line;
	}
	
	/**
	 * Returns the section
	 * @return the section
	 */
	public char getSection()
	{
		return entries.get(index).section;
	}
	
	/**
	 * Returns the block number
	 * @return the block number
	 */
	public int getBlock()
	{
		return entries.get(index).block;
	}
	
	/**
	 * Returns the block length
	 * @return the block length
	 */
	public double getLength()
	{
		return entries.get(index).length;
	}
	
	/**
	 * Returns the block grade
	 * @return the block grade
	 */
	public double getGrade()
	{
		return entries.get(index).grade;
	}
	
	/**
	 * Returns the speed limit
	 * @return the speed limit
	 */
	public int getSpeedLimit()
	{
		return entries.get(index).limit;
	}
	
	/**
	 * Returns the track type
	 * @return the track type
	 */
	public TrackType getTrackType()
	{
		return entries.get(index).tracktype;
	}

	/**
	 * Returns the station name
	 * @return the station name
	 */
	public String getStation()
	{
		return entries.get(index).station;
	}

	/**
	 * Returns <code>true</code> if the block is underground
	 * @return <code>true</code> if the block is underground
	 */
	public boolean isUnderground()
	{
		return entries.get(index).underground;
	}

	/**
	 * Returns the block's elevation
	 * @return the block's elevation
	 */
	public double getElevation()
	{
		return entries.get(index).elevation;
	}

	/**
	 * Returns the block's cumulative elevation
	 * @return the block's cumulative elevation
	 */
	public double getCumuElevation()
	{
		return entries.get(index).cumuElevation;
	}

	/**
	 * Returns <code>true</code> if the block needs to link back to a switch
	 * @return <code>true</code> if the block needs to link back to a switch
	 */
	public boolean isLinkback()
	{
		return entries.get(index).linkback;
	}

	/**
	 * Returns <code>true</code> if the switch is 'backwards'
	 * @return <code>true</code> if the switch is 'backwards'
	 */
	public boolean isBackwards()
	{
		return entries.get(index).backwards;
	}
	
	/**
	 * Returns a string representation of the object
	 * @return a string representation of the object
	 */
	public String toString()
	{
		return entries.get(index).toString();
	}
}
