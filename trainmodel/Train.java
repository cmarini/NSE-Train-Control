package trainmodel;

import global.*;

public class Train
{
	private double length;
	private Line trainLine;
	private double mass;
	private int crewCount;
	private int maxCapacity;
	private int occupancy;
	private double distTraveled;
	private int maxTrainSpeed;
	private int maxPower;
	private boolean doors; // false = close, true = open
	private boolean headLights; // false = off, true = on
	private boolean cabinLights; // false = off, true = on
	private String trainId;
	private boolean transponder;
        private double height;
        private double width;
	private boolean [] failures;

	public Train(Line line, int crew, String id)
	{
		length = 32.2; //m
		trainLine = line;
		mass = 40.9 * 2000; // lbs
                height = 3.42; //m
                width = 2.65; //m
		crewCount = crew;
		maxCapacity =  222 + crewCount;
		distTraveled = 0; // meters
		headLights = false;
		cabinLights = false;
		maxTrainSpeed = 70; // km/h
		//maxPower = // ???
		occupancy = crewCount; // how many crew members?
		trainId = id;
		transponder = false;
                failures = new boolean [3];
                failures[0] = false;
                failures[1] = false;
                failures[2] = false;
	}

	public double calcVelocity()
	{
		// get int power
		// get track info
		//
            return 0.0;
	}
        
        public double calcAcceleration()
        {
            return 0.0;
        }
        
        public double getMass()
        {
            return mass + occupancy * 175;
        }
        
        public double getLength()
        {
            return length;
        }
        
        public double getWidth()
        {
            return width;
        }
        
        public int getCapacity()
        {
            return maxCapacity;
        }
        
        public int getOccupancy()
        {
            return occupancy;
        }
        
        public int getCrew()
        {
            return crewCount;
        }
        
        public double getHeight()
        {
            return height;
        }
        
        public boolean getHeadLights()
        {
            return headLights;
        }

	public void setHeadLights()
	{
		headLights = !headLights;
	}
        
        public boolean getCabinLights()
        {
            return cabinLights;
        }

	public void setCabinLights()
	{
		cabinLights = !cabinLights;
	}

	public void openDoors()
	{
		doors = true;
	}

	public void closeDoors()
	{
		doors = false;
	}

        public boolean getDoors()
        {
            return doors;
        }
        
	public Line getLine()
	{
		return trainLine;
	}
        
        public boolean [] getFailures()
        {
            return failures;
        }

	public boolean hasTransponder()
	{
		return transponder;
	}

	public String getTransponderInfo()
	{
            return "A";
	}
        
        public void setFailure(int i)
        {
            failures[i] = true;
        }
        
        public void fixFailure(int i)
        {
            failures[i] = false;
        }

	public void updateTrack()
	{
		// set occupied
		// set unoccupied
		// give reference to yourself
		// get grade info getGrade()
		// check for transponder and set var transponder
		// get transponder info if it exists
	}


}
