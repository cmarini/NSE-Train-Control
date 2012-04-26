package trainmodel;

import global.*;
import trackmodel.*;

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
	public boolean transponder;
	public double powerCmd;
	public double velocity;
	public double acceleration;
	public double oldVelocity;
	public double grade;
	public double time;
	private double height;
	private double width;
	private boolean [] failures;
	public Track trackPiece;
	public Track prevTrack;
	public String stationName;
	public Transponder.Type transType;
	public boolean emerBrake;
	public boolean servBrake;
	public double blockLength;
	public int blockSpeed;
	public int blockAuthority;
	public double distInBlock;
	public double remainder;

	public Train(Line line, int crew, String id)
	{
		length = 32.2; //m
		trainLine = line;
		mass = 40.9 * 2000 + crew * 175 * 2.2; // Kg
		height = 3.42; //m
		width = 2.65; //m
		crewCount = crew;
		maxCapacity =  222 + crewCount;
		distTraveled = 0; // meters
		headLights = false;
		cabinLights = false;
		maxTrainSpeed = 70; // km/h
		occupancy = 0; // just passengers
		trainId = id;
		transponder = false;
		failures = new boolean [3];
		failures[0] = false;
		failures[1] = false;
		failures[2] = false;
		emerBrake = false;
		servBrake = false;
		distInBlock = 0;
                blockLength = length;
                oldVelocity = 1;
	}

	public void calcVelocity()
	{
            
            System.out.println("powerCMD " + powerCmd + " time" + time + " oldVelocity" + oldVelocity + " mass" + mass);
                if(oldVelocity == 0)
                {
                    oldVelocity = 1;
                }
		if (emerBrake)
		{
			velocity = oldVelocity - 2.73 * time;
                        if(velocity <= 0)
                        {
                            velocity = 0;
                        }
                        oldVelocity = velocity;
		}
		else if (servBrake)
		{
			velocity = oldVelocity - 1.2 * time;
                        if(velocity <= 0)
                        {
                            velocity = 0;
                        }
                        oldVelocity = velocity;
		}
		else
		{
			velocity = (((powerCmd*time)/oldVelocity)/mass) + oldVelocity;
			oldVelocity = velocity;
		}
		//velocity = (((powerCmd*time)/oldVelocity)/mass) + oldVelocity;
		//oldVelocity = velocity;
		//return velocity;
	}

	public void setServiceBrake(boolean bR)
	{
		emerBrake = bR;
	}

	public void setEmergencyBrake(boolean bR)
	{
		servBrake = bR;
	}

	public double getVelocity()
	{
		return velocity;
	}

	public void setPower(double pwr)
	{
		powerCmd = pwr;
	}

	public void setClockRate(int cr)
	{
		time = cr;
	}

	public double calcAcceleration()
	{
		acceleration = powerCmd/(oldVelocity*mass);
		return acceleration;
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

	public Transponder.Type getTransponderInfo()
	{
		return transType;
	}

	public String getStationName()
	{
		return stationName;
	}

	public void setFailure(int i)
	{
		failures[i] = true;
	}

	public void fixFailure(int i)
	{
		failures[i] = false;
	}

	public void updateTrack(double t)
	{
		time = t;
		distTraveled = distTraveled + velocity * time;
		distInBlock = distInBlock + velocity * time;
		remainder = length - distInBlock;
		calcVelocity();
		//System.out.println("Velocity " + velocity + " distTraveled " + distTraveled);

		if (remainder < 0)
		{
			if(prevTrack != null)
			{
				prevTrack.setUnoccupied();
			}
		}

		if (distInBlock > blockLength)
		{
			trackPiece.setOccupied(prevTrack);
			prevTrack = trackPiece;
			if (trackPiece instanceof Switch)
			{
				trackPiece = ((Switch)trackPiece).getNext();
			}
			else
			{
				trackPiece = trackPiece.getNext();
			}
			distInBlock = distInBlock - blockLength;
			blockSpeed = trackPiece.getSpeedLimit();
			blockAuthority = trackPiece.getAuthority();
			grade = trackPiece.getGrade();
			blockLength = trackPiece.getBlockLength();

			if (trackPiece instanceof Transponder)
			{
				Transponder transponder = (Transponder) trackPiece;
				stationName = transponder.getStationName();
				transType = transponder.getType();
			}
		}
	}

	public void setTrack(Track T)
	{
		trackPiece = T;
		T.setOccupied(null);
		//updateTrack();
	}

	public Track getTrack()
	{
		return trackPiece;
	}

	public double getDistance()
	{
		return distTraveled;
	}

	public int getSpeedLimit()
	{
                blockSpeed = trackPiece.getSpeedLimit();
		return blockSpeed;
	}

	public int getAuthority()
	{
		blockAuthority = trackPiece.getAuthority();
                return blockAuthority;
	}

	public void setPassengers(int pass)
	{
		occupancy = pass;
	}


}
